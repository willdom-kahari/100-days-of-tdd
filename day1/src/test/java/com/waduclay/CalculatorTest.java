package com.waduclay;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class CalculatorTest {
    @Test
    public void shouldAddTwoNumbers(){
        int result = Calculator.add(1, 2);
        assertEquals(3, result);
    }
}
