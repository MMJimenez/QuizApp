package com.mmjimenez.quizapp.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mmjimenez.quizapp.databinding.FragmentQuizBinding
import com.mmjimenez.quizapp.ui.start.StartFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {
    private val viewModel: QuizViewModel by viewModels()
    private lateinit var binding: FragmentQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.quiz = viewModel.quizFlow.value
        initViewComponents()
    }

    private fun initViewComponents() {
        with(binding.progressBar) {
            progress = viewModel.getQuizProgress(max)
        }
        binding.fab.setOnClickListener {
            viewModel.checkCorrectAnswers(obtainCheckedOption())
            directionToNextQuestion()
        }
    }

    private fun obtainCheckedOption() =
        binding.radioBtnGroup.checkedRadioButtonId.let { id ->
        view?.findViewById<RadioButton>(id)?.text.toString()
    }

    private fun directionToNextQuestion() {
        viewModel.newQuestion()
        val action = QuizFragmentDirections.actionQuizFragmentSelf()
        findNavController().navigate(action)
    }
}