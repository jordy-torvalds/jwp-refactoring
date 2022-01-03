package kitchenpos.table.fixture;

import kitchenpos.table.domain.TableGroup;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static kitchenpos.order.fixture.OrderTableFixture.*;

public class TableGroupFixture {
    public static final Long 테이블_그룹_1번_ID = 1L;
    public static final Long 테이블_그룹_2번_ID = 2L;
    public static Supplier<TableGroup> 테이블_그룹_1번 = () -> new TableGroup(테이블_그룹_1번_ID, LocalDateTime.now()
            , asList(
            주문_테이블_그룹_등록_예정인_고객이_0명인_빈_테이블.get(),
            주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블.get()
    ));

    public static Supplier<TableGroup> 테이블_그룹_2번 = () ->  new TableGroup(테이블_그룹_2번_ID, LocalDateTime.now()
            , asList(
            주문_테이블_고객이_3명인_첫번째_테이블.get(),
            주문_테이블_고객이_3명인_두번째_테이블.get()
    ));
}
