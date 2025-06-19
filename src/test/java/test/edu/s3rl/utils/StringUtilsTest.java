package test.edu.s3rl.utils;

import edu.s3rl.utils.StringUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

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
    void testWhenMinifyingIfStringIsBlankThenReturnNull() {
        assertNull(StringUtils.minify(null));
    }

    @Test
    void testWhenMinifyingIfStringHasOnlyWhitespacesThenReturnTheCorrectString() {
        assertEquals("", StringUtils.minify(""));
        assertEquals("", StringUtils.minify("  "));
    }

    @Test
    void testWhenMinifyingIfStringIsNotBlankThenReturnTheCorrectString() {
        assertEquals("a", StringUtils.minify(" a      "));
        assertEquals("a b", StringUtils.minify("  a   b  "));
    }

    @Test
    void testWhenMinifyingIfStringHasJavaCodeThenReturnTheCorrectString() {

        String actual = """
                public class Example {
                    int field;
                    int method () {
                        return field;
                    }
                }
            """;

        String expected = "public class Example { int field; int method () { return field; } }";

        assertEquals(expected, StringUtils.minify(actual));
    }

    @Test
    void testWhenMinifyingIfStringHasCCodeThenReturnTheCorrectString() {

        String actual = """
                int main(){
                    printf("%d", 12);
                }
            """;

        String expected = "int main(){ printf(\"%d\", 12); }";

        assertEquals(expected, StringUtils.minify(actual));
    }

    @Test
    void testWhenMinifyingIfStringHasNewLineSymbolThenReturnTheCorrectString() {

        assertEquals("a a", StringUtils.minify("a\na"));
        assertEquals("a bb", StringUtils.minify("\na\n\nbb\n"));
        assertEquals("a a", StringUtils.minify("a\na"));
        assertEquals("a bb", StringUtils.minify("\na\n\nbb\n"));

        assertEquals("the test", StringUtils.minify("the\n test"));
        assertEquals("the test", StringUtils.minify("the\r\n test"));
        assertEquals("the test", StringUtils.minify("the\r test"));

        assertEquals("the test", StringUtils.minify("the\t test"));
    }
}
