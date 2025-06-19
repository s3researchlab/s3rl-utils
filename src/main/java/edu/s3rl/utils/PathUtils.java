package edu.s3rl.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Utility class for common file operations.
 */
public class PathUtils {

    /**
     * Private constructor is to prevent the instantiation of this class directly.
     * Throws {@link UnsupportedOperationException} to prevent instantiation,
     * including attempts via reflection.
     */
    private PathUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * Checks whether the specified file or folder exists in the file system
     *
     * @param path the {@link Path} to the file or folder; must not be {@code null}
     * @return {@code true} if the path exists; {@code false} otherwise
     * @throws IllegalArgumentException if {@code path} is {@code null}
     */
    public static boolean exists(Path path) {

        if (path == null) {
            throw new IllegalArgumentException("path should not be null");
        }

        return Files.exists(path);
    }

    public static String readString(Path file) throws IOException {

        if (!isValidFile(file)) {
            throw new IllegalArgumentException("file not valid");
        }

        return Files.readString(file, StandardCharsets.UTF_8);
    }

    public static void writeToFile(Path file, String content) throws IOException {

        writeToFile(file, content, false);
    }

    public static void writeToFile(Path file, String content, boolean append) throws IOException {

        if (file == null) {
            throw new IllegalArgumentException("File should not be null");
        }

        if (content == null) {
            throw new IllegalArgumentException("Content should not be null");
        }

        if (isFolder(file)) {
            throw new IOException("File exists but it is a directory. Expected a regular file.");
        }

        if (append) {
            Files.writeString(file, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } else {
            Files.writeString(file, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    /**
     * Returns the path to the system temporary folder. This is determined
     * by the {@code java.io.tmpdir} system property.
     *
     * @return the {@link Path} representing the system temporary directory
     */
    public static Path tempFolder() {

        String tempDir = System.getProperty("java.io.tmpdir");

        return Path.of(tempDir);
    }

    public static boolean isFolder(Path file) {
        return Files.isDirectory(file);
    }

    public static boolean isValidFile(Path file) {

        if (file == null || !exists(file)) {
            return false;
        }

        return Files.isRegularFile(file);
    }

    public static boolean isValidFolder(Path file) {

        if (file == null || !exists(file)) {
            return false;
        }

        return Files.isDirectory(file);
    }

    public static boolean isEmpty(Path file) throws IOException {

        if (file == null) {
            throw new IllegalArgumentException("File should not be null");
        }

        return Files.size(file) == 0;
    }

    public static void deleteFile(Path file) throws IOException {

        if (!isValidFile(file)) {
            throw new IllegalArgumentException("File %s does not exist or is not a regular file".formatted(file));
        }

        Files.delete(file);
    }

}
