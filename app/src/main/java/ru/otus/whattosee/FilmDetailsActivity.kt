package ru.otus.whattosee

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class FilmDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.film_description_activity)
        findViewById<TextView>(R.id.descriptionText).setText(intent.getIntExtra("filmDesc", 0))
        findViewById<ImageView>(R.id.filmImg).setImageResource( intent.getIntExtra("filmImg", 0))
        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val result = Intent().apply {
                putExtra("isFavorite",  findViewById<CheckBox>(R.id.isFavorite).isChecked)
                putExtra("comment", findViewById<EditText>(R.id.comment).text.toString())
            }
            setResult(RESULT_OK, result)
            this.finish()
        }
        findViewById<Button>(R.id.inviteButton).setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.inviteText, getString(intent.getIntExtra("filmName", 0))))
            sendIntent.type = "text/plain"
            sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(sendIntent)
        }
    }
}