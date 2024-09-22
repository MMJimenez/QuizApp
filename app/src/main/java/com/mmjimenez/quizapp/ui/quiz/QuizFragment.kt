package com.mmjimenez.quizapp.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mmjimenez.quizapp.R
import com.mmjimenez.quizapp.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class QuizFragment : Fragment() {
    private val viewModel: QuizViewModel by viewModels()
    private lateinit var binding: FragmentQuizBinding
    private var isCheckedResult = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.quiz = viewModel.actualQuiz
        initViewComponents()
    }

    private fun initViewComponents() {
        with(binding.progressBar) {
            progress = viewModel.getQuizProgress(max)
        }
        binding.fab.setOnClickListener {
            if (!isCheckedResult) {
                correctQuiz()
            } else {
                continueQuiz()
            }
        }
    }

    private fun obtainCheckedRadioBtn() =
        binding.radioBtnGroup.checkedRadioButtonId.let { id ->
            view?.findViewById<RadioButton>(id)
        }

    private fun correctQuiz() {
        obtainCheckedRadioBtn()?.let { rdBtn ->
            Timber.v("pulsado" + rdBtn.text.toString())
            if (!viewModel.checkCorrectAnswers(rdBtn.text.toString())) {
                rdBtn.setResultColor(false)
            }
        } ?: noOptionSelected()
        val indexAnswer = viewModel.getCorrectOption()
        with(binding) {
            when (indexAnswer) {
                0 -> rdBtn0.setResultColor(true)
                1 -> rdBtn1.setResultColor(true)
                2 -> rdBtn2.setResultColor(true)
                3 -> rdBtn3.setResultColor(true)
            }
        }
        disableOptions()
        isCheckedResult = true
    }
    private fun noOptionSelected() {
        with(binding) {
            rdBtn0.setResultColor(false)
            rdBtn1.setResultColor(false)
            rdBtn2.setResultColor(false)
            rdBtn3.setResultColor(false)
        }
    }


    private fun disableOptions() {
        with(binding) {
            rdBtn0.isEnabled = false
            rdBtn1.isEnabled = false
            rdBtn2.isEnabled = false
            rdBtn3.isEnabled = false
        }
    }

    private fun View.setResultColor(isCorrect: Boolean) {
        setBackgroundColor(
            if (isCorrect) {
                ContextCompat.getColor(requireContext(), R.color.green_pale)
            } else {
                ContextCompat.getColor(requireContext(), R.color.red_pale)
            }
        )
    }

    private fun continueQuiz() {
        if (viewModel.passToNextQuestion()) {
            directionToNextQuestion()
        } else {
            directionToResultFragment()
        }
    }

    private fun directionToNextQuestion() {
        val action = QuizFragmentDirections.actionQuizFragmentSelf()
        findNavController().navigate(action)
    }

    private fun directionToResultFragment() {
        val action = QuizFragmentDirections.actionQuizFragmentToResultFragment()
        findNavController().navigate(action)
    }
}