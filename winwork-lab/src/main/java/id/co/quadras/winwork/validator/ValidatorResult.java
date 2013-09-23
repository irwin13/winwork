package id.co.quadras.winwork.validator;

import java.util.HashMap;
import java.util.Map;

public class ValidatorResult<M> {

    private ValidationStatus validationStatus;
    private M processReturnObject;

    private final Map<String, String> fieldMessages = new HashMap<String, String>();

    public ValidatorResult(M processReturnObject, ValidationStatus validationStatus) {
        this.processReturnObject = processReturnObject;
        this.validationStatus = validationStatus;
    }

    public ValidatorResult() {}

    public static ValidatorResult successResult() {
        ValidatorResult result = new ValidatorResult();
        result.setValidationStatus(ValidationStatus.SUCCESS);
        return result;
    }

    public static ValidatorResult errorResult() {
        ValidatorResult result = new ValidatorResult();
        result.setValidationStatus(ValidationStatus.ERROR);
        return result;
    }

    public Map<String, String> putFieldMessage(String field, String message) {
        fieldMessages.put(field, message);
        return fieldMessages;
    }

    public Map<String, String> removeFieldMessage(String field) {
        fieldMessages.remove(field);
        return fieldMessages;
    }

    public void setValidationStatus(ValidationStatus validationStatus) {
        this.validationStatus = validationStatus;
    }

    public void setProcessReturnObject(M processReturnObject) {
        this.processReturnObject = processReturnObject;
    }

    public ValidationStatus getValidationStatus() {
        return validationStatus;
    }

    public M getProcessReturnObject() {
        return processReturnObject;
    }

    public Map<String, String> getFieldMessages() {
        return fieldMessages;
    }

    public String getFieldMessage(String fieldName) {
        return fieldMessages.get(fieldName);
    }

}
