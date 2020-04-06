package com.thoughtworks.InputException;

public class InputException extends RuntimeException {
    private String fieldName;

    public InputException(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getMessage() {
        return String.format("%s不合法，请重新输入", getFieldName());
    }
}
