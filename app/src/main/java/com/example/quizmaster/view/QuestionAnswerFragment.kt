package com.example.quizmaster.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quizmaster.databinding.FragmentQuestionAnswerBinding
import com.example.quizmaster.model.SurveyResponse
import retrofit2.Response


class QuestionAnswerFragment(val result: Response<SurveyResponse>) : Fragment() {
    lateinit var mBinding : FragmentQuestionAnswerBinding
    lateinit var mAdapter: QuestionsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentQuestionAnswerBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = QuestionsAdapter(result.body()?.content?.get(0)?.survey?.surveyQuestions)
        mBinding.questionsRecyclerview.adapter = mAdapter
    }




}