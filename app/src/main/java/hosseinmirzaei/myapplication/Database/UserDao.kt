package hosseinmirzaei.myapplication.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("select * from users_table")
    fun getAll(): List<User>

    @Query("SELECT * FROM users_table WHERE username LIKE :username")
    fun findByName(username: String): User

    @Query("SELECT * FROM users_table WHERE email LIKE :email")
    fun findByEmail(email: String): User

    @Insert
    fun insertUser(users: User)

}