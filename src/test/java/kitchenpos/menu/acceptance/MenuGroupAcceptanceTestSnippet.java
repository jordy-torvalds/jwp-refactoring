package kitchenpos.menu.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.product.domain.Product;
import org.junit.jupiter.api.function.Executable;
import org.springframework.http.MediaType;

import java.util.List;

import static org.apache.http.HttpHeaders.LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class MenuGroupAcceptanceTestSnippet {

    public static Executable 메뉴그룹_등록_요청_및_성공_확인(MenuGroup creatingMenuGroup) {
        return () -> {
            ExtractableResponse<Response> response = 메뉴그룹_등록_요청(creatingMenuGroup);

            메뉴그룹이_등록됨(response, creatingMenuGroup);
        };
    }

    public static ExtractableResponse<Response> 메뉴그룹_등록_요청(MenuGroup creatingMenuGroup) {

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(creatingMenuGroup)
                .when().post("/api/menu-groups")
                .then().log().all()
                .extract();
    }

    public static void 메뉴그룹이_등록됨(ExtractableResponse<Response> response, MenuGroup creatingMenuGroup) {
        MenuGroup createdMenuGroup = response.as(MenuGroup.class);

        assertThat(response.statusCode()).isEqualTo(CREATED.value());
        assertThat(response.header(LOCATION)).isNotBlank();
        assertThat(createdMenuGroup).isEqualTo(creatingMenuGroup);
    }


    public static Executable 메뉴그룹_조회_요청_및_성공_확인(List<MenuGroup> expectedMenuGroups) {
        return () -> {
            ExtractableResponse<Response> response = 메뉴그룹_조회_요청();

            메뉴그룹이_조회됨(response, expectedMenuGroups);
        };
    }

    public static ExtractableResponse<Response> 메뉴그룹_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/menu-groups")
                .then().log().all()
                .extract();
    }

    public static void 메뉴그룹이_조회됨(ExtractableResponse<Response> response, List<MenuGroup> expectedMenuGroups) {
        List<MenuGroup> foundMenuGroups = response.jsonPath().getList(".", MenuGroup.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(foundMenuGroups).isEqualTo(expectedMenuGroups);
    }
}
