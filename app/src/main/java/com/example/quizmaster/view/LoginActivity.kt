package com.example.quizmaster.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.quizmaster.R
import com.example.quizmaster.databinding.ActivityLoginBinding
import com.example.quizmaster.model.LoginResponse
//import com.example.quizmaster.network.RetrofitBuilder
import com.example.quizmaster.util.Constants
import com.example.quizmaster.viewmodel.LoginViewModel
import com.example.quizmaster.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mViewModel.loginLiveData.observe(this, ::loginResponse)
        setContentView(mBinding.root)
        setListeners()
    }

    private fun setListeners() {
        mBinding.loginButton.setOnClickListener {
            if (!mBinding.usernameEditText.text.isNullOrEmpty() && !mBinding.passwordEditText.text.isNullOrEmpty()) {
                mViewModel.login(
                    mBinding.usernameEditText.text.toString(),
                    mBinding.passwordEditText.text.toString()
                )
            } else {
                showDialog("Username or Password Fields cannot be empty!")
            }
        }
    }

    private fun loginResponse(result: Response<LoginResponse>) {
        if (result.isSuccessful) {
            Log.i("get api success", result.body().toString())
            Constants.SetToken(result.body()?.token.toString(), this@LoginActivity)
            Constants.setLoggedIn(true, this@LoginActivity)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            try {
                val json = JSONObject(result.errorBody()?.string())
                showDialog(json.getString("message"))
            } catch (e: Exception) {
                "Failed to parse error message"
            }

        }
    }

    private fun showDialog(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Alert")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog: DialogInterface, which: Int ->
            dialog.dismiss()
        }
        alertDialogBuilder.setCancelable(false)
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
}