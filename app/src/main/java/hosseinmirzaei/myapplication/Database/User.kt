package hosseinmirzaei.myapplication.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
class User {

    @PrimaryKey(autoGenerate = true)
    var uId: Int = 0

    var id: Int = -1

    var username: String = ""

    var email: String = ""

}