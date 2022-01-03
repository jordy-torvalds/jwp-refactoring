package kitchenpos.order.application;

import kitchenpos.menu.dao.MenuDao;
import kitchenpos.order.dao.OrderDao;
import kitchenpos.order.dao.OrderLineItemDao;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItem;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.table.dao.OrderTableDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static kitchenpos.order.fixture.OrderFixture.주문_후라이드_한마리_감자튀김_계산완료;
import static kitchenpos.order.fixture.OrderFixture.주문_후라이드_한마리_감자튀김_조리중;
import static kitchenpos.order.fixture.OrderTableFixture.주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블;
import static kitchenpos.order.fixture.OrderTableFixture.주문_테이블_조리_중인_주문_테이블;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private MenuDao menuDao;

    @Mock
    private OrderDao orderDao;

    @Mock
    private OrderLineItemDao orderLineItemDao;

    @Mock
    private OrderTableDao orderTableDao;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(menuDao, orderDao, orderLineItemDao, orderTableDao);
    }

    @Test
    void create_예외_빈_주문상품_리스트() {

        // given
        Order order = new Order(1L, 1L, OrderStatus.COOKING.name(), LocalDateTime.now(), Collections.emptyList());

        // when, then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> orderService.create(order));
    }

    @Test
    void create_예외_저장되지_않은_메뉴() {
        // given
        final List<Long> menuIds = 주문_후라이드_한마리_감자튀김_조리중.get().getOrderLineItems().stream()
                .map(OrderLineItem::getMenuId)
                .collect(Collectors.toList());
        final Order createdOrder = 주문_후라이드_한마리_감자튀김_조리중.get();

        // when
        when(menuDao.countByIdIn(menuIds)).thenReturn(1L);

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderService.create(createdOrder));
    }

    @Test
    void create_존재하지_않는_주문_테이블() {
        // given
        final List<Long> menuIds = 주문_후라이드_한마리_감자튀김_조리중.get().getOrderLineItems().stream()
                .map(OrderLineItem::getMenuId)
                .collect(Collectors.toList());
        final Order savedOrder = 주문_후라이드_한마리_감자튀김_조리중.get();

        // when
        when(menuDao.countByIdIn(menuIds)).thenReturn((long) 주문_후라이드_한마리_감자튀김_조리중.get().getOrderLineItems().size());
        when(orderTableDao.findById(주문_후라이드_한마리_감자튀김_조리중.get().getOrderTableId())).thenReturn(Optional.empty());

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderService.create(savedOrder));
    }

    @Test
    void create_빈_주문_테이블() {
        // given
        final Order order = 주문_후라이드_한마리_감자튀김_조리중.get();
        final List<Long> menuIds = order.getOrderLineItems().stream()
                .map(OrderLineItem::getMenuId)
                .collect(Collectors.toList());

        // when
        when(menuDao.countByIdIn(menuIds)).thenReturn((long) order.getOrderLineItems().size());
        when(orderTableDao.findById(order.getOrderTableId())).thenReturn(Optional.of(주문_테이블_그룹_등록_예정인_고객이_3명인_빈_테이블.get()));

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderService.create(order));
    }

    @Test
    void create_성공() {
        // given
        final Order order = 주문_후라이드_한마리_감자튀김_조리중.get();
        final List<Long> menuIds = order.getOrderLineItems().stream()
                .map(OrderLineItem::getMenuId)
                .collect(Collectors.toList());

        // when
        when(menuDao.countByIdIn(menuIds)).thenReturn((long) order.getOrderLineItems().size());
        when(orderTableDao.findById(order.getOrderTableId())).thenReturn(Optional.of(주문_테이블_조리_중인_주문_테이블.get()));
        when(orderDao.save(order)).thenReturn(order);
        for (OrderLineItem orderLineItem : order.getOrderLineItems()) {
            when(orderLineItemDao.save(orderLineItem)).thenReturn(orderLineItem);
        }

        // then
        assertDoesNotThrow(() -> orderService.create(order));
    }

    @Test
    void list_성공() {
        // when
        final Order order = 주문_후라이드_한마리_감자튀김_조리중.get();
        when(orderDao.findAll()).thenReturn(asList(order));
        for (OrderLineItem orderLineItem : order.getOrderLineItems()) {
            when(orderLineItemDao.findAllByOrderId(orderLineItem.getOrderId())).thenReturn(asList(orderLineItem));
        }
        List<Order> listedOrder = orderService.list();

        // then
        assertThat(listedOrder).containsExactly(order);
    }

    @Test
    void changeOrderStatus_예외_존재하지_않는_주문() {
        // given
        Order givenOrder = 주문_후라이드_한마리_감자튀김_조리중.get();
        Long givenOrderId = givenOrder.getId();
        Order argumentOrder = new Order(
                null,
                null,
                OrderStatus.MEAL.name(),
                null,
                null
        );

        // when
        when(orderDao.findById(givenOrder.getId())).thenReturn(Optional.empty());

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderService.changeOrderStatus(givenOrderId, argumentOrder));
    }

    @Test
    void changeOrderStatus_예외_이미_계산_완료된_주문() {
        // given
        Order givenOrder = 주문_후라이드_한마리_감자튀김_계산완료.get();
        Long givenOrderId = givenOrder.getId();
        Order argumentOrder = new Order(
                null,
                null,
                OrderStatus.MEAL.name(),
                null,
                null
        );

        // when
        when(orderDao.findById(givenOrder.getId())).thenReturn(Optional.of(givenOrder));

        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> orderService.changeOrderStatus(givenOrderId, argumentOrder));
    }

    @Test
    void changeOrderStatus_성공() {
        // given
        Order givenOrder = 주문_후라이드_한마리_감자튀김_조리중.get();
        Order argumentOrder = new Order(
                null,
                null,
                OrderStatus.MEAL.name(),
                null,
                null
        );

        // when
        when(orderDao.findById(givenOrder.getId())).thenReturn(Optional.of(givenOrder));
        givenOrder.setOrderStatus(OrderStatus.MEAL.name());
        when(orderDao.save(givenOrder)).thenReturn(givenOrder);
        for (OrderLineItem orderLineItem : givenOrder.getOrderLineItems()) {
            when(orderLineItemDao.findAllByOrderId(orderLineItem.getOrderId())).thenReturn(asList(orderLineItem));
        }

        // then
        assertDoesNotThrow(() -> orderService.changeOrderStatus(givenOrder.getId(), argumentOrder));
    }
}