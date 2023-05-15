package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.univocity.parsers.common.processor.BatchedColumnProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class FileReader implements IFileReader {
    
    @Override
    public Map<String, Object> readFile(File sourceFile) throws UnsupportedEncodingException, FileNotFoundException {
        Reader inputReader = new InputStreamReader(new FileInputStream(sourceFile), "UTF-8");
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setProcessor(new BatchedColumnProcessor(1000) {
            @Override
            public void batchProcessed(int rowsInThisBatch) {
            }
        });

        CsvParser parser = new CsvParser(settings);

        List<String[]> rows = parser.parseAll(inputReader);

        String[] parsedHeaders = parser.getRecordMetadata().headers();
        HashMap<String, Integer> headers = new HashMap<>();
        int index = 0;
        for (String header : parsedHeaders) {
            headers.put(header, index++);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("headers", headers);
        result.put("rows", rows);

        return result;
    }

}