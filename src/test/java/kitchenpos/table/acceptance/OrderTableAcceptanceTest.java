package kitchenpos.table.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.OrderTableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_등록_요청_및_성공_확인;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_조회_요청_및_성공_확인;
import static kitchenpos.table.acceptance.OrderTableAcceptanceTestSnippet.주문테이블_등록_요청_및_성공_확인;
import static kitchenpos.table.acceptance.OrderTableAcceptanceTestSnippet.주문테이블_조회_요청_및_성공_확인;

class OrderTableAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @DisplayName("주문테이블을 관리한다")
    @Test
    void manageOrderTables() {
        // when, then
        OrderTableResponse 응답_주문_테이블1 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(5, true));

        // when, then
        OrderTableResponse 응답_주문_테이블2 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(10, true));

        // given
        List<OrderTableResponse> 예상_주문테이블_조회_결과 = asList(응답_주문_테이블1, 응답_주문_테이블2);

        // when, then
        주문테이블_조회_요청_및_성공_확인(예상_주문테이블_조회_결과);
    }

    @DisplayName("주문테이블을 등록한다")
    @Test
    void createOrderTables() {
        // when, then
        OrderTableResponse 응답_주문_테이블1 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(5, true));

        // when, then
        OrderTableResponse 응답_주문_테이블2 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(10, true));
    }
}
