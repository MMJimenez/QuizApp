package com.mmjimenez.quizapp.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mmjimenez.quizapp.databinding.FragmentStartBinding
import com.mmjimenez.quizapp.ui.start.adapter.ScoresAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StartFragment : Fragment() {
    private val viewModel: StartViewModel by viewModels()
    private lateinit var binding: FragmentStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        initViewComponents()
        initCollectors()
    }

    override fun onResume() {
        super.onResume()
        viewModel.preload()
    }

    private fun initViewComponents() {
        binding.btnQuiz.setOnClickListener { directionToQuizFragment() }
        viewModel.getScoreHistory().let { list ->
            with(binding.historyRecycler) {
                if (list.isNotEmpty()) {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = ScoresAdapter(list.sortedByDescending { it.result })
                } else {
                    visibility = View.GONE
                    binding.txtHistory.visibility = View.GONE
                }
            }
        }
    }

    private fun initCollectors() {
        collectSnackbar()
    }

    private fun collectSnackbar() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.snackbarFlow.collect { nullable ->
                    nullable?.let {
                        prepareSnackbar(it).show()
                    }
                }
            }
        }
    }

    private fun prepareSnackbar(snackbar: com.mmjimenez.quizapp.model.pojo.Snackbar) =
        Snackbar.make(binding.root, snackbar.messageId, Snackbar.LENGTH_SHORT)

    private fun directionToQuizFragment() {
        viewModel.startQuiz()
        val action = StartFragmentDirections.actionStartQuizFragmentToQuizFragment()
        findNavController().navigate(action)
    }
}
