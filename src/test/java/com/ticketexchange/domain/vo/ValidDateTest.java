package com.ticketexchange.domain.vo;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

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
}
