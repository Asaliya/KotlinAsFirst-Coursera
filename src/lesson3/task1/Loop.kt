@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
//решение с использованием цикла
fun digitNumber(n: Int): Int {
    var count: Int = 1
    if (n < 10) 1
    if (n >= 10) {
        var nCount: Int = n
        do {
            val i = nCount / 10
            ++count
            nCount = i
        } while (i >= 10)
    }
    return count
}

//решение с использованием рекурсии
fun digitNumberRecursion(n: Int): Int =
        when {
            n < 10 -> 1
            n == 10 -> 2
            else -> digitNumberRecursion(n / 10) + 1
        }

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */

fun fib(n: Int): Int =
        if (n <= 2) 1
        else fib(n - 1) + fib(n - 2)

//решение с использованием цикла
fun fibLoop(n: Int): Int {
    var fibPrevious: Int = 1
    var fibFollowing: Int = 1
    var fibSum: Int = 1
    for (i in 1..n - 2) {
        fibSum = fibPrevious + fibFollowing
        fibPrevious = fibFollowing
        fibFollowing = fibSum
    }
    return fibSum
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var gcdM: Int = m
    var gcdN: Int = n

    if (m == n) return m
    if (m > n && m % n == 0) return m
    if (n > m && n % m == 0) return n
    while (gcdM != gcdN) {
        if (gcdM > gcdN) gcdM = gcdM - gcdN
        if (gcdN > gcdM) gcdN = gcdN - gcdM
    }
    return m * (n / gcdM)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var result: Int = 0
    for (i in 1..(n - 1)) {
        result = 1 + i
        if (n % (1 + i) == 0) return result
    }
    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var result: Int = 0
    for (i in 1..(n - 1)) {
        result = n - i
        if (n % (n - i) == 0) return result
    }
    return result
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var gcdM: Int = m
    var gcdN: Int = n
    while (gcdM != gcdN) {
        if (gcdM > gcdN) gcdM = gcdM - gcdN
        if (gcdN > gcdM) gcdN = gcdN - gcdM
    }
    if (gcdM == 1) return true
    return false
}


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val mStart: Double = m.toDouble()
    val start: Int = sqrt(mStart).toInt()
    if (m == start * start) return true
    if ((start + 1) >= 46341) return false
    for (i in (start + 1)..n / 2) {
        if ((i * i) in m..n) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X / 2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count: Int = 0
    var followMember = x
    while (followMember > 1) {
        if (followMember % 2 == 0)
            followMember /= 2
        else followMember = (3 * followMember) + 1
        count += 1
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
//fun sinb(x: Double, eps: Double): Double {
//    var resultX: Double = x
//    var sin: Double = 0.0
//    var factorial: Int = 1
//    var i: Int = 1
//    var l: Int = 1
//
//    while (eps < (resultX / factorial)) {
//        resultX = resultX * x * x
//        factorial = factorial(2 * i - 1)
//        l = l * (-1)
//        sin = sin + l * resultX / factorial
//        i = i + 2
//
//        println("factorial = ${factorial}")
//        println("result = ${resultX / factorial}")
//        println("sin = ${sin}")
//    }
//    return sin
//}

fun sign(n: Int): Int =
        if (n % 2 == 0) 1
        else -1


fun tl(x: Double, n: Int): Double {
    var res = 1.0

    for (i in 1..n) {
        res = res * (x / n)
    }

    return res
}

fun sin(x: Double, eps: Double): Double {
    var r: Double = 0.0

    for (n in 0..Int.MAX_VALUE) {
        r = r + sign(n) * tl(x, 2 * n + 1)
        println("n=${n}")
        println("xn=${x.pow(2.0 * n + 1.0)}")
        println("fac=${factorial(2 * n + 1)}")
        println("tl=${tl(x,n)}")
        println("r=${r}")
        if (tl(x,2 * n + 1) < eps) return r
    }

return r
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var i: Int = 0
    var factorial: Int = 1
    var sum: Double = 0.0
    var cos: Double = 1.0

while (eps < abs(cos)) {
    sum = sum + cos
    i++
    factorial = factorial * (2 * i - 1) * (2 * i)
    cos = cos * -x * x / factorial
}
return sum
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    val k = digitNumber(n)
    var count: Int = 0
    var isRevert: Int = 0
    for (i in 1..k) {
        isRevert = isRevert + ((n / 10.0.pow(k - i).toInt() % 10) * 10.0.pow(i - 1)).toInt()
    }
    return isRevert
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    if (n < 10) return true

    val k = digitNumber(n)
    var count: Int = k
    for (i in 1..k / 2) {
        if (((n / 10.0.pow(k - i)) % 10.0).toInt() == ((n % 10.0.pow(i)) / 10.0.pow(i - 1)).toInt()) {
            count -= 2
        }
    }
    if (count > 1) return false
    return true
}

fun isPalindromeB(n: Int): Boolean {
    var s = 1000000000 // we know 2^31 the Int size; `s` is the cursor moving from left side
    var d = n

    while (true) {
        if (d < 10) return true
        if (d == 10) return false

        val first = d / s
        d %= s

        if (first != 0) { // start lookup the first digit
            // when we found the first let's get the last
            val last = d % 10
            if (first != last) return false
            d /= 10
        }

        s /= 10
    }
}


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n < 10) return false

    val k = digitNumber(n)
    var count: Int = 1
    for (i in 1..(k - 1)) {
        if (((n / 10.0.pow(k - 1))).toInt() == ((n % 10.0.pow(i)) / 10.0.pow(i - 1)).toInt()) {
            count = count + 1
        }
    }
    if (count == k) return false
    return true
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var k: Int = 0
    var result: Int = 1
    for (i in 1..Int.MAX_VALUE) {
        k = k + digitNumber(i * i)
        result = ((i * i) / (10.0.pow(abs(n - k))) % 10).toInt()
        if (k >= n) break
    }
    return result
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var k: Int = 0
    var result: Int = 0
    for (i in 1..Int.MAX_VALUE) {
        k = k + digitNumber(fibLoop(i))
        result = (fibLoop(i) / (10.0.pow(abs(n - k))) % 10).toInt()
        if (k >= n) break
    }
    return result
}


fun main(args: Array<String>) {
    val result = sin(100 * PI, 1e-5)
    println("$result")
}
