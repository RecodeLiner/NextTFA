package com.rcl.nexttfa

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.rcl.nexttfa.ui.RootComponentImpl
import com.rcl.nexttfa.ui.theme.NextTFATheme

class MainActivity : ComponentActivity() {
    private val component: RootComponent = RootComponent(defaultComponentContext())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NextTFATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        RootComponentImpl(component)
                    }
                }
            }
        }
    }
    override fun onNewIntent(intent: Intent) {
        intent.data?.let { data ->
            component.vm.deepLinkHandler(data.toString())
        }
        super.onNewIntent(intent)
    }
}