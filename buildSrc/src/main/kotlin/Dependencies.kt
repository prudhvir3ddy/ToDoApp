/** This file contains versions of all the dependencies used in the module  */

const val kotlinVersion = "1.3.71"
const val buildVersion = "3.6.2"

object BuildPlugins {

  object Versions {
    const val buildToolsVersion = "29.0.3"
  }

  const val androidGradlePlugin = "com.android.tools.build:gradle:$buildVersion"
  const val androidKotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
  const val androidApplication = "com.android.application"
  const val kotlinAndroid = "kotlin-android"
  const val kotlinAndroidExtensions = "kotlin-android-extensions"
  const val kotlinKapt = "kotlin-kapt"

  object AndroidSdk {

    const val compile = 29
    const val min = 21
    const val target = 29

  }


  /** General Libraries */
  object Libs {

    object libVersions {
      const val jetpack = "1.1.0"
      const val ktxCore = "1.2.0"
      const val constraintLayout = "1.1.3"
      const val materialDesign = "1.1.0"
      const val roomVersion = "2.2.5"
      const val workVersion = "2.3.4"
      const val crashlyticsVersion = "17.0.0-beta04"
      const val glideVersion = "4.11.0"
      const val viewPagerVersion = "1.0.0"
      const val daggerVersion = "2.25.4"
    }

    const val ktStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${libVersions.jetpack}"
    const val ktxCore = "androidx.core:core-ktx:${libVersions.ktxCore}"
    const val constraintLayout =
      "androidx.constraintlayout:constraintlayout:${libVersions.constraintLayout}"
    const val materialDesign = "com.google.android.material:material:${libVersions.materialDesign}"
    const val room = "androidx.room:room-runtime:${libVersions.roomVersion}"
    const val work = "androidx.work:work-runtime-ktx:${libVersions.workVersion}"
    const val crashlyticsVersion =
      "com.google.firebase:firebase-crashlytics:${libVersions.crashlyticsVersion}"
    const val glide = "com.github.bumptech.glide:glide:${libVersions.glideVersion}"
    const val viewPager = "androidx.viewpager2:viewpager2:${libVersions.viewPagerVersion}"
    const val dagger = "com.google.dagger:dagger:${libVersions.daggerVersion}"

    const val roomKapt = "androidx.room:room-compiler:${libVersions.roomVersion}"
    const val daggerKapt = "com.google.dagger:dagger-compiler:${libVersions.daggerVersion}"
    const val glideKapt = "com.github.bumptech.glide:compiler:${libVersions.glideVersion}"
  }

  /** Libraries for Tests */
  object testLibs {

    private object testLibVersions {
      const val coreTestVersion = "1.2.0"
      const val mockitoVersion = "2.23.4"
      const val workVersion = "2.3.4"
      const val junitVersion = "4.13"
    }

    const val work = "androidx.work:work-testing:${testLibVersions.workVersion}"
    const val coreTest = "androidx.test:core:${testLibVersions.coreTestVersion}"
    const val mockito = "org.mockito:mockito-core:${testLibVersions.mockitoVersion}"
    const val junit = "junit:junit:${testLibVersions.junitVersion}"
  }

  /** Libraries for Firebase */
  object firebaseLibs {

    private object firebaseLibsVersion {
      const val gradleCrashlyticsVersion = "2.0.0-beta04"
      const val gmsVersion = "4.3.3"
      const val fcmVersion = "19.0.1"
    }

    const val gradleCrashlytics =
      "com.google.firebase:firebase-crashlytics-gradle:${firebaseLibsVersion.gradleCrashlyticsVersion}"
    const val googleGms = "com.google.gms:google-services:${firebaseLibsVersion.gmsVersion}"
    const val fcm = "com.google.firebase:firebase-messaging:${firebaseLibsVersion.fcmVersion}"
    const val firebaseCrashlytics = "com.google.firebase.crashlytics"
    const val rawGms = "com.google.gms.google-services"
  }

}