package hosseinmirzaei.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hosseinmirzaei.myapplication.Database.User
import hosseinmirzaei.myapplication.Models.ToDos
import kotlinx.android.synthetic.main.template_row.view.*

class MyAdapter(private val names: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.usernameText.text = names[position].username
        holder.emailText.text = names[position].email
        holder.idText.text = names[position].id.toString()
    }

    override fun getItemCount(): Int {
        return names.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameText: TextView = itemView.usernameItem
        val emailText: TextView = itemView.emailItem
        val idText: TextView = itemView.idItem
    }

}