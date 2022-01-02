package kitchenpos.order.acceptance;

import kitchenpos.AcceptanceTest;
import kitchenpos.menu.dto.*;
import kitchenpos.menu.fixture.MenuGroupFixture;
import kitchenpos.order.dto.OrderLineItemRequests;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.order.dto.OrderResponse;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import kitchenpos.product.fixture.ProductFixture;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.OrderTableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static kitchenpos.menu.acceptance.MenuAcceptanceTestSnippet.메뉴_등록_요청_및_성공_확인;
import static kitchenpos.menu.acceptance.MenuGroupAcceptanceTestSnippet.메뉴그룹_등록_요청_및_성공_확인;
import static kitchenpos.menu.fixture.MenuGroupFixture.map;
import static kitchenpos.menu.fixture.MenuGroupFixture.메뉴그룹_치킨류;
import static kitchenpos.order.acceptance.OrderAcceptanceTestSnippet.*;
import static kitchenpos.order.domain.OrderStatus.*;
import static kitchenpos.product.acceptance.ProductAcceptanceTestSnippet.상품_등록_요청_및_성공_확인;
import static kitchenpos.product.fixture.ProductFixture.상품_양념_치킨;
import static kitchenpos.product.fixture.ProductFixture.상품_후라이드_치킨;
import static kitchenpos.table.acceptance.OrderTableAcceptanceTestSnippet.주문테이블_등록_요청_및_성공_확인;

class OrderAcceptanceTest extends AcceptanceTest {
    private OrderResponse 응답_주문_등록_요청_및_성공_확인;

    @BeforeEach
    public void setUp() {
        super.setUp();

        // given
        ProductResponse 응답_상품_후라이드_치킨 = 상품_등록_요청_및_성공_확인(ProductFixture.map(상품_후라이드_치킨.get()));

        // given
        ProductResponse 응답_상품_양념_치킨 = 상품_등록_요청_및_성공_확인(ProductFixture.map(상품_양념_치킨.get()));

        // given
        MenuGroupResponse 응답_메뉴그룹_치킨류 = 메뉴그룹_등록_요청_및_성공_확인(MenuGroupFixture.map(메뉴그룹_치킨류.get()));

        // given
        MenuProductRequests 요청_메뉴상품_후라이드_양념치킨_두마리_세트 =
                MenuProductRequests.of(asList(응답_상품_후라이드_치킨.getId(), 응답_상품_양념_치킨.getId()),
                        asList(1L, 1L));

        // given
        MenuRequest 요청_메뉴_후라이드_양념치킨_두마리_세트 =
                new MenuRequest("후라이드 양념치킨 두마리 세트", valueOf(36_000), 응답_메뉴그룹_치킨류.getId(), 요청_메뉴상품_후라이드_양념치킨_두마리_세트.get());

        // given
        MenuResponse 응답_메뉴_후라이드_양념치킨_두마리_세트 = 메뉴_등록_요청_및_성공_확인(요청_메뉴_후라이드_양념치킨_두마리_세트);

        // given
        OrderLineItemRequests 요청_주문항목_후라이드_양념치킨_두마리_세트 =
                OrderLineItemRequests.of(singletonList(응답_메뉴_후라이드_양념치킨_두마리_세트.getId()),
                        singletonList(1L));

        // given
        OrderTableResponse 응답_주문_테이블1 = 주문테이블_등록_요청_및_성공_확인(new OrderTableRequest(5, false));

        // given
        OrderRequest 요청_주문_후라이드_양념치킨_두마리_세트 =
                new OrderRequest(응답_주문_테이블1.getId(), null, null, 요청_주문항목_후라이드_양념치킨_두마리_세트.get());

        // when, then
        응답_주문_등록_요청_및_성공_확인 = 주문_등록_요청_및_성공_확인(요청_주문_후라이드_양념치킨_두마리_세트);
    }

    @DisplayName("주문을 관리한다")
    @Test
    void manageOrder() {
        주문_조회_요청_및_성공_확인(new ArrayList<>(singletonList(응답_주문_등록_요청_및_성공_확인)));

        주문의_상태_변경_및_성공_확인(응답_주문_등록_요청_및_성공_확인.getId(), new OrderRequest(MEAL.name()));
        주문의_상태_변경_및_성공_확인(응답_주문_등록_요청_및_성공_확인.getId(), new OrderRequest(COMPLETION.name()));

        완료된_주문의_상태를_변경_및_실패_확인(응답_주문_등록_요청_및_성공_확인.getId(), new OrderRequest(COOKING.name()));
        완료된_주문의_상태를_변경_및_실패_확인(응답_주문_등록_요청_및_성공_확인.getId(), new OrderRequest(MEAL.name()));
    }

    @DisplayName("주문을 조회한다")
    @Test
    void foundOrder() {
        주문_조회_요청_및_성공_확인(new ArrayList<>(singletonList(응답_주문_등록_요청_및_성공_확인)));
    }

    @DisplayName("주문을 상태를 변경한다(COOKING -> MEAL -> (COMPLETION --X--> COOKING / COMPLETION --X--> MEAL)")
    @Test
    void changeOrderStatus() {
        주문의_상태_변경_및_성공_확인(응답_주문_등록_요청_및_성공_확인.getId(), new OrderRequest(MEAL.name()));
        주문의_상태_변경_및_성공_확인(응답_주문_등록_요청_및_성공_확인.getId(), new OrderRequest(COMPLETION.name()));

        완료된_주문의_상태를_변경_및_실패_확인(응답_주문_등록_요청_및_성공_확인.getId(), new OrderRequest(COOKING.name()));
        완료된_주문의_상태를_변경_및_실패_확인(응답_주문_등록_요청_및_성공_확인.getId(), new OrderRequest(MEAL.name()));
    }
}
