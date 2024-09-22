package com.mmjimenez.quizapp.ui.start.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmjimenez.quizapp.R
import com.mmjimenez.quizapp.model.pojo.Score

class ScoresAdapter(private var scores: List<Score>) : RecyclerView.Adapter<ScoreViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ScoreViewHolder(
            layoutInflater.inflate(
                R.layout.score_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        if (itemCount > 0) {
            holder.render(scores[position])
        }
    }

    override fun getItemCount() = scores.size
}