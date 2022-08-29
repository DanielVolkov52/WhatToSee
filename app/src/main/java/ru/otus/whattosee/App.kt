package ru.otus.whattosee

import android.app.Application

class App() : Application() {

    var filmItems: MutableList<FilmItem> = mutableListOf()
    var favoritFilmsItems: MutableList<FilmItem> = mutableListOf()
    var lastSelectedFilmId : Int = -1

    override fun onCreate() {
        super.onCreate()
        filmItems.add(
            FilmItem(
                0,
                getString(R.string.forrestGampName),
                R.drawable.forrestgump,
                getString(R.string.forrestGampDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                1,
                getString(R.string.schindlersListName),
                R.drawable.schindlerslist,
                getString(R.string.schindlersListDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                2,
                getString(R.string.shawshankRedemptionName),
                R.drawable.shawshankredemptio,
                getString(R.string.shawshankRedemptionDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                3,
                getString(R.string.pulpFictionName),
                R.drawable.pulpfiction,
                getString(R.string.pulpFictionDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                4,
                getString(R.string.inceptionName),
                R.drawable.inception,
                getString(R.string.inceptionDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                5,
                getString(R.string.interstellarName),
                R.drawable.interstellar,
                getString(R.string.interstellarDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                6,
                getString(R.string.intouchablesName),
                R.drawable.intouchables,
                getString(R.string.intouchablesDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                7,
                getString(R.string.gladiatorName),
                R.drawable.gladiator,
                getString(R.string.gladiatorDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                8,
                getString(R.string.shutterIslandName),
                R.drawable.shutterisland,
                getString(R.string.shutterIslandDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )
        filmItems.add(
            FilmItem(
                9,
                getString(R.string.greenMileName),
                R.drawable.green_mile,
                getString(R.string.greenMileDescription),
                getString(R.string.defaultShortDescription),
                false,
                false
            )
        )

    }

    fun setNewLastSelectedFilm(filmItem: FilmItem)
    {
        if(lastSelectedFilmId!=-1)
        {
            filmItems.find { it.filmId==lastSelectedFilmId }?.isLastSelected=false
        }
        lastSelectedFilmId = filmItem.filmId
        filmItems.find { it.filmId==filmItem.filmId }?.isLastSelected=true
    }

    fun changeFilmFavorite(filmItem: FilmItem)
    {
        val film = filmItems.find { it.filmId==filmItem.filmId }
        if(film != null)
        {
            if(!film.isFavorite)
            {
                film.isFavorite = true
                favoritFilmsItems.add(film)
            }
            else
            {
                film.isFavorite = false
                favoritFilmsItems.remove(film)
            }
        }
    }
}