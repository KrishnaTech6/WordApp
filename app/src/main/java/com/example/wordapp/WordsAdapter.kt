package com.example.wordapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.databinding.ItemWordBinding
import com.example.wordapp.model.Word


class WordsAdapter : ListAdapter<Word, WordsAdapter.WordViewHolder>(WordDC()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsAdapter.WordViewHolder {
        val binding = ItemWordBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordsAdapter.WordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class WordViewHolder(private val binding : ItemWordBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word?) {
            binding.word = item
        }
    }
    private class WordDC: DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean = (oldItem.word == newItem.word)
    }

}