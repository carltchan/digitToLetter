package com.chany.digit.service;

import java.util.*;

/**
 *
 * @author chany
 * @
 */
public class DigitToLetterService implements IDigitToLetterService {

    /**
     * 数字和字母映射
     */
    public  Map<String,String> digitLetterMapper= new HashMap() ;

    /**
     * 输入包含字母组合集
     */
    private  List<String> inArr = new ArrayList<String>();

    /**
     * 输出组合集
     */
    public  List<String> outArr = new ArrayList<String>();


    public DigitToLetterService() {
        this.digitLetterMapper.put("1", "1");
        this.digitLetterMapper.put("2", "A_B_C");
        this.digitLetterMapper.put("3", "D_E_F");
        this.digitLetterMapper.put("4", "G_H_I");
        this.digitLetterMapper.put("5", "J_K_L");
        this.digitLetterMapper.put("6", "M_N_O");
        this.digitLetterMapper.put("7", "P_Q_R_S");
        this.digitLetterMapper.put("8", "T_U_V");
        this.digitLetterMapper.put("9", "W_X_Y_Z");
        this.digitLetterMapper.put("0", "0");
        this.digitLetterMapper.put("*", "*");
        this.digitLetterMapper.put("#", "#");
    }

    /**
     * 获取字母组合
     * @return
     */
    public List<String> getLetters(String digits){
        String str = "";
        String[] digits_in = digits.split(",");

        //提取数字对于的数组
        if (digits_in.length > 0) {
            for (int i = 0; i < digits_in.length; i++) {
                String letters = this.digitLetterMapper.get(digits_in[i]);
                if (null != letters && letters.length() > 0) {
                    inArr.add(letters);
                }
            }
        }

        List<String> arr = new ArrayList<String>();
        if (inArr.size() > 0) {
            //处理字母组合
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
     * 组合选择（从列表中选择n个组合）
     * @param dataList 待选列表
     * @param n 选择个数
     */
    public void combinationSelect(Object[] dataList, int n) {
        combinationSelect(dataList, 0, new Object[n], 0);
    }
    /**
     * 组合选择
     * @param dataList 待选列表
     * @param dataIndex 待选开始索引
     * @param resultList 前面（resultIndex-1）个的组合结果
     * @param resultIndex 选择索引，从0开始
     */
    public void combinationSelect(Object[] dataList, int dataIndex, Object[] resultList, int resultIndex) {
        int resultLen = resultList.length;
        int resultCount = resultIndex + 1;
        // 全部选择完时，输出组合结果
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

        // 递归选择下一个
        for (int i = dataIndex; i < dataList.length + resultCount - resultLen; i++) {
            resultList[resultIndex] = dataList[i];
            combinationSelect(dataList, i + 1, resultList, resultIndex + 1);
        }
    }



}
