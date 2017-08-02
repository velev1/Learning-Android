package com.example.velev.dexterlab.enums;

/**
 * Created by velev on 1.8.2017 г..
 */

public enum MessageProvider {

    SHORT_STRING {
        @Override
        public String getLengthErrorMessage(int expected, int actual) {

            String msg = "The string length must be > or = to" +
                    expected + " but it was " + actual;
            return msg;
        }
    },
    LONG_STRING {
        public String getLengthErrorMessage(int expected, int actual) {

            String msg = "The string length must be < or = to" +
                    expected + " but it was " + actual;
            return msg;
        }
    },

    INVALID_SYMBOL {
        @Override
        public String getInvalidSymbolMessage(String invalidSymbols) {
            String msg = "The String cannot contain " + invalidSymbols;

            return msg;
        }
    };

    public String getInvalidSymbolMessage(String input) {
        return "";
    }

    public String getLengthErrorMessage(int expected, int actual) {
        return "";
    }
}
