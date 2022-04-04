package com.example.swap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.swap.ui.theme.SwapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwapTheme {
                LoadMainUi()
            }
        }
    }
}

@Preview(
    name = "Main Activity",
    showSystemUi = true,
    showBackground = true,
    backgroundColor = 0xffff
)
@Composable
fun LoadMainUi() {
    Column {
        Image(
            painterResource(
                id = R.drawable.ic_new_launcher_foreground
            ),
            contentDescription = "content description",
            modifier = Modifier.height(300.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "My cool app",
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = "Another cool text",
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = "5$",
                style = MaterialTheme.typography.caption
            )
        }
    }

}