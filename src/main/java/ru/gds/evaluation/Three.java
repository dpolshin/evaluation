package ru.gds.evaluation;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Write a Java method that takes a String of text and inserts newline characters ('\n')
 * so that each line of text in the returned String has at most maxCharsPerLine characters (not counting the newline).
 * It may also be necessary to delete some spaces.
 * <p>
 * The method should obey the following rules:
 * <p>
 * Words should never be broken up, unless a word has more than maxCharsPerLine characters in it.
 * A word is any sequence of characters that contains no spaces and no newlines.
 * Existing line breaks should be preserved.
 * Lines should not be made shorter than necessary. In other words, if an additional word would fit on a line it should go on that line.
 * Words should be separated by either a single space or by one or more newlines.
 * Lines cannot start or end with spaces.
 * <p>
 * Assume that the text does not contain any other whitespace characters besides spaces and newlines.
 * <p>
 * The signature for your method should be:
 * <p>
 * public static String wrapText(String text, int maxCharsPerLine)
 */
public class Three {

    public static void main(String[] args) {

        String text =
                "/**\n" +
                        "* Write a Java method that takes a String of text and inserts newline characters \n" +
                        "* so that each line of text in the returned String has at most maxCharsPerLine characters (not counting the newline).\n" +
                        "* It may also be necessary to delete some spaces.\n" +
                        "* <p>\n" +
                        "* The method should obey the following rules:\n" +
                        "* <p>\n" +
                        "* Words should never be broken up, unless a word has more than maxCharsPerLine characters in it.\n" +
                        "* A word is any sequence of characters that contains no spaces and no newlines.\n" +
                        "* Existing line breaks should be preserved.\n" +
                        "* Lines should not be made shorter than necessary. In other words, if an additional word would fit on a line it should go on that line.\n" +
                        "* Words should be separated by either a single space or by one or more newlines.\n" +
                        "* Lines cannot start or end with spaces.\n" +
                        "* <p>\n" +
                        "* Assume that the text does not contain any other whitespace characters besides spaces and newlines.\n" +
                        "* <p>\n" +
                        "* The signature for your method should be:\n" +
                        "* <p>\n" +
                        "* public static String wrapText(String text, int maxCharsPerLine)\n" +
                        "*/";


        String text2 = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" + "\n" +
                "wwwwwwwwwwwwwwwwwwwwwwwyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy";


        int maxLength = 10;
        System.out.println(wrapText(text, maxLength));
    }


    /**
     * Format text.
     *
     * @param text      source
     * @param maxLength per line
     * @return formatted text
     */
    public static String wrapText(String text, int maxLength) {

        Tokenizer tokens = new Tokenizer(text);
        StringBuilder b = new StringBuilder();

        int currentLength = 0;

        while (tokens.hasNext()) {
            String word = tokens.next();
            int wordLength = word.length();


            if (wordLength > maxLength) {
                if (currentLength != 0) {
                    b.append("\n");
                }

                String[] parts = word.split("(?<=\\G.{" + maxLength + "})");

                for (String s : parts) {
                    b.append(s).append("\n");
                }
                currentLength = 0;

            } else if (currentLength + wordLength < maxLength) {
                b.append(word).append(" ");
                currentLength += wordLength;

            } else if (currentLength == maxLength) {
                b.append("\n");
                currentLength = 0;

            } else if (currentLength + wordLength > maxLength) {
                b.append("\n");
                b.append(word).append(" ");
                currentLength = wordLength;
            }
        }

        return b.toString();
    }


    /**
     * Get next word or line break from source text.
     */
    static class Tokenizer implements Iterator<String> {
        private static final String BREAK = "\n";
        private int row = 0;
        private int col = 0;
        private List<String[]> text;

        public Tokenizer(String source) {
            String[] lines = source.split("\\n");
            text = Stream.of(lines)
                    .map(s -> s.split("\\s+"))
                    .collect(Collectors.toList());
        }

        @Override
        public boolean hasNext() {
            return row < text.size() - 1 || (row == text.size() - 1 && col < text.get(row).length);
        }

        @Override
        public String next() {
            if (text.get(row).length == col) {
                col = 0;
                row++;
                return BREAK;
            } else if (text.get(row).length > col) {
                return text.get(row)[col++];
            }
            return null;
        }
    }

}
