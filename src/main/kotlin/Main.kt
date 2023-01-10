fun main() {
    val str = readln()
    val shift = readln().toInt()

    val strArray = str.toCharArray()
    repeat(strArray.size) { index ->
        val strElement = str[index]
        if (strElement.isLetter()) {
            strArray[index] = getShiftedLetter(strElement, shift)
        }
    }

    var newStr = ""
    repeat(strArray.size) {
        newStr += strArray[it].toString()
    }

    println(newStr)
}

fun getShiftedLetter(letter: Char, shift: Int): Char {
    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    var newPosition = alphabet.indexOf(letter) + shift
    if (newPosition > (alphabet.length - 1)) newPosition = (newPosition - alphabet.length)

    return alphabet.elementAt(newPosition)
}

fun getCorrespondingLetter(letter: Char): Char {
    val chars = ('a'..'z')
    val charsReversed = ('z' downTo 'a')
    return charsReversed.elementAt(chars.indexOf(letter))
}