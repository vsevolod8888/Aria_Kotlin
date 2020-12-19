package database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "songs")
data class Song( @PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name = "ID")
                 var ID:Int = 0,

    @ColumnInfo(name = "song_name")
var songName: String,

@ColumnInfo(name = "file_name")
var fileName: String) {

}