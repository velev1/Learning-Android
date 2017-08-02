package com.example.velev.dexterlab;

import com.example.velev.dexterlab.enums.MessageProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by velev on 1.8.2017 г..
 */

public final class Validator {

    public void isStringValidLength(String input, int minLength, int maxLength){
        if(input.length() < minLength) {
            String msg = MessageProvider.SHORT_STRING.getLengthErrorMessage(minLength, input.length());
            throw new IndexOutOfBoundsException(msg);
        }

        if(input.length() > maxLength) {
            String msg = MessageProvider.LONG_STRING.getLengthErrorMessage(maxLength, input.length());
            throw new IndexOutOfBoundsException(msg);
        }
    }

    public void isSymbolsValid(String input){
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(input);

        boolean isMatch  = matcher.find();
        if(isMatch) {

            String invalidSymbols = "[~#@*+%{}<>\\[\\]|\"\\_^]";
            String msg = MessageProvider.INVALID_SYMBOL.getInvalidSymbolMessage(invalidSymbols);
            throw new IllegalArgumentException(msg);
        }
    }
}
