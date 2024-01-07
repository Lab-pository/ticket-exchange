package com.ticketexchange.product.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import com.ticketexchange.product.domain.Probability;

class ProbabilityTest {

    @Test
    void 확률이_0과_1사이인_경우_성공적으로_생성된다() {
        assertAll(() -> {
            assertThatCode(() -> new Probability(0)).doesNotThrowAnyException();
            assertThatCode(() -> new Probability(0.5)).doesNotThrowAnyException();
            assertThatCode(() -> new Probability(1)).doesNotThrowAnyException();
        });
    }

    @Test
    void 확률이_0미만인_경우_예외를_던진다() {
        assertThatThrownBy(() -> new Probability(-0.00001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 확률이_1초과인_경우_예외를_던진다() {
        assertThatThrownBy(() -> new Probability(1.00001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 확률_값이_다른_경우_다르다() {
        final double 값 = 0.5;
        final Probability 확률 = new Probability(값);

        assertThat(new Probability(0.0)).isNotEqualTo(확률);
    }

    @Test
    void 확률_값이_같은_경우_같다() {
        final double 값 = 0.5;
        final Probability 확률 = new Probability(값);

        assertThat(new Probability(값)).isEqualTo(확률);
    }
}
