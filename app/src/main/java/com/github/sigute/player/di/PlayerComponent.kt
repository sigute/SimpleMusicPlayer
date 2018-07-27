package com.github.sigute.player.di

import android.media.MediaPlayer
import com.github.sigute.player.api.HeartThisService

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [(PlayerModule::class)])
interface PlayerComponent {
    val heartThisService: HeartThisService
    val mediaPlayer: MediaPlayer
}
