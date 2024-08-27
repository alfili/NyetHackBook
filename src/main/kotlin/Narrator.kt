package ru.filico

import kotlin.random.Random
import kotlin.random.nextInt

// Лямбда функция
// Не должна вызываться сразу! Поэтому без () скобок
// Неявный возврат (как я люблю, без return)
val exampleNarrationModifier: (String) -> String = { msg ->
    val numExclamationPoints = 3
    msg.uppercase() + "!".repeat(numExclamationPoints)
}

// it - вместо msg из примера выше.
// Хорошо подходит при работе с простыми лямбда выражениями
// Совсем НЕ подходит, если нужна сложная логика
val exampleNarrationModifierWithIt: (String) -> String = {
    val numExclamationPoints = 3
    it.uppercase() + "!".repeat(numExclamationPoints)
}

val loudNarration: (String, String) -> String = { msg, tone ->
    when (tone) {
        "excited" -> {
            val numExclamationPoints = 3
            msg.uppercase() + "!".repeat(numExclamationPoints)
        }
        "sneaky" -> {
            "$msg. Повествователь только что спалил игрока!"
        }
        else -> msg.uppercase()
    }
}

var narrationModifier: (String) -> String = { it }

fun changeNarrationMood() {
    val mood: String
    val modifier: (String) -> String
    when (Random.nextInt(1..5)) {
        1 -> {
            mood = "loud"
            modifier = { msg ->
                val numEx = 3
                msg.uppercase() + "!".repeat(numEx)
            }
        }
        2 -> {
            mood = "tired"
            modifier = { msg ->
                msg.lowercase().replace(" ", "... ")
            }
        }
        3 -> {
            mood = "unsure"
            modifier = { msg ->
                "$msg?"
            }
        }
        4 -> {
            mood = "leet"
            modifier = { msg ->
                msg.replace('l', '1', true).replace('e', '3', true).replace('t', '7', true)
            }
        }
        else -> {
            mood = "professional"
            modifier = { msg ->
                "$msg."
            }
        }
    }

    narrationModifier = modifier
    narrate("Рассказчик чувствет себя $mood")
}

// inline - позволяет не выделять память под экземпляр лямбда функции
// В итоге, оно просто вставит все необходимые переменные в месте вызова функции
/*
    Такое невозможно сделать с:
    - рекурсивными функциями
    - функциями, которые используют более ограниченную область видимости (public -> private)
 */
inline fun narrate(msg: String, modifier: (String) -> String = { narrationModifier(it) }) {
    println(modifier(msg))
}