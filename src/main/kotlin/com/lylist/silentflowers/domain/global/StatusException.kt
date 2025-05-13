package com.lylist.silentflowers.domain.global

import org.springframework.http.HttpStatus

open class StatusException(
    val status: HttpStatus,
    override val message: String = status.reasonPhrase,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)