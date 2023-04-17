object Deps {

    object Common {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
        const val appCompat = "androidx.appcompat:appcompat:1.4.0-beta01"
        const val coreKtx = "androidx.core:core-ktx:1.7.0-rc01"
        const val constaintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"
        const val material = "com.google.android.material:material:1.5.0-alpha04"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        const val adapterCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    }

    object OkHttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.4.1"
    }

    object Coroutines {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    }

    object Architecture {
        const val lifecycleExt = "android.arch.lifecycle:extensions:1.1.1"
    }

    object RxAndroid {
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.18"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    }

    object Navigation {
        const val ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    }

    object AdapterDelegates {
        const val kotlinDsl = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:4.3.0"
    }

    object Picasso {
        const val picasso = "com.squareup.picasso:picasso:2.71828"
    }

    object Test {
        const val archTesting = "androidx.arch.core:core-testing:2.1.0"
        const val truth = "com.google.truth:truth:1.0.1"
        const val mockK = "io.mockk:mockk:1.10.0"
        const val junit4 = "junit:junit:4.13.2"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val hiltNavCompose ="androidx.hilt:hilt-navigation-compose:1.1.0-alpha01"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val materialIcons = "androidx.compose.material:material-icons-core:${Versions.compose}"
        const val materialIconsExt = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        const val viewmodelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0"
        const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
        const val rxjava = "androidx.compose.runtime:runtime-rxjava2:${Versions.compose}"
        const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
        const val accompanistSwipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}"
        const val accompanistInsetsUi = "com.google.accompanist:accompanist-insets-ui:${Versions.accompanist}"
    }

    object Timber {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object UiTests {
//    androidTestImplementation 'androidx.compose.ui:ui-test-junit4:1.0.5'
//    androidTestImplementation 'androidx.test:runner:1.4.1-alpha03'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.0-alpha03'
    }

    //TODO: To be removed soon
    const val circularProgressbar = "com.mikhaellopez:circularprogressbar:2.0.0"
}