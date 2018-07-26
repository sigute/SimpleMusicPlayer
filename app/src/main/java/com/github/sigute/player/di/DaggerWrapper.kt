package com.github.sigute.player.di

object DaggerWrapper {
    val component: PlayerComponent by lazy {
        DaggerPlayerComponent
                .builder()
                .playerModule(PlayerModule())
                .build()
    }
}
