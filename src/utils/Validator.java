package utils;

import java.util.HashMap;

public class Validator {
    public enum AttributeName {
        TYPE,
        USE,
        REF
    }

    public enum ValueType {
        NUMBER,
        LETTERS,
        EMAIL,
        PHONE,
        DATE,
        TIME,
        DEFAULT
    }

    public enum UseType {
        REQUIRED,
        DEFAULT
    }

    public boolean validateAttribute(String attributeName, String attributeValue) {
        validateAttributeName(attributeName);

        AttributeName attribute = AttributeName.valueOf(attributeName);

        switch (attribute) {
            case TYPE:
                return validateValueType(attributeValue);
            case USE:
                return validateUseType(attributeValue);
            case REF:
                return validateRef(attributeValue);
        }
        return false;
    }

    public boolean isRequired(String type) {
        try {
            UseType useType = UseType.valueOf(type);

            switch (useType) {
                case REQUIRED:
                    return true;
                default:
                    return false;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("INVALID USE TYPE: " + type);
        }
    }

    public boolean isHost(String host) {
        return host.matches(
                "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$")
                || host.matches(
                        "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}");
    }

    public boolean isPort(String port) {
        return port.matches("^([1-9][0-9]{0,3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$");
    }

    public boolean validateValue(String type, String value) {
        if (value.isBlank()) {
            return true;
        }

        try {
            ValueType valueType = ValueType.valueOf(type);

            switch (valueType) {
                case NUMBER:
                    return isValidNumber(value);
                case LETTERS:
                    return isValidLetters(value);
                case EMAIL:
                    return isValidEmail(value);
                case PHONE:
                    return isValidPhone(value);
                case DATE:
                    return isValidDate(value);
                case TIME:
                    return isValidTime(value);
                default:
                    return true;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("INVALID VALUE: " + value);
        }
    }

    public boolean validateRefArray(HashMap<String, Integer> headers, String[] rowData) {
        if (!CurrentValues.Attributes.get("REF").equals("[]")) {
            String[] listRef = CurrentValues.Attributes.get("REF").split(";", -1);
            for (int i = 0; i < listRef.length-1; i++) {
                String key = "";
                String value = "";

                String[] ref = listRef[i].split("=", -1);
                key = ref[0];

                if (!headers.containsKey(key)) {
                    throw new IllegalArgumentException("INVALID REFERENCE: " + key);
                }

                if (ref.length > 1) {
                    value = ref[1];
                    String data = rowData[headers.get(key)] == null || rowData[headers.get(key)].isBlank() ? "" : rowData[headers.get(key)];
                    if (data.equals(value)) {
                        return true;
                    }
                    return false;
                }

                if (rowData[headers.get(key)] == null || rowData[headers.get(key)].isBlank()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateAttributeName(String attributeName) {
        try {
            AttributeName.valueOf(attributeName);

            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("INVALID ATTRIBUTE NAME: " + attributeName);
        }
    }

    private boolean validateValueType(String type) {
        try {
            ValueType.valueOf(type);

            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("INVALID VALUE TYPE: " + type);
        }
    }

    private boolean validateUseType(String type) {
        try {
            UseType.valueOf(type);

            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("INVALID USE TYPE: " + type);
        }
    }

    private boolean validateRef(String ref) {
        if (ref.matches("^(\\w+\\={0,1}\\w*\\;)+$")) {
            return true;
        }

        throw new IllegalArgumentException("INVALID REFERENCE FORMAT: " + ref);
    }

    private boolean isValidNumber(String value) {
        return value.matches("^[0-9]+$");
    }

    private boolean isValidLetters(String value) {
        return value.matches("^[a-zA-Z]+( [a-zA-Z]+)*$");
    }

    private boolean isValidEmail(String value) {
        return value.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    private boolean isValidPhone(String value) {
        return value.matches("^0[0-9]{9,10}$");
    }

    private boolean isValidDate(String value) {
        return value.matches("^19[0-9]{2}|2[0-9]{3}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
    }

    private boolean isValidTime(String value) {
        return value.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
    }

}
