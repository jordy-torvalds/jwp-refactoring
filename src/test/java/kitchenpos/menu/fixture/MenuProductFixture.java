package kitchenpos.menu.fixture;

import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.dto.MenuProductRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static kitchenpos.menu.fixture.MenuFixture.*;
import static kitchenpos.product.fixture.ProductFixture.*;

public class MenuProductFixture {
    private static Long 메뉴_상품_후라이드_치킨_ID = 1L;
    private static Long 메뉴_상품_양념_치킨_ID = 2L;
    private static Long 메뉴_상품_양념_치킨_두마리_ID = 3L;
    private static Long 메뉴_상품_후라이드_치킨_두마리_ID = 4L;
    private static Long 메뉴_상품_감자튀김_ID = 5L;
    private static Long 메뉴_상품_치즈볼_ID = 6L;
    private static Long 메뉴_상품_옛날통닭_ID = 7L;
    public static Supplier<MenuProduct> 메뉴_상품_후라이드_치킨
            = () -> new MenuProduct(메뉴_상품_후라이드_치킨_ID, 메뉴_후라이드_치킨_한마리_ID, 상품_후라이드_치킨_ID, 1L);

    public static Supplier<MenuProduct> 메뉴_상품_양념_치킨
            = () -> new MenuProduct(메뉴_상품_양념_치킨_ID, 메뉴_양념_치킨_한마리_ID, 상품_양념_치킨_ID, 1L);

    public static Supplier<MenuProduct> 메뉴_상품_양념_치킨_두마리
            = () -> new MenuProduct(메뉴_상품_양념_치킨_두마리_ID, 메뉴_양념_두마리_치킨_세트_ID, 상품_양념_치킨_ID, 2L);

    public static Supplier<MenuProduct> 메뉴_상품_후라이드_치킨_두마리
            = () -> new MenuProduct(메뉴_상품_후라이드_치킨_두마리_ID, 메뉴_후라이드_두마리_치킨_세트_ID, 상품_후라이드_치킨_ID, 2L);

    public static Supplier<MenuProduct> 메뉴_상품_감자튀김
            = () -> new MenuProduct(메뉴_상품_감자튀김_ID, 메뉴_감자튀김_ID, 상품_감자튀김_ID, 1L);

    public static Supplier<MenuProduct> 메뉴_상품_치즈볼
            = () -> new MenuProduct(메뉴_상품_치즈볼_ID, 메뉴_치즈볼_ID, 상품_치즈볼_ID, 1L);

    public static Supplier<MenuProduct> 메뉴_상품_옛날통닭
            = () -> new MenuProduct(메뉴_상품_옛날통닭_ID, 메뉴_옛날통닭_ID, 상품_옛날통닭_ID, 1L);
}
