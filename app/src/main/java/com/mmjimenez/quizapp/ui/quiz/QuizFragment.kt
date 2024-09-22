package com.mmjimenez.quizapp.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
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
            continueQuiz()
        }
    }

    private fun obtainCheckedOption() =
        binding.radioBtnGroup.checkedRadioButtonId.let { id ->
        view?.findViewById<RadioButton>(id)?.text.toString()
    }

    private fun continueQuiz() {
        viewModel.checkCorrectAnswers(obtainCheckedOption())
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