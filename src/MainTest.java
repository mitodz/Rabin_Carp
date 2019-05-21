import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void rabinCarp1() {
        String pattern = "aba";
        String text = "abacaba";
        String actual = new Main().RabinCarp(pattern, text);
        String expected = "0 4 ";
        assertEquals(expected, actual);
    }

    @Test
    void rabinCarp2() {
        String pattern = "Test";
        String text = "testTesttesT";
        String actual = new Main().RabinCarp(pattern, text);
        String expected = "4 ";
        assertEquals(expected, actual);
    }

    @Test
    void rabinCarp3() {
        String pattern = "aaaaa";
        String text = "baaaaaaa";
        String actual = new Main().RabinCarp(pattern, text);
        String expected = "1 2 3 ";
        assertEquals(expected, actual);
    }
}