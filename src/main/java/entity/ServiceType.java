package entity;

public enum ServiceType {
    INTERNET("Internet"),
    PHONE_CALL("Phone call"),
    SMS("SMS");

    private final String name;

    private ServiceType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
