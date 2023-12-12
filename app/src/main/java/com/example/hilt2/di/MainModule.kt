package com.example.hilt2.di

import android.content.Context
import com.example.hilt2.FAMILY
import com.example.hilt2.NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    @FirstName
    fun provideName(): String = "Haj Amin"

    @Provides
    @Singleton
    fun provideMiddleName(): String = "Mohammad"

    @Provides
    @Singleton
    @Family
    fun provideFamily(): String = "Ghorbany"

    @Provides
    @Singleton
    @Named("LastName")
    fun provideLastName(@ApplicationContext context: Context): String = "Leilestani"
}
