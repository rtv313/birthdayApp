package com.rtv313isc.birthdayapp

import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse


class GetFriendsBasicCall {

    fun getFriends(){
        val accessToken = AccessToken.getCurrentAccessToken()
        val userId = accessToken?.userId
        val graphPath = "/$userId/friends?fields=id,name,birthday"

        val request = GraphRequest.newGraphPathRequest(
            accessToken,
            graphPath,
            object : GraphRequest.Callback {
                override fun onCompleted(response: GraphResponse) {
                    // Insert your code here
                    val x = 0
                }
            })

        request.executeAsync()
    }
}