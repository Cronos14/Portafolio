package com.javatar.portafolio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javatar.portafolio.R
import com.javatar.portafolio.models.BaseData
import com.javatar.portafolio.models.Project
import com.javatar.portafolio.utils.HEADER
import com.javatar.portafolio.utils.Utils
import kotlinx.android.synthetic.main.row_project.view.*


class ProjectAdapter(val items: List<Project>, val clickListener: (Project) -> Unit) :
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
            ProjectViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.row_project,
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is HeaderViewHolder) {
            holder.bind(items[position] as BaseData)
        } else if (holder is ProjectViewHolder) {
            holder.bind(items[position], clickListener)
        }

    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = items[position].type

    class ProjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(project: Project, clickListener: (Project) -> Unit) {
            Utils.loadImageWithPicasso(project.image, itemView.img_logo)
            itemView.tv_title.text = project.title
            itemView.setOnClickListener { clickListener(project) }
        }
    }

}