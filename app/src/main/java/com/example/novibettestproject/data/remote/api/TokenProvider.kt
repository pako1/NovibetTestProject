package com.example.novibettestproject.data.remote.api

import com.example.novibettestproject.data.remote.models.Token

class TokenProvider {

    private var _token: Token = Token(null)

    fun isTokenAvailable(): Boolean = _token.value != null

    fun getToken(): Token {
        return _token
    }

    fun setToken(token: Token) {
        _token = token
    }
}