object AppDependencies {

    // Kotlin and coroutine frameworks
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinCoRoutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutine}"
    const val jakeWhartonCoRoutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coRoutineAdapter}"

    // General Android frameworks for UI
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val fragmentKTX = "androidx.fragment:fragment-ktx:${Versions.fragmentKTX}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKTX}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.support}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // Lifecycle frameworks
    const val lifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.archLifeCycle}"
    const val lifeCycleRunTime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.archLifeCycle}"
    const val lifeCycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.archLifeCycle}"
    const val lifeCycleKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archLifeCycle}"
    const val lifeCycleLiveDataKTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.archLifeCycle}"

    // Square Libraries consumed
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp3Interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp3}"
    const val okHttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttpMockServer}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val simpleXml = "com.squareup.retrofit2:converter-simplexml:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    
    // Dagger
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"

    // Testing Frameworks
    const val junit = "junit:junit:${Versions.jUnit}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"
    const val junitRunner = "androidx.test.ext:junit:${Versions.extJUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val androidxTestRules = "androidx.test:rules:${Versions.androidxTest}"
    const val androidXTestRunner = "androidx.test:runner:${Versions.androidxTest}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCore}"
    const val hamcrest = "org.hamcrest:hamcrest-library:${Versions.hamcrest}"
    const val truth = "com.google.truth:truth:${Versions.googleTruth}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val kotlinCoRoutinesCoreTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutineTest}"
    const val nharmaanMockito =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.nhaarmanMockito}"

    // Robolectric and Espresso framework
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val robolectricShadows = "org.robolectric:shadows-multidex:${Versions.robolectricShadows}"
    const val robolectricSupport =
        "org.robolectric:shadows-supportv4:${Versions.robolectricShadows}"
    const val espressoIdling =
        "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"

    // Navigation framework and fragment testing
    const val navigationKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigation}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentKTX}"
}