package com.giphytesttask.di

import com.giphytesttask.BuildConfig.BASE_URL
import com.giphytesttask.data.GifRepository
import com.giphytesttask.data.GifRepositoryImpl
import com.giphytesttask.data.Mapper
import com.giphytesttask.data.network.GiphyApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideMyDispatchers(): MyDispatchers = MyDispatchers()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideApiServiceGiphy(client: OkHttpClient, moshi: Moshi): GiphyApiService {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
            .create(GiphyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMapper(): Mapper = Mapper()

    @Provides
    @Singleton
    fun provideGifRepository(
        dispatchers: MyDispatchers,
        giphyApiService: GiphyApiService,
        mapper: Mapper
    ): GifRepository = GifRepositoryImpl(dispatchers, giphyApiService, mapper)
}