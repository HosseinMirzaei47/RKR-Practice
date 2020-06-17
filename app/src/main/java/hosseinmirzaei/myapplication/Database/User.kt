package hosseinmirzaei.myapplication.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(

    @PrimaryKey(autoGenerate = true) val uId: Int,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "username") val username: String?,
    @ColumnInfo(name = "email") val email: String?

)