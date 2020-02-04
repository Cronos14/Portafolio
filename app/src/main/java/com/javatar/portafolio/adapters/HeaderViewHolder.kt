package com.javatar.portafolio.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.javatar.portafolio.models.BaseData
import kotlinx.android.synthetic.main.row_header.view.*

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(baseData: BaseData) {
        itemView.tv_header.text = baseData.title
    }
}