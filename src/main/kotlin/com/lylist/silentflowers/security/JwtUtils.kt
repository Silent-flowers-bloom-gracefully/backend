package com.lylist.silentflowers.security

import com.lylist.silentflowers.domain.user.port.out.CreateAuthenticationPort
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils : CreateAuthenticationPort, GetUsernameOnAuthentication {

    private val key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    private val expiration = 1056771434

    override fun ofUsername(username: String): String = Jwts.builder()
        .setSubject(username)
        .setIssuedAt(Date())
        .setExpiration(Date(Date().time + expiration))
        .signWith(key)
        .compact()

    override fun getUsername(authentication: String): String =
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJwt(authentication)
            .body.subject
}