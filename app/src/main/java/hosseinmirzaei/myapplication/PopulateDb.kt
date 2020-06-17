package hosseinmirzaei.myapplication

import android.content.Context
import android.os.AsyncTask
import androidx.room.Room
import hosseinmirzaei.myapplication.Database.AppDatabase
import hosseinmirzaei.myapplication.Database.User

class PopulateDb(private val users: ArrayList<User>, private val applicationContext: Context) :
    AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg p0: Void?): Void? {
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "users_table").build()
        (0 until users.size).forEach {
            db.userDao().insertUser(users[it]); println("jalil   loading users")
        }
        return null
    }

}