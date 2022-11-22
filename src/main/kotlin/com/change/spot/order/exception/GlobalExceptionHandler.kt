package com.change.spot.order.exception

import com.change.spot.order.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException?): ErrorResponse {
        return  ErrorResponse(
            HttpStatus.BAD_REQUEST.name, e!!.message
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseBody
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException?): ErrorResponse {
        return ErrorResponse(
            HttpStatus.BAD_REQUEST.name, e!!.message!!
        )
    }

    @ExceptionHandler(InvalidOrderException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleException(ex: InvalidOrderException): ErrorResponse? {
        return ErrorResponse(
            HttpStatus.BAD_REQUEST.name, ex.message!!
        )
    }
}