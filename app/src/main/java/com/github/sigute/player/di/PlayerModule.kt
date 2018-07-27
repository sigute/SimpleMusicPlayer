package com.github.sigute.player.di

import android.media.MediaPlayer
import com.github.sigute.player.api.HeartThisService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class PlayerModule {
    @Provides
    @Singleton
    internal fun provideHeartThisService(retrofit: Retrofit): HeartThisService {
        return retrofit.create(HeartThisService::class.java)
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(" https://api-v2.hearthis.at")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun providesMediaPlayer(): MediaPlayer {
        return MediaPlayer()
    }
}
