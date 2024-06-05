package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.StringUtils.substringsBetween;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    // casos en los que str es null o vacio
    @Test
    void strIsNullOrEmpty() {
        assertThat(substringsBetween(null, "a", "b")).isEqualTo(null);
        assertThat(substringsBetween("", "a", "b")).isEqualTo(new String[]{});
    }

    // Casos en los que open es nulo o vacio
    @Test
    void openIsNullOrEmpty() {
        assertThat(substringsBetween("abc", null, "b")).isEqualTo(null);
        assertThat(substringsBetween("abc", "", "a")).isEqualTo(null);
    }

    // Casos en los que close es nulo o vacio
    @Test
    void closeIsNullOrEmpty() {
        assertThat(substringsBetween("abc", "a", null)).isEqualTo(null);
        assertThat(substringsBetween("abc", "a", "")).isEqualTo(null);
    }
    // La cadena str tiene longitud 1
    @Test
    void strOfLength1() {
        assertThat(substringsBetween("a", "a", "b")).isEqualTo(null);
        assertThat(substringsBetween("a", "b", "a")).isEqualTo(null);
        assertThat(substringsBetween("a", "b", "b")).isEqualTo(null);
        assertThat(substringsBetween("a", "a", "a")).isEqualTo(null);
    }
    // Open y close tienen longitud 1
    @Test
    void openAndCloseOfLength1() {
        assertThat(substringsBetween("abc", "x", "y")).isEqualTo(null);
        assertThat(substringsBetween("abc", "a", "y")).isEqualTo(null);
        assertThat(substringsBetween("abc", "x", "c")).isEqualTo(null);
        assertThat(substringsBetween("abc", "a", "c")).isEqualTo(new String[]{"b"});
        assertThat(substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(new String[]{"b", "bytbyr"});
    }
    // Open y close tienen diferentes tamanios
    @Test
    void openAndCloseTagsOfDifferentSizes() {
        assertThat(substringsBetween("aabcc", "xx", "yy")).isEqualTo(null);
        assertThat(substringsBetween("aabcc", "aa", "yy")).isEqualTo(null);
        assertThat(substringsBetween("aabcc", "xx", "cc")).isEqualTo(null);
        assertThat(substringsBetween("aabbcc", "aa", "cc")).isEqualTo(new String[]{"bb"});
        assertThat(substringsBetween("aabbccaaeecc", "aa", "cc")).isEqualTo(new String[]{"bb", "ee"});
        assertThat(substringsBetween("a abb ddc ca abbcc", "a a", "c c")).isEqualTo(null);
    }
    // No hay cadena entre open y close
    @Test
    void notSubstringBetweenOpenAndCloseTags() {
        assertThat(substringsBetween("aabb", "aa", "bb")).isEqualTo(new String[]{""});
    }

    @Test
    void ignoresSpecialCharacters() {
        assertThat(substringsBetween("a*b?c!a@d", "a", "d")).isEqualTo(new String[] { "bca" });
    }

    @Test
    void throwsExceptionWhenOpenOrCloseIsEmpty() {
        assertThrows(InvalidDelimiterException.class, () -> {
            substringsBetween("abc", "", "b");
        });
        assertThrows(InvalidDelimiterException.class, () -> {
            substringsBetween("abc", "a", "");
        });
    }



}