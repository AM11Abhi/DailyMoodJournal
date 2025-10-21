package com.example.dailymoodjournal.data

class MoodRepository(private val moodDao: MoodDao) {

    suspend fun insertMood(moodEntry: MoodEntry) {
        moodDao.insertMood(moodEntry)
    }

    suspend fun getAllMoods(): List<MoodEntry> {
        return moodDao.getAllMoods()
    }

    suspend fun deleteMood(moodEntry: MoodEntry) {
        moodDao.deleteMood(moodEntry)
    }
}
