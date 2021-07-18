package kitchenpos.menu.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.menu.dto.MenuGroupRequest;
import kitchenpos.menu.dto.MenuGroupResponse;
import org.junit.jupiter.api.*;

import java.util.List;

import static java.util.Arrays.asList;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_등록_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_조회_요청_및_성공_확인;
import static kitchenpos.menu.fixture.MenuGroupFixture.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MenuGroupAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @DisplayName("메뉴 그룹을 관리한다")
    @Test
    void manageMenuGroups() throws Throwable {
        // when, then
        MenuGroupResponse 응답_메뉴그룹_치킨류 = 메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_치킨류.getName()));

        // when, then
        MenuGroupResponse 응답_메뉴그룹_주류 = 메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_주류.getName()));

        // when, then
        MenuGroupResponse 응답_메뉴그룹_서브메뉴 = 메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_서브메뉴.getName()));

        // given
        List<MenuGroupResponse> 예상_메뉴그룹_조회_결과 = asList(응답_메뉴그룹_치킨류, 응답_메뉴그룹_주류, 응답_메뉴그룹_서브메뉴);

        // when, then
        메뉴그룹_조회_요청_및_성공_확인(예상_메뉴그룹_조회_결과);
    }
    @DisplayName("메뉴그룹을 등록한다")
    @Test
    void createMenuGroups() throws Throwable {
        // when, then
        MenuGroupResponse 응답_메뉴그룹_치킨류 = 메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_치킨류.getName()));

        // when, then
        MenuGroupResponse 응답_메뉴그룹_주류 = 메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_주류.getName()));

        // when, then
        MenuGroupResponse 응답_메뉴그룹_서브메뉴 = 메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_서브메뉴.getName()));
    }
}
