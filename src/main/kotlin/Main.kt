import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    var alg = "shift"
    var target = "enc"
    var shift = 0
    var str = ""
    var inFile = ""
    var outFile = ""
    var error = false
    var errorMsg = ""

    repeat(args.size) { index->
        if (args[index] == "-alg")  {
            if (args[index + 1] in arrayOf("-mode", "-key", "-data", "-in", "-out")) {
                error = true
                errorMsg = "Error: -alg not specified correctly!"
            } else {
                alg = args[index + 1]
            }
        }
        if (args[index] == "-mode")  {
            if (args[index + 1] in arrayOf("-alg", "-key", "-data", "-in", "-out")) {
                error = true
                errorMsg = "Error: -mode not specified correctly!"
            } else {
                target = args[index + 1]
            }
        }
        if (args[index] == "-key") {
            if (args[index + 1] in arrayOf("-alg", "-mode", "-data", "-in", "-out")) {
                error = true
                errorMsg = "Error: -key not specified correctly!"
            } else {
                shift = args[index + 1].toInt()
            }
        }
        if (args[index] == "-data") {
            if (args[index + 1] in arrayOf("-alg", "-mode", "-key", "-in", "-out")) {
                error = true
                errorMsg = "Error: -data not specified correctly!"
            } else {
                str = args[index + 1]
            }
        }
        if (args[index] == "-in") {
            if (args[index + 1] in arrayOf("-alg", "-mode", "-key", "-data", "-out")) {
                error = true
                errorMsg = "Error: -in not specified correctly!"
            } else {
                inFile = args[index + 1]
            }
        }
        if (args[index] == "-out") {
            if (args[index + 1] in arrayOf("-alg", "-mode", "-key", "-data", "-in")) {
                error = true
                errorMsg = "Error: -out not specified correctly!"
            } else {
                outFile = args[index + 1]
            }
        }
    }

    if (error) {
        println("Error: $errorMsg")
    } else {
        if (str == "" && inFile == "") {
            str = ""
            showOutput(outFile, str, shift, target, alg)
        } else {
            if (str.isBlank() && inFile.isNotBlank()) {
                if (File(inFile).exists()) {
                    str = File(inFile).readText()
                    showOutput(outFile, str, shift, target, alg)
                } else {
                    println("Error: Input file does not exist!")
                }
            } else {
                showOutput(outFile, str, shift, target, alg)
            }
        }
    }
}

fun showOutput(outFile: String, str: String, shift: Int, target: String, alg: String) {
    if (outFile == "") {
        println(encryptDecryptUnicode(str, shift, target, alg))
    } else {
        File(outFile).writeText(encryptDecryptUnicode(str, shift, target, alg))
    }
}

fun encryptDecryptUnicode(str: String, shift: Int, target: String, alg: String): String {
    val strArray = str.toCharArray()

    if (alg == "shift") {
        repeat(strArray.size) { index ->
            if (str[index].isLetter()) {
                strArray[index] = getShiftedLetter(str[index], shift, target)
            }
        }
    } else {
        repeat(strArray.size) { index ->
            strArray[index] = getShiftedLetterUnicode(str[index], shift, target)
        }
    }

    var newStr = ""
    repeat(strArray.size) {
        newStr += strArray[it].toString()
    }

    return newStr
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

fun getShiftedLetter(letter: Char, shift: Int, target: String): Char {
    when(target) {
        "enc" -> {
            val alphabet = if (letter.isLowerCase()) {
                "abcdefghijklmnopqrstuvwxyz"
            } else {
                "abcdefghijklmnopqrstuvwxyz".uppercase()
            }
            var newPosition = alphabet.indexOf(letter) + shift
            if (newPosition > (alphabet.length - 1)) newPosition = (newPosition - alphabet.length)
            return alphabet.elementAt(newPosition)
        }

        else -> {
            val alphabet = if (letter.isLowerCase()) {
                "abcdefghijklmnopqrstuvwxyz"
            } else {
                "abcdefghijklmnopqrstuvwxyz".uppercase()
            }
            var newPosition = alphabet.indexOf(letter) - shift
            if (newPosition < 0) newPosition = (alphabet.length - abs(newPosition))
            return alphabet.elementAt(newPosition)
        }
    }
}

fun getCorrespondingLetter(letter: Char): Char {
    val chars = ('a'..'z')
    val charsReversed = ('z' downTo 'a')
    return charsReversed.elementAt(chars.indexOf(letter))
}