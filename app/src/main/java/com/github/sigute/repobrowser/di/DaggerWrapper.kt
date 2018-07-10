package com.github.sigute.repobrowser.di

object DaggerWrapper {
    val component: RepoBrowserComponent by lazy {
        DaggerRepoBrowserComponent
                .builder()
                .repoBrowserModule(RepoBrowserModule())
                .build()
    }
}
