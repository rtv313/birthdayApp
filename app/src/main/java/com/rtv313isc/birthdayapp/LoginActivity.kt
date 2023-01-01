package com.rtv313isc.birthdayapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
    private val callback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult) {
            Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        override fun onCancel() {
            Log.d("MainActivity", "Facebook onCancel.")
        }

        override fun onError(error: FacebookException) {
            Log.d("MainActivity", "Facebook onError.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
           login()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    private fun login() {
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = (accessToken != null) && !accessToken.isExpired
        if (!isLoggedIn) {
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager, callback)
        }
    }
}