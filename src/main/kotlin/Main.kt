fun main() {
    val target = readln()
    val str = readln()
    val shift = readln().toInt()
    encryptDecryptUnicode(str, shift, target)
}

fun encryptDecryptUnicode(str: String, shift: Int, target: String) {
    val strArray = str.toCharArray()
    repeat(strArray.size) { index ->
        strArray[index] = getShiftedLetterUnicode(str[index], shift, target)
    }

    var newStr = ""
    repeat(strArray.size) {
        newStr += strArray[it].toString()
    }

    println(newStr)
}

fun getShiftedLetterUnicode(letter: Char, shift: Int, target: String): Char {
    val unicodePosition = letter.code
    return when(target) {
        "enc" -> {
            var newUnicodePosition = unicodePosition + shift
            if (newUnicodePosition > 255) newUnicodePosition = (newUnicodePosition - 256)
            newUnicodePosition.toChar()
        }

        else -> {
            var newUnicodePosition = unicodePosition - shift
            if (newUnicodePosition < 0) newUnicodePosition = (255 - newUnicodePosition + 1)
            newUnicodePosition.toChar()
        }
    }
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