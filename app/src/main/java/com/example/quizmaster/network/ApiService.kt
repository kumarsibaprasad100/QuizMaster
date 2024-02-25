package com.example.quizmaster.network

import com.example.quizmaster.model.LoginRequest
import com.example.quizmaster.model.LoginResponse
import com.example.quizmaster.model.SubmitRequest
import com.example.quizmaster.model.SubmitResponse
import com.example.quizmaster.model.SurveyResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("api/ContentQuestion/GetContentQuestion")
    suspend fun getQuestions(@Header("Authorization") authToken: String): Response<SurveyResponse>

    @POST("/api/User/authenticate")
    suspend fun userLogin(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("api/ContentQuestion/UpdateContentQuestionResult")
    suspend fun submitMarks(
        @Header("Authorization") authToken: String,
        @Body request: SubmitRequest
    ): ResponseBody
}