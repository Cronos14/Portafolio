package com.javatar.portafolio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javatar.portafolio.R
import com.javatar.portafolio.models.Experience
import com.javatar.portafolio.utils.HEADER
import com.javatar.portafolio.utils.Utils
import kotlinx.android.synthetic.main.row_experience.view.*


class ExperienceAdapter(val items: List<Experience>, val clickListener: (Experience) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            ExperienceViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_experience,
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HeaderViewHolder) {
            holder.bind(items[position])
        } else if (holder is ExperienceViewHolder) {
            holder.bind(items[position], clickListener)
        }

    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].type

    class ExperienceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(experience: Experience, clickListener: (Experience) -> Unit) {
            Utils.loadImageWithPicasso(experience.image, itemView.img_logo)
            itemView.tv_job_title.text = experience.title
            itemView.tv_name_enterprise.text = experience.enterprise
            itemView.tv_date.text = experience.date
            itemView.setOnClickListener { clickListener(experience) }
        }
    }

}