package ru.otus.whattosee

interface FilmItemClickListener {
    fun onFilmClick(filmItem: FilmItem, position: Int)
    fun onStarClick(filmItem: FilmItem, position: Int)
}