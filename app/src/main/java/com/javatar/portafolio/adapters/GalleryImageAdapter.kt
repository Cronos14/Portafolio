package com.javatar.portafolio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javatar.portafolio.R
import com.javatar.portafolio.utils.Utils
import kotlinx.android.synthetic.main.row_gallery_image.view.*


class GalleryImageAdapter(val items: List<String>?) :
    RecyclerView.Adapter<GalleryImageAdapter.ImageGalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageGalleryViewHolder =
        ImageGalleryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_gallery_image,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ImageGalleryViewHolder, position: Int) =
        holder.bind(items?.get(position))

    override fun getItemCount() = items?.size ?: 0

    class ImageGalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(url: String?) {
            Utils.loadImageWithPicasso(url, itemView.img_image)
        }
    }

}