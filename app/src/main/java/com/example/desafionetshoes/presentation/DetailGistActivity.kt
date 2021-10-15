package com.example.desafionetshoes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import coil.load
import com.example.desafionetshoes.R
import com.example.desafionetshoes.data.remoteDS.Gist
import com.example.desafionetshoes.data.localDS.GistFav

class DetailGistActivity : AppCompatActivity() {

    private lateinit var photoUser: ImageView
    private lateinit var nameUser: TextView
    private lateinit var filenameDetail: TextView
    private lateinit var filetypeDetail: TextView
    private lateinit var btnFavorite: ToggleButton

    private lateinit var Textoteste: TextView

    private val viewModel = DetailGistActivityViewModel()

    private lateinit var gistWorked : Gist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_gist)

        photoUser = findViewById(R.id.ImgPhoto)
        nameUser = findViewById(R.id.TxtNameUsr)
        filenameDetail = findViewById(R.id.TxtNameArchive)
        filetypeDetail = findViewById(R.id.TxtTypeArchive)
        btnFavorite = findViewById(R.id.TgbtnFavorite)


        var idGistChoosed = intent.getStringExtra(CHOOSED_GIST_ID)
        if (idGistChoosed != null){
            //chama a funcao da VM passando o parametro ID
            viewModel.getGistDetail(idGistChoosed)
            setupObserveGistDetail()
        }


        btnFavorite.setOnCheckedChangeListener(){ _, isChecked ->
            if (isChecked) {
                btnFavorite.setBackgroundResource(R.drawable.baseline_star_20)
                //chamo a funcao de favoritar o gist e mudo o atributo booleano
                var data = GistFav(gistWorked.id,
                    gistWorked.files.map { file -> file.value.filename }.toString(),
                    gistWorked.files.map { file -> file.value.type }.toString(),
                    gistWorked.owner.login, gistWorked.owner.avatar_url)
                viewModel.saveDataIntoDb(data)
                Toast.makeText(this,"favoritou", Toast.LENGTH_LONG).show()
                //Textoteste.text = "favoritou"

            } else {
                btnFavorite.setBackgroundResource(R.drawable.baseline_star_border_20)
                //verifico o atributo booleano, se estiver true chamo a funcao de desfavoritar e mudo para false
                var data = GistFav(gistWorked.id,
                    gistWorked.files.map { file -> file.value.filename }.toString(),
                    gistWorked.files.map { file -> file.value.type }.toString(),
                    gistWorked.owner.login, gistWorked.owner.avatar_url)
                viewModel.deleteFavs(data)
                Toast.makeText(this,"desfavoritou", Toast.LENGTH_LONG).show()
                //Textoteste.text = "desfavoritou"
            }
        }


    }

    fun setupObserveGistDetail() {
        viewModel.gistDetailLiveData.observe(this,
            { resposta ->
                nameUser.text = resposta.owner.login.uppercase()
                filenameDetail.text = resposta.files.map { file -> file.value.filename }.toString()
                filetypeDetail.text = resposta.files.map { file -> file.value.type }.toString()
                photoUser.load(resposta.owner.avatar_url)
                gistWorked = resposta

            }
        )
    }
}