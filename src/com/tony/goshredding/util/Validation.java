/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tony.goshredding.util;

/**
 *
 * @author huwei
 */
public class Validation {

    public static boolean isPresent(String inputStr) {
        // Presence check to determine if the input string is empty // Parameter: Takes a String as input
        // Returns: Outputs a Boolean, false if empty, true otherwise
        boolean valid = true;
        if (inputStr.equals("")) {
            valid = false;
        }
        return valid;
    }

    public static boolean isReal(String inputStr) {
        //Real Type check to determine if the input string is a real number // Parameter: Takes a String as input
        // Returns: Outputs a Boolean, true if a real number, false otherwise
        boolean valid = true;
        try {
            Float.parseFloat(inputStr);
            valid = true;
        } catch (NumberFormatException e) {
            valid = false;
        }
        return valid;
    }

    public static boolean isDate(String inputStr) {
        //Date Format check to determine if the correct dd/mm/yyyy is used
        // Parameter: Takes a String as input
        // Returns: Outputs a Boolean, true if correct format, false otherwise
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
            }
            else if (intDateArray[1] == 4 || intDateArray[1] == 6 || intDateArray[1] == 9
                    || intDateArray[1] == 11) {
                if (intDateArray[0] > 30 || intDateArray[0] < 1) {
                    valid = false;
                }
            }
            else if (intDateArray[1] == 1 || intDateArray[1] == 3 || intDateArray[1] == 5
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

    public static boolean isRange(int input, int lower, int upper) {
        boolean valid = true;
        if (input >= lower && input <= upper) {
            valid = true;

        } else {
            valid = false;
        }
        return valid;
    }

    public static boolean isLength(String inputStr, int length) {
        boolean valid = true;
        if (inputStr.length() != length) {
            valid = false;
        }
        return valid;
    }

    public static boolean isDoubleVerification(String inputStr1, String inputStr2) {
        boolean valid = true;
        if (!inputStr1.equals(inputStr2)) {
            valid = false;
        }
        return valid;
    }
}
