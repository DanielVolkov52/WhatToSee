package ru.otus.whattosee

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class FilmDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var filmItem =
            (application as App).filmItems.find { it.filmId == intent.getIntExtra("filmId", 0) }
        if (filmItem == null) {
            filmItem = (application as App).filmItems[0]//заглушка
        }
        setContentView(R.layout.film_description_activity)
        findViewById<TextView>(R.id.descriptionText).setText(filmItem.filmDesc)
        findViewById<ImageView>(R.id.filmImg).setImageResource(filmItem.imgId)
        findViewById<ImageView>(R.id.isFavoriteImg).setImageResource(if (filmItem.isFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)
        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val result = Intent().apply {
                putExtra("isFavorite", filmItem.isFavorite)
                putExtra("comment", findViewById<EditText>(R.id.comment).text.toString())
            }
            setResult(RESULT_OK, result)
            this.finish()
        }
        findViewById<Button>(R.id.inviteButton).setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.inviteText, filmItem.name)
            )
            sendIntent.type = "text/plain"
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(sendIntent)
        }
        findViewById<ImageView>(R.id.isFavoriteImg).setOnClickListener {
            (application as App).changeFilmFavorite(filmItem)
            findViewById<ImageView>(R.id.isFavoriteImg).setImageResource(if (filmItem.isFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)
            if (filmItem.isFavorite) {
                val snackbar =
                    Snackbar.make(it, getString(R.string.successStr), Snackbar.LENGTH_LONG)

                snackbar.setAction(getString(R.string.goToFavorites)) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("isFavoriteList", true)
                    startActivity(intent)
                }
                snackbar.show()
            }
        }
    }
}