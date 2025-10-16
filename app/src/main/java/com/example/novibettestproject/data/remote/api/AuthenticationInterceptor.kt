package com.example.novibettestproject.data.remote.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authenticationToken = tokenProvider.getToken()
        val requestBuilder = originalRequest.newBuilder()

        return if (authenticationToken.value == null) {
            chain.proceed(requestBuilder.build())
        } else {
            chain.proceed(
                requestBuilder
                    .addHeader(AUTHENTICATION_HEADER, "Bearer ${authenticationToken.value}")
                    .build()
            )
        }
    }
}