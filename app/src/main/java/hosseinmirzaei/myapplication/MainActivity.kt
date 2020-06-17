package hosseinmirzaei.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hosseinmirzaei.myapplication.APIs.AuthApi
import hosseinmirzaei.myapplication.Models.ToDos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerView.Adapter<*>
    lateinit var progressBar: ProgressBar
    private lateinit var button: Button

    private var toDoList = arrayListOf<ToDos>()

    companion object {
        private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()

        sendRequest()

    }

    private fun sendRequest() {

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
                    showRecycler(toDoList)

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

    private fun showRecycler(list: ArrayList<ToDos>) {

        recyclerView = findViewById(R.id.recyclerView)
        adapter = MyAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        progressBar.visibility = View.INVISIBLE

    }

    private fun findViews() {
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
    }

}