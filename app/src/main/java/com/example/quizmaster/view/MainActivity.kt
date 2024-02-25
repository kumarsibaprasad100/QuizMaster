package com.example.quizmaster.view

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizmaster.R
import com.example.quizmaster.databinding.ActivityMainBinding
import com.example.quizmaster.model.SurveyResponse
//import com.example.quizmaster.network.RetrofitBuilder
import com.example.quizmaster.util.Constants
import com.example.quizmaster.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.ResponseBody
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    lateinit var mViewModel: MainViewModel
    private var isQuestionsFragmentOpen = false
    private var marks = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        callApi()
    }

    private fun callApi() {
        mViewModel.surveyLiveData.observe(this, ::surveyResponse)
        mViewModel.getSurveyData( Constants.getToken(this@MainActivity))
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun surveyResponse(result: Response<SurveyResponse>) {
        if (result.isSuccessful) {
            Log.i("get api error", result.body().toString())
            showFragment(SurveyFragment(result))
            updateUI(result)
        } else {
            Log.i("get api error", result.errorBody().toString())
        }
    }


    private fun updateUI(result: Response<SurveyResponse>) {
        mBinding.btnSurvey.setOnClickListener {
            if (isQuestionsFragmentOpen) {
                //call post api
                marks = calculateMarks(result)
                Log.i("finalMarks", marks.toString())
                mViewModel.submitLiveData.observe(this, ::submitPostResponse)
                if (answersAttempted(result)) {
                    mViewModel.submitResponse(
                        /*RetrofitBuilder.apiService,*/
                        Constants.getToken(this@MainActivity),
                        marks.toString(),
                        "completed"
                    )
                } else {
                    showDialog(getString(R.string.please_attempt_all_questions), false)
                }


            } else {

                showFragment(QuestionAnswerFragment(result))
                mBinding.btnSurvey.text = "Submit"
                isQuestionsFragmentOpen = true

            }
        }
    }


    private fun submitPostResponse(result: ResponseBody) {
        showDialog(getString(R.string.your_answers_are_submitted_successfully),true)
    }

    private fun calculateMarks(result: Response<SurveyResponse>): Int {
        var currentMarks = 0
        result.body()?.content?.get(0)?.survey?.surveyQuestions?.let {
            for (question in it) {
                var isCorrect = true
                question?.answers?.let {
                    for (answers in question.answers) {
                        if ((answers?.isSelected == false && answers?.mark == 1) || (answers?.isSelected == true && answers?.mark == 0)) {
                            isCorrect = false
                            break
                        }
                    }
                }
                if (isCorrect) {
                    currentMarks += 10
                }
            }
        }
        return currentMarks
    }

    private fun answersAttempted(result: Response<SurveyResponse>): Boolean {
        result.body()?.content?.get(0)?.survey?.surveyQuestions?.let {
            for (question in it) {
                question?.answers?.let {
                    var atLeastOneSelected = false
                    for (answers in question.answers) {
                        if (answers?.isSelected == true) {
                            atLeastOneSelected = true
                            break
                        }
                    }
                    if (!atLeastOneSelected) {
                        return false
                    }
                }
            }
        }
        return true
    }

    private fun showDialog(message:String, goBack:Boolean){
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Alert")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog: DialogInterface, which: Int ->
            dialog.dismiss()
            if(goBack) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
        alertDialogBuilder.setCancelable(false)
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            mBinding.btnSurvey.text = getString(R.string.go_to_survey)
            isQuestionsFragmentOpen = false
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}