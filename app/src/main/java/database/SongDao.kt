package database

import androidx.room.Dao
import androidx.room.Query


@Dao
interface SongDao {
    @Query("SELECT *FROM songs WHERE id = :songId")
    fun getSongById(songId: Int): Song?
}