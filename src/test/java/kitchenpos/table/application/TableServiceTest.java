package kitchenpos.table.application;

import kitchenpos.order.dao.OrderDao;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.table.dao.OrderTableDao;
import kitchenpos.table.domain.OrderTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static kitchenpos.order.fixture.OrderTableFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class TableServiceTest {
    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderTableDao orderTableDao;

    private TableService tableService;

    @BeforeEach
    void setUp() {
        tableService = new TableService(orderDao, orderTableDao);
    }

    @MethodSource("methodSource_create_성공")
    @ParameterizedTest
    void create_성공(OrderTable orderTable) {
        // when
        when(tableService.create(orderTable)).thenReturn(orderTable);
        OrderTable createdOrderTable = tableService.create(orderTable);

        // then
        assertThat(createdOrderTable).isEqualTo(orderTable);
        verify(orderTableDao, times(1)).save(orderTable);
    }

    Stream<OrderTable> methodSource_create_성공() {
        return Stream.of(
                주문_테이블_조리_중인_주문_테이블.get(),
                주문_테이블_식사_중인_주문_테이블.get(),
                주문_테이블_계산_완료된_주문_테이블.get()
        );
    }

    @Test
    void list_성공() {
        // given
        List<OrderTable> orderTables = asList(
                주문_테이블_조리_중인_주문_테이블.get(),
                주문_테이블_식사_중인_주문_테이블.get(),
                주문_테이블_계산_완료된_주문_테이블.get()
        );

        // when
        when(orderTableDao.findAll()).thenReturn(orderTables);
        List<OrderTable> foundOrderTables = tableService.list();

        // then
        assertThat(foundOrderTables).containsExactlyElementsOf(orderTables);
        verify(orderTableDao, times(1)).findAll();
    }

    @Test
    void changeEmpty_예외_테이블_그룹_아이디가_존재() {
        // when
        when(orderTableDao.findById(주문_테이블_고객이_3명인_첫번째_테이블_ID))
                .thenReturn(Optional.of(주문_테이블_고객이_3명인_첫번째_테이블.get()));

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableService.changeEmpty(주문_테이블_고객이_3명인_첫번째_테이블_ID,
                        주문_테이블_고객이_3명인_첫번째_테이블.get()));
    }

    @MethodSource("methodSource_changeEmpty_예외_완료되지_않은_주문")
    @ParameterizedTest
    void changeEmpty_예외_완료되지_않은_주문(OrderTable orderTable) {
        // when
        when(orderTableDao.findById(orderTable.getId())).thenReturn(Optional.of(orderTable));
        when(orderDao.existsByOrderTableIdAndOrderStatusIn(
                orderTable.getId(),
                Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name())))
                .thenReturn(true);

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableService.changeEmpty(orderTable.getId(), orderTable));
    }

    Stream<OrderTable> methodSource_changeEmpty_예외_완료되지_않은_주문() {
        return Stream.of(
                주문_테이블_조리_중인_주문_테이블.get(),
                주문_테이블_식사_중인_주문_테이블.get()
        );
    }

    @Test
    void changeEmpty_성공() {
        // given
        OrderTable givenOrderTable = 주문_테이블_계산_완료된_주문_테이블.get();

        OrderTable argumentOrderTable = new OrderTable(
                null,
                null,
                0,
                true
        );

        // when
        when(orderTableDao.findById(givenOrderTable.getId())).thenReturn(Optional.of(givenOrderTable));
        when(orderDao.existsByOrderTableIdAndOrderStatusIn(
                givenOrderTable.getId(),
                Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name())))
                .thenReturn(false);
        when(orderTableDao.save(givenOrderTable)).thenReturn(givenOrderTable);

        OrderTable savedOrderTable = tableService.changeEmpty(givenOrderTable.getId(), argumentOrderTable);

        // then
        assertAll(
                () -> assertThat(savedOrderTable.getId()).isEqualTo(givenOrderTable.getId()),
                () -> assertThat(savedOrderTable.getTableGroupId()).isEqualTo(givenOrderTable.getTableGroupId()),
                () -> assertThat(savedOrderTable.getNumberOfGuests()).isEqualTo(givenOrderTable.getNumberOfGuests()),
                () -> assertThat(savedOrderTable.isEmpty()).isTrue()
        );
    }

    @Test
    void changeNumberOfGuests_예외_부적절한_테이블_고객수() {
        // when, then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableService.changeNumberOfGuests(주문_테이블_그룹_등록_예정인_고객이_0명인_빈_테이블_ID,
                        주문_테이블_그룹_등록_예정인_고객이_0명인_빈_테이블.get()));
    }

    @Test
    void changeNumberOfGuests_예외_빈_테이블() {

        //when
        when(orderTableDao.findById(주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블_ID))
                .thenReturn(Optional.of(주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블.get()));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> tableService.changeNumberOfGuests(주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블_ID,
                        주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블.get()));
    }

    @Test
    void changeNumberOfGuests_성공() {
        // given
        OrderTable givenOrderTable = 주문_테이블_식사_중인_주문_테이블.get();

        OrderTable argumentOrderTable = new OrderTable(
                null,
                null,
                8,
                false
        );

        //when
        when(orderTableDao.findById(givenOrderTable.getId()))
                .thenReturn(Optional.of(givenOrderTable));
        when(orderTableDao.save(givenOrderTable))
                .thenReturn(givenOrderTable);

        OrderTable savedOrderTable = tableService.changeNumberOfGuests(givenOrderTable.getId(), argumentOrderTable);

        // then
        assertAll(
                () -> assertThat(savedOrderTable.getId()).isEqualTo(givenOrderTable.getId()),
                () -> assertThat(savedOrderTable.getTableGroupId()).isEqualTo(givenOrderTable.getTableGroupId()),
                () -> assertThat(savedOrderTable.getNumberOfGuests()).isEqualTo(argumentOrderTable.getNumberOfGuests()),
                () -> assertThat(savedOrderTable.isEmpty()).isEqualTo(givenOrderTable.isEmpty())
        );

    }
}