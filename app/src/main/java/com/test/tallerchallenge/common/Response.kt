package com.test.tallerchallenge.common

sealed class Response<T> {
    data class Success<T>(val data: Any) : Response<T>()
    data class Error<T>(val message: String) : Response<T>()
}