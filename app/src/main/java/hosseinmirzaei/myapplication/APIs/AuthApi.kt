package hosseinmirzaei.myapplication.APIs

import hosseinmirzaei.myapplication.Models.ToDos
import hosseinmirzaei.myapplication.Models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Call<User>

    @GET("todos")
    fun getToDos(
    ): Call<ArrayList<ToDos>>

}