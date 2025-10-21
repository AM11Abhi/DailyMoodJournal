package com.example.dailymoodjournal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.dailymoodjournal.data.MoodEntry
import com.example.dailymoodjournal.MoodViewModel
import com.example.dailymoodjournal.R
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var moodViewModel: MoodViewModel
    private lateinit var etMood: EditText
    private lateinit var etNote: EditText
    private lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        etMood = view.findViewById(R.id.etMood)
        etNote = view.findViewById(R.id.etNote)
        btnSave = view.findViewById(R.id.btnSaveMood)

        moodViewModel = MoodViewModel(requireActivity().application)

        btnSave.setOnClickListener {
            val moodText = etMood.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            if (moodText.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a mood", Toast.LENGTH_SHORT).show()
            } else {
                val moodEntry = MoodEntry(
                    date = Date(),
                    mood = moodText,
                    note = noteText
                )

                lifecycleScope.launch {
                    moodViewModel.insertMood(moodEntry)
                    Toast.makeText(requireContext(), "Mood saved!", Toast.LENGTH_SHORT).show()
                    etMood.text.clear()
                    etNote.text.clear()
                }
            }
        }

        return view
    }
}
