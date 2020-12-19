package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Song::class], version = 1, exportSchema = false)
abstract class SongsDB : RoomDatabase() {
    abstract fun songDao(): SongDao?

    companion object {
        const val DATABASE_NAME = "databases/songs.db"
        var instance: SongsDB? = null
        fun getInstance(context: Context): SongsDB? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongsDB::class.java, "songs.db"
                )
                    .allowMainThreadQueries() //можно будет делать запросы на мейн потоки
                    .createFromAsset(DATABASE_NAME)
                    .build()
            }
            return instance
        }
    }
}