package com.app.easyleasy.dagger.modules

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.app.easyleasy.BuildConfig
import com.app.easyleasy.dagger.ApplicationContext
import com.app.easyleasy.api.network.NetworkHelper
import com.app.easyleasy.api.network.NetworkService
import com.app.easyleasy.api.network.Networking
import com.app.easyleasy.application.AppineersApplication
import com.app.easyleasy.commonUtils.rx.RxSchedulerProvider
import com.app.easyleasy.commonUtils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AppineersApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    /**
     * We need to write @Singleton on the provide method if we are create the instance inside this method
     * to make it singleton. Even if we have written @Singleton on the instance's class
     */
    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            "",
            BuildConfig.Base_Url,
            application
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    @Provides
    fun provideAlertDialogBuilder(): AlertDialog.Builder = AlertDialog.Builder(application)

    @Provides
    fun provideLayoutInflater(): LayoutInflater = LayoutInflater.from(application)

}