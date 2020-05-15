package com.badoo.kmpmvi.app.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.badoo.kmpmvi.app.android.KittenAdapter.Holder
import com.squareup.picasso.Picasso

internal class KittenAdapter : RecyclerView.Adapter<Holder>() {

    private var urls = emptyList<String>()

    fun setUrls(urls: List<String>) {
        val oldUrls = this.urls
        this.urls = urls
        DiffUtil.calculateDiff(DiffUtilCallback(old = oldUrls, new = urls)).dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = urls.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.kitten_item, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(urls[position])
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.image_kitten)

        fun bind(url: String) {
            Picasso.get().load(url).into(image)
        }
    }

    private class DiffUtilCallback(
        private val old: List<String>,
        private val new: List<String>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition] === new[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition] == new[newItemPosition]
    }
}
