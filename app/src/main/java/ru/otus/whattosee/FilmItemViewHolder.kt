package ru.otus.whattosee

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class FilmItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val filmName: TextView = itemView.findViewById(R.id.filmName)
    private val filmImage: ImageView = itemView.findViewById(R.id.filmImage)
    private val isFavoriteImage: ImageView = itemView.findViewById(R.id.isFavoriteImg)
    private val filmItemZone: ConstraintLayout = itemView.findViewById(R.id.filmItemZone)
    private val shortDesc: TextView = itemView.findViewById(R.id.shortDesc)

    @SuppressLint("ResourceAsColor")
    fun bind(item: FilmItem, listener: FilmItemClickListener)
    {

        filmName.text = item.name
        filmImage.setImageResource(item.imgId)
        isFavoriteImage.setImageResource(if(item.isFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)
        shortDesc.text = item.shortDesc
        filmItemZone.setBackgroundResource(if(item.isLastSelected) R.drawable.item_background_last else R.drawable.item_background)
        filmItemZone.setOnClickListener{
            listener.onFilmClick(
                item,
                adapterPosition
            )
        }
        isFavoriteImage.setOnClickListener{
            listener.onStarClick(
                item,
                adapterPosition
            )
        }


    }

}