package com.example.materno_infantil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.materno_infantil.R
import com.example.materno_infantil.model.CategoriaConsejo

class CaruselHomeAdapter(private val items: MutableList<CategoriaConsejo>,
                         private val onItemClick: (CategoriaConsejo) -> Unit) :
    RecyclerView.Adapter<CaruselHomeAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenCarusel: ImageView = itemView.findViewById(R.id.imageView)
        val tituloCarusel: TextView = itemView.findViewById(R.id.titleTextView)
        val likeImage : ImageView = itemView.findViewById(R.id.likeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carusel_home, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val itemCarusel = items[position]
        holder.tituloCarusel.text = itemCarusel.nombre
        holder.imagenCarusel.setImageResource(itemCarusel.imagenResId)

        if (itemCarusel.like) {
            holder.likeImage.setImageResource(R.drawable.heart_relleno)
        } else {
            holder.likeImage.setImageResource(R.drawable.heart_red)
        }

        holder.likeImage.setOnClickListener {
            itemCarusel.like = !itemCarusel.like
            if (itemCarusel.like) {
                holder.likeImage.setImageResource(R.drawable.heart_relleno)
            } else {
                holder.likeImage.setImageResource(R.drawable.heart_red)
            }
        }

        holder.itemView.setOnClickListener {
            onItemClick(itemCarusel)
        }
    }

    private fun likeAnimation(imageView: ImageView, like:Boolean) : Boolean{
        if(!like){
            imageView.setImageResource(R.drawable.heart_relleno)
        }
        else{
            imageView.setImageResource(R.drawable.heart_red)
        }
        return !like
    }

    override fun getItemCount() = items.size
}
