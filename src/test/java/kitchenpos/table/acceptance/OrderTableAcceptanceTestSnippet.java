package kitchenpos.table.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.OrderTableResponse;
import org.springframework.http.MediaType;

import java.util.List;

import static org.apache.http.HttpHeaders.LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class OrderTableAcceptanceTestSnippet {

    public static OrderTableResponse 주문테이블_등록_요청_및_성공_확인(OrderTableRequest creatingOrderTableRequest) {
        ExtractableResponse<Response> response = 주문테이블_등록_요청(creatingOrderTableRequest);

        주문테이블이_등록됨(response, creatingOrderTableRequest);

        return response.as(OrderTableResponse.class);
    }

    public static List<OrderTableResponse> 주문테이블_조회_요청_및_성공_확인(List<OrderTableResponse> products) {
        ExtractableResponse<Response> response = 주문테이블_조회_요청();

        주문테이블이_조회됨(response, products);

        return response.jsonPath().getList(".", OrderTableResponse.class);
    }

    public static ExtractableResponse<Response> 주문테이블_등록_요청(OrderTableRequest creatingProduct) {

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(creatingProduct)
                .when().post("/api/tables")
                .then().log().all()
                .extract();
    }

    public static void 주문테이블이_등록됨(ExtractableResponse<Response> response, OrderTableRequest creatingProduct) {
        OrderTableResponse createdOrderTableResponse = response.as(OrderTableResponse.class);

        assertThat(response.statusCode()).isEqualTo(CREATED.value());
        assertThat(response.header(LOCATION)).isNotBlank();
        assertThat(createdOrderTableResponse.getNumberOfGuests()).isEqualTo(creatingProduct.getNumberOfGuests());
        assertThat(createdOrderTableResponse.isEmpty()).isEqualTo(creatingProduct.isEmpty());
    }

    public static ExtractableResponse<Response> 주문테이블_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/tables")
                .then().log().all()
                .extract();
    }

    public static void 주문테이블이_조회됨(ExtractableResponse<Response> response, List<OrderTableResponse> expectedOrderTableResponses) {
        List<OrderTableResponse> foundOrderTableResponses = response.jsonPath().getList(".", OrderTableResponse.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(foundOrderTableResponses).isEqualTo(expectedOrderTableResponses);
    }
}
