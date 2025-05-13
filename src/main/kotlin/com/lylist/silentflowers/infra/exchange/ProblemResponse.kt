package com.lylist.silentflowers.infra.exchange

import org.springframework.http.HttpStatus

/**
 * RFC 7807에 의거한 표준 에러 응답 Body
 *
 * "type"과 "instance" 파라미터는 각 에러 응답에 대한 참조 가능한 문서가 없으므로 제외합니다.
 * "type"의 경우, RFC 표준에 의해 [about:blank](about:blank)를 기본값으로 갖게 됩니다.
 */
class ProblemResponse(
    status: HttpStatus,
    detail: String
) {

    companion object {
        fun of(status: HttpStatus) = ProblemResponse(status, status.reasonPhrase)
    }

    /**
     * HTTP 응답의 상태 코드
     */
    val status: Int = status.value()

    /**
     * 사람이 읽을 수 있는 에러에 대한 간략한 설명
     *
     * 에러 상황이 많지 않으므로, HTTP 상태 코드의 공식 명칭을 사용합니다.
     */
    val title: String = status.reasonPhrase

    /**
     * 사람이 읽을 수 있는 에러에 대한 자세한 설명
     */
    val detail: String = detail
}