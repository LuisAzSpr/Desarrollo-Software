import org.example.Clumps;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;

class ClumpsTest {
    @ParameterizedTest
    @MethodSource("generator")
    void testClumps(int[] nums, int expectedNoOfClumps) {
        assertThat(Clumps.countClumps(nums))
                .isEqualTo(expectedNoOfClumps);
    }
    static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(new int[]{}, 0), // vacío
                Arguments.of(null, 0), // null
                Arguments.of(new int[]{1, 2, 2, 2, 1}, 1), // 1 grupo
                Arguments.of(new int[]{1}, 0), // 1 elemento
                Arguments.of(new int[]{2, 2}, 1), // 1 grupo
                Arguments.of(new int[]{1,1,2,2,3,3}, 3), // T10: 3 grupos
                Arguments.of(new int[]{1,1,1,2,2,2,3,3,3}, 3), // T11: 3 grupos grandes
                Arguments.of(new int[]{1,2,2,2,3,3,4,4,4}, 3) // T12: 3 grupos separados
        );
    }
}

