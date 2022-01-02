package kitchenpos.order.fixture;

import kitchenpos.order.domain.OrderLineItem;

import java.util.function.BiFunction;

import static kitchenpos.menu.fixture.MenuFixture.메뉴_감자튀김;
import static kitchenpos.menu.fixture.MenuFixture.메뉴_감자튀김_ID;

public class OrderLineItemFixture {
    private static Long index = 1L;

    public static OrderLineItem 주문_항목_주문_1번_감자튀김 =
            new OrderLineItem(1L, 1L, 메뉴_감자튀김_ID, 1);
    public static OrderLineItem 주문_항목_주문_2번_후라이드_치킨 =
            new OrderLineItem(2L, 1L, 메뉴_감자튀김_ID, 1);

    public static BiFunction<Long, Long, OrderLineItem> 주문_항목_감자튀김_생성
            = (orderId, quantity)  -> new OrderLineItem(index++, orderId, 메뉴_감자튀김_ID, quantity);

    public static BiFunction<Long, Long, OrderLineItem> 주문_항목_후라이드_치킨_생성
            = (orderId, quantity)  -> new OrderLineItem(index++, orderId, 메뉴_감자튀김_ID, quantity);
}
