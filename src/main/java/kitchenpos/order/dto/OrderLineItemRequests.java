package kitchenpos.order.dto;


import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class OrderLineItemRequests {

    private List<OrderLineItemRequest> values = new ArrayList<>();

    public OrderLineItemRequests() {
    }

    public OrderLineItemRequests(List<OrderLineItemRequest> values) {
        this.values = values;
    }

    public List<OrderLineItemRequest> get() {
        return values;
    }

    public static OrderLineItemRequests of(List<Long> ids, List<Long> quantities) {
        validateCreatingMenuProducts(ids, quantities);

        List<OrderLineItemRequest> menuProductRequests = new ArrayList<>();
        for(int i = 0 ; i < ids.size(); i++) {
            menuProductRequests.add(new OrderLineItemRequest(ids.get(i), quantities.get(i)));
        }
        return new OrderLineItemRequests(menuProductRequests);
    }

    private static void validateCreatingMenuProducts(List<Long> ids, List<Long> quantities) {
        if(ids.size() != quantities.size()) {
            throw new IllegalArgumentException(format("유효하지 않은 주문 항목 생성 요청 입니다. / ids.size(): %d / quantities.size(): %d /" , ids.size(), quantities.size()));
        }
    }
}
