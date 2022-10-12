package cz.mateusz.flashcardy.web.data;

public class ComplexOperationStatus {

    private OperationStatus operationStatus;

    public ComplexOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public static ComplexOperationStatus successful() {
        return new ComplexOperationStatus(OperationStatus.SUCCESS);
    }

    public static ComplexOperationStatus failure() {
        return new ComplexOperationStatus(OperationStatus.FAILURE);
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }
}
