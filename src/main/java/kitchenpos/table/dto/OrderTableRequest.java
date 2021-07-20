package kitchenpos.table.dto;

public class OrderTableRequest {
    private Long id;
    private Long tableGroupId;
    private int numberOfGuests;
    private boolean empty;

    public OrderTableRequest() {
    }

    public OrderTableRequest(int numberOfGuests, boolean empty) {
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public OrderTableRequest(Long id, Long tableGroupId, int numberOfGuests, boolean empty) {
        this.id = id;
        this.tableGroupId = tableGroupId;
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public Long getId() {
        return id;
    }

    public Long getTableGroupId() {
        return tableGroupId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }

    public static OrderTableRequest createChangingEmptyRequest(boolean empty) {
        OrderTableRequest orderTableRequest = new OrderTableRequest();
        orderTableRequest.empty = empty;
        return orderTableRequest;
    }

    public static OrderTableRequest createChangingGuestNumberRequest(int numberOfGuests) {
        OrderTableRequest orderTableRequest = new OrderTableRequest();
        orderTableRequest.numberOfGuests = numberOfGuests;
        return orderTableRequest;
    }
}
