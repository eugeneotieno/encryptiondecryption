fun main() {
    val str = "we found a treasure!"
    val strArray = str.toCharArray()
    repeat(strArray.size) { index ->
        val strElement = str[index]
        if (strElement.isLetter()) {
            strArray[index] = getCorrespondingLetter(strElement)
        }
    }

    var newStr = ""
    repeat(strArray.size) {
        newStr += strArray[it].toString()
    }

    println(newStr)
}

fun getCorrespondingLetter(letter: Char): Char {
    val chars = ('a'..'z')
    val charsReversed = ('z' downTo 'a')
    return charsReversed.elementAt(chars.indexOf(letter))
}