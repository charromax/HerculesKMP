import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.googleServices)
    kotlin("plugin.serialization") version "1.9.20"
    id("dev.sergiobelda.compose.vectorize") version "0.4.0"
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }


    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "composeApp"
            isStatic = false
            binaryOption("bundleId", "org.charr0max.herculeskmp.composeApp")
            // Add it to avoid sqllite3 issues in iOS
            linkerOpts.add("-lsqlite3")

        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.mvvm.livedata.material) // api mvvm-livedata, Material library android extensions
            implementation(libs.mvvm.livedata.glide) // api mvvm-livedata, Glide library android extensions
            implementation(libs.mvvm.livedata.swiperefresh) // api mvvm-livedata, SwipeRefreshLayout library android extensions
            implementation(libs.mvvm.databinding) // api mvvm-livedata, DataBinding support for Android
            implementation(libs.mvvm.viewbinding) // api mvvm-livedata, ViewBinding support for Android
            implementation(libs.ktor.android)
            implementation(libs.sqldelight.androidDriver)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            api(libs.firebase.sdk)
            implementation(libs.kt.serialization)
            implementation(libs.uuid)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.compose.vectorize.core)
            // Navigator
            implementation(libs.voyager.navigator)
            //SqlDelight
            implementation(libs.sqldelight.coroutines)
            implementation(libs.sqldelight.primitiveAdapters)
            implementation(libs.koin.core)
        }
        iosMain.dependencies {
            api(libs.firebase.sdk)
            implementation(libs.ktor.ios)
            implementation(libs.sqldelight.nativeDriver)
            implementation(libs.firebase.sdk)
            implementation(libs.mvvm.core)
            implementation(libs.mvvm.livedata)
            implementation(libs.mvvm.livedata.resources)
            implementation(libs.mvvm.state)
            implementation(libs.resources)
            implementation(libs.graphics) // toUIColor here
            implementation(libs.stately.common)
        }
    }
}

sqldelight {
    databases {
        create("HerkulesDatabase") {
            packageName.set("org.charr0max.herculeskmp.cache")
        }
    }
}

android {
    namespace = "org.charr0max.herculeskmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.charr0max.herculeskmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
dependencies {
    implementation(project.dependencies.platform("com.google.firebase:firebase-bom:32.7.1"))
    api("com.google.firebase:firebase-database")
    commonMainApi(libs.mvvm.core) // only ViewModel, EventsDispatcher, Dispatchers.UI
    commonMainApi(libs.mvvm.flow) // api mvvm-core, CFlow for native and binding extensions
    commonMainApi(libs.mvvm.livedata) // api mvvm-core, LiveData and extensions
    commonMainApi(libs.mvvm.state) // api mvvm-livedata, ResourceState class and extensions
    commonMainApi(libs.mvvm.livedata.resources) // api mvvm-core, moko-resources, extensions for LiveData with moko-resources
    commonMainApi(libs.mvvm.flow.resources) // api mvvm-core, moko-resources, extensions for Flow with moko-resources

    // compose multiplatform
    commonMainApi(libs.mvvm.compose) // api mvvm-core, getViewModel for Compose Multiplatform
    commonMainApi(libs.mvvm.flow.compose) // api mvvm-flow, binding extensions for Compose Multiplatform
    commonMainApi(libs.mvvm.livedata.compose) // api mvvm-livedata, binding extensions for Compose Multiplatform

    commonMainApi(libs.resources)
    commonMainApi(libs.resources.compose) // for compose multiplatform


    commonMainApi(libs.image.loader)

    commonTestImplementation(libs.mvvm.test) // test utilities
}
