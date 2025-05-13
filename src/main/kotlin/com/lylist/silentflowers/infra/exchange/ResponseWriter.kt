package com.lylist.silentflowers.infra.exchange

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus

/**
 * Filter 환경 등 프레임워크와 라이브러리에 의해 응답을 관리받을 수 없는 경우, 수동으로 응답 내용을 작성하기 위한 모듈
 *
 * 모든 응답은 [JSON][org.springframework.http.MediaType.APPLICATION_JSON]으로 변환됨
 */
interface ResponseWriter {

    fun error(response: HttpServletResponse, content: ProblemResponse)

    fun write(response: HttpServletResponse, content: Any, status: HttpStatus?)
}