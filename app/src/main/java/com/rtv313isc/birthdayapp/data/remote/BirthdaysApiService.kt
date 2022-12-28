package com.rtv313isc.birthdayapp.data.remote

import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.rtv313isc.birthdayapp.data.local.LocalBirthDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.sql.Date
import javax.inject.Inject

class BirthdaysApiService @Inject constructor(){

    private val _birthdaysListStateFlow = MutableStateFlow(mutableListOf<LocalBirthDay>())
    val birthdayListState : StateFlow<List<LocalBirthDay>> = _birthdaysListStateFlow

    fun getBirthdays(){
        val accessToken = AccessToken.getCurrentAccessToken()
        val userId = accessToken?.userId
        val graphPath = "/$userId/friends?fields=id,name,birthday"

        val request = GraphRequest.newGraphPathRequest(
            accessToken,
            graphPath,
            object : GraphRequest.Callback {
                override fun onCompleted(response: GraphResponse) {
                    // Insert your code here
                    val date = Date(2000,1,20)
                    _birthdaysListStateFlow.value = mutableListOf(LocalBirthDay(1,"john","DATE"))
                }
            })
        request.executeAsync()
    }
}