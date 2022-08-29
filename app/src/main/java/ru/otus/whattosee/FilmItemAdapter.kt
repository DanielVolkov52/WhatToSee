package ru.otus.whattosee

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmItemAdapter(filmItems: List<FilmItem>, private val listener: FilmItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var filmItems: MutableList<FilmItem> = mutableListOf()

    init {
        setData(filmItems.toMutableList())
    }

    fun getData() : MutableList<FilmItem>
    {
        return filmItems
    }

    fun setData(newFilmsList: MutableList<FilmItem>)
    {
        filmItems.clear()
        for (item in newFilmsList)
        {
            filmItems.add(item.copy())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FilmItemViewHolder(inflater.inflate(R.layout.film_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FilmItemViewHolder)
            holder.bind(filmItems[position], listener)
    }

    override fun getItemCount(): Int = filmItems.size
}
