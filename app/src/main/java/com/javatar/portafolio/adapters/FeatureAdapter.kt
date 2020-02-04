package com.javatar.portafolio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javatar.portafolio.R
import com.javatar.portafolio.models.BaseData
import com.javatar.portafolio.models.Feature
import com.javatar.portafolio.utils.HEADER
import com.javatar.portafolio.utils.Utils
import kotlinx.android.synthetic.main.row_feature_image.view.*
import kotlinx.android.synthetic.main.row_project.view.tv_title


class FeatureAdapter(val items: List<Feature>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == HEADER) {
            HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_header,
                    parent,
                    false
                )
            )
        } else {
            FeatureViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_feature_image,
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HeaderViewHolder) {
            holder.bind(items?.get(position) as BaseData)
        } else if (holder is FeatureViewHolder) {
            holder.bind(items?.get(position))
        }

    }

    override fun getItemCount() = items?.size ?: 0

    class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(feature: Feature?) {
            Utils.loadImageWithPicasso(feature?.icon, itemView.img_icon)
            itemView.tv_title.text = feature?.title
        }
    }

}