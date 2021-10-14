package com.example.desafionetshoes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import coil.load
import com.example.desafionetshoes.R
import com.example.desafionetshoes.presentation.CHOOSED_GIST_ID

class DetailGistActivity : AppCompatActivity() {

    private lateinit var photoUser: ImageView
    private lateinit var nameUser: TextView
    private lateinit var infoDetail: TextView
    private lateinit var btnFavorite: ToggleButton

    private val viewModel = DetailGistActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_gist)

        photoUser = findViewById(R.id.ImgPhoto)
        nameUser = findViewById(R.id.TxtNameUsr)
        infoDetail = findViewById(R.id.TxtDetail)
        btnFavorite = findViewById(R.id.TgbtnFavorite)

        var idGistChoosed = intent.getStringExtra(CHOOSED_GIST_ID)
        if (idGistChoosed != null){
            //chama a funcao da VM passando o parametro ID
            viewModel.getGistDetail(idGistChoosed)
            setupObserveGistDetail()

        }

    }

    fun setupObserveGistDetail() {
        viewModel.gistDetailLiveData.observe(this,
            { resposta ->
                nameUser.text = resposta.owner.login
                infoDetail.text = resposta.files.map { file -> file.value.type }.toString()
                photoUser.load(resposta.owner.avatar_url)

            }
        )
    }
}