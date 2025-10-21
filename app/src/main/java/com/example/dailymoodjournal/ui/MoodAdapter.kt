package com.example.dailymoodjournal.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dailymoodjournal.R
import com.example.dailymoodjournal.data.MoodEntry
import java.text.SimpleDateFormat
import java.util.Locale

class MoodAdapter : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    private var moods: List<MoodEntry> = emptyList()

    fun submitList(list: List<MoodEntry>) {
        moods = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mood, parent, false)
        return MoodViewHolder(view)
    }

    override fun getItemCount(): Int = moods.size

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        holder.bind(moods[position])
    }

    inner class MoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvMood: TextView = itemView.findViewById(R.id.tvMood)
        private val tvNote: TextView = itemView.findViewById(R.id.tvNote)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)

        fun bind(entry: MoodEntry) {
            tvMood.text = entry.mood
            tvNote.text = entry.note
            tvDate.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(entry.date)
        }
    }
}