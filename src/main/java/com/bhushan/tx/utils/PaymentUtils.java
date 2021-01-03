package com.bhushan.tx.utils;

import com.bhushan.tx.exception.InsufficientAmountException;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {

    private static Map<String, Double> paymentMap = new HashMap<>();

    static {
        paymentMap.put("SBI_CARD", 12000.0);
        paymentMap.put("CHARTEREDBANK_CARD", 10000.0);
        paymentMap.put("RBL_CARD", 5000.0);
        paymentMap.put("KOTAK_BANK", 8000.0);
    }

    public static boolean validateCreditLimit(String accNo, double paidAmount) throws InsufficientAmountException {
        if (paidAmount > paymentMap.get(accNo)) {
            throw new InsufficientAmountException("Insufficient fund..!");
        } else {
            return true;
        }
    }
}
