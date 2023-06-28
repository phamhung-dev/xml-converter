package services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JProgressBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.ini4j.InvalidFileFormatException;
import services.Logger.LogType;
import common.Config;
import common.CurrentValues;
import common.Data;
import common.Generator;
import common.SqlDataReader;
import common.Validator;
import common.Data.Type;
import common.Validator.FormatType;

public class Converter {

    private static FileReader fileReader = new FileReader();
    private static Validator validator = new Validator();
    private static Generator generator = new Generator();

    private String convertSingleFileToXml(String inputFilePath, String structName) throws IOException {
        try {
            String typeFile = Config.getStruct(structName, "TYPE_FILE");
            String header = Config.getStruct(structName, "HEADER");
            String typeList = Config.getStruct(structName, "TYPE_LIST");
            String object = Config.getStruct(structName, "OBJECT");

            CurrentValues.SourceFile = new File(inputFilePath);

            Map<String, Object> inputFile = fileReader.readFile(CurrentValues.SourceFile);

            HashMap<String, Integer> headersInputFile = (HashMap<String, Integer>) inputFile.get("headers");
            List<String[]> rows = (List<String[]>) inputFile.get("rows");

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            writeXml(typeFile, header, typeList, object, headersInputFile, rows, out);

            StringBuilder xml = new StringBuilder(new String(out.toByteArray(), StandardCharsets.UTF_8));

            StringBuilder xmlCleaned = removeEmptyTags(xml);

            StringBuilder prettyPrintXml = formatXml(xmlCleaned);

            String outputFileName = generator.generateTargetFileName(structName);
            String outputFilePath = Config.getFolder("OUTPUT") + "/" + outputFileName;
            File outputFile = new File(outputFilePath) {{
                if (!exists()) {
                    getParentFile().mkdirs();
                    createNewFile();
                }
            }};

            Files.writeString(outputFile.toPath(), prettyPrintXml, StandardCharsets.UTF_8);

            String fprocessPath = Config.getFolder("PROCESS") + "/" + CurrentValues.SourceFile.getName();
            File fprocess = new File(fprocessPath) {{
                if (!exists()) {
                    getParentFile().mkdirs();
                    createNewFile();
                }
            }};

            Files.move(CurrentValues.SourceFile.toPath(), fprocess.toPath(), StandardCopyOption.REPLACE_EXISTING);

            return outputFile.getAbsolutePath();
        } catch (Exception e) {
            String message = e.getMessage() + "::" + CurrentValues.SourceFile.getName();
            Logger.writeLogError(message, LogType.SEVERE);
            
            String ferrorPath = Config.getFolder("ERROR") + "/" + CurrentValues.SourceFile.getName();
            File ferror = new File(ferrorPath) {{
                if (!exists()) {
                    getParentFile().mkdirs();
                    createNewFile();
                }
            }};

            Files.move(CurrentValues.SourceFile.toPath(), ferror.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            return "";
        } finally {
            CurrentValues.SourceFile = null;
            CurrentValues.setDefaultAttributes();
        }
    }

    public List<String> convertMultipleFilesToXml(List<String> inputFilePaths, String structName, JProgressBar progressBar) throws IOException {
        List<String> outputFilePaths = new ArrayList<String>();

        Logger.cleanLogError();

        int totalFiles = inputFilePaths.size();

        for (String inputFilePath : inputFilePaths) {
            String outputFilePath = convertSingleFileToXml(inputFilePath, structName);
            outputFilePaths.add(outputFilePath);
            progressBar.setValue(progressBar.getValue() + (90 / totalFiles));
        }

        return outputFilePaths;
    }

    private Map<String, String> convertJTreeToXml(JTree tree, String templateName, String templateFolderPath, JProgressBar progressBar) throws XMLStreamException, IOException, TransformerException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(out);

        writer.writeStartDocument("utf-8", "1.0");

        progressBar.setValue(10);

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        traverseTree(writer, root);

        progressBar.setValue(50);

        writer.writeEndDocument();

        writer.flush();
        writer.close();

        StringBuilder xml = new StringBuilder(new String(out.toByteArray(), StandardCharsets.UTF_8));

        progressBar.setValue(55);

        StringBuilder prettyPrintXml = formatXml(xml);

        progressBar.setValue(60);

        templateName = templateName.toLowerCase();

        String outputFilePath = templateFolderPath + "/" + templateName + ".xml";
        File outputFile = new File(outputFilePath) {{
            if (!exists()) {
                getParentFile().mkdirs();
                createNewFile();
            }
        }};

        Files.writeString(outputFile.toPath(), prettyPrintXml, StandardCharsets.UTF_8);

        progressBar.setValue(95);
        
        Map<String, String> result = new HashMap<String, String>();
        
        result.put("templateName", templateName);
        result.put("outputFilePath", outputFilePath);

        progressBar.setValue(100);

        return result;
    }

