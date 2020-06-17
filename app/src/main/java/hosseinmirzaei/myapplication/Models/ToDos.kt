package hosseinmirzaei.myapplication.Models

import com.google.gson.annotations.SerializedName

class ToDos() {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("id")
    var id: Int = -1

    fun ToDos(title: String, id: Int) {
        this.title = title
        this.id = id
    }

}