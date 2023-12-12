package com.example.hilt2.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Family()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FirstName()