package com.example.wordapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wordapp.databinding.ItemWordBinding
import com.example.wordapp.model.Word


class WordsAdapter(private val viewModel: WordsViewModel) : ListAdapter<Word, WordsAdapter.WordViewHolder>(WordDC()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsAdapter.WordViewHolder {
        val binding = ItemWordBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: WordsAdapter.WordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class WordViewHolder(private val binding : ItemWordBinding, private val viewModel: WordsViewModel)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word?) {
            with(binding) {
                word = item
                deleteWord.setOnClickListener {
                    viewModel.deleteWord(item!!).observe(itemView.context as WordsActivity){
                        if (it)
                            Toast.makeText(itemView.context, "Success", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(itemView.context, "Success", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private class WordDC: DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean = (oldItem.word == newItem.word)
    }

}