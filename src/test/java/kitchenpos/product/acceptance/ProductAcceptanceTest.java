package kitchenpos.product.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_등록_요청_및_성공_확인;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_조회_요청_및_성공_확인;

class ProductAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @DisplayName("상품을 관리한다")
    @Test
    void manageProducts() throws Throwable {
        // when, then
        ProductResponse 응답_상품_후라이드_치킨 = 상품_등록_요청_및_성공_확인(new ProductRequest("후라이드 치킨", valueOf(15_000)));

        // when, then
        ProductResponse 응답_상품_양념_치킨 = 상품_등록_요청_및_성공_확인(new ProductRequest("양념 치킨", valueOf(15_000)));

        // given
        List<ProductResponse> 예상_상품_조회_결과 = asList(응답_상품_후라이드_치킨, 응답_상품_양념_치킨);

        // when, then
        상품_조회_요청_및_성공_확인(예상_상품_조회_결과);
    }

    @DisplayName("상품을 등록한다")
    @Test
    void createProduct() throws Throwable {
        상품_등록_요청_및_성공_확인(new ProductRequest("후라이드 치킨", valueOf(15_000)));

        상품_등록_요청_및_성공_확인(new ProductRequest("양념 치킨", valueOf(15_000)));
    }
}
