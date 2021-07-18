package kitchenpos.menu.acceptance;

import kitchenpos.AcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

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

    @DisplayName("상품을 관리한다")
    @TestFactory
    Stream<DynamicTest> manageProduct() {
        return Stream.of(
                dynamicTest("상품을 등록한다(후라이드 치킨)", 상품_등록_요청_및_성공_확인(상품_후라이드_치킨)),
                dynamicTest("상품을 등록한다(양념 치킨)", 상품_등록_요청_및_성공_확인(상품_양념_치킨)),
                dynamicTest("상품을 등록한다(후라이드 치킨, 양념 치킨)", 상품_조회_요청_및_성공_확인(asList(상품_후라이드_치킨, 상품_양념_치킨)))
        );
    }
}
