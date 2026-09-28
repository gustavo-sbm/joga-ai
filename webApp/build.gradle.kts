import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        // moduleName virou outputModuleName (API de Provider) no Kotlin 2.3.
        outputModuleName.set("webApp")
        browser()
        binaries.executable()
    }

    sourceSets {
        wasmJsMain.dependencies {
            // composeApp expõe compose.ui via "api", então não precisamos
            // declarar de novo aqui.
            implementation(project(":composeApp"))
        }
    }
}
