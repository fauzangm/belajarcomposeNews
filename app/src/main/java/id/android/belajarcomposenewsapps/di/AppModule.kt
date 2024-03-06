package id.android.belajarcomposenewsapps.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.android.belajarcomposenewsapps.data.pref.LocalUserPrefImpl
import id.android.belajarcomposenewsapps.domain.pref.LocalUserPref
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.AppEntryUseCases
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.ReadAppEntry
import id.android.belajarcomposenewsapps.domain.usecase.app_entry.SaveAppEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserPref(
        application: Application
    ): LocalUserPref = LocalUserPrefImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserPref: LocalUserPref
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserPref),
        saveAppEntry = SaveAppEntry(localUserPref)
    )
}