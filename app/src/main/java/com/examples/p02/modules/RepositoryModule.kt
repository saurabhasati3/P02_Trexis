package com.examples.p02.modules

import com.examples.p02.data.LoginDataSource
import com.examples.p02.data.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesLoginRepository(loginDataSource: LoginDataSource) = LoginRepository(loginDataSource)


}