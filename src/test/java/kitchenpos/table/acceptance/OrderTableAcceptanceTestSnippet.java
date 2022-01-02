package kitchenpos.table.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.table.dto.*;
import org.springframework.http.MediaType;

import java.util.List;

import static kitchenpos.order.acceptance.OrderAcceptanceTestSnippet.주문_등록_요청;
import static kitchenpos.order.acceptance.OrderAcceptanceTestSnippet.주문이_등록됨;
import static org.apache.http.HttpHeaders.LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

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

    public static void 주문테이블의_공석_여부_변경_요청_및_성공_확인(Long id, EmptyTableRequest emptyTableRequest) {
        ExtractableResponse<Response> response = 주문테이블_공석_상태_변경_요청(id, emptyTableRequest);

        주문테이블이_공석_여부가_변경됨(response, emptyTableRequest);
    }


    public static void 주문테이블의_인원수_변경_요청_및_성공_확인(Long id, TableGuestNumberRequest orderTableRequest) {
        ExtractableResponse<Response> response = 주문테이블_인원수_변경_요청(id, orderTableRequest);

        주문테이블의_인원수가_변경됨(response, orderTableRequest);
    }

    public static void 존재하지_않는_주문테이블을_빈_테이블로_변경_요청_및_실패_확인(Long id) {
        주문_테이블을_빈_테이블로_요청_했으나_유효하지_않은_요청으로_실패(id);
    }

    public static void 조리중이거나_식사_중인_주문이_있는_테이블을_빈_테이블로_변경_요청_및_실패_확인(OrderRequest orderRequest, Long id) {
        ExtractableResponse<Response> orderResponse = 주문_등록_요청(orderRequest);

        주문이_등록됨(orderResponse, orderRequest);

        주문_테이블을_빈_테이블로_요청_했으나_유효하지_않은_요청으로_실패(id);
    }

    public static void 주문_테이블을_빈_테이블로_요청_했으나_유효하지_않은_요청으로_실패(Long id) {
        ExtractableResponse<Response> response = 주문테이블_공석_상태_변경_요청(id, new EmptyTableRequest(true));

        주문테이블의_빈_테이블_변경이_실패됨(response);
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
                .when().get("/api/tables")
                .then().log().all()
                .extract();
    }

    public static void 주문테이블이_조회됨(ExtractableResponse<Response> response, List<OrderTableResponse> expectedOrderTableResponses) {
        List<OrderTableResponse> foundOrderTableResponses = response.jsonPath().getList(".", OrderTableResponse.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(foundOrderTableResponses).isEqualTo(expectedOrderTableResponses);
    }

    public static ExtractableResponse<Response> 주문테이블_공석_상태_변경_요청(Long id, EmptyTableRequest emptyTableRequest) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(emptyTableRequest)
                .when().put("/api/tables/{orderTableId}/empty", id)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 주문테이블_인원수_변경_요청(Long id, TableGuestNumberRequest tableGuestNumberRequest) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(tableGuestNumberRequest)
                .when().put("/api/tables/{orderTableId}/number-of-guests", id)
                .then().log().all()
                .extract();
    }

    public static void 주문테이블이_공석_여부가_변경됨(ExtractableResponse<Response> response, EmptyTableRequest emptyTableRequest) {
        OrderTableResponse orderTableResponse = response.as(OrderTableResponse.class);
        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(orderTableResponse.isEmpty()).isEqualTo(emptyTableRequest.isEmpty());
    }

    public static void 주문테이블의_빈_테이블_변경이_실패됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(INTERNAL_SERVER_ERROR.value());
    }


    public static void 주문테이블의_인원수가_변경됨(ExtractableResponse<Response> response, TableGuestNumberRequest orderTableRequest) {
        OrderTableResponse orderTableResponse = response.as(OrderTableResponse.class);
        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(orderTableResponse.getNumberOfGuests()).isEqualTo(orderTableRequest.getNumberOfGuests());
    }
}
