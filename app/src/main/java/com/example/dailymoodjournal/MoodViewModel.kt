package com.example.dailymoodjournal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailymoodjournal.data.MoodDatabase
import com.example.dailymoodjournal.data.MoodEntry
import com.example.dailymoodjournal.data.MoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoodViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MoodRepository

    init {
        val moodDao = MoodDatabase.Companion.getDatabase(application).moodDao()
        repository = MoodRepository(moodDao)
    }

    fun insertMood(moodEntry: MoodEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMood(moodEntry)
        }
    }

    suspend fun getAllMoods(): List<MoodEntry> {
        return withContext(Dispatchers.IO) {
            repository.getAllMoods()
        }
    }

    fun deleteMood(moodEntry: MoodEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMood(moodEntry)
        }
    }
}