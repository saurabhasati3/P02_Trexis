package com.examples.p02.modules

import com.examples.p02.networking.ApiEndPoints
import com.examples.p02.networking.ServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun provideUserService(serviceBuilder: ServiceBuilder): ApiEndPoints =
        serviceBuilder.buildService(ApiEndPoints::class.java)

}