package com.example.userlistdemo.di

import com.example.userlistdemo.BuildConfig
import com.example.userlistdemo.networkapi.DemoAppApi
import com.example.userlistdemo.repository.UserRepository
import com.example.userlistdemo.repository.UserRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideUserRepository(api: DemoAppApi): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    fun provideDemoApi(retrofit: Retrofit): DemoAppApi {
        return retrofit.create(DemoAppApi::class.java)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create())
            ).build()
    }

}