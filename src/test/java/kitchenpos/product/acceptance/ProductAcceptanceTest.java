package kitchenpos.product.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import kitchenpos.utils.ResponseTransferObject;
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

    private ResponseTransferObject<ProductResponse> RTO_상품_후라이드_치킨 = new ResponseTransferObject<>();
    private ResponseTransferObject<ProductResponse> RTO_상품_양념_치킨 = new ResponseTransferObject<>();
    private ResponseTransferObject<List<ProductResponse>> RTO_상품_조회_결과 = new ResponseTransferObject<>();

    @DisplayName("상품을 관리한다")
    @Test
    void manageProducts() throws Throwable {
        // when, then
        상품_등록_요청_및_성공_확인(new ProductRequest("후라이드 치킨", valueOf(15_000)), RTO_상품_후라이드_치킨).execute();

        // when, then
        상품_등록_요청_및_성공_확인(new ProductRequest("양념 치킨", valueOf(15_000)), RTO_상품_양념_치킨).execute();

        // given
        List<ProductResponse> 예상_상품_조회_결과 = asList(RTO_상품_후라이드_치킨.get(), RTO_상품_양념_치킨.get());

        // when, then
        상품_조회_요청_및_성공_확인(예상_상품_조회_결과, RTO_상품_조회_결과).execute();
    }

    @DisplayName("상품을 등록한다")
    @Test
    void createProduct() throws Throwable {
        상품_등록_요청_및_성공_확인(new ProductRequest("후라이드 치킨", valueOf(15_000)), RTO_상품_후라이드_치킨).execute();

        상품_등록_요청_및_성공_확인(new ProductRequest("양념 치킨", valueOf(15_000)), RTO_상품_양념_치킨).execute();
    }
}
