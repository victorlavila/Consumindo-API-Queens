package br.com.consumindoapiqueens.View.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.consumindoapiqueens.Model.QueensResponseItem
import br.com.consumindoapiqueens.R
import com.squareup.picasso.Picasso

class DatailsActivity : AppCompatActivity() {

    private val imageDetail by lazy { findViewById<ImageView>(R.id.image_detail) }
    private val descriptionQueen by lazy { findViewById<TextView>(R.id.description) }
    private val nameDescription by lazy { findViewById<TextView>(R.id.name_detail) }
    private val button by lazy { findViewById<Button>(R.id.button_detail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datails)

        val showDetailsQueen = intent.extras

        if(showDetailsQueen != null){
            val queen = showDetailsQueen.getSerializable("QUEEN") as QueensResponseItem
            Picasso.get().load(queen.imageUrl).into(imageDetail)
            descriptionQueen.text = queen.quote
            nameDescription.text = queen.name
        }

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}