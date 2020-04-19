plugins {
  id(BuildPlugins.androidApplication)
  id(BuildPlugins.kotlinAndroid)
  id(BuildPlugins.kotlinAndroidExtensions)
  id(BuildPlugins.firebaseLibs.rawGms)
  id(BuildPlugins.kotlinKapt)
  id(BuildPlugins.firebaseLibs.firebaseCrashlytics)
}

android {

  testOptions {
    unitTests(delegateClosureOf<com.android.build.gradle.internal.dsl.TestOptions.UnitTestOptions> {
      isReturnDefaultValues = true
    })
  }

  signingConfigs {
    register("release") {
      storeFile = file("keystores/todoapp.keystore")
      storePassword = "toooor"
      keyAlias = "key"
      keyPassword = "toooor"
    }
  }

  compileSdkVersion(BuildPlugins.AndroidSdk.compile)

  defaultConfig {
    applicationId = "com.prudhvir3ddy.todo_app_gettingthingsdone"
    minSdkVersion(BuildPlugins.AndroidSdk.min)
    targetSdkVersion(BuildPlugins.AndroidSdk.target)
    versionCode = 2
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      signingConfig = signingConfigs.getByName("release")
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  implementation(BuildPlugins.Libs.ktStdLib)
  implementation(BuildPlugins.Libs.ktxCore)
  implementation(BuildPlugins.Libs.appCompat)
  implementation(BuildPlugins.Libs.constraintLayout)
  testImplementation(BuildPlugins.testLibs.junit)

  implementation(BuildPlugins.Libs.materialDesign)

  implementation(BuildPlugins.Libs.room)
  kapt(BuildPlugins.Libs.roomKapt)

  testImplementation(BuildPlugins.testLibs.coreTest)
  testImplementation(BuildPlugins.testLibs.mockito)

  // Kotlin + CoRoutines
  implementation(BuildPlugins.Libs.work)
  androidTestImplementation(BuildPlugins.Libs.work)

  //noinspection GradleDependency
  implementation(BuildPlugins.firebaseLibs.fcm)
  implementation(BuildPlugins.Libs.crashlyticsVersion)

  //Glide
  implementation(BuildPlugins.Libs.glide)
  kapt(BuildPlugins.Libs.glideKapt)

  implementation(BuildPlugins.Libs.viewPager)

  //dagger
  implementation(BuildPlugins.Libs.dagger)
  kapt(BuildPlugins.Libs.daggerKapt)
}