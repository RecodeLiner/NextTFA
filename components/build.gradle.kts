plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    applyDefaultHierarchyTemplate()
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(libs.decompose.base)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}