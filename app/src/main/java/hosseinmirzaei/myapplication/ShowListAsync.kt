package hosseinmirzaei.myapplication

import android.os.AsyncTask
import android.util.Log
import hosseinmirzaei.myapplication.APIs.AuthApi
import hosseinmirzaei.myapplication.Models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShowListAsync(private val names: ArrayList<String>) : AsyncTask<Void, Void, ArrayList<String>>() {

    companion object {
        private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(AuthApi::class.java)

    override fun doInBackground(vararg p0: Void?): ArrayList<String> {

        val call = service.getUser(1)

        call.enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful && response.body()!!.username != null) {

                    names.add(response.body()!!.username!!)
                    Log.i("jalil", response.body()!!.username!!)

                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Log.i("jalil", t.message)
            }
        })
        return names

    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
    }
}