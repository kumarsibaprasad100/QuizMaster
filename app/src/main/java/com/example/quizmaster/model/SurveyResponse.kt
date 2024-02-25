package com.example.quizmaster.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class SurveyResponse(

	@field:SerializedName("content")
	val content: List<ContentItem?>? = null
) 


data class Survey(

	@field:SerializedName("surveyId")
	val surveyId: Int? = null,

	@field:SerializedName("surveyQuestions")
	val surveyQuestions: List<SurveyQuestionsItem?>? = null,

	@field:SerializedName("surveyName")
	val surveyName: String? = null
) 


data class SurveyQuestionsItem(

	@field:SerializedName("questions")
	val questions: String? = null,

	@field:SerializedName("answers")
	val answers: List<AnswersItem?>? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("isMultiChoice")
	val isMultiChoice: Boolean? = null,

	@field:SerializedName("question_id")
	val questionId: String? = null
) 


data class PageItem(

	@field:SerializedName("title_font")
	val titleFont: String? = null,

	@field:SerializedName("description_read")
	val descriptionRead: String? = null,

	@field:SerializedName("sub_title")
	val subTitle: String? = null,

	@field:SerializedName("title_colour")
	val titleColour: String? = null,

	@field:SerializedName("title_style")
	val titleStyle: String? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("sub_title_font")
	val subTitleFont: String? = null,

	@field:SerializedName("sub_title_colour")
	val subTitleColour: String? = null,

	@field:SerializedName("page_count")
	val pageCount: String? = null
) 


data class ContentItem(

	@field:SerializedName("survey")
	val survey: Survey? = null,

	@field:SerializedName("page")
	val page: List<PageItem?>? = null
) 


data class AnswersItem(

	@field:SerializedName("answerId")
	val answerId: String? = null,

	@field:SerializedName("optionText")
	val optionText: String? = null,

	@field:SerializedName("mark")
	val mark: Int? = null,

	var isSelected : Boolean = false
) 
