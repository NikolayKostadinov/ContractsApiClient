package bg.bms.contractsapiclient.models;

public enum Status {
    CONFIRMED(10), REJECTED(12), CANCELLED(99);
    private int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
