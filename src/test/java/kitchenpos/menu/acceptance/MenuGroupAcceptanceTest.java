package kitchenpos.menu.acceptance;

import kitchenpos.AcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_등록_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_조회_요청_및_성공_확인;
import static kitchenpos.menu.fixture.MenuGroupFixture.*;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_등록_요청_및_성공_확인;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_조회_요청_및_성공_확인;
import static kitchenpos.product.fixture.ProductFixture.상품_양념_치킨;
import static kitchenpos.product.fixture.ProductFixture.상품_후라이드_치킨;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MenuGroupAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @DisplayName("메뉴 그룹을 관리한다")
    @TestFactory
    Stream<DynamicTest> manageMenuGroup() {
        return Stream.of(
                dynamicTest("메뉴 그룹을 등록한다(치킨류)", 메뉴그룹_등록_요청_및_성공_확인(메뉴그룹_치킨류)),
                dynamicTest("메뉴 그룹을 등록한다(주류)", 메뉴그룹_등록_요청_및_성공_확인(메뉴그룹_주류)),
                dynamicTest("메뉴 그룹을 등록한다(서브메뉴)", 메뉴그룹_등록_요청_및_성공_확인(메뉴그룹_서브메뉴)),
                dynamicTest("메뉴 그룹을 조회한다(치킨류, 주류, 서브메뉴)", 메뉴그룹_조회_요청_및_성공_확인(asList(메뉴그룹_치킨류, 메뉴그룹_주류, 메뉴그룹_서브메뉴)))
        );
    }
}
