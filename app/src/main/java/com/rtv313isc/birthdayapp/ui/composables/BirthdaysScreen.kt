package com.rtv313isc.birthdayapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rtv313isc.birthdayapp.R

@Preview
@Composable
fun BirthdaysScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Birthdays(modifier = Modifier)
    }
}

@Composable
fun Birthdays(modifier: Modifier) {
    //val birthdays = birthdaysViewModel.birthDaysState

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF474645))
        .padding(20.dp)) {

        item {
            Text(
                text = "Birthdays:",
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }

        item { 
            Birthday(modifier = Modifier)
        }
        item {
            Birthday(modifier = Modifier)
        }
    }
}


@Composable
fun Birthday(modifier: Modifier){
    Row(modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.Bottom) {
        Image(
            modifier = Modifier
                .height(75.dp)
                .width(75.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Serpiente belica"
        )

        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = "Friend name",
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Text(
                text = "Birthday",
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
        }
    }
}