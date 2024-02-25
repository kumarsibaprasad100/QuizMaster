package com.example.quizmaster.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.model.AnswersItem


class AnswersAdapter(private val answers: List<AnswersItem?>?, private val multiChoice: Boolean) :
    RecyclerView.Adapter<AnswersAdapter.ViewHolder>() {
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = answers?.get(position)
        Log.i("selected answers " + position, answers.toString())
        holder.bind(answer, multiChoice)
    }

    override fun getItemCount(): Int {
        return answers?.size ?: 0
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rbAnswer: RadioButton = itemView.findViewById(R.id.rbAnswer)
        fun bind(answer: AnswersItem?, multiChoice: Boolean) {
            rbAnswer.text = answer?.optionText
            if (!multiChoice) {
                //for single choice
                rbAnswer.isChecked = adapterPosition == selectedPosition

                rbAnswer.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        if (answers != null) {
                            for (item in answers) {
                                item?.isSelected = false
                            }
                        }
                        answer?.isSelected = true
                        onItemClicked(adapterPosition)
                    }
                }
            } else {
                //for multiple choice
                rbAnswer.setOnClickListener {
                    rbAnswer.isChecked = answer?.isSelected != true
                    answer?.isSelected = rbAnswer.isChecked
                    Log.i("selected answers1 ", answers.toString())
                }

            }

        }
    }

    private fun onItemClicked(position: Int) {
        if (selectedPosition != position) {
            // Update the selected position and notify item changes
            selectedPosition = position
            notifyDataSetChanged()

            // Handle the click event or update your data accordingly
            // For example, you can use the listener to notify the activity or fragment
            // listener.onItemClicked(answerList[position])

        }

    }
}