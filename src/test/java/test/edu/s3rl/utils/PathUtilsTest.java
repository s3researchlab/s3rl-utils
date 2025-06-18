package test.edu.s3rl.utils;

import edu.s3rl.utils.PathUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class PathUtilsTest {

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
    void testTempFolderShouldNotReturnNull() {

        assertNotNull(PathUtils.tempFolder());
    }

    @Test
    void test() {

        String content = "Just a Test";
        Path tmpFile = PathUtils.tempFolder().resolve("test.txt");

        try {
            PathUtils.writeToFile(tmpFile, content);

            assertTrue(PathUtils.exists(tmpFile));
            assertFalse(PathUtils.isEmpty(tmpFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
