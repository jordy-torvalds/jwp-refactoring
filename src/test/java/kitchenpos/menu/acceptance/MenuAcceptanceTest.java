package kitchenpos.menu.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.menu.dto.*;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import org.junit.jupiter.api.*;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static kitchenpos.menu.acceptance.MenuAcceptanceTestSnippet.메뉴_등록_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuAcceptanceTestSnippet.메뉴_조회_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_등록_요청_및_성공_확인;
import static kitchenpos.menu.fixture.MenuGroupFixture.*;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_등록_요청_및_성공_확인;
import static kitchenpos.product.fixture.ProductFixture.상품_양념_치킨;
import static kitchenpos.product.fixture.ProductFixture.상품_후라이드_치킨;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MenuAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @DisplayName("메뉴를 관리한다")
    @Test
    void manageMenu() throws Throwable {
        // given
        ProductResponse 응답_상품_후라이드_치킨 = 상품_등록_요청_및_성공_확인(new ProductRequest(상품_후라이드_치킨.getName(), 상품_후라이드_치킨.getPrice())).get();

        // given
        ProductResponse 응답_상품_양념_치킨 = 상품_등록_요청_및_성공_확인(new ProductRequest(상품_양념_치킨.getName(), 상품_양념_치킨.getPrice())).get();

        // given
        MenuGroupResponse 응답_메뉴그룹_치킨류 = 메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_치킨류.getName())).get();

        // given
        MenuProductRequests 메뉴상품_후라이드_양념치킨_두마리_세트 =
                MenuProductRequests.of(asList(응답_상품_후라이드_치킨.getId(), 응답_상품_양념_치킨.getId()),
                asList(1L, 1L));

        // given
        MenuProductRequests 메뉴상품_후라이드_치킨_두마리_세트 =
                MenuProductRequests.of(asList(응답_상품_후라이드_치킨.getId()),
                        asList(2L));

        // given
        MenuRequest 요청_메뉴_후라이드_양념치킨_두마리_세트 =
                new MenuRequest("후라이드 양념치킨 두마리 세트", valueOf(36_000), 응답_메뉴그룹_치킨류.getId(), 메뉴상품_후라이드_양념치킨_두마리_세트.get());
        // given
        MenuRequest 요청_메뉴_후라이드_치킨_두마리_세트 =
                new MenuRequest("후라이드 두마리 세트", valueOf(36_000), 응답_메뉴그룹_치킨류.getId(), 메뉴상품_후라이드_치킨_두마리_세트.get());

        // when, then
        MenuResponse 응답_메뉴_후라이드_양념치킨_두마리_세트 = 메뉴_등록_요청_및_성공_확인(요청_메뉴_후라이드_양념치킨_두마리_세트).get();

        // when, then
        MenuResponse 응답_메뉴_후라이드_두마리_세트 = 메뉴_등록_요청_및_성공_확인(요청_메뉴_후라이드_치킨_두마리_세트).get();

        // given
        List<MenuResponse> 예상_메뉴_조회_결과 = asList(응답_메뉴_후라이드_양념치킨_두마리_세트, 응답_메뉴_후라이드_두마리_세트);

        // when, then
        메뉴_조회_요청_및_성공_확인(예상_메뉴_조회_결과).get();
    }
}
