package kitchenpos.menu.fixture;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.dto.MenuRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Supplier;

import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static kitchenpos.menu.fixture.MenuGroupFixture.*;
import static kitchenpos.menu.fixture.MenuProductFixture.*;

public class MenuFixture {
    public static final Long 메뉴_후라이드_치킨_한마리_ID = 1L;
    public static final Long 메뉴_양념_치킨_한마리_ID = 2L;
    public static final Long 메뉴_양념_후라이드_두마리_치킨_세트_ID = 3L;
    public static final Long 메뉴_양념_두마리_치킨_세트_ID = 4L;
    public static final Long 메뉴_후라이드_두마리_치킨_세트_ID = 5L;
    public static final Long 메뉴_감자튀김_ID = 6L;
    public static final Long 메뉴_치즈볼_ID = 7L;
    public static final Long 메뉴_옛날통닭_ID = 8L;

    public static Supplier<Menu> 메뉴_후라이드_치킨_한마리
            = () -> new Menu(메뉴_후라이드_치킨_한마리_ID, "후라이드 치킨 한마리", valueOf(18_000), 메뉴그룹_치킨류_ID, asList(메뉴_상품_후라이드_치킨.get()));
    public static Supplier<Menu> 메뉴_양념_치킨_한마리
            = () -> new Menu(메뉴_양념_치킨_한마리_ID, "양념 치킨 한마리", valueOf(18_000), 메뉴그룹_치킨류_ID, asList(메뉴_상품_양념_치킨.get()));
    public static Supplier<Menu> 메뉴_양념_후라이드_두마리_치킨_세트
            = () -> new Menu(메뉴_양념_후라이드_두마리_치킨_세트_ID, "양념, 후라이드 두마리 치킨 세트", valueOf(36_000), 메뉴그룹_치킨류_ID, asList(메뉴_상품_후라이드_치킨.get(), 메뉴_상품_양념_치킨.get()));
    public static Supplier<Menu> 메뉴_양념_두마리_치킨_세트
            = () -> new Menu(메뉴_양념_두마리_치킨_세트_ID, "양념 두마리 치킨 세트", valueOf(36_000), 메뉴그룹_치킨류_ID, asList(메뉴_상품_양념_치킨_두마리.get()));
    public static Supplier<Menu> 메뉴_후라이드_두마리_치킨_세트
            = () -> new Menu(메뉴_후라이드_두마리_치킨_세트_ID, "후라이드 두마리 치킨 세트", valueOf(36_000), 메뉴그룹_치킨류_ID, asList(메뉴_상품_후라이드_치킨_두마리.get()));
    public static Supplier<Menu> 메뉴_감자튀김
            = () -> new Menu(메뉴_감자튀김_ID, "감자튀김", valueOf(2000), 메뉴그룹_서브메뉴_ID, asList(메뉴_상품_감자튀김.get()));
    public static Supplier<Menu> 메뉴_치즈볼
            = () -> new Menu(메뉴_치즈볼_ID, "치즈볼", valueOf(4000), 메뉴그룹_서브메뉴_ID, asList(메뉴_상품_치즈볼.get()));
    public static Supplier<Menu> 메뉴_옛날통닭
            = () -> new Menu(메뉴_옛날통닭_ID, "옛날통닭", valueOf(14_000), 메뉴그룹_치킨류_ID, asList(메뉴_상품_옛날통닭.get()));
}
