package com.github.sigute.player.di

import com.github.sigute.player.api.GithubService

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [(RepoBrowserModule::class)])
interface RepoBrowserComponent {
    val githubService: GithubService
}
