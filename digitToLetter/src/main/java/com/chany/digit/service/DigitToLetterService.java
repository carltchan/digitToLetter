package com.chany.digit.service;

import java.util.*;

/**
 *Service for converting digits to letters
 * @author chany
 */
public class DigitToLetterService implements IDigitToLetterService {

    /**
     * digit and letter mapping
     */
    public  Map<String,String> digitLetterMapper= new HashMap() ;

    /**
     * The input contains a list of letter
     */
    private  List<String> inArr = new ArrayList<String>();

    /**
     * The output contains a list of letter
     */
    public  List<String> outArr = new ArrayList<String>();


    /**
     *Method of construction
     */
    public DigitToLetterService() {
        // initialize the mapping of digit and letter mapping
        this.digitLetterMapper.put("1", "");
        this.digitLetterMapper.put("2", "A_B_C");
        this.digitLetterMapper.put("3", "D_E_F");
        this.digitLetterMapper.put("4", "G_H_I");
        this.digitLetterMapper.put("5", "J_K_L");
        this.digitLetterMapper.put("6", "M_N_O");
        this.digitLetterMapper.put("7", "P_Q_R_S");
        this.digitLetterMapper.put("8", "T_U_V");
        this.digitLetterMapper.put("9", "W_X_Y_Z");
        this.digitLetterMapper.put("0", "");
        this.digitLetterMapper.put("*", "");
        this.digitLetterMapper.put("#", "");
    }

    /**
     * Gets the letter contained in the input numeric map
     * @param digits
     */
    public void getInputLetters(String digits){
        String[] digits_in = digits.split(",");
        //Extract the array corresponding to the number
        if (digits_in.length > 0) {
            for (int i = 0; i < digits_in.length; i++) {
                String tmpDigits;
                //If it is larger than one digit, it will be broken down to one digit
                if (digits_in[i].length() > 1) {
                    tmpDigits = digits_in[i].substring(0, 1);
                    String lastStr = digits_in[i].substring(1);
                    getInputLetters(lastStr);
                } else {
                    tmpDigits = digits_in[i];
                }
                String letters = this.digitLetterMapper.get(tmpDigits);
                if (null != letters && letters.length() > 0) {
                    inArr.add(letters);
                }
            }
        }
    }


    /**
     *Method for converting digits to letters
     * @param digits
     * @return list of letters
     */
    @Override
    public List<String> getLetters(String digits){
        //Gets the letter contained in the input numeric map
        getInputLetters(digits);
        List<String> arr = new ArrayList<String>();
        if (inArr.size() > 0) {
            //Dealing with letter combinations
            for (int i = 0; i < inArr.size(); i++) {
                String letters = inArr.get(i);
                String[] letterSpl = letters.split("_");
                if(letterSpl.length>0){
                    for (int j = 0; j < letterSpl.length; j++) {
                        arr.add(letterSpl[j]);
                    }
                }
            }
        }
        Object[] letterArr = arr.toArray();
        combinationSelect(letterArr, inArr.size());
        return this.outArr;
    }

    /**
     * Combination selection (select n combinations from the list)
     * @param dataList Waiting list
     * @param n Number of choices
     */
    public void combinationSelect(Object[] dataList, int n) {
        combinationSelect(dataList, 0, new Object[n], 0);
    }
    /**
     * Combination selection
     * @param dataList Waiting list
     * @param dataIndex Start index to be selected
     * @param resultList Combined results of the previous (resultindex-1)
     * @param resultIndex Select index, starting from 0
     */
    public void combinationSelect(Object[] dataList, int dataIndex, Object[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        //When all are selected, the combination result will be output
        if (resultCount > resultLen) {
            List list = Arrays.asList(resultList);
            boolean flag = true;
            String  outStr = "";
            for (int i = 0; i < list.size(); i++) {
                String letterStr = inArr.get(i);
                if(letterStr.indexOf(list.get(i).toString())<0){
                    flag = false;
                }
                outStr += list.get(i);
            }
            if(flag){
                outArr.add(outStr.toLowerCase());
            }
            return;
        }

        //Recursively select next
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
        }
    }



}
