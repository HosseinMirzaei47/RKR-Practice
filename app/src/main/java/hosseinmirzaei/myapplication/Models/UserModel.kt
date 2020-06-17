package hosseinmirzaei.myapplication.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserModel() {

    @SerializedName("id")
    @Expose
    var id: Int = -1

    @SerializedName("username")
    @Expose
    var username: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("website")
    @Expose
    var website: String? = null

    fun User(id: Int, username: String, email: String, website: String) {
        this.id = id
        this.username = username
        this.email = email
        this.website = website
    }

    fun User() {

    }

}
