package com.example.dailymoodjournal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete

@Dao
interface MoodDao {
    @Insert
    suspend fun insertMood(moodEntry: MoodEntry)

    @Query("SELECT * FROM mood_entries ORDER BY date DESC")
    suspend fun getAllMoods(): List<MoodEntry>

    @Delete
    suspend fun deleteMood(moodEntry: MoodEntry)
}
