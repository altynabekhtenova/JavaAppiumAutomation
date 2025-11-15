package org.example;

import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetClassString() {
        Assert.assertTrue("Фраза не содержит \"Hello\" или \"hello\"",
                getClassString().contains("Hello") || getClassString().contains("hello"));
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Число меньше или равно 45", getClassNumber() > 45);
    }

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Введенное значение != 14", 14, getLocalNumber());
    }

}
