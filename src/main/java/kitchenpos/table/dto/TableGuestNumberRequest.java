package kitchenpos.table.dto;

public class TableGuestNumberRequest {
    private int numberOfGuests;

    public TableGuestNumberRequest() {
    }

    public TableGuestNumberRequest(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}
