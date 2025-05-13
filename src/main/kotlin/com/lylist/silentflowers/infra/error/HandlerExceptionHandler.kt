package com.lylist.silentflowers.infra.error

import com.lylist.silentflowers.domain.global.StatusException
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.HandlerMethodValidationException
import org.springframework.web.multipart.support.MissingServletRequestPartException

@ControllerAdvice
class HandlerExceptionHandler(
    private val exceptionResolver: ExceptionResolver
) {

    @ExceptionHandler(
        exception = [
            MissingServletRequestParameterException::class,
            MissingPathVariableException::class,
            TypeMismatchException::class,
            MissingServletRequestParameterException::class,
            MissingServletRequestPartException::class,
            ServletRequestBindingException::class,
            HttpMessageNotReadableException::class,
            MethodArgumentNotValidException::class,
            HandlerMethodValidationException::class
        ]
    )
    fun handleBadRequestException(exception: Exception, response: HttpServletResponse) {
        exceptionResolver.resolveException(
            response,
            StatusException(HttpStatus.BAD_REQUEST, exception.message ?: "Bad Request", exception)
        );
    }

    @ExceptionHandler(StatusException::class)
    fun handleStatusException(exception: StatusException, response: HttpServletResponse) {
        exceptionResolver.resolveException(response, exception)
    }
}