import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    //alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    jvmToolchain(17)

    // Alvo Desktop (consumido pelo módulo desktopApp)
    jvm("desktop")

    // Alvo Web via Kotlin/Wasm (consumido pelo módulo webApp)
    //@OptIn(ExperimentalWasmDsl::class)
    //wasmJs {
    //    browser()
    //}

    // Alvo Android — usa o novo plugin de biblioteca KMP (exigido pelo AGP 9+)
    //android  {
     //   namespace = "com.to_do.shared"
     //   compileSdk = libs.versions.android.compileSdk.get().toInt()
     //   minSdk = libs.versions.android.minSdk.get().toInt()
//
     //   compilerOptions {
     //       jvmTarget.set(JvmTarget.JVM_17)
     //   }

    //    androidResources {
     //       enable = true
    //    }
   // }

    sourceSets {
        commonMain.dependencies {
            // Os atalhos do plugin (compose.ui, compose.material3...) foram
            // descontinuados na versão 1.10. Agora declaramos as coordenadas
            // completas, centralizadas em gradle/libs.versions.toml.
            // Repare: usamos "api" e não "implementation" para que os módulos
            // androidApp/desktopApp/webApp enxerguem essas bibliotecas também.
            api(libs.compose.runtime)
            api(libs.compose.foundation)
            api(libs.compose.material3)
            api(libs.compose.ui)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            api(libs.navigation.compose)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.datastore.core)
            implementation(libs.datastore.preferences.core)
            api(libs.compose.material.icons.core)
            implementation(libs.compose.material.icons.extended)
            implementation(libs.kotlinx.datetime)
        }

        //androidMain.dependencies {
         //   implementation(libs.ktor.client.okhttp)
       // }

        val desktopMain by getting
        desktopMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
    }
}
