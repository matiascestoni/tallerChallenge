package com.test.tallerchallenge.data

import com.test.tallerchallenge.common.Response
import com.test.tallerchallenge.domain.LoginRepository
import kotlinx.coroutines.delay

class LoginRepositoryImpl : LoginRepository {

    override suspend fun login(username: String, password: String): Response<Boolean> {
        delay(1000)
        return if (username == "admin" && password == "admin") {
            Response.Success(true)
        } else {
            Response.Error("Invalid credentials")
        }
    }
}