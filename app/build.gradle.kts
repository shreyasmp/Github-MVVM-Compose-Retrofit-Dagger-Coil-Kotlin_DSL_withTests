plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion = AppConfig.compileSDK
    buildToolsVersion = AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = "com.shreyas.nytimes"
        minSdk = AppConfig.minSDK
        targetSdk = AppConfig.targetSDK
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    dataBinding {
        android.buildFeatures.dataBinding = true
    }
    flavorDimensions(AppConfig.dimension)
    productFlavors {
        create("production") {
            dimension = AppConfig.dimension
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
        javaParameters = true
        freeCompilerArgs =
            freeCompilerArgs + "-Xjvm-default=enable" + "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi" + "-Xuse-experimental=kotlinx.coroutines.ObsoleteCoroutinesApi"
    }
    kapt {
        generateStubs = true
        correctErrorTypes = true
        javacOptions {
            option("-Adagger.fastInit=enabled")
        }
    }
    lint {
        warningsAsErrors = true
        abortOnError = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/notice.txt")
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
        unitTests.all { test ->
            test.jvmArgs = listOf("-ea -noverify")
        }
    }
    sourceSets {
        getByName("main").java.srcDir("src/main/java")
        getByName("test").java.srcDirs("src/test/java")
        getByName("test").resources.srcDirs("src/test/assets")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<Test>().all {
    jvmArgs("-ea -noverify")
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(AppDependencies.kotlinStdLib)
    implementation(AppDependencies.kotlinCoRoutinesCore)
    implementation(AppDependencies.jakeWhartonCoRoutineAdapter)
    implementation(AppDependencies.fragmentKTX)
    implementation(AppDependencies.appcompat)
    implementation(AppDependencies.constraintLayout)
    implementation(AppDependencies.material)
    implementation(AppDependencies.coreKtx)
    implementation(AppDependencies.cardView)
    implementation(AppDependencies.recyclerView)
    implementation(AppDependencies.legacySupport)

    kapt(AppDependencies.lifeCycleCompiler)
    implementation(AppDependencies.lifeCycleRunTime)
    implementation(AppDependencies.lifeCycleExtensions)
    implementation(AppDependencies.lifeCycleKTX)
    implementation(AppDependencies.lifeCycleLiveDataKTX)

    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.okhttp3Interceptor)
    implementation(AppDependencies.picasso)
    implementation(AppDependencies.simpleXml)
    implementation(AppDependencies.gsonConverter)

    implementation(AppDependencies.gson)
    implementation(AppDependencies.navigationKtx)
    implementation(AppDependencies.navigationUI)

    kapt(AppDependencies.daggerProcessor)
    kapt(AppDependencies.daggerCompiler)
    implementation(AppDependencies.dagger)
    implementation(AppDependencies.daggerAndroid)
    implementation(AppDependencies.daggerAndroidSupport)
    implementation(AppDependencies.espressoIdling)

    implementation(AppDependencies.composeActivities)
    implementation(AppDependencies.composeMaterialDesign)
    implementation(AppDependencies.composeAnimations)
    implementation(AppDependencies.composeTooling)
    implementation(AppDependencies.composeToolingPreview)
    implementation(AppDependencies.composeViewModels)

    testImplementation(AppDependencies.kotlinCoRoutinesCoreTest)
    testImplementation(AppDependencies.junit)
    testImplementation(AppDependencies.robolectric)
    testImplementation(AppDependencies.robolectricShadows)
    testImplementation(AppDependencies.robolectricSupport)
    testImplementation(AppDependencies.archCoreTesting)
    testImplementation(AppDependencies.junitRunner)
    testImplementation(AppDependencies.mockito)
    testImplementation(AppDependencies.hamcrest)
    testImplementation(AppDependencies.truth)
    testImplementation(AppDependencies.mockk)
    testImplementation(AppDependencies.mockkAndroid)
    testImplementation(AppDependencies.nharmaanMockito)
    testImplementation(AppDependencies.okHttpMockServer)

    kaptTest(AppDependencies.daggerProcessor)
    kaptTest(AppDependencies.daggerCompiler)
    testImplementation(AppDependencies.dagger)
    testImplementation(AppDependencies.daggerAndroid)
    testImplementation(AppDependencies.daggerAndroidSupport)

    androidTestImplementation(AppDependencies.androidXTestRunner)
    androidTestImplementation(AppDependencies.androidxTestRules)
    androidTestImplementation(AppDependencies.extJUnit)
    androidTestImplementation(AppDependencies.espressoCore)
    androidTestImplementation(AppDependencies.espressoContrib)
    androidTestImplementation(AppDependencies.mockito)
    androidTestImplementation(AppDependencies.mockk)
    androidTestImplementation(AppDependencies.mockkAndroid)
    androidTestImplementation(AppDependencies.navigationTesting)
    androidTestImplementation(AppDependencies.composeUITests)

    debugImplementation(AppDependencies.fragmentTesting)
    debugImplementation(AppDependencies.composeTooling)
}