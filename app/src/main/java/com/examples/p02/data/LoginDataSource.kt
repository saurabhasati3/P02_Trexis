package com.examples.p02.data

import com.examples.p02.data.model.LoggedInUser
import com.examples.p02.networking.ApiEndPoints
import java.io.IOException
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

class LoginDataSource @Inject constructor(private val apiEndPoints: ApiEndPoints) {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {

        val resp = apiEndPoints.login(username, password)

        return if (resp.isSuccessful) {
            val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString(), "Morty")
            Result.Success(fakeUser)
        } else {
            Result.Error(IOException("Error logging in", null))
        }

    }

    fun logout() {
        // TODO: revoke authentication
    }
}