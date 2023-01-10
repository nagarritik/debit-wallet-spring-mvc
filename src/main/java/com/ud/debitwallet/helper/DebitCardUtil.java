package com.ud.debitwallet.helper;

import java.util.Random;

public class DebitCardUtil {
    private static Random random = new Random();

    public static String generateRandomDebitCardNumber(){
        String debitCardNumber = "6";

        for (int i=0;i<14;i++){
            debitCardNumber+=random.nextInt(0,9);
        }

        return debitCardNumber;
    }

    public static long generateExpirationMonth(){
        return random.nextInt(1,12);
    }

    public static long generateExpirationYear(){
        return random.nextInt(2030,2050);
    }
}
