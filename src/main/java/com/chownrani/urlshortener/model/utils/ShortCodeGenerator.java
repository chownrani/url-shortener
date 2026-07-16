package com.chownrani.urlshortener.model.utils;

public class ShortCodeGenerator {

    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int SHORT_CODE_SIZE = 6;
    
    public static String createShortCode(Long urlSeq) {
        if (urlSeq == null) {
            throw new IllegalArgumentException("urlSeq must not be null");
        }
        if (urlSeq < 0) {
            throw new IllegalArgumentException("urlSeq must be greater than or equal to zero");
        }

        StringBuilder code = new StringBuilder();
        long value = urlSeq;

        do {
            int index = (int) (value % 62);
            code.append(BASE62.charAt(index));
            value /= 62;
        } while (value > 0);

        if (code.length() > SHORT_CODE_SIZE) {
            throw new IllegalArgumentException("urlSeq is too large to fit in 6 base62 characters");
        }

        while (code.length() < SHORT_CODE_SIZE) {
            code.append('0');
        }

        return code.reverse().toString();
    }
}
