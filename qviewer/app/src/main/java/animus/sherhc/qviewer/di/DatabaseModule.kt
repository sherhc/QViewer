package animus.sherhc.qviewer.di

import android.content.Context
import androidx.room.Room
import animus.sherhc.qviewer.data.AlbumDao
import animus.sherhc.qviewer.data.AppDatabase
import animus.sherhc.qviewer.data.SiteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSiteDao(database: AppDatabase): SiteDao {
        return database.siteDao()
    }

    @Provides
    fun provideAlbumDao(database: AppDatabase): AlbumDao {
        return database.albumDao()
    }
}