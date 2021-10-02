package edu.pkch.pkch.testlearn.simpleobject;

import org.javaunit.autoparams.AutoSource;
import org.junit.jupiter.params.ParameterizedTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

class SimpleObjectAutoParamsTest {

    @ParameterizedTest
    @AutoSource
    void stringAutoParams(String x) {
        System.out.println(x);
    }

    @ParameterizedTest
    @AutoSource
    void uuidAutoParams(UUID x) {
        System.out.println(x);
    }

    @ParameterizedTest
    @AutoSource
    void bigIntegerAutoParams(BigInteger x) {
        System.out.println(x);
    }

    @ParameterizedTest
    @AutoSource
    void bigDecimalAutoParams(BigDecimal x) {
        System.out.println(x);
    }
}
