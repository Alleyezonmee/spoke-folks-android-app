package com.soulinc.spokeFolks.base.di

import com.soulinc.spokeFolks.auth.data.network.IAuthService
import com.soulinc.spokeFolks.auth.data.repo.AuthRepositoryImpl
import com.soulinc.spokeFolks.auth.data.repo.IAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * paras's creation on 01-05-2021
 */
@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

  @Provides
  @ViewModelScoped
  fun getAuthRepository(service: IAuthService): IAuthRepository = AuthRepositoryImpl(service)
}