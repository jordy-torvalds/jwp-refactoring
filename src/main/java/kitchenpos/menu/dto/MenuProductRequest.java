package kitchenpos.menu.dto;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MenuProductRequest {

    private Long productId;

    private long quantity;

    public MenuProductRequest(Long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }
}
