package com.example.wordapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.example.wordapp.databinding.ItemWordBinding
import com.example.wordapp.model.Word
import com.google.android.material.snackbar.Snackbar


class WordsAdapter(private val viewModel: WordsViewModel) : ListAdapter<Word, WordsAdapter.WordViewHolder>(WordDC()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = ItemWordBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder:WordViewHolder, position: Int) {
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
                            Snackbar.make(binding.root, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                        else
                            Snackbar.make(binding.root, "Error Deleting", Toast.LENGTH_SHORT).show()
                    }
                }
                editWord.setOnClickListener {
                    showInputDialog(item!!, itemView.context as WordsActivity)
                }
            }
        }
        @SuppressLint("CheckResult")
        private fun showInputDialog(item: Word, context: Context){
            MaterialDialog(context).show {
                title(text = "Edit")
                input (prefill = item.word){ dialog, text ->
                    item.word = text.toString()
                    viewModel.updateWord(item).observe(context as WordsActivity){
                        if (it) {
                            Snackbar.make(binding.root, "Updated Successfully", Snackbar.LENGTH_SHORT).show()
                        }
                        else
                            Snackbar.make(binding.root, "Error Updating", Snackbar.LENGTH_SHORT).show()
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