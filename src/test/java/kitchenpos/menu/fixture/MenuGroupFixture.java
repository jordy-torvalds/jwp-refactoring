package kitchenpos.menu.fixture;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.dto.MenuGroupRequest;

import java.util.function.Supplier;

public class MenuGroupFixture {
    public static final Long 메뉴그룹_치킨류_ID = 1L;
    public static final Long 메뉴그룹_주류_ID = 2L;
    public static final Long 메뉴그룹_서브메뉴_ID = 3L;

    public static Supplier<MenuGroup> 메뉴그룹_치킨류
            = () -> new MenuGroup(메뉴그룹_치킨류_ID, "치킨류");

    public static Supplier<MenuGroup> 메뉴그룹_주류
            = () -> new MenuGroup(메뉴그룹_주류_ID, "주류");

    public static Supplier<MenuGroup> 메뉴그룹_서브메뉴
            = () -> new MenuGroup(메뉴그룹_서브메뉴_ID, "서브메뉴");

    public static MenuGroupRequest map(MenuGroup menuGroup) {
        return new MenuGroupRequest(menuGroup.getName());
    }
}
