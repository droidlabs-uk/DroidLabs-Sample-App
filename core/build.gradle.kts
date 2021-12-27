plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
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
    implementation(Deps.Common.appCompat)
    implementation(Deps.Common.material)

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltCompiler)

    implementation(Deps.Retrofit.retrofit)
    implementation(Deps.Retrofit.moshi)
    implementation(Deps.Retrofit.adapterRxJava)
    implementation(Deps.Retrofit.adapterCoroutines)

    implementation(Deps.OkHttp.okhttp)
    implementation(Deps.OkHttp.loggingInterceptor)

    implementation(Deps.Test.truth)
}
