package com.rtv313isc.birthdayapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.AccessToken

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chooseScreen()
    }

    private fun chooseScreen(){
        if (AccessToken.getCurrentAccessToken() == null || !AccessToken.isCurrentAccessTokenActive()) {
            startActivity(Intent(this@SplashScreen,LoginActivity::class.java))
        }else{
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
        }
    }
}