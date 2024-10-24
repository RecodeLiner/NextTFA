package com.rcl.nexttfa

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.doOnStart
import com.rcl.nexttfa.RootComponent.TopLevelChild.HomeLevelChild.*
import com.rcl.nexttfa.RootComponent.TopLevelChild.SettingsLevelChild.SettingsMainChild
import kotlinx.serialization.Serializable

class RootComponent(contextComponent: ComponentContext) : ComponentContext by contextComponent {
    val vm = instanceKeeper.getOrCreate { RootViewModel() }
    private val navigator = StackNavigation<TopLevelConfiguration>()

    val childStack = childStack(
        source = navigator,
        serializer = TopLevelConfiguration.serializer(),
        initialConfiguration = TopLevelConfiguration.HomeLevelConfiguration.HomeLaunchConfiguration,
        handleBackButton = true,
        childFactory = ::createChild
    )

    init {
        lifecycle.doOnStart {
            vm.onCreate()
        }
    }

    class RootViewModel : InstanceKeeper.Instance {
        fun deepLinkHandler(link: String) {
            //Napier.i("Link is $link")
        }

        fun onCreate() {

        }
    }

    fun onBack() {
        navigator.pop()
    }

    fun navigateTo(config: TopLevelConfiguration) {
        navigator.pushToFront(config)
    }

    private fun createChild(
        config: TopLevelConfiguration,
        context: ComponentContext,
    ): TopLevelChild {
        return when (config) {
            is TopLevelConfiguration.HomeLevelConfiguration.HomeLaunchConfiguration ->
                HomeLaunchChild(
                    HomeLaunchComponent(
                        componentContext = context
                    )
                )

            is TopLevelConfiguration.HomeLevelConfiguration.HomeSelectedConfiguration ->
                HomeSelectedChild(
                    HomeSelectedComponent(
                        componentContext = context
                    )
                )

            is TopLevelConfiguration.SettingsLevelConfiguration.SettingsMainConfiguration ->
                SettingsMainChild(
                    SettingsMainComponent(
                        componentContext = context
                    )
                )
        }
    }

    sealed interface TopLevelChild {
        sealed interface HomeLevelChild : TopLevelChild {
            data class HomeLaunchChild(val component: HomeLaunchComponent) : HomeLevelChild
            data class HomeSelectedChild(val component: HomeSelectedComponent) : HomeLevelChild
        }

        sealed interface SettingsLevelChild : TopLevelChild {
            data class SettingsMainChild(val component: SettingsMainComponent) : SettingsLevelChild
        }
    }

    @Serializable
    sealed interface TopLevelConfiguration {
        @Serializable
        sealed interface HomeLevelConfiguration : TopLevelConfiguration {
            @Serializable
            data object HomeLaunchConfiguration : HomeLevelConfiguration

            @Serializable
            data class HomeSelectedConfiguration(val id: Int) : HomeLevelConfiguration
        }

        @Serializable
        sealed interface SettingsLevelConfiguration : TopLevelConfiguration {
            @Serializable
            data object SettingsMainConfiguration : SettingsLevelConfiguration
        }
    }
}