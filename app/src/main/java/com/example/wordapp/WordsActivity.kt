package com.example.wordapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.example.wordapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<WordsViewModel>()
    private lateinit var wordsAdapter: WordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpRecyclerView()

        binding.fabWords.setOnClickListener{
            showInputDialog()
        }

        viewModel.getAllWords().observe(this){
            wordsAdapter.submitList(it)
            wordsAdapter.notifyDataSetChanged() // Force the adapter to refresh
        }
    }

    private fun setUpRecyclerView() {
        binding.wordsRv.apply {
            wordsAdapter = WordsAdapter(viewModel)
            layoutManager = LinearLayoutManager(this@WordsActivity)
            adapter = wordsAdapter
        }
    }

    @SuppressLint("CheckResult")
    private fun showInputDialog(){
        MaterialDialog(this).show {
            input { dialog, text ->
                viewModel.saveWord(text.toString()).observe(this@WordsActivity){
                    if (it)
                    Snackbar.make(binding.root, "Saved Successfully", Snackbar.LENGTH_SHORT).show()
                    else
                    Snackbar.make(binding.root, "Error Saving", Snackbar.LENGTH_SHORT).show()
                }
            }
            positiveButton(text = "Submit")
        }
    }
}
