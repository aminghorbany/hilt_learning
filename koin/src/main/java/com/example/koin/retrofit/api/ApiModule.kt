package com.example.koin.retrofit.api

import com.example.koin.BASE_URL
import com.example.koin.NETWORK_TIME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

//also we can use fun
const val baseURL = BASE_URL

const val networkTime = NETWORK_TIME

fun provideGson() : Gson = GsonBuilder().setLenient().create()

fun provideInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun provideClient(time : Long , interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder().apply {
    connectTimeout(time , TimeUnit.SECONDS)
    writeTimeout(time , TimeUnit.SECONDS)
    readTimeout(time , TimeUnit.SECONDS)
    addInterceptor(interceptor)
    retryOnConnectionFailure(true)
}.build()

fun provideRetrofit(baseURL : String , gson : Gson , client: OkHttpClient) : ApiServices = Retrofit.Builder()
    .baseUrl(baseURL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(ApiServices::class.java)


val networkModule = module {
    single { baseURL }
    single { networkTime }
    single { provideGson() }
    single { provideInterceptor() }
    single { provideClient(get() , get()) }
    single { provideRetrofit(get() , get() , get()) }
}
