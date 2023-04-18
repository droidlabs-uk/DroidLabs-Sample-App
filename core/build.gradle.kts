plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.androidHilt)
    kotlin(Plugins.serialization).version(Versions.serialization)
}

android {
    namespace = "com.droidlabs.core"
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    kapt {
        correctErrorTypes = true
    }

    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {
    implementation(Deps.Common.appCompat)
    implementation(Deps.Common.material)

    implementation(Deps.Hilt.hilt)
    implementation(Deps.Hilt.hiltNavCompose)
    kapt(Deps.Hilt.hiltCompiler)

    implementation(Deps.Retrofit.retrofit)
    implementation(Deps.Retrofit.moshi)
    implementation(Deps.Retrofit.adapterRxJava)
    implementation(Deps.Retrofit.adapterCoroutines)

    implementation(Deps.OkHttp.okhttp)
    implementation(Deps.OkHttp.loggingInterceptor)

    implementation(Deps.Ktor.core)
    implementation(Deps.Ktor.android)
    implementation(Deps.Ktor.negotiation)

//    implementation(Deps.Ktor.serialization)
    implementation(Deps.Ktor.json)

    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.activityCompose)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.materialIcons)
    implementation(Deps.Compose.materialIconsExt)
    implementation(Deps.Compose.composeCompiler)

    implementation(Deps.Test.truth)

    implementation(Deps.Timber.timber)
}
