package com.rcl.nexttfa

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate

class HomeLaunchComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val vm = instanceKeeper.getOrCreate { HomeLaunchViewModel() }

    class HomeLaunchViewModel : InstanceKeeper.Instance {

    }
}