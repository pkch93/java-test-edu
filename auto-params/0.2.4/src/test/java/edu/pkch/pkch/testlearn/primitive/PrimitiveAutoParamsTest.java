package edu.pkch.pkch.testlearn.primitive;

import org.javaunit.autoparams.AutoSource;
import org.javaunit.autoparams.Fixed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static org.assertj.core.api.Assertions.assertThat;

class PrimitiveAutoParamsTest {

    @ParameterizedTest
    @AutoSource
    void integerAutoParams(int x, int y) {
        // given & when
        int actual = Integer.sum(x, y);
        System.out.println(String.format("x: %d / y: %d", x, y));

        // then
        assertThat(actual).isEqualTo(x + y);
    }

    /**
     * int 타입과 {@link java.lang.Integer}은
     * {@link javax.validation.constraints.Max}와 {@link javax.validation.constraints.Min}로 최대/최소 값 설정이 가능하다.
     *
     * @see <a href="https://github.com/JavaUnit/AutoParams#setting-the-range-of-values">Setting the range of values 참고</a>
     */
    @ParameterizedTest
    @AutoSource
    void integerAutoParamsWithMinMax(@Min(1) @Max(10) int x) {
        assertThat(x >= 1).isTrue();
        assertThat(x <= 10).isTrue();

        System.out.println(String.format("x: %d", x));
    }

    @ParameterizedTest
    @AutoSource
    void longAutoParams(long x, long y) {
        // given & when
        long actual = Long.sum(x, y);
        System.out.println(String.format("x: %d / y: %d", x, y));

        // then
        assertThat(actual).isEqualTo(x + y);
    }

    @ParameterizedTest
    @AutoSource
    void floatAutoParams(float x, float y) {
        // given & when
        float actual = Float.sum(x, y);
        System.out.println(String.format("x: %f / y: %f", x, y));

        // then
        assertThat(actual).isEqualTo(x + y);
    }

    @ParameterizedTest
    @AutoSource
    void doubleAutoParams(double x, double y) {
        // given & when
        double actual = Double.sum(x, y);
        System.out.println(String.format("x: %f / y: %f", x, y));

        // then
        assertThat(actual).isEqualTo(x + y);
    }

    @ParameterizedTest
    @AutoSource
    void charAutoParams(char x) {
        System.out.println(String.format("x: %c", x));
    }

    @ParameterizedTest
    @AutoSource
    @DisplayName("boolean 값을 자동 생성")
    void booleanAutoParams(boolean x, boolean y) {
        System.out.println(
                String.format("x: %s, y: %s", x, y)
        );
    }

    /**
     * {@link org.javaunit.autoparams.Fixed}가 붙은 파라미터부터 그 이후에 나타나는 파라미터의 값을 고정시킨다.
     */
    @ParameterizedTest
    @AutoSource
    void integerAutoParamsWithFixed(@Fixed int x, int y, int z) {
        // given & when
        System.out.println(String.format("x: %d / y: %d / z: %d", x, y, z));

        // then
        assertThat(x).isEqualTo(y);
        assertThat(y).isEqualTo(z);
        assertThat(z).isEqualTo(x);
    }

    @ParameterizedTest
    @AutoSource
    void integerAutoParamsWithSecondFixed(int x, @Fixed int y, int z) {
        // given & when
        System.out.println(String.format("x: %d / y: %d / z: %d", x, y, z));

        // then
        assertThat(x).isNotEqualTo(y);
        assertThat(y).isEqualTo(z);
        assertThat(z).isNotEqualTo(x);
    }

    /**
     * {@link org.javaunit.autoparams.Fixed}가 붙은 타입과 다른 타입이 나타나면 값을 고정시키지 않는다.
     *
     */
    @ParameterizedTest
    @AutoSource
    void integerAutoParamsWithFixedAndOtherType(@Fixed int x, int y, boolean a, boolean b) {
        // given & when
        System.out.println(String.format("x: %d / y: %d / a: %s / b: %s", x, y, a, b));

        // then
        assertThat(x).isEqualTo(y);
    }
}
