package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme
import com.example.lemonadeapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                MakingLemonade()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MakingLemonade() {

    Box(
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_title),
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )
    }

    LemonImageAndText(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonImageAndText(modifier: Modifier = Modifier) {

    var screenNumber by remember { mutableStateOf(0) }
    var squeezeLemon by remember { mutableStateOf((2..4).random()) }
    val imageStageId = when (screenNumber) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> {
            R.drawable.lemon_restart
        }
    }
    val textStageId = when (screenNumber) {
        0 -> R.string.tap_lemonTreeText
        1 -> R.string.tap_lemonText
        2 -> R.string.tap_lemonadeGlassText
        else -> {
            R.string.tap_emptyGlassText
        }
    }
    var imageStageDescriptionId = R.string.lemonTreeImageDescription
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                if (screenNumber == 1) {
                    if (squeezeLemon != 0) {
                        squeezeLemon--
                    } else {
                        squeezeLemon = (2..4).random()
                        screenNumber++
                    }
                } else {
                    screenNumber++
                }
                if (screenNumber == 4) {
                    screenNumber = 0
                }
            },
            shape = RoundedCornerShape(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
        ) {
            Image(
                painter = painterResource(imageStageId),
                contentDescription = stringResource(imageStageDescriptionId)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(textStageId), fontSize = 18.sp)
    }
}