package ru.otus.whattosee

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children

private const val TAG = "Description_activity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnClickListeners()
    }



    fun setOnClickListeners() {
        findViewById<LinearLayout>(R.id.forrestGamp).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.forrestGampName)
            intent.putExtra("filmDesc", R.string.forrestGampDescription)
            intent.putExtra("filmImg", R.drawable.forrestgump)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.greenMile).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.greenMileName)
            intent.putExtra("filmDesc", R.string.greenMileDescription)
            intent.putExtra("filmImg", R.drawable.green_mile)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.glagiator).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.gladiatorName)
            intent.putExtra("filmDesc", R.string.gladiatorDescription)
            intent.putExtra("filmImg", R.drawable.gladiator)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.inception).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.inceptionName)
            intent.putExtra("filmDesc", R.string.inceptionDescription)
            intent.putExtra("filmImg", R.drawable.inception)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.shawshankRedemptio).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.shawshankRedemptionName)
            intent.putExtra("filmDesc", R.string.shawshankRedemptionDescription)
            intent.putExtra("filmImg", R.drawable.shawshankredemptio)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.interstellar).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.interstellarName)
            intent.putExtra("filmDesc", R.string.interstellarDescription)
            intent.putExtra("filmImg", R.drawable.interstellar)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.intouchables).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.intouchablesName)
            intent.putExtra("filmDesc", R.string.intouchablesDescription)
            intent.putExtra("filmImg", R.drawable.intouchables)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.pulpfiction).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.pulpFictionName)
            intent.putExtra("filmDesc", R.string.pulpFictionDescription)
            intent.putExtra("filmImg", R.drawable.pulpfiction)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.schindlerslist).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.schindlersListName)
            intent.putExtra("filmDesc", R.string.schindlersListDescription)
            intent.putExtra("filmImg", R.drawable.schindlerslist)
            launcher.launch(intent)
            selectId = it.id
        }
        findViewById<LinearLayout>(R.id.shutterisland).setOnClickListener {
            val intent = Intent(this, FilmDetailsActivity::class.java)
            intent.putExtra("filmName", R.string.shutterIslandName)
            intent.putExtra("filmDesc", R.string.shutterIslandDescription)
            intent.putExtra("filmImg", R.drawable.shutterisland)
            launcher.launch(intent)
            selectId = it.id
        }
    }

    override fun onResume() {
        super.onResume()
        val constraintlayout = findViewById<ConstraintLayout>(R.id.constraintlayout)
        constraintlayout.children.forEach {
            it.setBackgroundResource(if (it.id != selectId) R.color.trasperent else R.color.select)
        }
    }
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), object : ActivityResultCallback<ActivityResult?> {
        override fun onActivityResult(result: ActivityResult?) {
            result ?: return
            if(result.resultCode == Activity.RESULT_OK)
            {
                val comment = result.data?.getStringExtra("comment")
                val isFavorite = result.data?.getBooleanExtra("isFavorite", false)
                Log.d(TAG, "comment: " + comment + "\nisFavorite: " + isFavorite)
            }
        }
    })
    private var selectId = -1
}