    public Map<String, String> convertJTreeToHeader(JTree tree, String headerName, JProgressBar progressBar) throws XMLStreamException, IOException, TransformerException {
        String headerFolderPath = "config/header";
        Map<String, String> result = convertJTreeToXml(tree, headerName, headerFolderPath, progressBar);
        Config.setHeader(result.get("templateName"), new HashMap<String, String>() {{
            put("PATH", result.get("outputFilePath"));
        }});
        return result;
    }

    public Map<String, String> convertJTreeToObject(JTree tree, String objectName, JProgressBar progressBar) throws XMLStreamException, IOException, TransformerException {
        String objectFolderPath = "config/object";
        Map<String, String> result = convertJTreeToXml(tree, objectName, objectFolderPath, progressBar);
        Config.setObject(result.get("templateName"), new HashMap<String, String>() {{
            put("PATH", result.get("outputFilePath"));
        }});
        return result;
    }

    private DefaultTreeModel convertXmlToJTree(XMLEventReader template) throws XMLStreamException {
        Stack<DefaultMutableTreeNode> stack = new Stack<DefaultMutableTreeNode>();
        while (template.hasNext()) {
            XMLEvent event = template.nextEvent();

            if (event.isStartElement()) {
                StartElement element = event.asStartElement();
                DefaultMutableTreeNode node = new DefaultMutableTreeNode("tag (" + element.getName().getLocalPart() + ")");
                stack.push(node);
                Iterator<Attribute> attributes = element.getAttributes();
                if (attributes.hasNext()) {
                    String attr = "attributes (";
                    while (attributes.hasNext()) {
                        Attribute attribute = attributes.next();
                        attr += attribute.getName().getLocalPart() + "='" + attribute.getValue() + "', ";
                    }
                    attr = attr.substring(0, attr.length() - 2) + ")";
                    DefaultMutableTreeNode attrNode = new DefaultMutableTreeNode(attr);
                    node.add(attrNode);
                }
                continue;
            }

            if (event.isCharacters()) {
                if (!event.asCharacters().isWhiteSpace()) {
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode("value (" + event.asCharacters().getData() + ")");
                    stack.peek().add(node);
                }
                continue;
            }

            if (event.isEndElement()) {
                DefaultMutableTreeNode root = stack.pop();
                if (stack.isEmpty()) {
                    template.close();
                    return new DefaultTreeModel(root);
                } else {
                    stack.peek().add(root);
                }
            }
        }
        template.close();
        throw new XMLStreamException("Document ended before root element closed");
    }

    public DefaultTreeModel convertHeaderToJTree(String headerName) throws XMLStreamException, IOException {       
        XMLEventReader template = Config.getHeaderTemplate(headerName);
        return convertXmlToJTree(template);
    }

    public DefaultTreeModel convertObjectToJTree(String objectName) throws XMLStreamException, IOException {       
        XMLEventReader template = Config.getObjectTemplate(objectName);
        return convertXmlToJTree(template);
    }

    private static void traverseTree(XMLStreamWriter writer, DefaultMutableTreeNode node) throws XMLStreamException {
        String nodeText = node.getUserObject().toString();
        
        if (nodeText.matches("^tag \\(\\w+\\)$")) {
            Pattern pattern = Pattern.compile("^tag \\((.+?)\\)$");
            Matcher matcher = pattern.matcher(nodeText);
            if (matcher.find()) {
                writer.writeStartElement(matcher.group(1));
            }
        } else if (nodeText.matches("^attributes \\(.+\\)$")) {
            Pattern pattern = Pattern.compile("^attributes \\((.+?)\\)$");
            Matcher matcher = pattern.matcher(nodeText);
            if (matcher.find()) {
                String[] attributes = matcher.group(1).split(",");
                for (String attribute : attributes) {
                    String[] attributeParts = attribute.trim().split("=", -1);
                    writer.writeAttribute(attributeParts[0], attributeParts[1].substring(1, attributeParts[1].length()-1));
                }
            }
        } else if (nodeText.matches("^value \\(.+\\)$")) {
            Pattern pattern = Pattern.compile("^value \\((.+?)\\)$");
            Matcher matcher = pattern.matcher(nodeText);
            if (matcher.find()) {
                writer.writeCharacters(matcher.group(1));
            }
        }

        int childCount = node.getChildCount();
        for (int i = 0; i < childCount; i++) {
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
            traverseTree(writer, childNode);
        }
        
        if (nodeText.matches("^tag \\(\\w+\\)$")) {
            writer.writeEndElement();
        }
    }

    private void writeXml(String typeFile, String header, String typeList, String object, HashMap<String, Integer> headersInputFile, List<String[]> rows,
            OutputStream outputStream) throws XMLStreamException, IOException, SQLException {
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(outputStream);

        writer.writeStartDocument("utf-8", "1.0");

        writer.writeStartElement(typeFile);

        XMLEventReader headerTemplate = Config.getHeaderTemplate(header);

        writeDataHeader(writer, headerTemplate, new HashMap<String, Integer>(), new String[] {});

        writer.writeStartElement(typeList);

        XMLEventReader objectTemplate = Config.getObjectTemplate(object);

        for (String[] row : rows) {
            writeDataObject(writer, objectTemplate, headersInputFile, row);

            objectTemplate = Config.getObjectTemplate(object);
        }

        writer.writeEndElement();

        writer.writeEndElement();

        writer.writeEndDocument();

        writer.flush();
        writer.close();

        objectTemplate.close();
    }

