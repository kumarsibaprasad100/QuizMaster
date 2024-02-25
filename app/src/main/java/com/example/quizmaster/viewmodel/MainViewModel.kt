package com.example.quizmaster.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizmaster.model.LoginResponse
import com.example.quizmaster.model.SubmitRequest
import com.example.quizmaster.model.SubmitResponse
import com.example.quizmaster.model.SurveyResponse
import com.example.quizmaster.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService
) :ViewModel(){
    val surveyLiveData = MutableLiveData<Response<SurveyResponse>>()
    val submitLiveData = MutableLiveData<ResponseBody>()

    fun getSurveyData( token: String) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    apiService.getQuestions(token)
                }
                surveyLiveData.postValue(result)
            } catch (e: Exception) {
                Log.e("get api error",e.stackTrace.toString())
            }
        }
    }

    fun submitResponse( token: String, marks:String, status:String) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    apiService.submitMarks(token, SubmitRequest(status,marks))
                }
                submitLiveData.postValue(result)
                Log.e("get api success",result.toString())
            } catch (e: Exception) {
                Log.e("get api error",e.stackTrace.toString())
            }
        }
    }

}