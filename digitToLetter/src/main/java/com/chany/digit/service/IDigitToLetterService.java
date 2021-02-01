package com.chany.digit.service;

import java.util.List;

/**
 * @author chany
 */
public interface IDigitToLetterService {
    /**
     * input digit strings
     * @param digits
     * @return
     */
    List<String> getLetters(String digits);
}
