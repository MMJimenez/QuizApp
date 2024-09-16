package com.mmjimenez.quizapp.ui.startquiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mmjimenez.quizapp.R
import com.mmjimenez.quizapp.databinding.FragmentStartQuizBinding

class StartQuizFragment : Fragment() {
    private val viewModel by viewModels<StartQuizViewModel>()
    private lateinit var binding: FragmentStartQuizBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartQuizBinding.inflate(inflater, container, false)
        return binding.root
    }
}