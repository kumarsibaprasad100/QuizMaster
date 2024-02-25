package com.example.quizmaster.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.quizmaster.R
import com.example.quizmaster.model.SurveyResponse
import retrofit2.Response


class SurveyFragment(val result: Response<SurveyResponse>) : Fragment() {


    private lateinit var viewPager: ViewPager2

    private val imageList = result.body()?.content?.get(0)?.page

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.viewPager)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = ImageSliderAdapter(imageList, requireActivity())
        viewPager.adapter = adapter
    }


}