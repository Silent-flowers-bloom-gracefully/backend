package com.lylist.silentflowers.infra.error

import com.lylist.silentflowers.domain.global.StatusException
import com.lylist.silentflowers.infra.exchange.ProblemResponse
import com.lylist.silentflowers.infra.exchange.ResponseWriter
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class ExceptionResolver(
    private val responseWriter: ResponseWriter
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun resolveException(response: HttpServletResponse, e: Exception) {
        when (e) {
            is StatusException -> {
                if (e.status.isError) {
                    responseWriter.error(
                        response, ProblemResponse(
                            status = e.status,
                            detail = e.message
                        )
                    )
                } else {
                    response.status = e.status.value()
                }
            }

            else -> {
                e.printStackTrace()
                responseWriter.error(
                    response,
                    ProblemResponse.of(HttpStatus.INTERNAL_SERVER_ERROR)
                )
            }
        }

        log.info("[status {${response.status}] Exception resolved: ${e.javaClass.name} - ${e.message}")
    }
}