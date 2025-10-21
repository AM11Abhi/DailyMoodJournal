package com.example.dailymoodjournal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailymoodjournal.MoodViewModel
import com.example.dailymoodjournal.data.MoodEntry
import kotlinx.coroutines.launch
import com.example.dailymoodjournal.R

class HistoryFragment : Fragment() {

    private lateinit var moodViewModel: MoodViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        recyclerView = view.findViewById(R.id.rvMoods)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = MoodAdapter()
        recyclerView.adapter = adapter

        moodViewModel = MoodViewModel(requireActivity().application)

        // Fetch moods and update RecyclerView
        lifecycleScope.launch {
            val moods: List<MoodEntry> = moodViewModel.getAllMoods()
            adapter.submitList(moods)
        }

        return view
    }
}