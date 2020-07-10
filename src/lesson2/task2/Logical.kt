@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.*

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val least: Int = number % 100
    val most: Int = number / 100
    return (least / 10 + least % 10) == (most / 10 + most % 10)
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    ((x2 - x1) * (x2 - x1) == (y2 - y1) * (y2 - y1)) || ((x1 == x2) || (y1 == y2))

/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    var isLeap = false
    if (year % 4 == 0)
        if (year % 100 != 0) isLeap = true
        else if (year % 400 == 0) isLeap = true
    return when (month) {
        2 -> if (isLeap) 29 else 28
        1, 3, 5, 7, 8, 10, 12 -> 31
        else -> 30
    }
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    if (x1 == x2 && y1 == y2) return (r1 <= r2)
    if (x1 == x2) return (y1 - r1) in (y2 - r2)..(y2 + r2) && (y1 + r1) in (y2 - r2)..(y2 + r2)
    if (y1 == y2) return (x1 - r1) in (x2 - r2)..(x2 + r2) && (x1 + r1) in (x2 - r2)..(x2 + r2)

    val a: Double = (y2 - y1) / (x2 - x1)
    val b: Double = y1 - a * x1

    val A: Double = 1.0 + a * a

    var B: Double = 2.0 * a * b - 2.0 * x1 - 2.0 * a * y1
    var C: Double = x1 * x1 + b * b - 2.0 * b * y1 - y1 * y1 - r1 * r1
    var D: Double = sqrt(B * B - 4.0 * A * C)
    val x1l: Double = (- B + D) / (2.0 * A)
    val x1h: Double = (- B - D) / (2.0 * A)

    B = 2.0 * a * b - 2.0 * x2 - 2.0 * a * y2
    C = x2 * x2 + b * b - 2.0 * b * y2 - y2 * y2 - r2 * r2
    D = sqrt(B * B - 4.0 * A * C)
    val x2l: Double = (- B + D) / (2.0 * A)
    val x2h: Double = (- B - D) / (2.0 * A)

    return (x1l in x2l..x2h) && (x1h in x2l..x2h)
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    if ((a <= r && b <= s) || (a <= s && b <= r)) return true
    else if ((a <= r && c <= s) || (a <= s && c <= r)) return true
    else return (b <= r && c <= s) || (b <= s && c <= r)
}
