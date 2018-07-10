package com.github.sigute.repobrowser.di

import com.github.sigute.repobrowser.api.GithubService

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [(RepoBrowserModule::class)])
interface RepoBrowserComponent {
    val githubService: GithubService
}
