package kitchenpos.product.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductResponse {

    private Long id;
    private String name;
    private BigDecimal price;

    public ProductResponse() {
    }

    public ProductResponse(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ProductResponse(Long id, String name, BigDecimal price) {
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
        ProductResponse productResponse = (ProductResponse) o;
        return Objects.equals(id, productResponse.id) && Objects.equals(name, productResponse.name) && price.compareTo(productResponse.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price.intValue());
    }
}
