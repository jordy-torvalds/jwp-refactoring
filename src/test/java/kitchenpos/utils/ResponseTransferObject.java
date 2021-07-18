package kitchenpos.utils;

import java.util.function.Supplier;

public class ResponseTransferObject <T> {
    private T value;

    public ResponseTransferObject() {

    }
    public ResponseTransferObject(T argument) {
        this.value = argument;
    }

    public void change(T argument) {
        this.value = argument;
    }

    public T get() {
        return value;
    }
}
