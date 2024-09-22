package com.mmjimenez.quizapp.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mmjimenez.quizapp.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : Fragment() {
    private val viewModel: ResultViewModel by viewModels()
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.quiz = viewModel.actualQuiz
        initViewComponents()
    }

    private fun initViewComponents() {
        with(binding) {
            btnGoBack.setOnClickListener { directionToStartFragment() }
            btnRetry.setOnClickListener { retryQuiz() }
        }
    }

    private fun retryQuiz() {
        viewModel.restartQuiz()
        directionToQuizFragment()
    }

    private fun directionToQuizFragment() {
        val action = ResultFragmentDirections.actionResultFragmentToQuizFragment()
        findNavController().navigate(action)
    }

    private fun directionToStartFragment() {
        val action = ResultFragmentDirections.actionResultFragmentToStartQuizFragment()
        findNavController().navigate(action)
    }
}