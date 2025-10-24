//package com.javarush.boyarovich.cryptoanalyzer;
//
//public class BruteForce {
//
//    private char[] alphabet;
//
//    public BruteForce(char[] alphabet) {
//        this.alphabet = alphabet;
//    }
//
//    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
//        char[] chars = encryptedText.toCharArray();
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < chars.length; i++) {
//            for (int j = 0; j < alphabet.length; j++) {
//                if (chars[i] == alphabet[j]) {
//                    int index = (j - shift) % alphabet.length;
//                    if (index < 0) {
//                        index += alphabet.length;
//                    }
//                    sb.append(alphabet[index]);
//                    break;
//                }
//            }
//        }
//        return sb.toString();
//    }
//}