package com.javarush.boyarovich.cryptoanalyzer;

public class Cipher {

    private char[] alphabet;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean detect = false;

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (chars[i] == alphabet[j]) {
                    int index = (j + shift) % alphabet.length;
                    if (index < 0) {
                        index += alphabet.length;
                    }
                    sb.append(alphabet[index]);
                    detect = true;
                    break;
                }
            }
            if (!detect) {
                sb.append(chars[i]);
            }
            detect = false;
        }
        return sb.toString();
    }

    public String decrypt(String encryptedText, int shift) {
        char[] chars = encryptedText.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean detect = false;

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (chars[i] == alphabet[j]) {
                    int index = (j - shift) % alphabet.length;
                    if (index < 0) {
                        index += alphabet.length;
                    }
                    sb.append(alphabet[index]);
                    detect = true;
                    break;
                }
            }
            if (!detect) {
                sb.append(chars[i]);
            }
            detect = false;
        }
        return sb.toString();
    }
}
