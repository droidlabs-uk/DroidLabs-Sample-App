plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.androidHilt)
    id(Plugins.safeArgs)
    id(Plugins.detekt).version(Versions.detektVersion)
}

android {
    namespace = "com.droidlabs.app"
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        getByName("release") {
            java.setSrcDirs(listOf("src/release/kotlin", "src/main/kotlin"))
        }
        getByName("debug") {
            java.setSrcDirs(listOf("src/debug/kotlin", "src/main/kotlin"))
        }
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

    packaging {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        this.jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":features:transaction"))
    implementation(project(":features:cuvva"))
    implementation(project(":features:main"))
    implementation(project(":features:breakingbad"))

    implementation(Deps.Hilt.hilt)
    kapt(Deps.Hilt.hiltCompiler)

    implementation(Deps.Timber.timber)
}
