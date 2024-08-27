package ru.filico

fun main() {
    narrate("Добро пожаловать в мир НетХак!", ::makeYellow)// Красим текст сообщения в желтый цвет (ANSI)
    println("Введите имя вашего героя:")
    val playerName: String = readLine().toString()
    require(playerName != null && playerName.isNotEmpty()) {
        "У героя должно быть имя!"
    }
    changeNarrationMood()
    narrate("${createTitle(playerName)} $playerName будет получать по жопе!")
}

private fun createTitle(name: String): String {
    return when {
        // Проверка гласных в имени героя
        name.all { it.isDigit() } -> "Секретный герой"
        name.none { it.isLetter() } -> "Герой под программой защиты свидетелей"
        name.count { it.lowercase() in "aeoiu" || it.lowercase() in "аеиоуюя" } > 4 -> "Повелитель гласных"
        name.lowercase() == name.lowercase().reversed() -> "Повелитель палендрома"
        name.all { it.isUpperCase() } -> "Выдающийся"
        else -> "Неизвестный герой"
    }
}

private fun makeYellow(msg: String) = "\u001b[33;1m$msg\u001b[0m"