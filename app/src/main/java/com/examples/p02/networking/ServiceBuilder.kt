package com.examples.p02.networking

import retrofit2.Retrofit
import javax.inject.Inject

class ServiceBuilder @Inject constructor(private val retrofit: Retrofit) {
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}