package br.com.consumindoapiqueens.View.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.consumindoapiqueens.Model.QueensResponseItem
import br.com.consumindoapiqueens.R
import br.com.consumindoapiqueens.View.Adapter.QueensAdapter
import br.com.consumindoapiqueens.ViewModel.ViewModelQueens
import java.util.*

class MainActivity : AppCompatActivity() {
    private val results = mutableListOf<QueensResponseItem>()

    val recycler by lazy { findViewById<RecyclerView>(R.id.recycler_main) }
    val viewModelQueens by lazy { ViewModelProviders.of(this).get(ViewModelQueens::class.java) }

    lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById<ProgressBar>(R.id.progress_bar)

        val queensAdapter = QueensAdapter(results, this)
        recycler.adapter = queensAdapter
        var layoutManager = GridLayoutManager(this, 2)
        recycler.layoutManager = layoutManager

        viewModelQueens.listMutableQueens.observe(this, androidx.lifecycle.Observer {
            it?.let{itQuenns -> results.addAll(itQuenns)}
            queensAdapter.notifyDataSetChanged()
        })

        viewModelQueens.loading.observe(this, androidx.lifecycle.Observer {
            if(it){
                progressBar.visibility = VISIBLE
            } else{
                progressBar.visibility = GONE
            }
        })

        viewModelQueens.errorMessage.observe(this, androidx.lifecycle.Observer {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })

    }
}