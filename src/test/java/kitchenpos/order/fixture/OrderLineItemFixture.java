package kitchenpos.order.fixture;

import kitchenpos.order.domain.OrderLineItem;

import java.util.function.Supplier;

import static kitchenpos.menu.fixture.MenuFixture.메뉴_감자튀김_ID;
import static kitchenpos.menu.fixture.MenuFixture.메뉴_후라이드_치킨_한마리_ID;
import static kitchenpos.order.fixture.OrderFixture.*;

public class OrderLineItemFixture {
    private static Long 주문_항목_조리_중인_주문_감자튀김_ID = 1L;
    private static Long 주문_항목_조리_중인_주문_후라이드치킨_ID = 2L;
    private static Long 주문_항목_식사_중인_주문_감자튀김_ID = 3L;
    private static Long 주문_항목_식사_중인_주문_후라이드치킨_ID = 4L;
    private static Long 주문_항목_계산_완료_주문_감자튀김_ID = 5L;
    private static Long 주문_항목_계산_완료_주문_후라이드치킨_ID = 6L;
    private static Long 주문_항목_고객이_3명인_첫번째_테이블_계산완료_감자튀김_ID = 9L;
    private static Long 주문_항목_고객이_3명인_첫번째_테이블_계산완료_후라이드치킨_ID = 10L;
    private static Long 주문_항목_고객이_3명인_두번째_테이블_계산완료_감자튀김_ID = 11L;
    private static Long 주문_항목_고객이_3명인_두번째_테이블_계산완료_후라이드치킨_ID = 12L;
    private static Long 주문_항목_조리_중인_주문_추가_주문_감자튀김_ID = 13L;
    private static Long 주문_항목_조리_중인_주문_추가_주문_후라이드_치킨_ID = 14L;

    public static Supplier<OrderLineItem> 주문_항목_조리_중인_주문_감자튀김
            = () -> new OrderLineItem(주문_항목_조리_중인_주문_감자튀김_ID, 주문_후라이드_한마리_감자튀김_조리중_ID, 메뉴_감자튀김_ID, 1);
    public static Supplier<OrderLineItem> 주문_항목_조리_중인_주문_후라이드치킨 
            = () -> new OrderLineItem(주문_항목_조리_중인_주문_후라이드치킨_ID, 주문_후라이드_한마리_감자튀김_조리중_ID, 메뉴_후라이드_치킨_한마리_ID, 1);

    public static Supplier<OrderLineItem> 주문_항목_조리_중인_주문_추가_주문_감자튀김
            = () -> new OrderLineItem(주문_항목_조리_중인_주문_추가_주문_감자튀김_ID, 주문_후라이드_한마리_감자튀김_조리중_추가_주문_ID, 메뉴_감자튀김_ID, 1);
    public static Supplier<OrderLineItem> 주문_항목_조리_중인_주문_추가_주문_후라이드_치킨
            = () -> new OrderLineItem(주문_항목_조리_중인_주문_추가_주문_후라이드_치킨_ID, 주문_후라이드_한마리_감자튀김_조리중_추가_주문_ID, 메뉴_후라이드_치킨_한마리_ID, 1);

    public static Supplier<OrderLineItem> 주문_항목_식사_중인_주문_감자튀김 
            = () -> new OrderLineItem(주문_항목_식사_중인_주문_감자튀김_ID, 주문_후라이드_한마리_감자튀김_식사중_ID, 메뉴_감자튀김_ID, 1);
    public static Supplier<OrderLineItem> 주문_항목_식사_중인_주문_후라이드치킨 
            = () -> new OrderLineItem(주문_항목_식사_중인_주문_후라이드치킨_ID, 주문_후라이드_한마리_감자튀김_식사중_ID, 메뉴_후라이드_치킨_한마리_ID, 1);

    public static Supplier<OrderLineItem> 주문_항목_계산_완료_주문_감자튀김 
            = () -> new OrderLineItem(주문_항목_계산_완료_주문_감자튀김_ID, 주문_후라이드_한마리_감자튀김_계산완료_ID, 메뉴_감자튀김_ID, 1);
    public static Supplier<OrderLineItem> 주문_항목_계산_완료_주문_후라이드치킨 
            = () -> new OrderLineItem(주문_항목_계산_완료_주문_후라이드치킨_ID, 주문_후라이드_한마리_감자튀김_계산완료_ID, 메뉴_후라이드_치킨_한마리_ID, 1);

    public static Supplier<OrderLineItem> 주문_항목_고객이_3명인_첫번째_테이블_계산완료_감자튀김
            = () -> new OrderLineItem(주문_항목_고객이_3명인_첫번째_테이블_계산완료_감자튀김_ID, 주문_고객이_3명인_첫번째_테이블_계산완료_ID, 메뉴_감자튀김_ID, 1);
    public static Supplier<OrderLineItem> 주문_항목_고객이_3명인_첫번째_테이블_계산완료_후라이드치킨
            = () -> new OrderLineItem(주문_항목_고객이_3명인_첫번째_테이블_계산완료_후라이드치킨_ID, 주문_고객이_3명인_첫번째_테이블_계산완료_ID, 메뉴_후라이드_치킨_한마리_ID, 1);

    public static Supplier<OrderLineItem> 주문_항목_고객이_3명인_두번째_테이블_계산완료_감자튀김
            = () -> new OrderLineItem(주문_항목_고객이_3명인_두번째_테이블_계산완료_감자튀김_ID, 주문_고객이_3명인_두번째_테이블_계산완료_ID, 메뉴_감자튀김_ID, 1);
    public static Supplier<OrderLineItem> 주문_항목_고객이_3명인_두번째_테이블_계산완료_후라이드치킨
            = () -> new OrderLineItem(주문_항목_고객이_3명인_두번째_테이블_계산완료_후라이드치킨_ID, 주문_고객이_3명인_두번째_테이블_계산완료_ID, 메뉴_후라이드_치킨_한마리_ID, 1);

}
