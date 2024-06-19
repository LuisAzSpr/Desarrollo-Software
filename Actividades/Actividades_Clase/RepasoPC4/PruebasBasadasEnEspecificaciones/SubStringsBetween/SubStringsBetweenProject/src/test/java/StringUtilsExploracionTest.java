import org.example.StringsUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsExploracionTest {
    @Test
    void simpleCase() {
        assertThat(StringsUtil.substringsBetween("abcd", "a", "d"))
                .isEqualTo(new String[] { "bc" });
    }
    @Test
    void manyStrings() {
        assertThat(StringsUtil.substringsBetween("abcdabcdab", "a", "d"))
                .isEqualTo(new String[] { "bc", "bc" });
    }
    @Test
    void openAndCloseTagsThatAreLongerThan1Char() {
        assertThat(StringsUtil.substringsBetween("aabcddaabfddaab", "aa", "dd"))
                .isEqualTo(new String[]{ "bc", "bf" });
    }
}
