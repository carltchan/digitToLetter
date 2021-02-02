package com.chany.digit.service;

import java.util.List;

/**
 * Interface for converting digits to letters
 * @author chany
 */
public interface IDigitToLetterService {
    /**
     * Method for converting digits to letters
     * @param digits
     * @return list of letters
     */
    List<String> getLetters(String digits);
}
