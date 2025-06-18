package edu.s3rl.utils;

/**
 * Utility class for common string operations.
 */
public class StringUtils {

    /**
     * Private constructor is to prevent the instantiation of this class directly.
     * Throws {@link UnsupportedOperationException} to prevent instantiation,
     * including attempts via reflection.
     */
    private StringUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * Checks if a string is null, empty, or contains only whitespace characters.
     *
     * @param str the string to check; may be null
     * @return {@code true} if the string is null, empty, or whitespace-only; {@code false} otherwise
     */
    public static boolean isBlank(String str) {

        return str == null || str.isBlank();
    }

    /**
     * Replace all whitespaces characters (including spaces, tabs,
     * form feeds, line feeds, and other Unicode whitespace
     * characters) by " ".
     *
     * @param str the input string to be minified; may be null
     * @return a trimmed string with normalized spacing, or null if input is null
     */
    public static String minify(String str) {

        if (str == null) {
            return null;
        }

        return str.replaceAll("\\s+", " ").trim();
    }
}
