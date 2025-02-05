package com.test.tallerchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.test.tallerchallenge.presentation.navigation.NavigationWrapper
import com.test.tallerchallenge.ui.theme.TallerChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TallerChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    NavigationWrapper()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TallerChallengeTheme {
        MainApp()
    }
}