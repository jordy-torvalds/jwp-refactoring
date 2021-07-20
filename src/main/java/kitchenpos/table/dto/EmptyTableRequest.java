package kitchenpos.table.dto;

public class EmptyTableRequest {
    private boolean empty;

    public EmptyTableRequest() {
    }

    public EmptyTableRequest(boolean empty) {
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }
}
