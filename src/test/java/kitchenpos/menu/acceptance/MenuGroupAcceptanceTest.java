package kitchenpos.menu.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.dto.MenuGroupRequest;
import kitchenpos.menu.dto.MenuGroupResponse;
import kitchenpos.product.domain.Product;
import kitchenpos.utils.ResponseTransferObject;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Stream;

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

    private ResponseTransferObject<MenuGroupResponse> RTO_메뉴그룹_치킨류 = new ResponseTransferObject<>();
    private ResponseTransferObject<MenuGroupResponse> RTO_메뉴그룹_주류 = new ResponseTransferObject<>();
    private ResponseTransferObject<MenuGroupResponse> RTO_메뉴그룹_서브메뉴 = new ResponseTransferObject<>();
    private ResponseTransferObject<List<MenuGroupResponse>> RTO_메뉴그룹_조회_결과 = new ResponseTransferObject<>();

    @DisplayName("메뉴 그룹을 관리한다")
    @Test
    void manageMenuGroups() throws Throwable {
        // when, then
        메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_치킨류.getName()), RTO_메뉴그룹_치킨류).execute();

        // when, then
        메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_주류.getName()), RTO_메뉴그룹_주류).execute();

        // when, then
        메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_서브메뉴.getName()), RTO_메뉴그룹_서브메뉴).execute();

        // given
        List<MenuGroupResponse> 예상_메뉴그룹_조회_결과 = asList(RTO_메뉴그룹_치킨류.get(), RTO_메뉴그룹_주류.get(), RTO_메뉴그룹_서브메뉴.get());

        // when, then
        메뉴그룹_조회_요청_및_성공_확인(예상_메뉴그룹_조회_결과).execute();
    }
    @DisplayName("메뉴그룹을 등록한다")
    @Test
    void createMenuGroups() throws Throwable {
        // when, then
        메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_치킨류.getName()), RTO_메뉴그룹_치킨류).execute();

        // when, then
        메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_주류.getName()), RTO_메뉴그룹_주류).execute();

        // when, then
        메뉴그룹_등록_요청_및_성공_확인(new MenuGroupRequest(메뉴그룹_서브메뉴.getName()), RTO_메뉴그룹_서브메뉴).execute();
    }
}
