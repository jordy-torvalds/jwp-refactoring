package kitchenpos.product.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.function.Supplier;

import static org.apache.http.HttpHeaders.LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class ProductAcceptanceTestSnippet {

    public static Supplier<ProductResponse> 상품_등록_요청_및_성공_확인(ProductRequest creatingProductRequest) {
        return () -> {
            ExtractableResponse<Response> response = 상품_등록_요청(creatingProductRequest);

            상품이_등록됨(response, creatingProductRequest);

            return response.as(ProductResponse.class);
        };
    }

    public static ExtractableResponse<Response> 상품_등록_요청(ProductRequest creatingProduct) {

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(creatingProduct)
                .when().post("/api/products")
                .then().log().all()
                .extract();
    }

    public static void 상품이_등록됨(ExtractableResponse<Response> response, ProductRequest creatingProduct) {
        ProductResponse createdProductResponse = response.as(ProductResponse.class);

        assertThat(response.statusCode()).isEqualTo(CREATED.value());
        assertThat(response.header(LOCATION)).isNotBlank();
        assertThat(createdProductResponse.getName()).isEqualTo(creatingProduct.getName());
        assertThat(createdProductResponse.getPrice().intValue()).isEqualTo(creatingProduct.getPrice().intValue());
    }

    public static Supplier<List<ProductResponse>> 상품_조회_요청_및_성공_확인(List<ProductResponse> products) {
        return () -> {
            ExtractableResponse<Response> response = 상품_조회_요청();

            상품이_조회됨(response, products);

            return response.jsonPath().getList(".", ProductResponse.class);
        };
    }

    public static ExtractableResponse<Response> 상품_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/products")
                .then().log().all()
                .extract();
    }

    public static void 상품이_조회됨(ExtractableResponse<Response> response, List<ProductResponse> expectedProducts) {
        List<ProductResponse> foundProducts = response.jsonPath().getList(".", ProductResponse.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(foundProducts).isEqualTo(expectedProducts);
    }
}
