@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
//fun main(args: Array<String>) {
//    println("Введите время в формате ЧЧ:ММ:СС")
//    val line = readLine()
//    if (line != null) {
//        val seconds = timeStrToSeconds(line)
//        if (seconds == -1) {
//            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
//        }
//        else {
//            println("Прошло секунд с начала суток: $seconds")
//        }
//    }
//    else {
//        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
//    }
//}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ").toList()

    try {
        return when {
            (parts[0].toInt() == 0 || monthNumberFromString(parts[1]) == 0 || parts[2].toInt() == 0) -> ""
            parts.size > 3 -> ""
            parts[0].toInt() <= daysInMonth(monthNumberFromString(parts[1]), parts[2].toInt()) -> String.format("%02d.%02d.%d", parts[0].toInt(), monthNumberFromString(parts[1]), parts[2].toInt())
            else -> ""
        }
    } catch (e: IndexOutOfBoundsException) {
        return ""
    } catch (e: NumberFormatException) {
        return ""
    }
}

fun daysInMonth(month: Int, year: Int): Int {

    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        return 31
    if (month == 4 || month == 6 || month == 9 || month == 11)
        return 30
    if (year % 4 == 0 && year % 100 != 0 || year % 100 == 0 && year % 400 == 0)
        if (month == 2)
            return 29
    return 28
}

fun monthNumberFromString(month: String): Int {
    return when {
        month == "января" -> 1
        month == "февраля" -> 2
        month == "марта" -> 3
        month == "апреля" -> 4
        month == "мая" -> 5
        month == "июня" -> 6
        month == "июля" -> 7
        month == "августа" -> 8
        month == "сентября" -> 9
        month == "октября" -> 10
        month == "ноября" -> 11
        month == "декабря" -> 12
        else -> 0
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".").toList()

    try {
        return when {
            parts[0].toInt() == 0 || parts[1].toInt() == 0 || parts[2].toInt() == 0 -> ""
            parts.size > 3 -> ""
            parts[0].toInt() <= daysInMonth(parts[1].toInt(), parts[2].toInt()) -> String.format("%d %s %d", parts[0].toInt(), monthNameFromNumber(parts[1].toInt()), parts[2].toInt())
            else -> ""
            //%[parameter][flags][width][.precision][length]type
        }
    } catch (e: IndexOutOfBoundsException) {
        return ""
    } catch (e: NumberFormatException) {
        return ""
    }

}

fun monthNameFromNumber(month: Int): String {
    return when {
        month == 1 -> "января"
        month == 2 -> "февраля"
        month == 3 -> "марта"
        month == 4 -> "апреля"
        month == 5 -> "мая"
        month == 6 -> "июня"
        month == 7 -> "июля"
        month == 8 -> "августа"
        month == 9 -> "сентября"
        month == 10 -> "октября"
        month == 11 -> "ноября"
        month == 12 -> "декабря"
        else -> ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String = TODO()

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val parts = jumps.split(" ")
    var jumpsLengths: List<Int> = listOf()


    for (i in 0..parts.size - 1) {
        println("1.${parts[i]}")
        jumpsLengths = jumpsLengths + (parts[i].toIntOrNull() ?: 0)
        println("2.${jumpsLengths}")
    }
    println("3.${jumpsLengths.max() ?: 0}")
    println("4.${jumpsLengths.toSet().size}")
    return when {
        (jumpsLengths.max() ?: 0) == 0 -> -1
        (jumpsLengths.toSet() - 0).size == 1 -> -1
        else -> (jumpsLengths.max() ?: 0)
    }
}


/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val parts = jumps.split(" ")
    var jumpsLengths: List<Int> = listOf()

    return try {
        for (i in 0..parts.size - 2) {
            if (parts[i + 1].split("").filter({ it == "+" }).isNotEmpty() == true)
                jumpsLengths = jumpsLengths + (parts[i].toIntOrNull() ?: 0)
        }
        when {
            (jumpsLengths.max() ?: 0) == 0 -> -1
            else -> (jumpsLengths.max() ?: 0)
        }
    } catch (e: IndexOutOfBoundsException) {
        -1
    }
}


