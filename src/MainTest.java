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

    @Test
    void rabinCarp4() {
        String pattern = "aaa";
        String text = "baabbbaaabbbaaabbbaaa";
        String actual = new Main().RabinCarp(pattern, text);
        String expected = "6 12 18 ";
        assertEquals(expected, actual);
    }

    @Test
    void rabinCarp5() {
        String pattern = "aaab";
        String text = "aaaaaaab";
        String actual = new Main().RabinCarp(pattern, text);
        String expected = "4 ";
        assertEquals(expected, actual);
    }

    @Test
    void rabinCarp6() {
        String pattern = "aaab";
        String text = "aaab";
        String actual = new Main().RabinCarp(pattern, text);
        String expected = "0 ";
        assertEquals(expected, actual);
    }

    @Test
    void rabinCarp7() {
        String pattern = "aaab";
        String text = "aaac";
        String actual = new Main().RabinCarp(pattern, text);
        String expected = "";
        assertEquals(expected, actual);
    }

}