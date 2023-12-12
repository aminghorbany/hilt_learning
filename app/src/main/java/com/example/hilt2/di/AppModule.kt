package com.example.hilt2.di

import android.content.Context
import com.example.hilt2.R
import com.example.hilt2.RES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named(RES_NAME)
    fun provideResName(@ApplicationContext context: Context) : String = context.getString(R.string.my_name)
}