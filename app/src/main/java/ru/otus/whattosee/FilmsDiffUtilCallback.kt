package ru.otus.whattosee

import androidx.recyclerview.widget.DiffUtil

class FilmsDiffUtilCallback(oldList: List<FilmItem>, newList: List<FilmItem>) :
    DiffUtil.Callback() {
    private val oldList: List<FilmItem>
    private val newList: List<FilmItem>
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct: FilmItem = oldList[oldItemPosition]
        val newProduct: FilmItem = newList[newItemPosition]
        return oldProduct.filmId == newProduct.filmId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct: FilmItem = oldList[oldItemPosition]
        val newProduct: FilmItem = newList[newItemPosition]
        return oldProduct == newProduct
    }

    init {
        this.oldList = oldList
        this.newList = newList
    }
}