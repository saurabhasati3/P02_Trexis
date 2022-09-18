package com.examples.p02.modules

import com.examples.p02.data.RemoteDataSource
import com.examples.p02.data.LoginDataSource
import com.examples.p02.networking.ApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesLoginDataSource(apiEndPoints: ApiEndPoints) = LoginDataSource(apiEndPoints)

    @Provides
    @Singleton
    fun providesAccountsDataSource(apiEndPoints: ApiEndPoints) = RemoteDataSource(apiEndPoints)
}