package com.delsi.bestsellerlist.ui.books.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.delsi.bestsellerlist.R
import com.delsi.bestsellerlist.data.vo.BestsellerType


class BestsellerTypeAdapter(
    context: Context,
    resourceId: Int,
    bestsellersType: List<BestsellerType>
) : ArrayAdapter<BestsellerType>(context, resourceId, bestsellersType) {

    private inner class ViewHolder {
        var itemView: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var viewHolder = ViewHolder()
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(this.context)
                .inflate(R.layout.type_label_item, parent, false)

            viewHolder.itemView = view.findViewById(R.id.typeTitle)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }

        val item: BestsellerType? = getItem(position)
        if (item != null) {
            viewHolder.itemView?.text = item.displayName
        }

        return view!!

    }

}