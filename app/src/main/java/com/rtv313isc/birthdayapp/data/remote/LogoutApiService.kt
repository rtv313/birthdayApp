package com.rtv313isc.birthdayapp.data.remote

import android.content.Context
import android.content.Intent
import com.facebook.*
import com.facebook.login.LoginManager
import com.rtv313isc.birthdayapp.LoginActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutApiService @Inject constructor() {

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
                    val intent = Intent(activity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    activity.startActivity(intent)
                }
            })
        request.executeAsync()
    }
}