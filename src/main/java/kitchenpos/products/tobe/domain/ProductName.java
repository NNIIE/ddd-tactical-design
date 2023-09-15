package kitchenpos.products.tobe.domain;

import kitchenpos.products.infra.PurgomalumClient;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProductName {

    private String name;

    protected ProductName() {}

    public ProductName(final String name, final PurgomalumClient purgomalumClient) {
        validateName(name, purgomalumClient);
        this.name = name;
    }

    private void validateName(final String name, final PurgomalumClient purgomalumClient) {
        if (Objects.isNull(name) || purgomalumClient.containsProfanity(name)) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductName that = (ProductName) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}
