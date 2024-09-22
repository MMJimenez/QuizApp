package com.mmjimenez.quizapp.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mmjimenez.quizapp.R
import com.mmjimenez.quizapp.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class QuizFragment : Fragment() {
    private val viewModel: QuizViewModel by viewModels()
    private lateinit var binding: FragmentQuizBinding

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
            correctQuiz()
//            continueQuiz()
        }
    }

    private fun obtainCheckedRadioBtn() =
        binding.radioBtnGroup.checkedRadioButtonId.let { id ->
            view?.findViewById<RadioButton>(id)
        }

    private fun correctQuiz() {
        obtainCheckedRadioBtn()?.let { rdBtn ->
            viewModel.checkCorrectAnswers(rdBtn.text.toString()).takeIf { !it }?.let {
                rdBtn.setBackgroundColor(resources.getColor(R.color.red_pale))
            }
        }
        val indexAnswer = viewModel.getCorrectOption()
        with(binding) {
            when (indexAnswer) {
                0 -> rdBtn0.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_pale))
                1 -> rdBtn1.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_pale))
                2 -> rdBtn2.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_pale))
                3 -> rdBtn3.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green_pale))
            }

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