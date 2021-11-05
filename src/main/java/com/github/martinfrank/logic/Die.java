package com.github.martinfrank.logic;

import java.util.Locale;
import java.util.Random;

public class Die {

    private static final String INPUT_VALIDATION_PATTERN = "^\\d*D\\d*(\\+\\d+)?$";
    private static final String SINGLE_DIE_PATTERN = "\\d*D\\d*";
    private static final String ADDITION_SEPARATOR_PATTERN = "\\+";
    private static final String DIE_SEPARATOR_PATTERN = "D";
    private static final String ADD_PATTERN = "\\d*";
    private static final String WHITESPACE_PATTERN = "\\s+";

    //package visible for testing
    final int amount;
    final int sides;
    final int addition;

    public Die(String input) {
        String adjusted = input.toUpperCase(Locale.ROOT).replaceAll(WHITESPACE_PATTERN,"");
        if (!adjusted.matches(INPUT_VALIDATION_PATTERN)) {
            throw new IllegalArgumentException("invalid dice input pattern: "+adjusted+" - does not match "+INPUT_VALIDATION_PATTERN);
        }

        String[] parts = input.split(ADDITION_SEPARATOR_PATTERN);
        if(parts[0].matches(SINGLE_DIE_PATTERN)){
            String dicePart = parts[0];
            int dIndex = dicePart.indexOf(DIE_SEPARATOR_PATTERN);
            if(dIndex == 0){
                amount = 1;
            }else{
                amount = Integer.parseInt(dicePart.substring(0,dIndex));
            }
            sides = Integer.parseInt(dicePart.substring(dIndex+1));
        }else{
            amount = 0;
            sides = 0;
        }

        if(parts[0].matches(ADD_PATTERN)){
            addition = Integer.parseInt(parts[0]);
        }else if(parts.length == 2 && parts[1].matches(ADD_PATTERN)){
            addition = Integer.parseInt(parts[1]);
        }else{
            addition = 0;
        }
    }

    public int result(long seed){
        Random random = new Random();
        int result = 0;
        for(int roll = 0; roll < amount; roll++){
            result = result + random.nextInt(sides)+1;
        }
        result = result + addition;
        return result;
    }

    public int result(){
        return result(0);
    }

    public int min(){
        return amount + addition;
    }

    public int max(){
        return amount * sides + addition;
    }

    @Override
    public String toString() {
        return ""+amount+"D"+sides+"+"+addition;
    }
}
