plugins {
  id(BuildPlugins.ANDROID_APPLICATION)
  id(BuildPlugins.KOTLIN_ANDROID)
  id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
  id(BuildPlugins.RAW_GMS)
  id(BuildPlugins.KOTLIN_KAPT)
  id(BuildPlugins.FIREBASE_CRASHLYTICS)
  id(BuildPlugins.DAGGER_HILT)
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

  compileSdkVersion(BuildPlugins.AndroidSdk.COMPILE)

  defaultConfig {
    applicationId = "com.prudhvir3ddy.todo_app_gettingthingsdone"
    minSdkVersion(BuildPlugins.AndroidSdk.MIN)
    targetSdkVersion(BuildPlugins.AndroidSdk.TARGET)
    versionCode = 10
    versionName = "2.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
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
  dataBinding.isEnabled = true
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  implementation(BuildPlugins.Lib.KT_STD_LIB)
  implementation(BuildPlugins.Lib.KTX_CORE)
  implementation(BuildPlugins.Lib.LIFECYCLE_VIEWMODEL_KTX)
  implementation(BuildPlugins.Lib.APP_COMPAT)
  implementation(BuildPlugins.Lib.CONSTRAINT_LAYOUT)
  implementation(BuildPlugins.Lib.MATERIAL_DESIGN)

  implementation("androidx.activity:activity-ktx:1.1.0")

  testImplementation(BuildPlugins.TestLibs.JUNIT)
  testImplementation(BuildPlugins.TestLibs.CORE_TEST)
  testImplementation(BuildPlugins.TestLibs.MOCKITO)

  implementation(BuildPlugins.Lib.ROOM_KTX)
  kapt(BuildPlugins.Lib.ROOM_KAPT)

  implementation(BuildPlugins.Lib.WORK_KTX)
  androidTestImplementation(BuildPlugins.Lib.WORK_KTX)

  implementation(BuildPlugins.Lib.FCM)
  implementation(BuildPlugins.Lib.CRASHLYTICS_FIREBASE)

  implementation(BuildPlugins.Lib.GLIDE)
  kapt(BuildPlugins.Lib.GLIDE_KAPT)

  implementation(BuildPlugins.Lib.VIEWPAGER)

  implementation(BuildPlugins.Lib.DAGGER_HILT_ANDROID)
  kapt(BuildPlugins.Lib.DAGGER_HILT_ANDROID_COMPILER)
  implementation(BuildPlugins.Lib.HILT_LIFECYCLE_VIEWMODEL)
  kapt(BuildPlugins.Lib.HILT_COMPILER)
  implementation("androidx.hilt:hilt-work:1.0.0-alpha01")

  implementation(BuildPlugins.Lib.RETROFIT)
  implementation(BuildPlugins.Lib.RETROFIT_CONVERTER_MOSHI)
  implementation(BuildPlugins.Lib.HTTP_LOGGING_INTERCEPTOR)

  implementation(BuildPlugins.Lib.TIMBER)
  debugImplementation(BuildPlugins.Lib.LEAK_CANARY)

}