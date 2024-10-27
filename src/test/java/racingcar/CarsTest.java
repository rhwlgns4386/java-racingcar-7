package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CarsTest {

    @ParameterizedTest
    @MethodSource("provideNameAndResultCars")
    public void 이름을_컴마로_분리하여_자동차들을_생성한다(String names, List<Car> cars) {
        assertThat(Cars.from(names)).isEqualTo(new Cars(cars));
    }

    static Stream<Arguments> provideNameAndResultCars() {
        return Stream.of(
                arguments("pobi", List.of(new Car("pobi")),
                        arguments("pobi,jihun", List.of(new Car("jihun"), new Car("pobi")))
                ));
    }

    @Test
    public void 자동차를_한번에_이동한다() {
        List<Car> before = List.of(new Car("test", 1), new Car("test2", 0));
        Cars cars = new Cars(before);

        Cars result = cars.move(() -> MoveCommand.MOVE);

        List<Car> after = List.of(new Car("test", 2), new Car("test2", 1));
        assertThat(result).isEqualTo(new Cars(after));
    }
}
