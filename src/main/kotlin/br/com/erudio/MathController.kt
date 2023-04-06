package br.com.erudio

import br.com.erudio.exceptions.ExceptionResponse
import br.com.erudio.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.sqrt

@RestController
class MathController {
    val counter: AtomicLong = AtomicLong()

    @RequestMapping("/sum/{firstNumber}/{secondNumber}")
    fun sum(
        @PathVariable(value = "firstNumber") firstNumber: String?,
        @PathVariable(value = "secondNumber") secondNumber: String?
    ): Double {
        if ( !isNumeric(firstNumber) || !isNumeric(secondNumber) )
            throw UnsupportedMathOperationException("Please, set a numeric value")

        return convertNumber(firstNumber) + convertNumber(secondNumber)
    }

    @RequestMapping("subtraction/{firstNumber}/{secondNumber}")
    fun subtraction(
        @PathVariable(value = "firstNumber") firstNumber: String?,
        @PathVariable(value = "secondNumber") secondNumber: String?
    ): Double {
        if ( !isNumeric(firstNumber) || !isNumeric(secondNumber) )
            throw UnsupportedMathOperationException("Please, set a numeric value")

        return convertNumber(firstNumber) - convertNumber(secondNumber)
    }

    @RequestMapping("multiplication/{firstNumber}/{secondNumber}")
    fun multiplication(
        @PathVariable(value = "firstNumber") firstNumber: String?,
        @PathVariable(value = "secondNumber") secondNumber: String?
    ): Double {
        if ( !isNumeric(firstNumber) || !isNumeric(secondNumber) )
            throw UnsupportedMathOperationException("Please, set a numeric value")

        return convertNumber(firstNumber) * convertNumber(secondNumber)
    }

    @RequestMapping("division/{firstNumber}/{secondNumber}")
    fun division(
        @PathVariable(value = "firstNumber") firstNumber: String?,
        @PathVariable(value = "secondNumber") secondNumber: String?
    ): Double {
        if ( !isNumeric(firstNumber) || !isNumeric(secondNumber) )
            throw UnsupportedMathOperationException("Please, set a numeric value")
        if ( secondNumber == "0.0" || secondNumber == "0" )
            throw UnsupportedMathOperationException("Numbers cannot be divided by zero")

        return convertNumber(firstNumber) / convertNumber(secondNumber)
    }

    @RequestMapping("mean/{firstNumber}/{secondNumber}")
    fun mean(
        @PathVariable(value = "firstNumber") firstNumber: String?,
        @PathVariable(value = "secondNumber") secondNumber: String?
    ): Double {
        if ( !isNumeric(firstNumber) || !isNumeric(secondNumber) )
            throw UnsupportedMathOperationException("Please, set a numeric value")

        return ( convertNumber(firstNumber) + convertNumber(secondNumber) ) / 2
    }

    @RequestMapping("squareRoot/{firstNumber}")
    fun squareRoot(
        @PathVariable(value = "firstNumber") firstNumber: String?
    ): Double {
        if ( !isNumeric(firstNumber) )
            throw UnsupportedMathOperationException("Please, set a numeric value")
        if ( firstNumber == "0.0" || firstNumber == "0" )
            throw UnsupportedMathOperationException("Please, set a positive number")

        return sqrt(convertNumber(firstNumber))
    }

    private fun convertNumber(strNumber: String?): Double {
        if ( strNumber.isNullOrBlank() ) return 0.0

        val number = strNumber.replace(",".toRegex(), ".")

        return if ( isNumeric(number) ) number.toDouble() else 0.0
    }

    private fun isNumeric(strNumber: String?): Boolean {
        if ( strNumber.isNullOrBlank() ) return false

        val number = strNumber.replace(",".toRegex(), ".")

        return number.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }
}