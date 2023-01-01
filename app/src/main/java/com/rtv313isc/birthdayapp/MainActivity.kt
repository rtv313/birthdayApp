package com.rtv313isc.birthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rtv313isc.birthdayapp.presentation.list.BirthdaysViewModel
import com.rtv313isc.birthdayapp.ui.theme.BirthdayAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        GetFriendsBasicCall().getFriends()
        super.onCreate(savedInstanceState)

        setContent {
            BirthdayAppTheme {
                val viewModel: BirthdaysViewModel = hiltViewModel()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    // below line is use to create a button.
                    Button(
                        // below line is use to add onclick
                        // parameter for our button onclick
                        onClick = {
                            // when user is clicking the button
                            // we are displaying a toast message.
                            viewModel.logout()
                        },
                        // in below line we are using modifier
                        // which is use to add padding to our button
                        modifier = Modifier.padding(all = Dp(10F)),

                        // below line is use to set or
                        // button as enable or disable.
                        enabled = true,

                        // below line is use to
                        // add border to our button.
                        border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),

                        // below line is use to add shape for our button.
                        shape = MaterialTheme.shapes.medium,

                    )
                    // below line is use to
                    // add text on our button
                    {
                        Text(text = "LOGOUT", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BirthdayAppTheme {
        Greeting("Android")
    }
}