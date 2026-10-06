package com.example.medicineretail.di

import com.example.medicineretail.data.remote.ApiServices
import com.example.medicineretail.models.RetrofitInstance
import com.example.medicineretail.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiServices(): ApiServices {
        return RetrofitInstance.apiServices
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiServices: ApiServices
    ): AuthRepository {
        return AuthRepository(apiServices)
    }
}