plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
    id(Plugins.androidHilt)
}

android {
    namespace = "com.droidlabs.breakingbad"
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
    }

    buildFeatures {
        viewBinding = true
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
    implementation(project(":core"))

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltCompiler)

    implementation(Deps.Common.material)

    implementation(Deps.AdapterDelegates.kotlinDsl)

    implementation(Deps.Navigation.ktx)
    implementation(Deps.Navigation.fragment)

    implementation(Deps.Picasso.picasso)

    testImplementation(Deps.Test.mockK)
    testImplementation(Deps.Test.junit4)
    testImplementation(Deps.Test.archTesting)
}
