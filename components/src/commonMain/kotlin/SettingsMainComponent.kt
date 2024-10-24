package com.rcl.nexttfa

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate

class SettingsMainComponent(componentContext: ComponentContext) :
    ComponentContext by componentContext {
    val vm = instanceKeeper.getOrCreate { SettingsMainVM() }

    class SettingsMainVM : InstanceKeeper.Instance {

    }
}