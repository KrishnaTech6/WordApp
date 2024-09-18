package com.example.wordapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
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
                    Toast.makeText(this@WordsActivity, "Success", Toast.LENGTH_SHORT).show()
                    else
                    Toast.makeText(this@WordsActivity, "Success", Toast.LENGTH_SHORT).show()
                }
            }
            positiveButton(text = "Submit")
        }
    }
}
