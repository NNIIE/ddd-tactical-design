package kitchenpos.products.tobe.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("상품은")
class ProductTest {

    @Nested
    @DisplayName("등록할 수 있다.")
    class 등록할_수_있다 {

        @DisplayName("이름이 비어있다면 등록할 수 없다")
        @ParameterizedTest(name = "{0}인 경우")
        @NullAndEmptySource
        void 이름이_비어있다면_등록할_수_없다(String value) {
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Product(UUID.randomUUID(), value, 1000));
        }

        @DisplayName("가격이 0원 미만이라면 등록할 수 없다")
        @Test
        void 가격이_0원_미만이라면_등록할_수_없다() {
            assertThatIllegalArgumentException()
                .isThrownBy(() -> new Product(UUID.randomUUID(), "test", -1));
        }

    }

    @Nested
    @DisplayName("변경할 가격이")
    class 변경할_가격이 {

        @DisplayName("0원 미만이라면 변경 불가능하다.")
        @ParameterizedTest(name = "{0} 인 경우")
        @ValueSource(longs = {-1, -10})
        void 빵원_미만이라면_변경_불가능하다(long value) {
            final Product product = new Product(UUID.randomUUID(), "test", 1000);

            assertThatIllegalArgumentException()
                .isThrownBy(() -> product.changePrice(new Money(value)));
        }

        @DisplayName("0원 이상이라면 변경 가능하다.")
        @ParameterizedTest(name = "{0} 인 경우")
        @ValueSource(longs = {0, 1})
        void 빵원_이상이라면_변경_가능하다(long value) {
            final Product product = new Product(UUID.randomUUID(), "test", 1000);

            assertDoesNotThrow(() -> product.changePrice(new Money(value)));
        }
    }
}