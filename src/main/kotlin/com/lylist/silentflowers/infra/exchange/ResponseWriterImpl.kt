package com.lylist.silentflowers.infra.exchange

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

/**
 * @see ResponseWriter
 */
@Component
class ResponseWriterImpl(
    private val objectMapper: ObjectMapper
) : ResponseWriter {

    override fun error(response: HttpServletResponse, content: ProblemResponse) {
        response.run {
            status = content.status
            contentType = MediaType.APPLICATION_PROBLEM_JSON_VALUE
            characterEncoding = "UTF-8"

            writer.run {
                write(objectMapper.writeValueAsString(content))
            }
        }
    }

    override fun write(response: HttpServletResponse, content: Any, status: HttpStatus?) {
        response.run {
            if (status != null) this.status = status.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = "UTF-8"

            writer.run {
                write(objectMapper.writeValueAsString(content))
            }
        }
    }
}