package com.ticketexchange.product.domain.vo;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import com.ticketexchange.product.domain.Quantity;

class QuantityTest {

    @Test
    void 음수가_아닌_경우_정상적으로_생성된다() {
        assertAll(() -> {
            assertThatCode(() -> new Quantity(0)).doesNotThrowAnyException();
            assertThatCode(() -> new Quantity(1)).doesNotThrowAnyException();
        });
    }

    @Test
    void 음수의_수량은_생성되지_않는다() {
        assertThatThrownBy(() -> new Quantity(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
