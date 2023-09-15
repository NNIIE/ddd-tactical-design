package kitchenpos.products.tobe.domain;

import kitchenpos.products.infra.PurgomalumClient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Table(name = "product")
@Entity
public class Product {

    @Column(name = "id", columnDefinition = "binary(16)")
    @Id
    private UUID id;

    @Embedded
    @Column
    private ProductName name;

    @Embedded
    @Column
    private ProductPrice price;

    protected Product() {}

    public Product(final String name,
                   final BigDecimal price,
                   final PurgomalumClient purgomalumClient) {

        this.id = UUID.randomUUID();
        this.name = new ProductName(name, purgomalumClient);
        this.price = new ProductPrice(price);
    }

    public void changePrice(final BigDecimal price) {
        this.price = new ProductPrice(price);
    }

    public UUID getId() {
        return id;
    }

    public ProductName getName() {
        return name;
    }

    public ProductPrice getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
