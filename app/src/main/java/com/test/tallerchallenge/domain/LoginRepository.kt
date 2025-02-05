package com.test.tallerchallenge.domain

import com.test.tallerchallenge.common.Response

interface LoginRepository {
    suspend fun login(username: String, password: String): Response<Boolean>
}