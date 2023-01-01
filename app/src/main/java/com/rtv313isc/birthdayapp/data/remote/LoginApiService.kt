package com.rtv313isc.birthdayapp.data.remote

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.rtv313isc.birthdayapp.LoginActivity
import com.rtv313isc.birthdayapp.MainActivity
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginApiService @Inject constructor() {

    fun facebookLogin(activity: AppCompatActivity) {
        val callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"))
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                }

                override fun onCancel() {
                    Log.d("MainActivity", "Facebook onCancel.")
                }

                override fun onError(error: FacebookException) {
                    Log.d("MainActivity", "Facebook onError.")
                }
            })
    }

    fun facebookLogout(activity: Context) {
        val accessToken = AccessToken.getCurrentAccessToken()
        val request = GraphRequest(
            accessToken,
            "/me/permissions/",
            null,
            HttpMethod.DELETE,
            object : GraphRequest.Callback {
                override fun onCompleted(graphResponse: GraphResponse) {
                    LoginManager.getInstance().logOut()
                    activity.startActivity(Intent(activity, LoginActivity::class.java))
                }
            })
        request.executeAsync()
    }
}