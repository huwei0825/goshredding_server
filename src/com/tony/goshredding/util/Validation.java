package com.tony.goshredding.util;

/**
 * This is the validation class.
 *
 * @author Songyun hu.
 */
public class Validation {
    /**
     * check to determine if the input string is empty
     * @param inputStr the input string
     * @return false if empty, true otherwise
     */
    public static boolean isPresent(String inputStr) {
        boolean valid = true;
        if (inputStr.equals("")) {
            valid = false;
        }
        return valid;
    }
    /**
     * check to determine if the input string is a real number
     * @param inputStr the input string.
     * @return  true if a real number, false otherwise
     */
    public static boolean isReal(String inputStr) {
        boolean valid = false;
        try {
            Float.parseFloat(inputStr);
            valid = true;
        } catch (Exception e) {
            valid = false;
        }
        return valid;
    }
    /**
     * check to determine if the correct dd/mm/yyyy is used
     * @param inputStr the input string date.
     * @return  true if correct format, false otherwise
     */
    public static boolean isDate(String inputStr) {
        boolean valid = true;
        String[] dateArray;
        int[] intDateArray = new int[3];
        if (inputStr.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
            dateArray = inputStr.split("/");
            for (int i = 0; i < 3; i++) {
                intDateArray[i] = Integer.parseInt(dateArray[i]);
            }
            if (intDateArray[1] == 2) {
                if (intDateArray[0] > 29 || intDateArray[0] < 1) {
                    valid = false;
                }
            } else if (intDateArray[1] == 4 || intDateArray[1] == 6 || intDateArray[1] == 9
                    || intDateArray[1] == 11) {
                if (intDateArray[0] > 30 || intDateArray[0] < 1) {
                    valid = false;
                }
            } else if (intDateArray[1] == 1 || intDateArray[1] == 3 || intDateArray[1] == 5
                    || intDateArray[1] == 7 || intDateArray[1] == 8 || intDateArray[1] == 10 || intDateArray[1] == 12) {
                if (intDateArray[0] > 31 || intDateArray[0] < 1) {
                    valid = false;
                }
            } else {
                valid = false;
            }
        } else {
            valid = false;
        }
        return valid;
    }
    /**
     * check if the input is between the lower and upper.
     * @param input the input number.
     * @param lower the lower number.
     * @param upper the upper number.
     * @return true if yes.false otherwise
     */
    public static boolean isRange(int input, int lower, int upper) {
        boolean valid = true;
        if (input >= lower && input <= upper) {
            valid = true;

        } else {
            valid = false;
        }
        return valid;
    }
    /**
     * check if the input string is the input length.
     * @param inputStr the input string
     * @param length the input length.
     * @return true if yes,false otherwise
     */
    public static boolean isLength(String inputStr, int length) {
        boolean valid = true;
        if (inputStr.length() != length) {
            valid = false;
        }
        return valid;
    }
    /**
     * check if the two input string is same.
     * @param inputStr1 the first string
     * @param inputStr2 the second string
     * @return true if yes,false otherwise
     */
    public static boolean isDoubleVerification(String inputStr1, String inputStr2) {
        boolean valid = true;
        if (!inputStr1.equals(inputStr2)) {
            valid = false;
        }
        return valid;
    }
}
