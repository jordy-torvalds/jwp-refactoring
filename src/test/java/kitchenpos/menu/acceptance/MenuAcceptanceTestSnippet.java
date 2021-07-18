package kitchenpos.menu.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import org.junit.jupiter.api.function.Executable;
import org.springframework.http.MediaType;

import java.util.List;

import static org.apache.http.HttpHeaders.LOCATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

public class MenuAcceptanceTestSnippet {

    public static Executable 메뉴_등록_요청_및_성공_확인(Menu creatingMenu) {
        return () -> {
            ExtractableResponse<Response> response = 메뉴_등록_요청(creatingMenu);

            메뉴가_등록됨(response, creatingMenu);
        };
    }

    public static ExtractableResponse<Response> 메뉴_등록_요청(Menu creatingMenu) {

        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(creatingMenu)
                .when().post("/api/menus")
                .then().log().all()
                .extract();
    }

    public static void 메뉴가_등록됨(ExtractableResponse<Response> response, Menu creatingMenu) {
        Menu createdMenu = response.as(Menu.class);

        assertThat(response.statusCode()).isEqualTo(CREATED.value());
        assertThat(response.header(LOCATION)).isNotBlank();
        assertThat(createdMenu.getName()).isEqualTo(creatingMenu.getName());
        assertThat(createdMenu.getPrice().intValue()).isEqualTo(creatingMenu.getPrice().intValue());
        assertThat(createdMenu.getMenuProducts().size()).isEqualTo(creatingMenu.getMenuProducts().size());
        for (int i = 0; i < createdMenu.getMenuProducts().size(); i++) {
            MenuProduct createdMenuProduct = createdMenu.getMenuProducts().get(i);
            MenuProduct creatingMenuProduct = creatingMenu.getMenuProducts().get(i);

            assertThat(createdMenuProduct.getQuantity()).isEqualTo(creatingMenuProduct.getQuantity());
            assertThat(createdMenuProduct.getProductId()).isEqualTo(creatingMenuProduct.getProductId());
        }

    }


    public static Executable 메뉴_조회_요청_및_성공_확인(List<Menu> expectedMenus) {
        return () -> {
            ExtractableResponse<Response> response = 메뉴_조회_요청();

            메뉴가_조회됨(response, expectedMenus);
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

    public static void 메뉴가_조회됨(ExtractableResponse<Response> response, List<Menu> expectedMenus) {
        List<Menu> foundMenus = response.jsonPath().getList(".", Menu.class);

        assertThat(response.statusCode()).isEqualTo(OK.value());
        assertThat(foundMenus.size()).isEqualTo(expectedMenus.size());
        for(int i = 0; i < foundMenus.size(); i++) {
            equalMenu(foundMenus.get(i), expectedMenus.get(i));
        }
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
