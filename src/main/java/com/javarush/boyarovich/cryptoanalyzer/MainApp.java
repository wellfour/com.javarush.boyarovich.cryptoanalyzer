package com.javarush.boyarovich.cryptoanalyzer;

import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static void main(String[] args) {
        Cipher cipher = new Cipher(ALPHABET);
        FileManager fileManager = new FileManager();
        try {
            menu(cipher, fileManager);
        } finally {
            scanner.close();
        }
    }

    private static void menu(Cipher cipher, FileManager fileManager) {
        while (true) {
            System.out.println("Введите цифру для выбора действия:");
            System.out.println("1. Шифрование");
            System.out.println("2. Расшифровка с ключом");
            System.out.println("3. Brute force");
            System.out.println("4. Статистический анализ");
            System.out.println("0. Выход");
            System.out.print("> ");

            String line = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Введите цифру!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Введите ключ");
                    break;
                case 2:
                    System.out.println("Расшифровка с ключом (реализуйте логику)");
                    break;
                case 3:
                    System.out.println("Brute force (реализуйте логику)");
                    break;
                case 4:
                    System.out.println("Статистический анализ (реализуйте логику)");
                    break;
                case 0:
                    System.out.println("Вы вышли из приложения");
                    return;
                default:
                    System.out.println("Введите цифру из списка!");
            }
        }
    }
}
