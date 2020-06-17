package hosseinmirzaei.myapplication

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import hosseinmirzaei.myapplication.APIs.AuthApi
import hosseinmirzaei.myapplication.Database.AppDatabase
import hosseinmirzaei.myapplication.Database.User
import hosseinmirzaei.myapplication.Models.ToDos
import hosseinmirzaei.myapplication.Models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var progressBar: ProgressBar
    private lateinit var button: Button

    private var toDoList = arrayListOf<ToDos>()
    private var users = arrayListOf<User>()

    companion object {
        private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "users_table").build()

        sentUsersRequest()

    }

    private fun sentToDosRequest() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AuthApi::class.java)

        val call = service.getToDos()

        progressBar.visibility = View.VISIBLE
        call.enqueue(object : Callback<ArrayList<ToDos>> {

            override fun onResponse(call: Call<ArrayList<ToDos>>, response: Response<ArrayList<ToDos>>) {
                if (response.isSuccessful) {
                    println("jalil  onResponse")
                    toDoList.clear()
                    toDoList = response.body()!!
                    /*showRecycler(toDoList)*/

                } else {
                    println("jalil  onResponse else")
                    val toDos = ToDos()
                    toDos.id = -1
                    toDos.title = "Default"
                    toDoList.add(toDos)
                }
            }

            override fun onFailure(call: Call<ArrayList<ToDos>>, t: Throwable) {
                println("jalil  onFailure ${t.message}")
            }
        })

    }

    private fun sentUsersRequest() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AuthApi::class.java)
        val call = service.getAllUsers()

        progressBar.visibility = View.VISIBLE
        call.enqueue(object : Callback<ArrayList<User>> {

            override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                if (response.isSuccessful) {

                    println("jalil  onResponse")
                    users.clear()
                    users.addAll(response.body()!!)
                    PopulateDb(users, applicationContext).execute()
                    showRecycler(users)


                } else {
                    println("jalil  onResponse else")
                }
            }

            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                println("jalil  onFailure ${t.message}")
            }
        })

    }

    private fun showRecycler(list: ArrayList<User>) {

        recyclerView = findViewById(R.id.recyclerView)
        adapter = MyAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        progressBar.visibility = View.INVISIBLE

    }

    class TestDb(private val db: AppDatabase) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            println("jalil ${db.userDao().findByName("Bret").email}")
            return null
        }

    }

    private fun findViews() {
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
    }

}