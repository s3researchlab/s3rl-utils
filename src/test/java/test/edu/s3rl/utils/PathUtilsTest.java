package test.edu.s3rl.utils;

import edu.s3rl.utils.PathUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class PathUtilsTest {

    private static Path tmpFolder;

    @BeforeAll
    static void init() {
        tmpFolder = PathUtils.tempFolder();
    }

    @Test
    void testIfConstructorIsInstantiatedThenThrowException() {

        assertThrows(InvocationTargetException.class, () -> {

            Constructor<PathUtils> ctor = PathUtils.class.getDeclaredConstructor();

            // Since the constructor is private, you need to make it
            // accessible to invocation
            ctor.setAccessible(true);

            ctor.newInstance();
        });
    }

    @Test
    void testExistsIfPathIsNullThenThrowException() {

        assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.exists(null);
        });
    }

    @Test
    void testExistsIfFileExistsThenReturnTrue() {

        Path actual = Path.of("pom.xml");

        assertTrue(PathUtils.exists(actual));
    }

    @Test
    void testExistsIfFolderExistsThenReturnTrue() {

        Path actual = Path.of("src");

        assertTrue(PathUtils.exists(actual));
    }

    @Test
    void testTempFolderShouldNotReturnBlank() {

        assertNotNull(PathUtils.tempFolder());
        assertNotEquals("", PathUtils.tempFolder().toString().trim());
    }

    @Test
    void testWriteToFileIfInvalidArgumentsThenThrowAnException() {

        Path tmpFile = tmpFolder.resolve("test.txt");

        assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.writeToFile(null, "the content");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.writeToFile(tmpFile, null);
        });
    }


    @Test
    void testWriteToFileIfFolderThenThrowAnException() {

        assertThrows(IOException.class, () -> {

            Path tmpFile = PathUtils.tempFolder();

            PathUtils.writeToFile(tmpFile, "the content");
        });
    }

    @Test
    void testWriteToFileIfAppendThenNoException() {

        String content = "The content";
        Path tmpFile = tmpFolder.resolve("append.txt");

        try {

            if (PathUtils.exists(tmpFile)) {
                PathUtils.deleteFile(tmpFile);
            }

            PathUtils.writeToFile(tmpFile, content, true);
            PathUtils.writeToFile(tmpFile, content, true);

            assertTrue(PathUtils.exists(tmpFile));
            assertFalse(PathUtils.isEmpty(tmpFile));
            assertEquals("The contentThe content", PathUtils.readString(tmpFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testWriteToFileIfNewFileThenNoException() {

        String content = "The content";
        Path tmpFile = tmpFolder.resolve("new.txt");

        try {

            if (PathUtils.exists(tmpFile)) {
                PathUtils.deleteFile(tmpFile);
            }

            PathUtils.writeToFile(tmpFile, content);
            PathUtils.writeToFile(tmpFile, content);

            assertTrue(PathUtils.exists(tmpFile));
            assertFalse(PathUtils.isEmpty(tmpFile));
            assertEquals(content, PathUtils.readString(tmpFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeleteFileIfFileExistsThenNoException() {

        Path tmpFile = tmpFolder.resolve("delete-file.txt");

        try {

            PathUtils.writeToFile(tmpFile, "The content");

            assertTrue(PathUtils.exists(tmpFile));
            PathUtils.deleteFile(tmpFile);
            assertFalse(PathUtils.exists(tmpFile));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDeleteFileIfFileDoesNotExistThenThrowAnException() {

        Path tmpFile = tmpFolder.resolve("delete-file.txt");

        assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.deleteFile(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.deleteFile(tmpFolder);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            PathUtils.deleteFile(tmpFile);
        });
    }

}
