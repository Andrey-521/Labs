package ru.university.lab2.strings;

public final class StringLab {

    private StringLab() {
    }

    public static void run() {
        System.out.println("\n===== Обработка текста =====");

        String palindrome =
                "А роза упала на лапу Азора!";

        System.out.println("Палиндром: "
                + isPalindrome(palindrome));

        String sentence = "кот съел мышь";

        System.out.println("Исходная строка: " + sentence);
        System.out.println("Обратный порядок слов: "
                + reverseWords(sentence));

        String text = "Hello Мир 123";
        int[] statistics = countCharacters(text);

        System.out.println("Гласных: " + statistics[0]);
        System.out.println("Согласных: " + statistics[1]);
        System.out.println("Цифр: " + statistics[2]);
        System.out.println("Пробелов: " + statistics[3]);

        String encrypted = caesarEncrypt("Hello, World!", 3);

        System.out.println("Шифр Цезаря: " + encrypted);
        System.out.println("Расшифровка: "
                + caesarDecrypt(encrypted, 3));

        System.out.println("Самое длинное слово: "
                + findLongestWord(
                "Это предложение с самым длинным словом"));
    }

    private static boolean isPalindrome(String text) {
        String normalized = text
                .toLowerCase()
                .replaceAll("[^\\p{L}\\p{N}]", "");

        String reversed = new StringBuilder(normalized)
                .reverse()
                .toString();

        return normalized.equals(reversed);
    }

    private static String reverseWords(String sentence) {
        String[] words = sentence.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            if (result.length() > 0) {
                result.append(' ');
            }

            result.append(words[i]);
        }

        return result.toString();
    }

    private static int[] countCharacters(String text) {
        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int spaces = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = Character.toLowerCase(text.charAt(i));

            if (isVowel(ch)) {
                vowels++;
            } else if (Character.isLetter(ch)) {
                consonants++;
            } else if (Character.isDigit(ch)) {
                digits++;
            } else if (Character.isWhitespace(ch)) {
                spaces++;
            }
        }

        return new int[]{vowels, consonants, digits, spaces};
    }

    private static boolean isVowel(char ch) {
        return "аеёиоуыэюяaeiou".indexOf(ch) >= 0;
    }

    private static String caesarEncrypt(String text, int shift) {
        return caesar(text, shift);
    }

    private static String caesarDecrypt(String text, int shift) {
        return caesar(text, -shift);
    }

    private static String caesar(String text, int shift) {
        char[] chars = text.toCharArray();
        shift = Math.floorMod(shift, 26);

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (ch >= 'a' && ch <= 'z') {
                chars[i] = (char) (
                        'a' + (ch - 'a' + shift) % 26
                );
            } else if (ch >= 'A' && ch <= 'Z') {
                chars[i] = (char) (
                        'A' + (ch - 'A' + shift) % 26
                );
            }
        }

        return new String(chars);
    }

    private static String findLongestWord(String text) {
        String longest = "";

        for (String word : text.split("\\s+")) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }

        return longest;
    }
}
