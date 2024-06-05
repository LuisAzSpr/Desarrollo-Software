package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.StringUtils.regexSubstringsBetween;

public class RegexTest {
    @Test
    void regexSimpleCase() {
        assertThat(regexSubstringsBetween("abcd", "a", "d")).isEqualTo(new String[] { "bc" });
    }
    @Test
    void regexManyStrings() {
        assertThat(regexSubstringsBetween("abcdabcdab", "a", "d")).isEqualTo(new String[]{ "bc", "bc" });
    }
    @Test
    void regexOpenAndCloseTagsThatAreLongerThan1Char() {
        assertThat(regexSubstringsBetween("aabcddaabfddaab", "aa", "dd")).isEqualTo(new String[] { "bc", "bf" });
    }
}
