package com.soulinc.spokeFolks.base.di

import com.soulinc.spokeFolks.account.data.network.IAccountService
import com.soulinc.spokeFolks.auth.data.network.IAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

/**
 * paras's creation on 01-05-2021
 */
@Module
@InstallIn(ViewModelComponent::class)
class ServiceModule {

  @Provides
  @ViewModelScoped
  fun provideAuthService(retrofit: Retrofit): IAuthService = retrofit.create(IAuthService::class.java)

  @Provides
  @ViewModelScoped
  fun provideAccountService(retrofit: Retrofit): IAccountService = retrofit.create(IAccountService::class.java)
}