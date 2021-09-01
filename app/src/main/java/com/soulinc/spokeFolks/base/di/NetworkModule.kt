package com.soulinc.spokeFolks.base.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.soulinc.spokeFolks.BASE_URL
import com.soulinc.spokeFolks.base.network.NetworkAuthenticator
import com.soulinc.spokeFolks.base.network.NetworkInterceptor
import com.soulinc.spokeFolks.base.prefs.AuthPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * paras's creation on 01-05-2021
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideGson(): Gson = GsonBuilder().create()

  @Provides
  @Singleton
  fun provideGsonConverter(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

  @Provides
  @Singleton
  fun provideNetworkInterceptor(authPrefs: AuthPrefs): NetworkInterceptor = NetworkInterceptor(authPrefs)

  @Provides
  @Singleton
  fun provideNetworkAuthenticator(authPrefs: AuthPrefs): NetworkAuthenticator = NetworkAuthenticator(authPrefs)

  @Provides
  @Singleton
  fun provideOkHttpClient(authenticator: NetworkAuthenticator, interceptor: NetworkInterceptor): OkHttpClient = OkHttpClient.Builder().apply {
    authenticator(authenticator)
    addInterceptor(interceptor)
  }.build()

  @Provides
  @Singleton
  fun provideRetrofitBuilder(gsonConverterFactory: Converter.Factory): Retrofit.Builder = Retrofit.Builder().addConverterFactory(gsonConverterFactory)

  @Provides
  @Singleton
  fun provideRetrofit(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): Retrofit = retrofitBuilder.apply {
    baseUrl(BASE_URL)
    client(okHttpClient)
  }.build()
}