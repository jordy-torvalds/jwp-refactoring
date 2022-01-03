package kitchenpos.order.fixture;

import kitchenpos.order.domain.Order;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static kitchenpos.order.domain.OrderStatus.*;
import static kitchenpos.order.fixture.OrderLineItemFixture.*;
import static kitchenpos.order.fixture.OrderTableFixture.*;

public class OrderFixture {
    private static Supplier<LocalDateTime> currentDateTimeSupplier = LocalDateTime::now;

    public static final Long 주문_후라이드_한마리_감자튀김_조리중_ID = 1L;
    public static final Long 주문_후라이드_한마리_감자튀김_조리중_추가_주문_ID = 2L;
    public static final Long 주문_후라이드_한마리_감자튀김_식사중_ID = 3L;
    public static final Long 주문_후라이드_한마리_감자튀김_계산완료_ID = 4L;
    public static final Long 주문_고객이_3명인_첫번째_테이블_계산완료_ID = 5L;
    public static final Long 주문_고객이_3명인_두번째_테이블_계산완료_ID = 6L;
    public static final Long 주문_테이블_그룹_2번_테이블_첫번째_주문_ID = 7L;
    public static final Long 주문_테이블_그룹_2번_테이블_두번째_주문_ID = 8L;

    public static Supplier<Order> 주문_후라이드_한마리_감자튀김_조리중
            = () -> new Order(주문_후라이드_한마리_감자튀김_조리중_ID,
            주문_테이블_조리_중인_주문_테이블_ID,
            COOKING.name(),
            currentDateTimeSupplier.get(),
            asList(주문_항목_조리_중인_주문_감자튀김.get(), 주문_항목_조리_중인_주문_후라이드치킨.get()));

    public static Supplier<Order> 주문_후라이드_한마리_감자튀김_조리중_추가_주문
            = () -> new Order(주문_후라이드_한마리_감자튀김_조리중_추가_주문_ID,
            주문_테이블_조리_중인_주문_테이블_ID,
            COOKING.name(),
            currentDateTimeSupplier.get(),
            asList(주문_항목_조리_중인_주문_감자튀김.get(), 주문_항목_조리_중인_주문_후라이드치킨.get()));

    public static Supplier<Order> 주문_후라이드_한마리_감자튀김_식사중
            = () -> new Order(주문_후라이드_한마리_감자튀김_식사중_ID,
            주문_테이블_식사_중인_주문_테이블_ID,
            MEAL.name(),
            currentDateTimeSupplier.get(),
            asList(주문_항목_식사_중인_주문_감자튀김.get(), 주문_항목_식사_중인_주문_후라이드치킨.get()));

    public static Supplier<Order> 주문_후라이드_한마리_감자튀김_계산완료
            = () -> new Order(주문_후라이드_한마리_감자튀김_계산완료_ID,
            주문_테이블_계산_완료된_주문_테이블_ID,
            COMPLETION.name(),
            currentDateTimeSupplier.get(),
            asList(주문_항목_계산_완료_주문_감자튀김.get(), 주문_항목_계산_완료_주문_후라이드치킨.get()));

    public static Supplier<Order> 주문_고객이_3명인_첫번째_테이블_계산완료
            = () -> new Order(주문_고객이_3명인_첫번째_테이블_계산완료_ID,
            주문_테이블_고객이_3명인_첫번째_테이블_ID,
            COMPLETION.name(),
            currentDateTimeSupplier.get(),
            asList(주문_항목_고객이_3명인_첫번째_테이블_계산완료_감자튀김.get(), 주문_항목_고객이_3명인_첫번째_테이블_계산완료_후라이드치킨.get()));

    public static Supplier<Order> 주문_고객이_3명인_두번째_테이블_계산완료
            = () -> new Order(주문_고객이_3명인_두번째_테이블_계산완료_ID,
            주문_테이블_고객이_3명인_두번째_테이블_ID,
            COMPLETION.name(),
            currentDateTimeSupplier.get(),
            asList(주문_항목_고객이_3명인_두번째_테이블_계산완료_감자튀김.get(), 주문_항목_고객이_3명인_두번째_테이블_계산완료_후라이드치킨.get()));
}
