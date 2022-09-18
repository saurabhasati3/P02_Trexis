package com.examples.p02.data

import com.examples.p02.data.model.Account
import com.examples.p02.data.model.Balance
import com.examples.p02.networking.ApiEndPoints
import java.io.IOException
import javax.inject.Inject

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

class RemoteDataSource @Inject constructor(private val apiEndPoints: ApiEndPoints) {

    suspend fun fetchAccountsList(): Result<ArrayList<Account>> {

        val resp = apiEndPoints.fetchAccounts()

        return if (resp.isSuccessful) {
            Result.Success(resp.body() as ArrayList<Account>)
        } else {
            Result.Error(IOException("Error fetching in", null))
        }
    }

    suspend fun fetchTransactions(accountId:Int): Result<ArrayList<Balance>> {

        val resp = apiEndPoints.fetchTransactions(accountId)

        return if (resp.isSuccessful) {
            Result.Success(resp.body() as ArrayList<Balance>)
        } else {
            Result.Error(IOException("Error fetching in", null))
        }
    }


}