package ru.otus.whattosee

data class FilmItem (val filmId : Int, val name: String, val imgId: Int, val filmDesc: String, val shortDesc: String, var isFavorite : Boolean, var isLastSelected: Boolean)