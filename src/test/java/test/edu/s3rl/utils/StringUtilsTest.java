package test.edu.s3rl.utils;

import edu.s3rl.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    void testIfStringIsNullThenTrue() {
        assertTrue(StringUtils.isBlank(null));
    }

    @Test
    void testIfStringIsEmptyThenTrue() {
        assertTrue(StringUtils.isBlank(""));
    }

    @Test
    void testIfStringHasOnlyWhitespacesThenTrue() {
        assertTrue(StringUtils.isBlank("  "));
        assertTrue(StringUtils.isBlank("\t\n\r"));
    }

    @Test
    void testIfStringNoBlankThenFalse() {
        assertFalse(StringUtils.isBlank("a"));
        assertFalse(StringUtils.isBlank("a "));
        assertFalse(StringUtils.isBlank(" a"));
        assertFalse(StringUtils.isBlank(" a "));
    }

    @Test
    void testIfConstructorIsInstantiatedThenThrowException() {

        assertThrows(InvocationTargetException.class, () -> {

            Constructor<StringUtils> ctor = StringUtils.class.getDeclaredConstructor();

            // Since the constructor is private, you need to make it
            // accessible to invocation
            ctor.setAccessible(true);

            ctor.newInstance();
        });
    }
}
