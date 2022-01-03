package kitchenpos.table.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.menu.dto.*;
import kitchenpos.order.dto.OrderLineItemRequests;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import kitchenpos.table.dto.EmptyTableRequest;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.OrderTableResponse;
import kitchenpos.table.dto.TableGuestNumberRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static kitchenpos.menu.acceptance.MenuAcceptanceTestSnippet.메뉴_등록_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_등록_요청_및_성공_확인;
import static kitchenpos.menu.fixture.MenuGroupFixture.메뉴그룹_치킨류;
import static kitchenpos.order.acceptance.OrderAcceptanceTestSnippet.주문_등록_요청_및_성공_확인;
import static kitchenpos.order.fixture.OrderTableFixture.주문_테이블_조리_중인_주문_테이블;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_등록_요청_및_성공_확인;
import static kitchenpos.table.acceptance.OrderTableAcceptanceTestSnippet.*;

class OrderTableAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @DisplayName("주문 테이블을 관리한다")
    @Test
    void manageOrderTables() {
        // when, then
        OrderTableResponse 응답_생성된_4명_주문_테이블 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(4  , false));

        // when, then
        OrderTableResponse 응답_생성된_10명의_고객이_있는_주문_테이블 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(10, false));

        // given
        List<OrderTableResponse> 예상_주문테이블_조회_결과 = asList(응답_생성된_4명_주문_테이블, 응답_생성된_10명의_고객이_있는_주문_테이블);

        // when, then
        주문테이블_조회_요청_및_성공_확인(예상_주문테이블_조회_결과);

        // when, then
        존재하지_않는_주문테이블을_빈_테이블로_변경_요청_및_실패_확인(Long.MAX_VALUE);

        // when, then
//        주문_등록_요청_및_성공_확인(new OrderRequest(응답_생성된_4명_주문_테이블.getId(), null, null, 요청_주문항목_후라이드_양념치킨_두마리_세트.get()));

        // when, then
//        조리중이거나_식사_중인_주문이_있는_테이블을_빈_테이블로_변경_요청_및_실패_확인(주문_테이블_조리_중인_주문_테이블);

        // when, then
        주문테이블의_공석_여부_변경_요청_및_성공_확인(응답_생성된_4명_주문_테이블.getId(), new EmptyTableRequest(true));

        // when, then
        주문테이블의_인원수_변경_요청_및_성공_확인(응답_생성된_10명의_고객이_있는_주문_테이블.getId(), new TableGuestNumberRequest(5));
    }

    @DisplayName("주문 테이블을 등록한다")
    @Test
    void createOrderTables() {
        // when, then
        OrderTableResponse 응답_주문_테이블1 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(5, true));

        // when, then
        OrderTableResponse 응답_주문_테이블2 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(10, true));
    }

    @DisplayName("주문테이블의 공석 여부 상태를 변경한다")
    @Test
    void changeEmptyTable() {
        // given
        OrderTableResponse 응답_생성된_빈_테이블 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(4, false));

        // given
        OrderTableResponse 응답_생성된_10명의_고객이_있는_주문_테이블 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(10, false));

        // when, then
        주문테이블의_공석_여부_변경_요청_및_성공_확인(응답_생성된_빈_테이블.getId(), new EmptyTableRequest(true));

        // when, then
        주문테이블의_공석_여부_변경_요청_및_성공_확인(응답_생성된_10명의_고객이_있는_주문_테이블.getId(), new EmptyTableRequest(true));
    }

    @DisplayName("존재하지 않는 주문 테이블의 공석 여부를 변경하려하면 실패한다.")
    @Test
    void changesToNonExistentTablesFail() {
        // when, then
        존재하지_않는_주문테이블을_빈_테이블로_변경_요청_및_실패_확인(Long.MAX_VALUE);
    }

    @DisplayName("주문테이블의 인원 수를 변경한다")
    @Test
    void changeNumberOfGuests() {
        // given
        OrderTableResponse 응답_생성된_빈_테이블 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(4, false));

        // given
        OrderTableResponse 응답_생성된_10명의_고객이_있는_주문_테이블 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(10, false));

        // when, then
        주문테이블의_인원수_변경_요청_및_성공_확인(응답_생성된_빈_테이블.getId(), new TableGuestNumberRequest(3));

        // when, then
        주문테이블의_인원수_변경_요청_및_성공_확인(응답_생성된_10명의_고객이_있는_주문_테이블.getId(), new TableGuestNumberRequest(5));
    }
}
