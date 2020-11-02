package com.maryann.calculator.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteOfExtraCharactersTest {

    @Test
    public void cleanSpaces() {
        Assert.assertEquals("5+5.0-3.0", DeleteOfExtraCharacters.cleanSpaces("   5  +5.0  -  3.0    "));
        Assert.assertEquals("9yroot3+57.0", DeleteOfExtraCharacters.cleanSpaces("9yroot    3+57  .0  "));
        Assert.assertEquals("sin45.0+cos45.0", DeleteOfExtraCharacters.cleanSpaces("s   i  n 45  .0  + c   o  s 45.0"));
    }
}