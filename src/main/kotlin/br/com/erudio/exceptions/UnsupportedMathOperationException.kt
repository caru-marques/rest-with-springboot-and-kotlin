package br.com.erudio.exceptions

import java.lang.RuntimeException

class UnsupportedMathOperationException(exception: String?): RuntimeException(exception)