package br.com.consumindoapiqueens.View.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.consumindoapiqueens.Model.QueensResponseItem
import br.com.consumindoapiqueens.R
import br.com.consumindoapiqueens.View.UI.DatailsActivity
import com.squareup.picasso.Picasso

class QueensAdapter (

    private val queensList: List<QueensResponseItem>,
    val context: Context)
    : RecyclerView.Adapter<QueensAdapter.QueensViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueensViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false)
        return QueensViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueensViewHolder, position: Int) {
        val currentQueens = queensList.elementAt(position)
        Picasso.get().load(currentQueens.imageUrl).into(holder.imageQueen)
        holder.idQueen.text = currentQueens.id.toString()
        holder.nameQueen.text = currentQueens.name

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DatailsActivity::class.java)
            intent.putExtra("QUEEN", queensList[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return queensList.size
    }

    inner class QueensViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageQueen = itemView.findViewById<ImageView>(R.id.image_queen)
        val idQueen = itemView.findViewById<TextView>(R.id.id_queen)
        val nameQueen = itemView.findViewById<TextView>(R.id.name_queen)
    }

}