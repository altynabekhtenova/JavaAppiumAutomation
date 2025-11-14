package org.example;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{
    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Введенное значение != 14", 14, getLocalNumber());
    }

}
