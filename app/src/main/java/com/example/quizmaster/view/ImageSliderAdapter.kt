package com.example.quizmaster.view

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizmaster.R
import com.example.quizmaster.model.PageItem

class ImageSliderAdapter(private val imageList: List<PageItem?>?, private val context: FragmentActivity) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_slider, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList?.get(position))
    }

    override fun getItemCount(): Int {
        return imageList?.size ?: 0
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: AppCompatImageView = itemView.findViewById(R.id.imageView)
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val description: TextView = itemView.findViewById(R.id.tvDescription)

        fun bind(data: PageItem?) {
            Glide.with(context)
                .load(data?.imageUrl)
                .into(imageView)
            title.text = Html.fromHtml(data?.title,Html.FROM_HTML_MODE_COMPACT)
            description.text = Html.fromHtml(data?.description,Html.FROM_HTML_MODE_COMPACT)
        }
    }
}