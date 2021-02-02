package com.chany.digit.service;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DigitToLetterServiceTest {

    private IDigitToLetterService digitToLetterService;

    private String inputDigits;

    @Before
    public void setup(){
        digitToLetterService = new DigitToLetterService();
        Random random = new Random();
        //Input random number
        inputDigits = random.nextInt(9)+","+random.nextInt(9);
    }

    @Test
    public void getLetters() {
        System.out.println("inupt: "+inputDigits);
        System.out.println("output: "+digitToLetterService.getLetters(inputDigits));
    }
}