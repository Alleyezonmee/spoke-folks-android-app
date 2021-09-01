package com.soulinc.spokeFolks.base.di

import android.content.Context
import com.soulinc.spokeFolks.base.prefs.AuthPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * paras's creation on 09-07-2021
 */
@Module @InstallIn(SingletonComponent::class)
class SharedPreferenceModule {

  @Provides @Singleton fun provideAuthPrefs(@ApplicationContext context: Context): AuthPrefs = AuthPrefs(context)
}