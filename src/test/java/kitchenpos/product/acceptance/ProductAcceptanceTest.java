package kitchenpos.product.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.product.domain.Product;
import kitchenpos.utils.ResponseTransferObject;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_등록_요청_및_성공_확인;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_조회_요청_및_성공_확인;
import static kitchenpos.product.fixture.ProductFixture.상품_양념_치킨;
import static kitchenpos.product.fixture.ProductFixture.상품_후라이드_치킨;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class ProductAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    private ResponseTransferObject<Product> RTO_상품_후라이드_치킨 = new ResponseTransferObject<>();
    private ResponseTransferObject<Product> RTO_상품_양념_치킨 = new ResponseTransferObject<>();
    private ResponseTransferObject<List<Product>> RTO_상품_조회_결과 = new ResponseTransferObject<>();

    @DisplayName("상품을 관리한다")
    @Test
    void manageProducts() throws Throwable {
        // when, then
        상품_등록_요청_및_성공_확인(상품_후라이드_치킨, RTO_상품_후라이드_치킨).execute();

        // when, then
        상품_등록_요청_및_성공_확인(상품_양념_치킨, RTO_상품_양념_치킨).execute();

        // given
        List<Product> 예상_상품_조회_결과 = asList(RTO_상품_후라이드_치킨.get(), RTO_상품_양념_치킨.get());

        // when, then
        상품_조회_요청_및_성공_확인(예상_상품_조회_결과, RTO_상품_조회_결과).execute();
    }

    @DisplayName("상품을 등록한다")
    @Test
    void createProduct() throws Throwable {
        상품_등록_요청_및_성공_확인(상품_후라이드_치킨, RTO_상품_후라이드_치킨).execute();

        상품_등록_요청_및_성공_확인(상품_양념_치킨, RTO_상품_양념_치킨).execute();
    }
}
