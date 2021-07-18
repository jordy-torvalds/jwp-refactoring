package kitchenpos.product.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.product.domain.Product;
import org.junit.jupiter.api.function.Executable;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class ProductAcceptanceTestSnippet {

    public static Executable 상품_등록_요청_및_성공_확인(Product creatingProduct) {
        return () -> {
            ExtractableResponse<Response> response = 상품_등록_요청(creatingProduct);

            상품이_등록됨(response, creatingProduct);
        };
    }

    public static ExtractableResponse<Response> 상품_등록_요청(Product creatingProduct) {

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(creatingProduct)
                .when().post("/api/products")
                .then().log().all()
                .extract();
    }

    public static void 상품이_등록됨(ExtractableResponse<Response> response, Product creatingProduct) {
        Product createdProduct = response.as(Product.class);

        assertThat(response.statusCode()).isEqualTo(CREATED.value());
        assertThat(createdProduct).isEqualTo(creatingProduct);
    }

    public static Executable 상품_조회_요청_및_성공_확인(List<Product> products) {
        return () -> {
            ExtractableResponse<Response> response = 상품_조회_요청();

            상품이_조회됨(response, products);
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

    public static void 상품이_조회됨(ExtractableResponse<Response> response, List<Product> expectedProducts) {
        List<Product> foundProducts = response.jsonPath().getList(".", Product.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(foundProducts).isEqualTo(expectedProducts);
    }
}
