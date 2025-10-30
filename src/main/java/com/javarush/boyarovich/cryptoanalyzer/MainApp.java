package com.javarush.boyarovich.cryptoanalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
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
                    encrypt(cipher, fileManager);
                    break;
                case 2:
                    decrypt(cipher, fileManager);
                    break;
                case 3:
                    decryptByBruteForce(cipher, fileManager);
                    break;
                case 4:
                    System.out.println("Не реализовано.");
                    break;
                case 0:
                    System.out.println("Вы вышли из приложения.");
                    return;
                default:
                    System.out.println("Введите цифру из списка!");
            }
        }
    }

    private static void encrypt(Cipher cipher, FileManager fileManager) {
        System.out.println("Введите ключ");
        String keyLine = scanner.nextLine().trim();
        int key;
        try {
            key = Integer.parseInt(keyLine);
        } catch (NumberFormatException e) {
            System.out.println("Введите число!");
            return;
        }

        System.out.print("Путь к входному файлу: ");
        String inPath = scanner.nextLine().trim();
        Path in = Path.of(inPath);

        if (!Files.exists(in)) {
            System.out.println("Файл не найден");
            return;
        }

        String fileName = in.getFileName().toString();
        Path parent = in.getParent();
        Path out = parent.resolve("encrypted_" + fileName);
        String outPath = out.toString();

        try {
            String result = fileManager.readFile(inPath);
            String encryptedText = cipher.encrypt(result, key);
            fileManager.writeFile(encryptedText, outPath);
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

    private static void decrypt(Cipher cipher, FileManager fileManager) {
        System.out.println("Введите ключ");
        String keyLine = scanner.nextLine().trim();
        int key;
        try {
            key = Integer.parseInt(keyLine);
        } catch (NumberFormatException e) {
            System.out.println("Введите число!");
            return;
        }

        System.out.print("Путь к входному файлу: ");
        String inPath = scanner.nextLine().trim();
        Path in = Path.of(inPath);

        if (!Files.exists(in)) {
            System.out.println("Файл не найден");
            return;
        }

        String fileName = in.getFileName().toString();
        Path parent = in.getParent();
        Path out = parent.resolve("decrypted_" + fileName);
        String outPath = out.toString();

        try {
            String result = fileManager.readFile(inPath);
            String decryptedText = cipher.decrypt(result, key);
            fileManager.writeFile(decryptedText, outPath);
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }

    private static void decryptByBruteForce(Cipher cipher, FileManager fileManager) {
        System.out.print("Путь к входному файлу: ");
        String inPath = scanner.nextLine().trim();
        Path in = Path.of(inPath);

        if (!Files.exists(in)) {
            System.out.println("Файл не найден");
            return;
        }

        Path parent = in.getParent();

        for (int i = 0; i < ALPHABET.length; i++) {
            Path out = parent.resolve("decrypted_" + i);
            String outPath = out.toString();

            try {
                String result = fileManager.readFile(inPath);
                String decryptedText = cipher.decrypt(result, i);
                fileManager.writeFile(decryptedText, outPath);
            } catch (IOException e) {
                System.out.println("Файл не найден");
            }
        }
    }
}
