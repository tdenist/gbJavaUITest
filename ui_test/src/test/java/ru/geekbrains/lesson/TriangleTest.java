package ru.geekbrains.lesson;

import org.junit.jupiter.api.Assertions;
import static ru.geekbrains.lesson.Triangle.triangleArea;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.lesson.exceptions.InvalidInputSideLengthException;
import ru.geekbrains.lesson.exceptions.TriangleIsNotExistException;

public class TriangleTest
{
    private static Logger logger = LoggerFactory.getLogger("TriangleTest");

    @ParameterizedTest
    @DisplayName("Проверка срабатывания исключения, если переданы некорректные данные: треугольник не существует")
    @CsvSource({"1, 2, 3", "2, 2, 4"})
    void exceptionWhenTriangleIsNotExist(int sideA, int sideB, int sideC){
        Assertions.assertThrows(TriangleIsNotExistException.class, () -> triangleArea(sideA, sideB, sideC));
    }

    @ParameterizedTest
    @DisplayName("Проверка срабатывания исключения, если переданы некорректные данные: " +
            "одна или несколько сторон меньше или равны 0")
    @CsvSource({"-3, 4, 5","3, 0, 5 ","3, -4, -5", "-3, -4, -5"})
    void exceptionWhenInvalidInputSideLength(int sideA, int sideB, int sideC){
        Assertions.assertThrows(InvalidInputSideLengthException.class, () -> triangleArea(sideA, sideB, sideC));
    }

    @ParameterizedTest
    @DisplayName("Проверка правильного расчета площади")
    @CsvSource({"6, 3, 4, 5"})
    void calculationAreaIsTrue(double area, int sideA, int sideB, int sideC) throws InvalidInputSideLengthException, TriangleIsNotExistException {
        Assertions.assertEquals( area, triangleArea(sideA, sideB, sideC) );
    }
}
