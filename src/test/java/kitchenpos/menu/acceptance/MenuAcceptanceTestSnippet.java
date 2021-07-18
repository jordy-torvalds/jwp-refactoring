package kitchenpos.menu.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class MenuAcceptanceTestSnippet {

    public static Supplier<MenuResponse> 메뉴_등록_요청_및_성공_확인(MenuRequest creatingMenu) {
        return () -> {
            ExtractableResponse<Response> response = 메뉴_등록_요청(creatingMenu);

            메뉴가_등록됨(response, creatingMenu);

            return response.as(MenuResponse.class);
        };
    }

    public static ExtractableResponse<Response> 메뉴_등록_요청(MenuRequest creatingMenu) {

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(creatingMenu)
                .when().post("/api/menus")
                .then().log().all()
                .extract();
    }

    public static void 메뉴가_등록됨(ExtractableResponse<Response> response, MenuRequest creatingMenu) {
        MenuResponse createdMenu = response.as(MenuResponse.class);

        assertThat(response.statusCode()).isEqualTo(CREATED.value());
        assertThat(createdMenu.getName()).isEqualTo(creatingMenu.getName());
        assertThat(createdMenu.getPrice().intValue()).isEqualTo(creatingMenu.getPrice().intValue());
        assertThat(createdMenu.getMenuGroupId()).isEqualTo(creatingMenu.getMenuGroupId());
        for(int i = 0; i < createdMenu.getMenuProducts().size(); i++) {
            assertThat(createdMenu.getMenuProducts().get(i).getQuantity())
                    .isEqualTo(creatingMenu.getMenuProducts().get(i).getQuantity());
            assertThat(createdMenu.getMenuProducts().get(i).getProductId())
                    .isEqualTo(creatingMenu.getMenuProducts().get(i).getProductId());
        }
    }


    public static Supplier<List<MenuResponse>>  메뉴_조회_요청_및_성공_확인(List<MenuResponse> expectedMenus) {
        return () -> {
            ExtractableResponse<Response> response = 메뉴_조회_요청();

            메뉴가_조회됨(response, expectedMenus);
            return response.jsonPath().getList(".", MenuResponse.class);
        };
    }

    public static ExtractableResponse<Response> 메뉴_조회_요청() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/menus")
                .then().log().all()
                .extract();
    }

    public static void 메뉴가_조회됨(ExtractableResponse<Response> response, List<MenuResponse> expectedMenus) {
        List<MenuResponse> foundMenus = response.jsonPath().getList(".", MenuResponse.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(foundMenus).containsAll(expectedMenus);
    }

    private static void equalMenu(Menu actualMenu, Menu expectedMenu) {
        assertThat(actualMenu.getName()).isEqualTo(expectedMenu.getName());
        assertThat(actualMenu.getPrice().intValue()).isEqualTo(expectedMenu.getPrice().intValue());
        assertThat(actualMenu.getMenuProducts().size()).isEqualTo(expectedMenu.getMenuProducts().size());
        for (int i = 0; i < actualMenu.getMenuProducts().size(); i++) {
            MenuProduct actualMenuProduct = actualMenu.getMenuProducts().get(i);
            MenuProduct creatingMenuProduct = expectedMenu.getMenuProducts().get(i);

            assertThat(actualMenuProduct.getQuantity()).isEqualTo(creatingMenuProduct.getQuantity());
            assertThat(actualMenuProduct.getProductId()).isEqualTo(creatingMenuProduct.getProductId());
        }
    }
}
