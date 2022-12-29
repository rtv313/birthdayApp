package com.rtv313isc.birthdayapp.data.remote

import android.annotation.SuppressLint
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.rtv313isc.birthdayapp.data.local.LocalBirthDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class BirthdaysApiService @Inject constructor() {
    private val _birthdaysListStateFlow = MutableStateFlow(mutableListOf<LocalBirthDay>())
    val birthdayListState: StateFlow<List<LocalBirthDay>> = _birthdaysListStateFlow

    fun getBirthdays() {
        val accessToken = AccessToken.getCurrentAccessToken()
        val userId = accessToken?.userId
        val graphPath = "/$userId/friends?fields=id,name,birthday"
        val request = GraphRequest.newGraphPathRequest(
            accessToken,
            graphPath,
            object : GraphRequest.Callback {
                override fun onCompleted(response: GraphResponse) {
                    _birthdaysListStateFlow.value = processFriendsData(response)
                }
            })
        request.executeAsync()
    }

    @SuppressLint("SimpleDateFormat")
    private fun processFriendsData(response: GraphResponse): MutableList<LocalBirthDay> {
        val friendsJsonArray = response.jsonObject?.get("data") as JSONArray
        val birthdaysList = mutableListOf<LocalBirthDay>()

        for (i in 0 until friendsJsonArray.length()) {
            val friendJson = friendsJsonArray.get(i) as JSONObject
            val id = friendJson.get("id").toString().toLong()
            val name = friendJson.get("name").toString()
            val splitDate = friendJson.get("birthday").toString().split("/")
            val day = splitDate[1]
            val month = splitDate[0]
            // We set the same year for sorting purposes plus we don't need year of birth
            val birthday = SimpleDateFormat("dd/MM/yyyy").parse("$day/$month/1998")
            birthday?.let { birthdaysList.add(LocalBirthDay(id, name, birthday)) }
        }
        return birthdaysList
    }
}