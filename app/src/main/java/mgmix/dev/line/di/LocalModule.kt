package mgmix.dev.line.di

import android.content.Context
import androidx.room.Room

import dagger.Module
import dagger.Provides
import mgmix.dev.line.repository.data.db.LineDatabase
import mgmix.dev.line.repository.data.db.NoteDatabase
import mgmix.dev.line.repository.data.db.LineDatabaseImpl

@Module
internal class LocalModule {


    @Provides
    fun provideLineDatabase(
        noteDb: NoteDatabase
    ): LineDatabase {
        return LineDatabaseImpl(
            noteDb.homeDao()
        )
    }


    @Provides
    fun createNoteDatabase(
        context: Context
    ): NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "line.db"
        ).build()
    }


}