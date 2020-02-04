package com.javatar.portafolio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javatar.portafolio.R
import com.javatar.portafolio.models.Aptitude
import com.javatar.portafolio.models.BaseData
import com.javatar.portafolio.utils.HEADER
import kotlinx.android.synthetic.main.row_text.view.*


class TextAdapter(val items: List<Aptitude>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            AptitudeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_text,
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HeaderViewHolder) {
            holder.bind(items?.get(position) as BaseData)
        } else if (holder is AptitudeViewHolder) {
            holder.bind(items?.get(position))
        }

    }

    override fun getItemCount() = items?.size ?: 0

    override fun getItemViewType(position: Int) = items?.get(position)?.type ?: 0

    fun isHeader(position: Int): Boolean {
        return items?.get(position)?.type == HEADER
    }

    class AptitudeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(aptitude: Aptitude?) {
            itemView.tv_title.text = aptitude?.title
        }
    }

}