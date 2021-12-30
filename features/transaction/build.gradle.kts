plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.androidHilt)
}

android {
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
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
    implementation(project(":core"))

    implementation(Deps.Hilt.hilt)
    implementation(Deps.Hilt.hiltNavCompose)
    kapt(Deps.Hilt.hiltCompiler)

    implementation(Deps.Common.appCompat)
    implementation(Deps.Common.material)

    implementation(Deps.Navigation.fragment)

    implementation(Deps.RxAndroid.rxJava)
    implementation(Deps.RxAndroid.rxKotlin)
    implementation(Deps.RxAndroid.rxAndroid)

    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.activityCompose)
    implementation(Deps.Compose.viewmodelCompose)
    implementation(Deps.Compose.rxjava)
    implementation(Deps.Compose.livedata)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.materialIcons)
    implementation(Deps.Compose.materialIconsExt)
    implementation(Deps.Compose.composeCompiler)
    implementation(Deps.Compose.accompanistInsetsUi)
    implementation(Deps.Compose.accompanistSwipeRefresh)

    implementation(Deps.Retrofit.retrofit)

    testImplementation(Deps.Test.mockK)
    testImplementation(Deps.Test.junit4)
}