/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val parts = expression.split(" ")
    var result = parts[0].toInt()

    if (parts.all { charSequenceCheck(it) == true })
        for (i in 0..parts.size - 2 step 2) {
            if (parts[i].matches("[0-9]+".toRegex()) && !parts[i + 1].matches("[0-9]+".toRegex()))
                if (parts[i + 1] == "+")
                    result = result + (parts[i + 2].toInt())
                if (parts[i + 1] == "-")
                result = result - (parts[i + 2].toInt())
        }
    return result
}

fun charSequenceCheck(str: String): Boolean {
    return when {
        str.all { it.isDigit() == true } -> true
        str.all { it.isDigit() == false } && str.length == 1 && (str == "+" || str == "-") -> true
        else -> throw  IllegalArgumentException()
    }
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val words = str.toLowerCase().split(" ")
    var result = 0
    var i: Int = 0

    try {
        while (words[i] != words[i + 1] && i <= words.size - 1) {
            result = result + words[i].length + 1
            i++
        }
        return result
    } catch (e: IndexOutOfBoundsException) {
        -1
    }
    return -1
}


/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    val inString = description.split("; ")
    var costs: Map<Double, String> = mapOf()

    try {
        for (element in inString) {
            val itemCost = element.split(" ")
            if (itemCost[1].toDouble() >= 0.0)
                costs = costs + (itemCost[1].toDouble() to itemCost[0])
            else throw  IllegalArgumentException()
        }
        val max: Double = costs.keys.max() ?: 0.0
        return costs.getOrDefault(max, "")
    } catch (e: IndexOutOfBoundsException) {
        return ""
    } catch (e: NumberFormatException) {
        return ""
    } catch (e: IllegalArgumentException) {
        return ""
    }
}



/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val romanLetters = roman.split("")
    var n: Int = 0
    var k = 0

    try {
        if (romanLetters.isNotEmpty() == true) {
            if ("CDIMLVX".toSet().intersect(roman.toSet()) != roman.toSet()) throw NumberFormatException()
            else for (i in k until romanLetters.size - 1) {

                if (romanLetters[i] == "M" && romanLetters[i - 1] != "C"){
                    n = n + 1000
                    k= k+1
                }
                if (romanLetters[i] == "C" && romanLetters[i + 1] == "M"){
                    n = n + 900
                    k= k+2
                }
                if (romanLetters[i] == "D" && romanLetters[i - 1] != "C"){
                    n = n + 500
                    k= k+1
                }
                if (romanLetters[i] == "C" && romanLetters[i + 1] == "D"){
                    n = n + 400
                    k= k+2
                }
                if (romanLetters[i] == "C" && romanLetters[i + 1] != "D" && romanLetters[i + 1] != "M" && romanLetters[i - 1] != "X"){
                    n = n + 100
                    k= k+1
                }
                if (romanLetters[i] == "X" && romanLetters[i + 1] == "C"){
                    n = n + 90
                    k= k+2
                }
                if (romanLetters[i] == "L" && romanLetters[i - 1] != "X"){
                    n = n + 50
                    k= k+1
                }
                if (romanLetters[i] == "X" && romanLetters[i + 1] == "L"){
                    n = n + 40
                    k= k+2
                }
                if (romanLetters[i] == "X" && romanLetters[i + 1] != "C" && romanLetters[i + 1] != "L" && romanLetters[i - 1] != "I"){
                    n = n + 10
                    k= k+1
                }
                if (romanLetters[i] == "I" && romanLetters[i + 1] == "X"){
                    n = n + 9
                    k= k+2
                }
                if (romanLetters[i] == "V" && romanLetters[i - 1] != "I"){
                    n = n + 5
                    k= k+1
                }
                if (romanLetters[i] == "I" && romanLetters[i + 1] == "V"){
                    n = n + 4
                    k= k+2
                }
                if (romanLetters[i] == "I" && romanLetters[i + 1] != "V" && romanLetters[i + 1] != "X"){
                    n = n + 1
                    k= k+1
                }
            }
        }
        return n
    } catch (e: IndexOutOfBoundsException) {
        return -1
    } catch (e: NumberFormatException) {
        return -1
    } catch (e: IllegalArgumentException) {
        return -1
    }
}


/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()


fun main(args: Array<String>) {
    val result = fromRoman("FRS")
    println("$result")
}
