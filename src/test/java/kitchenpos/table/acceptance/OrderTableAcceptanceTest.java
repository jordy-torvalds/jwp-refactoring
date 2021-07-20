package kitchenpos.table.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.table.dto.EmptyTableRequest;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.OrderTableResponse;
import kitchenpos.table.dto.TableGuestNumberRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
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
        OrderTableResponse 응답_생성된_빈_테이블 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(4  , false));

        // when, then
        OrderTableResponse 응답_생성된_10명의_고객이_있는_주문_테이블 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(10, false));

        // given
        List<OrderTableResponse> 예상_주문테이블_조회_결과 = asList(응답_생성된_빈_테이블, 응답_생성된_10명의_고객이_있는_주문_테이블);

        // when, then
        주문테이블_조회_요청_및_성공_확인(예상_주문테이블_조회_결과);

        // when, then
        주문테이블의_공석_여부_변경_요청_및_성공_확인(응답_생성된_빈_테이블.getId(), new EmptyTableRequest(true));

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

    @DisplayName("주문테이블의 빈 테이블 여부 상태를 변경한다")
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
