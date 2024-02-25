package com.example.quizmaster.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizmaster.model.LoginRequest
import com.example.quizmaster.model.LoginResponse
import com.example.quizmaster.model.SurveyResponse
import com.example.quizmaster.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
)  : ViewModel(){
    val loginLiveData = MutableLiveData<Response<LoginResponse>>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    apiService.userLogin(LoginRequest(username,password))
                }
                loginLiveData.postValue(result)
            } catch (e: Exception) {
                Log.e("get api error",e.stackTrace.toString())
            }
        }
    }

}