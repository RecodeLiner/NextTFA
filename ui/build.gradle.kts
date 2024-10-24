plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
}

kotlin {
    applyDefaultHierarchyTemplate()
    jvm()

    sourceSets {
        commonMain.dependencies {
            api(project(":components"))
            implementation(libs.decompose.compose)
            implementation(libs.decompose.comp.experimtental)
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.material)
        }
    }
}