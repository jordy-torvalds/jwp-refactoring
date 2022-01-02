package kitchenpos.product.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductRequest {

    private Long id;
    private String name;
    private BigDecimal price;

    public ProductRequest() {
    }

    public ProductRequest(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ProductRequest(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequest productRequest = (ProductRequest) o;
        return Objects.equals(id, productRequest.id) && Objects.equals(name, productRequest.name) && price.compareTo(productRequest.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price.intValue());
    }
}
