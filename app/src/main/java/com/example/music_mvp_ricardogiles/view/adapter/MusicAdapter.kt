package com.example.music_mvp_ricardogiles.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_mvp_ricardogiles.databinding.MusicItemLayoutBinding
import com.example.music_mvp_ricardogiles.model.Songs
import com.squareup.picasso.Picasso

class MusicAdapter(private var dataSet: MutableList<Songs>, private var onClickHandler: (Songs) -> Unit):
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    class MusicViewHolder(private val binding: MusicItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(mergedData: Songs){
            binding.apply {
                musicAlbumItem.text = mergedData.collectionName
                musicTitleItem.text = mergedData.trackName
                musicArtisItem.text = mergedData.artistName
                Picasso.get()
                    .load(mergedData.artworkUrl100)
                    .resize(250,250)
                    .into(musicCoverItem)
            }
        }
    }



    fun updateDataSet(newDataSet: List<Songs>){
        /*dataSet = newDataSet*/
        dataSet.addAll(newDataSet)
        notifyItemRangeInserted(0, newDataSet.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MusicViewHolder (
            MusicItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setOnClickListener {
            onClickHandler(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size
}