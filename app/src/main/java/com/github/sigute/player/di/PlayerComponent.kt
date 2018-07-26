package com.github.sigute.player.di

import com.github.sigute.player.api.HeartThisService

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [(PlayerModule::class)])
interface PlayerComponent {
    val heartThisService: HeartThisService
}
