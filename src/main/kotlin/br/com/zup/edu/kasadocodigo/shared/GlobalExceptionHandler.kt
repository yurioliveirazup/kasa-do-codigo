package br.com.zup.edu.kasadocodigo.shared

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun handle(ex: MethodArgumentNotValidException) = ex.fieldErrors
            .map { mapOf(it.field to it.defaultMessage) }
            .let { ResponseEntity.badRequest().body(it) }
}