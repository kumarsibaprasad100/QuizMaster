package com.example.quizmaster.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.model.SurveyQuestionsItem


class QuestionsAdapter(private val survey: List<SurveyQuestionsItem?>?) : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>(){
    var answersAdapter : AnswersAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = survey?.get(position)
        holder.bind(question)
    }

    override fun getItemCount(): Int {
        return survey?.size ?: 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvQuestion: TextView = itemView.findViewById(R.id.questionTextView)
        private val rvAnswers: RecyclerView = itemView.findViewById(R.id.answersRecyclerView)
        fun bind(question: SurveyQuestionsItem?) {
            tvQuestion.text = "${position+1} . "+question?.questions
            // Set up RecyclerView for answers
            answersAdapter = AnswersAdapter(question?.answers, question?.isMultiChoice ?: false)
            rvAnswers.adapter = answersAdapter
        }
    }

}