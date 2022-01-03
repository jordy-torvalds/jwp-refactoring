package kitchenpos.order.fixture;

import kitchenpos.table.domain.OrderTable;

import java.util.function.Supplier;

import static kitchenpos.table.fixture.TableGroupFixture.테이블_그룹_1번_ID;
import static kitchenpos.table.fixture.TableGroupFixture.테이블_그룹_2번_ID;

public class OrderTableFixture {
    public static final Long 주문_테이블_조리_중인_주문_테이블_ID = 1L;
    public static final Long 주문_테이블_식사_중인_주문_테이블_ID = 2L;
    public static final Long 주문_테이블_계산_완료된_주문_테이블_ID = 3L;
    public static final Long 주문_테이블_고객이_0명인_빈_상태가_아닌_테이블_ID = 4L;
    public static final Long 주문_테이블_그룹_등록_예정인_고객이_0명인_빈_테이블_ID = 5L;
    public static final Long 주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블_ID = 6L;

    public static final Long 주문_테이블_고객이_3명인_첫번째_테이블_ID = 7L;
    public static final Long 주문_테이블_고객이_3명인_두번째_테이블_ID = 8L;

    public static Supplier<OrderTable> 주문_테이블_조리_중인_주문_테이블 = () -> new OrderTable(주문_테이블_조리_중인_주문_테이블_ID, null, 2, false);
    public static Supplier<OrderTable> 주문_테이블_식사_중인_주문_테이블 = () -> new OrderTable(주문_테이블_식사_중인_주문_테이블_ID, null, 3, false);
    public static Supplier<OrderTable> 주문_테이블_계산_완료된_주문_테이블 = () -> new OrderTable(주문_테이블_계산_완료된_주문_테이블_ID, null, 5, false);
    public static Supplier<OrderTable> 주문_테이블_고객이_0명인_빈_상태가_아닌_테이블 = () -> new OrderTable(주문_테이블_고객이_0명인_빈_상태가_아닌_테이블_ID, null, -1, false);
    public static Supplier<OrderTable> 주문_테이블_그룹_등록_예정인_고객이_0명인_빈_테이블 = () -> new OrderTable(주문_테이블_그룹_등록_예정인_고객이_0명인_빈_테이블_ID, null, 0, true);
    public static Supplier<OrderTable> 주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블 = () -> new OrderTable(주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블_ID, null, 3, true);
    public static Supplier<OrderTable> 주문_테이블_고객이_3명인_첫번째_테이블 = () -> new OrderTable(주문_테이블_고객이_3명인_첫번째_테이블_ID, 테이블_그룹_2번_ID, 3, false);
    public static Supplier<OrderTable> 주문_테이블_고객이_3명인_두번째_테이블 = () -> new OrderTable(주문_테이블_고객이_3명인_두번째_테이블_ID, 테이블_그룹_2번_ID, 3, false);
}
