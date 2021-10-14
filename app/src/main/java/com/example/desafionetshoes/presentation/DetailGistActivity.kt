package com.example.desafionetshoes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import com.example.desafionetshoes.R

class DetailGistActivity : AppCompatActivity() {

    private lateinit var photoUser: ImageView
    private lateinit var nameUser: TextView
    private lateinit var infoDetail: TextView
    private lateinit var btnFavorite: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_gist)

        photoUser = findViewById(R.id.ImgPhoto)
        nameUser = findViewById(R.id.TxtNameUsr)
        infoDetail = findViewById(R.id.TxtDetail)
        btnFavorite = findViewById(R.id.TgbtnFavorite)




    }
}