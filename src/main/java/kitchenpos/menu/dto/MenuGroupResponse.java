package kitchenpos.menu.dto;

import java.util.Objects;

public class MenuGroupResponse {
    private Long id;
    private String name;

    public MenuGroupResponse() {
    }

    public MenuGroupResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuGroupResponse menuGroupResponse = (MenuGroupResponse) o;
        return Objects.equals(id, menuGroupResponse.id) && Objects.equals(name, menuGroupResponse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}