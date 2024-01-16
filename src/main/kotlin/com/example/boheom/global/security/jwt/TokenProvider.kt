package com.example.boheom.global.security.jwt

import com.example.boheom.global.exception.TokenExpiredException
import com.example.boheom.global.exception.TokenInvalidException
import com.example.boheom.global.security.auth.AuthDetailsService
import com.example.boheom.domain.user.domain.RefreshToken
import com.example.boheom.domain.user.domain.repository.RefreshTokenRepository
import com.example.boheom.domain.user.presentation.dto.response.TokenResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.util.Date
import javax.servlet.http.HttpServletRequest

@Component
class TokenProvider(
    private val tokenProperties: TokenProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun generateToken(accountId: String): TokenResponse {
        val accessToken: String = generateAccessToken(accountId)
        val refreshToken: String = generateRefreshToken(accountId)
        return TokenResponse(accessToken, refreshToken)
    }

    fun generateAccessToken(accountId: String): String {
        return createToken(accountId, "access", tokenProperties.accessExp)
    }

    fun generateRefreshToken(accountId: String): String {
        val refreshToken = createToken(accountId, "refresh", tokenProperties.refreshExp)
        refreshTokenRepository.save(RefreshToken(accountId, refreshToken))
        return refreshToken
    }

    private fun createToken(accountId: String, typ: String, exp: Long): String {
        return Jwts.builder()
            .setSubject(accountId)
            .claim("typ", typ)
            .signWith(SignatureAlgorithm.HS256, tokenProperties.secretKey)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()
    }

    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getAccountId(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getAccountId(token: String): String {
        return getClaims(token).subject
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey(tokenProperties.secretKey)
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException
        } catch (e: Exception) {
            e.printStackTrace()
            throw TokenInvalidException
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(tokenProperties.header)

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenProperties.prefix)
            && bearerToken.length > tokenProperties.prefix.length + 1
        ) {
            bearerToken.substring(tokenProperties.prefix.length)
        } else null
    }
}