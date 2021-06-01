package edu.pkch.testlearn.primitive;

import org.javaunit.autoparams.AutoSource;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * int 값을 자동으로 파라미터로 주입해준다.
 *
 */
class IntegerAutoParamsTest {

    @ParameterizedTest
    @AutoSource
    void sum(int x, int y) {
        // given & when
        int actual = Integer.sum(x, y);
        System.out.println(String.format("x: %d / y: %d", x, y));

        // then
        assertThat(actual).isEqualTo(x + y);
    }
}
