package ru.university.lab2.strings;

public class StringLab {

    public void run() {
        Palindrome();
        Reverse_Words();
        Count_Characters();
        Caesar();
        Longest_Word();
    }

    private void Palindrome() {
        String[] tests = {
                "А роза упала на лапу Азора",
                "Madam, I'm Adam",
                "Привет, мир!",
                "12321",
                "12345"
        };

        for (String test : tests) {
            System.out.println("\"" + test + "\" -> " + isPalindrome(test));
        }
        System.out.println();
    }

    private boolean isPalindrome(String text) {
        char[] chars = text.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(chars[left])) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(chars[right])) {
                right--;
            }

            char l = Character.toLowerCase(chars[left]);
            char r = Character.toLowerCase(chars[right]);

            if (l != r) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    private void Reverse_Words() {
        String sentence = "кот съел мышь";
        System.out.println("до:    " + sentence);
        System.out.println("после: " + reverseWords(sentence));
        System.out.println();
    }

    private String reverseWords(String sentence) {
        String trimmed = sentence.trim();
        if (trimmed.isEmpty()) {
            return "";
        }

        String[] words = trimmed.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) {
                sb.append(' ');
            }
        }

        return sb.toString();
    }

    private void Count_Characters() {
        String text = "Hello, мир! 123";
        int[] counts = countCharacters(text);

        System.out.println("Текст: " + text);
        System.out.println("Гласные:   " + counts[0]);
        System.out.println("Согласные: " + counts[1]);
        System.out.println("Цифры:     " + counts[2]);
        System.out.println("Пробелы:   " + counts[3]);
        System.out.println();
    }

    private int[] countCharacters(String text) {
        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int spaces = 0;

        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isWhitespace(c)) {
                spaces++;
            } else if (isVowel(c)) {
                vowels++;
            } else if (isConsonant(c)) {
                consonants++;
            }
        }

        return new int[]{vowels, consonants, digits, spaces};
    }

    private boolean isVowel(char c) {
        char lower = Character.toLowerCase(c);
        return "aeiouyаеёиоуыэюя".indexOf(lower) >= 0;
    }

    private boolean isConsonant(char c) {
        char lower = Character.toLowerCase(c);
        return "bcdfghjklmnpqrstvwxyzбвгджзйклмнпрстфхцчшщ".indexOf(lower) >= 0;
    }

    private void Caesar() {
        String original = "Hello, World!";
        int shift = 3;

        String encrypted = caesarEncrypt(original, shift);
        String decrypted = caesarDecrypt(encrypted, shift);

        System.out.println("Оригинал:    " + original);
        System.out.println("Шифр:        " + encrypted);
        System.out.println("Расшифровка: " + decrypted);
        System.out.println();
    }

    private String caesarEncrypt(String text, int shift) {
        return caesarShift(text, shift);
    }

    private String caesarDecrypt(String text, int shift) {
        return caesarShift(text, -shift);
    }

    private String caesarShift(String text, int shift) {
        StringBuilder sb = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                sb.append((char) ('A' + normalizeShift(c - 'A' + shift)));
            } else if (c >= 'a' && c <= 'z') {
                sb.append((char) ('a' + normalizeShift(c - 'a' + shift)));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private int normalizeShift(int value) {
        return ((value % 26) + 26) % 26;
    }

    private void Longest_Word() {
        String text = "Java — это мощный язык программирования";
        System.out.println("Текст: " + text);
        System.out.println("Самое длинное слово: " + findLongestWord(text));
        System.out.println();
    }

    private String findLongestWord(String text) {
        char[] chars = text.toCharArray();
        String longest = "";
        StringBuilder current = new StringBuilder();

        for (char c : chars) {
            if (Character.isLetter(c)) {
                current.append(c);
            } else {
                if (current.length() > longest.length()) {
                    longest = current.toString();
                }
                current.setLength(0);
            }
        }

        if (current.length() > longest.length()) {
            longest = current.toString();
        }

        return longest;
    }
}
