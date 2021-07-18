package kitchenpos.menu.acceptance;

import kitchenpos.AcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static kitchenpos.menu.acceptance.MenuAcceptanceTestSnippet.메뉴_등록_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuAcceptanceTestSnippet.메뉴_조회_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_등록_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_조회_요청_및_성공_확인;
import static kitchenpos.menu.fixture.MenuFixture.*;
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
    @TestFactory
    Stream<DynamicTest> manageMenu() {
        return Stream.of(
                dynamicTest("상품을 등록한다(후라이드 치킨)", 상품_등록_요청_및_성공_확인(상품_후라이드_치킨)),
                dynamicTest("상품을 등록한다(양념 치킨)", 상품_등록_요청_및_성공_확인(상품_양념_치킨)),
                dynamicTest("메뉴 그룹을 등록한다(치킨류)", 메뉴그룹_등록_요청_및_성공_확인(메뉴그룹_치킨류)),
                dynamicTest("메뉴를 등록한다(후라이드 치킨 한마리)", 메뉴_등록_요청_및_성공_확인(메뉴_후라이드_치킨_한마리)),
                dynamicTest("메뉴를 등록한다(양념 치킨 한마리)", 메뉴_등록_요청_및_성공_확인(메뉴_양념_치킨_한마리)),
                dynamicTest("메뉴를 등록한다(양념 후라이드 두마리 치킨 세트)", 메뉴_등록_요청_및_성공_확인(메뉴_양념_후라이드_두마리_치킨_세트)),
                dynamicTest("메뉴를 조회한다(후라이드 치킨 한마리, 양념 치킨 한마리, 양념 후라이드 두마리 치킨 세트)"
                        , 메뉴_조회_요청_및_성공_확인(asList(메뉴_후라이드_치킨_한마리, 메뉴_양념_치킨_한마리, 메뉴_양념_후라이드_두마리_치킨_세트)))
        );
    }
}