    private String getData(HashMap<String, Integer> headersInputFile, String[] rowData, String s) throws IOException, SQLException {
        List<String> listExpressionLanguage = Data.getListExpressionLanguage(s);

        String dataNameExtracted = "";
        String data = "";

        for (String expressionLanguage : listExpressionLanguage) {
            switch (Data.determineType(expressionLanguage)) {
                case FROM_FILE:
                    dataNameExtracted = Data.extractDataName(expressionLanguage, Type.FROM_FILE);
                    Integer col = headersInputFile.get(dataNameExtracted);
                    if (col == null) {
                        throw new InvalidFileFormatException("Invalid data name: " + dataNameExtracted);
                    } else {
                        data = rowData[col] == null ? "" : rowData[col];
                    }
                    break;
                case FROM_DB:
                    dataNameExtracted = Data.extractDataName(expressionLanguage, Type.FROM_DB);
                    data = SqlDataReader.getData(dataNameExtracted);
                    break;
                case FROM_GENERATOR:
                    dataNameExtracted = Data.extractDataName(expressionLanguage, Type.FROM_GENERATOR);
                    data = generator.generateData(dataNameExtracted);
                    break;
                case FROM_DEFAULT_VALUES:
                    dataNameExtracted = Data.extractDataName(expressionLanguage, Type.FROM_DEFAULT_VALUES);
                    data = Config.getValue(dataNameExtracted);
                    if (data == null) {
                        throw new IllegalArgumentException("Invalid default value type: " + dataNameExtracted);
                    }
                    break;
                default:
                    data = "";
            }

            if (validator.isRequired(CurrentValues.Attributes.get("USE")) && data.isBlank()) {
                throw new InvalidFileFormatException("Required data is empty: " + dataNameExtracted);
            }

            s = s.replace(expressionLanguage, data);
        }

        s = Data.format(s, FormatType.valueOf(CurrentValues.Attributes.get("FORMAT").toUpperCase()));

        return s;
    }

    private void writeData(XMLStreamWriter writer, XMLEventReader template, HashMap<String, Integer> headersInputFile,
            String[] rowData, boolean allowRefAttribute) throws XMLStreamException, IOException, SQLException {
        while (template.hasNext()) {
            XMLEvent event = template.nextEvent();

            if (event.isStartElement()) {
                StartElement element = event.asStartElement();

                Iterator<Attribute> attributes = element.getAttributes();
                while (attributes.hasNext()) {
                    Attribute attribute = attributes.next();
                    String attributeName = attribute.getName().getLocalPart().toUpperCase();
                    String attributeValue = attribute.getValue().toUpperCase();
                    if (validator.validateAttribute(attributeName, attributeValue, allowRefAttribute)) {
                        CurrentValues.Attributes.put(attributeName, attributeValue);
                    }
                }

                writer.writeStartElement(element.getName().getLocalPart());

                if (!validator.validateRefArray(headersInputFile, rowData)) {
                    skipChildTags(template);
                }

                continue;
            }

            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (!characters.isWhiteSpace()) {
                    String data = getData(headersInputFile, rowData, characters.getData());

                    if (validator.validateValue(CurrentValues.Attributes.get("TYPE"), data)) {
                        writer.writeCharacters(data);
                    } else {
                        throw new InvalidFileFormatException("Data is not " + CurrentValues.Attributes.get("TYPE") + " type: " + data);
                    }
                }

                CurrentValues.setDefaultAttributes();
                
                continue;
            }

            if (event.isEndElement()) {
                writer.writeEndElement();

                continue;
            }
        }
    }

    private void writeDataHeader(XMLStreamWriter writer, XMLEventReader template, HashMap<String, Integer> headersInputFile,
            String[] rowData) throws XMLStreamException, IOException, SQLException {
        writeData(writer, template, headersInputFile, rowData, false);
    }

    private void writeDataObject(XMLStreamWriter writer, XMLEventReader template, HashMap<String, Integer> headersInputFile,
            String[] rowData) throws XMLStreamException, IOException, SQLException {
        writeData(writer, template, headersInputFile, rowData, true);
    }

    private void skipChildTags(XMLEventReader template) throws XMLStreamException {
        int count = 0;
        while (template.hasNext()) {
            XMLEvent event = template.nextEvent();

            if (event.isStartElement()) {
                count++;
            }

            if (event.isEndElement()) {
                count--;
            }

            if (count == 0) {
                break;
            }
        }
    }

    private StringBuilder removeEmptyTags(StringBuilder xml){
        String regex = "<\\w+></\\w+>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(xml);
        while (matcher.find()) {
            xml = new StringBuilder(xml.toString().replaceAll(regex, ""));
            matcher = pattern.matcher(xml);
        }
        return xml;
    }

    private StringBuilder formatXml(StringBuilder xml) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        StreamSource source = new StreamSource(new StringReader(xml.toString()));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));
        return new StringBuilder(output.toString());
    }

}