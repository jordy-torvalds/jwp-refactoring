package kitchenpos.table.application;

import kitchenpos.order.dao.OrderDao;
import kitchenpos.table.dao.OrderTableDao;
import kitchenpos.table.dao.TableGroupDao;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.TableGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static kitchenpos.order.fixture.OrderTableFixture.*;
import static kitchenpos.table.fixture.TableGroupFixture.테이블_그룹_1번;
import static kitchenpos.table.fixture.TableGroupFixture.테이블_그룹_2번;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.*;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TableGroupServiceTest {
    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderTableDao orderTableDao;

    @Mock
    private TableGroupDao tableGroupDao;

    private TableGroupService tableGroupService;

    @BeforeEach
    void setUp() {
        tableGroupService = new TableGroupService(orderDao, orderTableDao, tableGroupDao);
    }

    @MethodSource("methodSource_create_예외_2개_미만의_주문테이블")
    @ParameterizedTest
    void create_예외_2개_미만의_주문테이블(TableGroup tableGroup) {
        // when, then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableGroupService.create(tableGroup));
    }

    Stream<Arguments> methodSource_create_예외_2개_미만의_주문테이블() {
        return Stream.of(
                Arguments.of(new TableGroup(1L, LocalDateTime.now(), Collections.emptyList())),
                Arguments.of(new TableGroup(1L, LocalDateTime.now(), Collections.singletonList(new OrderTable())))
        );
    }

    @Test
    void create_예외_저장되지_않은_주문_테이블() {
        // when
        TableGroup targetTableGroup = 테이블_그룹_1번.get();
        final List<Long> orderTableIds = targetTableGroup.getOrderTables().stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());
        when(orderTableDao.findAllByIdIn(orderTableIds)).thenReturn(Collections.emptyList());


        // when, then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableGroupService.create(targetTableGroup));
    }

    @MethodSource("methodSource_create_예외_테이블_비어있지_않거나_테이블_그룹_번호가_있는_테이블")
    @ParameterizedTest
    void create_예외_테이블_비어있지_않거나_테이블_그룹_번호가_있는_테이블() {
        // when
        TableGroup targetTableGroup = 테이블_그룹_1번.get();
        final List<Long> orderTableIds = targetTableGroup.getOrderTables().stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());

        when(orderTableDao.findAllByIdIn(orderTableIds)).thenReturn(asList(targetTableGroup.getOrderTables().get(0),
                targetTableGroup.getOrderTables().get(1),
                주문_테이블_식사_중인_주문_테이블.get()));

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableGroupService.create(targetTableGroup));
    }

    Stream<Arguments> methodSource_create_예외_테이블_비어있지_않거나_테이블_그룹_번호가_있는_테이블() {
        return Stream.of(
                Arguments.of(주문_테이블_식사_중인_주문_테이블),
                Arguments.of(주문_테이블_고객이_3명인_첫번째_테이블.get())
        );
    }

    @Test
    void create_성공() {
        // when
        TableGroup targetTableGroup = 테이블_그룹_1번.get();
        final List<Long> orderTableIds = targetTableGroup.getOrderTables().stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());

        when(orderTableDao.findAllByIdIn(orderTableIds)).thenReturn(targetTableGroup.getOrderTables());
        when(tableGroupDao.save(targetTableGroup)).thenReturn(targetTableGroup);

        // then
        assertDoesNotThrow(() -> tableGroupService.create(targetTableGroup));
    }

    @Test
    void ungroup_예외_식사_중이거나_조리_중인_주문을_포함한_테이블() {
        // given
        TableGroup targetTableGroup = 테이블_그룹_2번.get();
        final List<Long> orderTableIds = targetTableGroup.getOrderTables().stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());

        // when
        when(orderTableDao.findAllByTableGroupId(targetTableGroup.getId())).thenReturn(targetTableGroup.getOrderTables());
        when(orderDao.existsByOrderTableIdInAndOrderStatusIn(
                orderTableIds, Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name()))).thenReturn(true);

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableGroupService.ungroup(targetTableGroup.getId()));
    }

    @Test
    void ungroup_성공() {
        // given
        TableGroup targetTableGroup = 테이블_그룹_2번.get();
        final List<Long> orderTableIds = targetTableGroup.getOrderTables().stream()
                .map(OrderTable::getId)
                .collect(Collectors.toList());

        // when
        when(orderTableDao.findAllByTableGroupId(targetTableGroup.getId())).thenReturn(targetTableGroup.getOrderTables());
        when(orderDao.existsByOrderTableIdInAndOrderStatusIn(
                orderTableIds, Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name()))).thenReturn(false);
        tableGroupService.ungroup(targetTableGroup.getId());

        // then
        for(OrderTable orderTable :targetTableGroup.getOrderTables()) {
            verify(orderTableDao, times(1)).save(orderTable);
        }
    }
}