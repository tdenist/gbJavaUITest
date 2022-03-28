package ru.geekbrains.lesson;

import ru.geekbrains.lesson.exceptions.*;

public class Triangle {

    public static double triangleArea(int a, int b, int c) throws TriangleIsNotExistException, InvalidInputSideLengthException {

        // проверка, что переданы значения сторон больше 0,
        // иначе выбрасывается исключение
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new InvalidInputSideLengthException();
        }

        // проверка существования треугольника: сумма двух любых сторон должна быть больше третьей
        // если условие не выполняется, то выбрасывается исключение
        if (!((a + b) > c && (a + c) > b && (b + c) > a)) {
            throw new TriangleIsNotExistException();
        }

        // рассчет площади треугольника
        int p = (a + b + c) / 2; //полупериметр треугольника
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}