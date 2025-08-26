package com.formation.composecourse.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.formation.composecourse.data.common.serializer.AppPreferencesSerializer
import com.formation.composecourse.domain.common.model.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Singleton
    @Provides
    fun provideAppPreferencesSerializer(): AppPreferencesSerializer = AppPreferencesSerializer()

    @Singleton
    @Provides
    fun provideAppPreferencesDatastore(
        @ApplicationContext context: Context,
        serializer: AppPreferencesSerializer
    ): DataStore<AppPreferences> {
        return DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile("app_preferences.pb") }
        )
    }
}
