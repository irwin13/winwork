package id.co.quadras.winwork.validator;


public enum ValidationStatus {
    SUCCESS("SUCCESS"), ERROR("ERROR");

    private final String status;

    private ValidationStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}