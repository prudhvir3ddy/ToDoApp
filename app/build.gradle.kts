plugins {
  id(BuildPlugins.ANDROID_APPLICATION)
  id(BuildPlugins.KOTLIN_ANDROID)
  id(BuildPlugins.KOTLIN_ANDROID_EXTENSIONS)
  id(BuildPlugins.FirebaseLibs.RAW_GMS)
  id(BuildPlugins.KOTLIN_KAPT)
  id(BuildPlugins.FirebaseLibs.FIREBASE_CRASHLYTICS)
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
    versionCode = 7
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
  viewBinding.isEnabled = true
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  implementation(BuildPlugins.Libs.KT_STD_LIB)
  implementation(BuildPlugins.Libs.KTX_CORE)
  implementation(BuildPlugins.Libs.LIFECYCLE_VIEWMODEL_KTX)
  implementation(BuildPlugins.Libs.APP_COMPAT)
  implementation(BuildPlugins.Libs.CONSTRAINT_LAYOUT)
  implementation(BuildPlugins.Libs.MATERIAL_DESIGN)

  testImplementation(BuildPlugins.TestLibs.JUNIT)
  testImplementation(BuildPlugins.TestLibs.CORE_TEST)
  testImplementation(BuildPlugins.TestLibs.MOCKITO)

  implementation(BuildPlugins.Libs.ROOM_KTX)
  kapt(BuildPlugins.Libs.ROOM_KAPT)

  implementation(BuildPlugins.Libs.WORK_KTX)
  androidTestImplementation(BuildPlugins.Libs.WORK_KTX)

  implementation(BuildPlugins.FirebaseLibs.FCM)
  implementation(BuildPlugins.Libs.CRASHLYTICS_FIREBASE)

  implementation(BuildPlugins.Libs.GLIDE)
  kapt(BuildPlugins.Libs.GLIDE_KAPT)

  implementation(BuildPlugins.Libs.VIEWPAGER)

  implementation(BuildPlugins.Libs.DAGGER)
  kapt(BuildPlugins.Libs.DAGGER_KAPT)

  implementation(BuildPlugins.Libs.RETROFIT)
  implementation(BuildPlugins.Libs.RETROFIT_CONVERTER_MOSHI)
  implementation(BuildPlugins.Libs.HTTP_LOGGING_INTERCEPTOR)

  implementation(BuildPlugins.Libs.TIMBER)
  debugImplementation(BuildPlugins.Libs.LEAK_CANARY)

}