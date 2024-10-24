package com.rcl.nexttfa.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.experimental.stack.ChildStack
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.PredictiveBackParams
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.experimental.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.rcl.nexttfa.RootComponent

@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootComponentImpl(rootComponent: RootComponent) {
    val stack by rootComponent.childStack.subscribeAsState()
    ChildStack(
        stack = stack,
        animation = stackAnimation(
            animator = fade() + scale(),
            predictiveBackParams = {
                PredictiveBackParams(
                    backHandler = rootComponent.backHandler,
                    onBack = rootComponent::onBack,
                )
            },
        ),
    ) { topLevelChild ->
        when (val instance = topLevelChild.instance) {
            is RootComponent.TopLevelChild.HomeLevelChild.HomeLaunchChild -> {
                HomeLaunchImpl(instance.component)
            }
            is RootComponent.TopLevelChild.HomeLevelChild.HomeSelectedChild -> {
                HomeSelectedImpl(instance.component)
            }
            is RootComponent.TopLevelChild.SettingsLevelChild.SettingsMainChild -> {
                SettingsMainImpl(instance.component)
            }
        }
    }
}