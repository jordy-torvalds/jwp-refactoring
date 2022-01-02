package kitchenpos.menu.dto;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MenuProductRequests {

    private List<MenuProductRequest> values = new ArrayList<>();

    public MenuProductRequests() {
    }

    public MenuProductRequests(List<MenuProductRequest> values) {
        this.values = values;
    }

    public List<MenuProductRequest> get() {
        return values;
    }

    public static MenuProductRequests of(List<Long> ids, List<Long> quantities) {
        validateCreatingMenuProducts(ids, quantities);

        List<MenuProductRequest> menuProductRequests = new ArrayList<>();
        for(int i = 0 ; i < ids.size(); i++) {
            menuProductRequests.add(new MenuProductRequest(ids.get(i), quantities.get(i)));
        }
        return new MenuProductRequests(menuProductRequests);
    }

    private static void validateCreatingMenuProducts(List<Long> ids, List<Long> quantities) {
        if(ids.size() != quantities.size()) {
            throw new IllegalArgumentException(format("유효하지 않은 메뉴 샹품 생성 요청 입니다. / ids.size(): %d / quantities.size(): %d /" , ids.size(), quantities.size()));
        }
    }
}
