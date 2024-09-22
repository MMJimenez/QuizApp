package com.mmjimenez.quizapp.ui.start.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mmjimenez.quizapp.databinding.ScoreItemBinding
import com.mmjimenez.quizapp.model.pojo.Score

class ScoreViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ScoreItemBinding.bind(view)

    fun render(score: Score) {
        binding.score = score
    }
}