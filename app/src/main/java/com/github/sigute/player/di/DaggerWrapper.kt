package com.github.sigute.player.di

object DaggerWrapper {
    val component: RepoBrowserComponent by lazy {
        DaggerRepoBrowserComponent
                .builder()
                .repoBrowserModule(RepoBrowserModule())
                .build()
    }
}
