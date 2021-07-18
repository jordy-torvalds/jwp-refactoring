package kitchenpos.order.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.order.dto.OrderResponse;
import org.springframework.http.MediaType;

import java.util.List;

import static org.apache.http.HttpHeaders.LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

public class OrderAcceptanceTestSnippet {

    public static OrderResponse 주문_등록_요청_및_성공_확인(OrderRequest creatingOrderRequest) {
        ExtractableResponse<Response> response = 주문_등록_요청(creatingOrderRequest);

        주문이_등록됨(response, creatingOrderRequest);

        return response.as(OrderResponse.class);
    }

    public static List<OrderResponse> 주문_조회_요청_및_성공_확인(List<OrderResponse> orderResponses) {
        ExtractableResponse<Response> response = 주문_조회_요청();

        주문이_조회됨(response, orderResponses);

        return response.jsonPath().getList(".", OrderResponse.class);
    }

    public static OrderResponse 주문의_상태_변경_및_성공_확인(Long id, OrderRequest orderRequest) {
        ExtractableResponse<Response> response = 주문_상태_변경(id, orderRequest);

        주문이_상태가_변경됨(response, id, orderRequest);

        return response.as(OrderResponse.class);
    }

    public static void 완료된_주문의_상태를_변경_및_실패_확인(Long id, OrderRequest orderRequest) {
        ExtractableResponse<Response> response = 주문_상태_변경(id, orderRequest);

        주문_상태_변경이_실패함(response);
    }

    public static ExtractableResponse<Response> 주문_등록_요청(OrderRequest creatingOrderRequest) {

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(creatingOrderRequest)
                .when().post("/api/orders")
                .then().log().all()
                .extract();
    }

    public static void 주문이_등록됨(ExtractableResponse<Response> response, OrderRequest creatingOrderRequest) {
        OrderResponse createdOrderResponse = response.as(OrderResponse.class);

        assertThat(response.statusCode()).isEqualTo(CREATED.value());
        assertThat(response.header(LOCATION)).isNotBlank();
        assertThat(createdOrderResponse.getOrderTableId()).isEqualTo(creatingOrderRequest.getOrderTableId());
        assertThat(createdOrderResponse.getOrderLineItems().size()).isEqualTo(creatingOrderRequest.getOrderLineItems().size());
        for(int i = 0; i < createdOrderResponse.getOrderLineItems().size(); i ++) {
            assertThat(createdOrderResponse.getOrderLineItems().get(i).getMenuId()).isEqualTo(creatingOrderRequest.getOrderLineItems().get(i).getMenuId());
            assertThat(createdOrderResponse.getOrderLineItems().get(i).getQuantity()).isEqualTo(creatingOrderRequest.getOrderLineItems().get(i).getQuantity());
        }
    }

    public static ExtractableResponse<Response> 주문_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/orders")
                .then().log().all()
                .extract();
    }

    public static void 주문이_조회됨(ExtractableResponse<Response> response, List<OrderResponse> expectedOrderResponses) {
        List<OrderResponse> foundOrderResponses = response.jsonPath().getList(".", OrderResponse.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(foundOrderResponses).containsAll(expectedOrderResponses);
    }

    public  static ExtractableResponse<Response> 주문_상태_변경(Long id, OrderRequest orderRequest) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(orderRequest)
                .when().put("/api/orders/{orderId}/order-status", id)
                .then().log().all()
                .extract();
    }

    public static void 주문이_상태가_변경됨(ExtractableResponse<Response> response, Long id, OrderRequest orderRequest) {
        OrderResponse orderResponse = response.as(OrderResponse.class);
        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(orderResponse.getId()).isEqualTo(id);
        assertThat(orderResponse.getOrderStatus()).isEqualTo(orderRequest.getOrderStatus());
    }

    private static void 주문_상태_변경이_실패함(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(INTERNAL_SERVER_ERROR.value());
    }
}
