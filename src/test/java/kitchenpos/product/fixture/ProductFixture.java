package kitchenpos.product.fixture;

import kitchenpos.product.domain.Product;
import kitchenpos.product.dto.ProductRequest;

import java.util.function.Supplier;

import static java.math.BigDecimal.valueOf;

public class ProductFixture {
    public static final Long 상품_후라이드_치킨_ID = 1L;
    public static final Long 상품_양념_치킨_ID = 2L;
    public static final Long 상품_무료_콜라_서비스_ID = 3L;
    public static final Long 상품_감자튀김_ID = 4L;
    public static final Long 상품_치즈볼_ID = 5L;
    public static final Long 상품_옛날통닭_ID = 6L;
    
    public static final Supplier<Product>  상품_후라이드_치킨 = () -> new Product(상품_후라이드_치킨_ID, "후라이드 치킨", valueOf(18_000));
    public static final Supplier<Product>  상품_양념_치킨 = () -> new Product(상품_양념_치킨_ID, "양념 치킨", valueOf(18_000));
    public static final Supplier<Product>  상품_무료_콜라_서비스 = () -> new Product(상품_무료_콜라_서비스_ID, "무료_콜라_서비스", valueOf(0));
    public static final Supplier<Product>  상품_감자튀김 = () -> new Product(상품_감자튀김_ID, "감자튀김", valueOf(2_000));
    public static final Supplier<Product>  상품_치즈볼 = () -> new Product(상품_치즈볼_ID, "치즈볼", valueOf(4_000));
    public static final Supplier<Product>  상품_옛날통닭 = () -> new Product(상품_옛날통닭_ID, "옛날통닭", valueOf(14_000));

    public static ProductRequest map(Product product) {
        return new ProductRequest(product.getId(), product.getName(), product.getPrice());
    }
}
