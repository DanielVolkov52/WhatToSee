package ru.otus.whattosee

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar


private const val TAG = "Description_activity"

class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler) }
    private val app by lazy {application as App}
    private var isFavoriteList = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isFavoriteList = intent.getBooleanExtra("isFavoriteList", false)
        if(isFavoriteList)
        {
            setTitle(getString(R.string.favoriteTitle))
        }
        initRecycler()
    }


    private fun initRecycler() {
        val layoutManager = GridLayoutManager(this, if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 1 else 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FilmItemAdapter(if(!isFavoriteList) app.filmItems else app.favoritFilmsItems, object : FilmItemClickListener {
                override fun onFilmClick(filmItem: FilmItem, position: Int) {
                    val intent = Intent(this@MainActivity, FilmDetailsActivity::class.java)
                    intent.putExtra("filmId", filmItem.filmId)
                    launcher.launch(intent)
                    app.setNewLastSelectedFilm(filmItem)
                }

                override fun onStarClick(filmItem: FilmItem, position: Int) {
                    app.changeFilmFavorite(filmItem)
                    if(!filmItem.isFavorite) {
                        val snackbar = Snackbar.make(
                            recyclerView,
                            getString(R.string.successStr),
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.setAction(getString(R.string.goToFavorites)) {
                            val intent = Intent(this@MainActivity, MainActivity::class.java)
                            intent.putExtra("isFavoriteList", true)
                            launcher.launch(intent)
                        }
                        snackbar.show()
                    }
                    else if(isFavoriteList)
                    {
                        val snackbar = Snackbar.make(
                            recyclerView,
                            getString(R.string.successRemovedStr),
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.setAction(getString(R.string.cancel)) {
                            app.changeFilmFavorite(filmItem)
                            checkDiff()
                        }
                        snackbar.show()
                    }
                    checkDiff()
                }
            })


        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        /*ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)
            ?.let { divider.setDrawable(it) }*/
        recyclerView.addItemDecoration(divider)

    }

    fun checkDiff()
    {
        val adapter = (recyclerView.adapter as FilmItemAdapter)
        val filmsDiffUtilCallback = FilmsDiffUtilCallback(adapter.getData(), if(!isFavoriteList) app.filmItems else app.favoritFilmsItems)
        val filmsDiffResult = DiffUtil.calculateDiff(filmsDiffUtilCallback)
        adapter.setData(if(!isFavoriteList) app.filmItems else app.favoritFilmsItems)
        filmsDiffResult.dispatchUpdatesTo(adapter)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), object : ActivityResultCallback<ActivityResult?> {
        override fun onActivityResult(result: ActivityResult?) {
            checkDiff()
            result?:return
            if(result.resultCode == Activity.RESULT_OK)
            {
                val comment = result.data?.getStringExtra("comment")
                val isFavorite = result.data?.getBooleanExtra("isFavorite", false)
                Log.d(TAG, "comment: $comment\nisFavorite: $isFavorite")
            }
        }
    })
}