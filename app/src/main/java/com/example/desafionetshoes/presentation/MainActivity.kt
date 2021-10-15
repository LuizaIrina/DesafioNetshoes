package com.example.desafionetshoes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafionetshoes.R

class MainActivity : AppCompatActivity() {

    private lateinit var gistsAdapter: GistsAdapter
    private lateinit var rvGistsList: RecyclerView

    private lateinit var textoTeste: TextView

    private val viewModel = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGistsList = findViewById(R.id.RvListGists)
        gistsAdapter = GistsAdapter(this)
        rvGistsList.adapter = gistsAdapter
        rvGistsList.layoutManager = LinearLayoutManager(this)

        viewModel.getGists()
        setupObserveGistsList()

        //textoTeste = findViewById(R.id.TxtWelcome)

    }

    fun setupObserveGistsList() {
        viewModel.gistsLiveData.observe(this,
            { resposta ->
                resposta?.let {
                    gistsAdapter.dataset.addAll(it)
                    gistsAdapter.notifyDataSetChanged()
                }
            }
        )
    }


}