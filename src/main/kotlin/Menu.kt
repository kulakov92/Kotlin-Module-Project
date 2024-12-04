class Menu(private val options: List<Pair<String, () -> Unit>>) {
    fun showMenu() {
        options.forEachIndexed { index, option ->
            println("$index. ${option.first}")
        }
        val selectedIndex = readlnOrNull()?.toIntOrNull()
        if (selectedIndex == null) {
            println("Ошибка! Проверьте правильность ввода команды - команда должна быть цифрой!")
            return
        } else {
            if (selectedIndex !in options.indices) {
                println("Ошибка! Проверьте правильность ввода команды - такой цифры нет, введите корректный символ!")
                return
            }
        }
        options[selectedIndex].second()
    }
}