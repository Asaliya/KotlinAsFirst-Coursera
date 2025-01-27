@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
        shoppingList: List<String>,
        costs: Map<String, Double>): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
        phoneBook: MutableMap<String, String>,
        countryCode: String) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
        text: List<String>,
        vararg fillerWords: String): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    var merged = (mapB + mapA).toMutableMap() - ""
    for (department in merged.keys) {
        if (mapB.contains(department) == true) {
            if (merged.getOrDefault(department, "") != mapB.getOrDefault(department, "")) {
                val pair = department to (merged.getOrDefault(department, "") + ", " + mapB.getOrDefault(department, ""))
                merged = merged + pair
            }
        }
    }
    return merged.toMap()
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val gradesAll = ((grades.values).toSortedSet()).toList()
    val namesAll = (grades.keys).toList()
    var namesForGrade = listOf<String>()
    var gradesToNames: Map<Int, List<String>> = mapOf()

    for (item in 0..gradesAll.size - 1) {
        val grade: Int = (gradesAll[item] ?: 0)
        for (elem in 0..namesAll.size - 1) {
            val name: String = namesAll[elem]
            if (grade == grades.getOrDefault(name, 0)) {
//                    println("${grade == grades.getOrDefault(name, 0)}")
                namesForGrade = namesForGrade + name
            }
            gradesToNames = gradesToNames + (grade to namesForGrade)
        }
        namesForGrade = listOf()
    }
    return gradesToNames
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for (i in 1..a.size)
        for ((key, value) in a) {
            if (a[key] == b[key] && a[value] == b[value]) true
            else return false
        }
    return true
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    var averageSP: Map<String, Double> = mapOf()
    //как инициализировать averageSP в случае val
    val companies = stockPrices.toMap().keys - ""

    for (item in companies) {
        var averagePrice: Double = 0.0
        var counter: Int = 0
        for (i in 0..stockPrices.size - 1) {
            if (stockPrices[i].first == item) {
                averagePrice = averagePrice + stockPrices[i].second
                ++counter
            }
        }
        averagePrice = averagePrice / counter
        averageSP = averageSP + (item to averagePrice)
    }
    return averageSP
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var cheapestStuff: String? = null
    var cheapestStuffSev: String? = ""
    val stuffList = stuff.toList()
    var minCost = 0.0

    for (i in 0..stuffList.size - 1) {
        if (kind == stuffList[i].second.first) {
            cheapestStuff = stuffList[i].first

            for (j in i + 1..stuffList.size - 1)
                if (kind == stuffList[j].second.first && stuffList[i].second.second >= stuffList[j].second.second) {
                    cheapestStuff = stuffList[j].first
                    println("1 ${cheapestStuff}")
                    minCost = stuffList[j].second.second
                }
        }
        println("1 $cheapestStuff")
        break

    }

    //если товаров с минимальной ценой несколько:
    // находим доп. наименования товара заданного типа с минимальной стоимостью
    for (i in 0..stuffList.size - 1)
        if (kind == stuffList[i].second.first && minCost == stuffList[i].second.second) {
            if (stuffList[i].first != cheapestStuff) {
                cheapestStuffSev = cheapestStuffSev + ", " + stuffList[i].first
            }
        }
    return when {
        cheapestStuff == null -> null
        cheapestStuffSev.toString().isNotEmpty() -> cheapestStuff + cheapestStuffSev
        else -> cheapestStuff
    }
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */

fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    var allFriends: Map<String, Set<String>> = emptyMap()

    for (name in friends.keys.union(friends.values.flatten())) {
        allFriends = allFriends + (name to (findFriendsReq(name, friends, setOf(name)) - name))
    }

    return allFriends
}

fun findFriendsReq(name: String, friends: Map<String, Set<String>>, knownFriends: Set<String>): Set<String> {
    val directFriends = friends.getOrDefault(name, emptySet())
    var newKnownFriends = knownFriends

    for (f in directFriends) {
        if (!knownFriends.contains(f)) {
            newKnownFriends = newKnownFriends + f
            val foundFriends = findFriendsReq(f, friends, newKnownFriends)
            newKnownFriends = newKnownFriends.union(foundFriends)
        }
    }

    return newKnownFriends
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    val keysToRemove = mutableListOf<String>()
    for (key in (a.keys).toList<String>()) {
        if (b.containsKey(key) == true) {
            if (a.getOrDefault(key, "") == b.getOrDefault(key, ""))
                keysToRemove.add(key)
        }
    }
    for (key in keysToRemove) {
        a.remove(key)
    }
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    var inBoth: List<String> = listOf()
    for (element in a.toSet()) {
        if (b.toSet().contains(element)) {
            inBoth = inBoth + element
        }
    }
    return inBoth
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean =
        chars.toSet() == word.toLowerCase().toSet()

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    var repeats: Map<String, Int> = mapOf()
    for (letter in list.toSet()) {
        if (counting(letter, list) > 1)
            repeats = repeats + Pair(letter, counting(letter, list))
    }
    return repeats
}

fun counting(str: String, list: List<String>): Int {
    var counter = 0
    for (element in list)
        if (element == str)
            ++counter
    return counter

}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    for (i in 0..words.size - 1)
        for (j in i + 1..words.size - 1) {
            if (words[i].toSet() == words[j].toSet())
                return true
        }
    return false
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    for (i in 0..list.size - 1)
        for (j in i + 1..list.size - 1) {
            if (list[i] + list[j] == number) {
                return i to j
            }
        }
    return (-1 to -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    var worthToTreasure: Map<Double, String> = mapOf()
    var backpack: List<String> = listOf()

    for ((key, value) in treasures)
        if (value.second != 0 && value.first != 0 && key != "") {
            worthToTreasure = worthToTreasure + ((value.second.toDouble() / value.first.toDouble()) to key)
        }
    val worthes = worthToTreasure.keys.sortedDescending()
    var weight: Double = 0.0
    for (i in 0..worthes.size - 1) {
        val treasureToPack = worthToTreasure.getOrDefault(worthes[i], "")
        if ((treasures.getOrDefault(treasureToPack, 1 to 0).first.toDouble() + weight).toInt() > capacity) continue

        weight = weight + treasures.getOrDefault(treasureToPack, 1 to 0).first.toDouble()
        backpack = backpack + worthToTreasure.getOrDefault(worthes[i], "")
        worthToTreasure = worthToTreasure - worthes[i]

        for (i in 0..worthes.size - 2) {
            if ((treasures.getOrDefault(worthToTreasure.getOrDefault(worthes[i + 1], ""), 1 to 0).first.toDouble() + weight).toInt() > capacity) break
        }
    }
    return backpack.toSet()
}


fun main(args: Array<String>) {
    val result = bagPacking(mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
            450)
    println("$result")
}