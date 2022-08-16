package com.carolruo.projeto.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1l;

    private List<FieldMessage> fieldMessageList = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return fieldMessageList;
    }

    public void addError(String fieldName, String message) {
        fieldMessageList.add(new FieldMessage(fieldName, message));
    }
}
