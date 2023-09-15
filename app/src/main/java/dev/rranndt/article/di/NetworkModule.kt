package dev.rranndt.article.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rranndt.article.R
import dev.rranndt.article.data.remote.NewsApi
import dev.rranndt.article.util.UiText
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideInterceptor(application: Application): Interceptor {
        return Interceptor { chain ->
            chain.proceed(
                chain
                    .request()
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        UiText.Resource(R.string.api_key).asString(application)
                    )
                    .build()
            )
        }
    }

    @Provides
    @Singleton
    fun provideClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            // Level BODY should only be allowed to run in debugging
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsAPi(
        okHttpClient: OkHttpClient,
        application: Application
    ): NewsApi {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(UiText.Resource(R.string.base_url).asString(application))
            .client(okHttpClient)
            .build()
            .create(NewsApi::class.java)
    }
}