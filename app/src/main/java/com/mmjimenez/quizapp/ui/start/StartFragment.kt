package com.mmjimenez.quizapp.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mmjimenez.quizapp.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private val viewModel by viewModels<StartViewModel>()
    private lateinit var binding: FragmentStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.btnQuiz.setOnClickListener { directionToQuizFragment() }

        return binding.root
    }

    private fun directionToQuizFragment() {
        val action = StartFragmentDirections.actionStartQuizFragmentToQuizFragment()
        findNavController().navigate(action)
    }
}