package com.ticketexchange.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.ticketlounge.domain.product.ValidDate;

class ValidDateTest {

    @Test
    void 시작일보다_종료일이_짧은경우_예외를_던진다() {
        final var 시작일 = LocalDate.of(2023, 1, 1);
        final var 종료일 = LocalDate.of(2022, 12, 31);

        assertThatThrownBy(() -> new ValidDate(시작일, 종료일))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 시작일보다_종료일이_같은경우_성공적으로_생성된다() {
        final var 시작일 = LocalDate.of(2023, 1, 1);
        final var 종료일 = LocalDate.of(2023, 1, 1);

        assertThatCode(() -> new ValidDate(시작일, 종료일)).doesNotThrowAnyException();
    }

    @Test
    void 시작일보다_종료일이_긴경우_성공적으로_생성된다() {
        final var 시작일 = LocalDate.of(2023, 1, 1);
        final var 종료일 = LocalDate.of(2023, 1, 2);

        assertThatCode(() -> new ValidDate(시작일, 종료일)).doesNotThrowAnyException();
    }

    @Test
    void 시작일과_종료일이_모두_같은경우_같은_객체이다() {
        final var 시작일 = LocalDate.of(2023, 1, 1);
        final var 종료일 = LocalDate.of(2023, 1, 2);
        final ValidDate 유효_응모일 = new ValidDate(시작일, 종료일);

        assertThat(new ValidDate(시작일, 종료일)).isEqualTo(유효_응모일);
    }

    @Test
    void 시작일과_종료일이_하나라도_다른경우_다른_객체이다() {
        final var 시작일 = LocalDate.of(2023, 1, 1);
        final var 종료일 = LocalDate.of(2023, 1, 2);
        final var 다른_시작일 = LocalDate.of(2022, 1, 1);
        final var 다른_종료일 = LocalDate.of(2024, 1, 2);
        final ValidDate 유효_응모일 = new ValidDate(시작일, 종료일);

        assertAll(() -> {
            assertThat(new ValidDate(시작일, 다른_종료일)).isNotEqualTo(유효_응모일);
            assertThat(new ValidDate(다른_시작일, 종료일)).isNotEqualTo(유효_응모일);
            assertThat(new ValidDate(다른_시작일, 다른_종료일)).isNotEqualTo(유효_응모일);
        });
    }
}
