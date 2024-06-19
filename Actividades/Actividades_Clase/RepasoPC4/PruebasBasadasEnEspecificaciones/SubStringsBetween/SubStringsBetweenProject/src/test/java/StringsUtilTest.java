import org.example.InvalidDelimiterException;
import org.example.StringsUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringsUtilTest {

    @Test
    void strIsNullOrEmpty(){
        assertThat(StringsUtil.substringsBetween(null,"a","c")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("","a","c")).isEqualTo(new String[0]);
    }

    @Test
    void openIsNullOrEmpty(){
        assertThat(StringsUtil.substringsBetween("abc",null,"c")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("abc","","c")).isEqualTo(null);
    }

    @Test
    void closeIsNullOrEmpty(){
        assertThat(StringsUtil.substringsBetween("abc","a",null)).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("abc","a","")).isEqualTo(null);
    }

    @Test
    void strOfLenght1(){
        assertThat(StringsUtil.substringsBetween("a","a","b")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","b","a")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","b","b")).isEqualTo(null);
        assertThat(StringsUtil.substringsBetween("a","a","a")).isEqualTo(null);
    }

    @Test
    void openAndCloseOfLenght1(){
        // ningun delimitador se encuentra
        assertThat(StringsUtil.substringsBetween("abc","x","y")).isEqualTo(null);
        // solo el delimitador "open" se encuentra
        assertThat(StringsUtil.substringsBetween("abc","a","y")).isEqualTo(null);
        // solo el delimitador "close" se encuentra
        assertThat(StringsUtil.substringsBetween("abc","x","c")).isEqualTo(null);
        // Los 2 delimitadores se encuentra pero no hay nada en el medio
        assertThat(StringsUtil.substringsBetween("abc","a","b")).isEqualTo(new String[]{""});
        // Los 2 delimitadores y hay una cadena en el medio
        assertThat(StringsUtil.substringsBetween("abc","a","c")).isEqualTo(new String[]{"b"});
        // str con espacios con delimitadores de longitud 1
        assertThat(StringsUtil.substringsBetween("abcabyt byrc", "a", "c")).isEqualTo(new String[]{"b", "byt byr"});
    }

    @Test
    void openAndCloseTagsOfDifferentSizes() {
        // ninguno de los limitadores se encuentra
        assertThat(StringsUtil.substringsBetween("aabcc","xx","yy")).isEqualTo(null);
        // el delimitador "open" se encuentra
        assertThat(StringsUtil.substringsBetween("aabcc","aa","yy")).isEqualTo(null);
        // el delimitador "close" se encuentra
        assertThat(StringsUtil.substringsBetween("aabcc","xx","cc")).isEqualTo(null);
        // los dos delimitadores se encuentran con nada en el medio
        assertThat(StringsUtil.substringsBetween("aacc","aa","cc")).isEqualTo(new String[]{""});
        // los dos delimitadores se encuentra con algo en el medio
        assertThat(StringsUtil.substringsBetween("aabcc","aa","cc")).isEqualTo(new String[]{"b"});
        // str con espacios, para delimitadores con espacios
        assertThat(StringsUtil.substringsBetween("a abb ddc ca abbcc", "a a", "c c")).isEqualTo(new String[]{"bb dd"});
    }
}

/*
A diferencia del codigo inicial se elimino este test y se puso como un escenario en los otros test
ya que se considera que se podria comportar diferente para cuando los delimitadores son de tamanio 1
o mayor que 1.

@Test
void notSubstringBetweenOpenAndCloseTags() {
 assertThat(substringsBetween("aabb", "aa", "bb")).isEqualTo(new String[]{""});
}
 */


/*
A diferencia del codigo inicial se elimino este test y se puso como un escenario en los otros test
ya que se considera que se podria comportar diferente para cuando los delimitadores son de tamanio 1
o mayor que 1.

@Test
void notSubstringBetweenOpenAndCloseTags() {
 assertThat(substringsBetween("aabb", "aa", "bb")).isEqualTo(new String[]{""});
}
 */

