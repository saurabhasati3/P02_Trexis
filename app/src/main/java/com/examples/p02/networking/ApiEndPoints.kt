package com.examples.p02.networking

import com.examples.p02.data.model.Account
import com.examples.p02.data.model.Balance
import retrofit2.Response
import retrofit2.http.*

interface ApiEndPoints {
    @FormUrlEncoded
    @POST("/login")
    suspend fun login(@Field("username")  name:String , @Field("password")  passwd:String ): Response<Void>


    @GET("/accounts")
    suspend fun fetchAccounts(): Response<ArrayList<Account>>


    @GET("/transactions")
    suspend fun fetchTransactions(@Query("accountId") accountId:Int): Response<ArrayList<Balance>>

}