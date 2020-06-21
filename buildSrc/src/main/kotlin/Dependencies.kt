/** This file contains versions of all the dependencies used in the module  */

const val KOTLIN_VERSION = "1.3.71"
const val BUILD_VERSION = "3.6.2"

object BuildPlugins {

  const val TOOLS_GRADLE = "com.android.tools.build:gradle:$BUILD_VERSION"
  const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
  const val ANDROID_APPLICATION = "com.android.application"
  const val KOTLIN_ANDROID = "kotlin-android"
  const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
  const val KOTLIN_KAPT = "kotlin-kapt"

  object AndroidSdk {

    const val COMPILE = 29
    const val MIN = 21
    const val TARGET = 29

  }

  /** General Libraries */
  object Libs {

    object LibVersions {
      const val APP_COMPAT = "1.1.0"
      const val KTX_CORE = "1.2.0"
      const val CONSTRAINT_LAYOUT = "1.1.3"
      const val MATERIAL_DESIGN = "1.1.0"
      const val ROOM = "2.2.5"
      const val WORK = "2.3.4"
      const val CRASHLYTICS = "17.0.0-beta04"
      const val GLIDE = "4.11.0"
      const val VIEWPAGER = "1.0.0"
      const val DAGGER = "2.25.4"
      const val RETROFIT = "2.8.1"
      const val LIFECYCLE_VIEWMODEL = "2.2.0"
      const val TIMBER = "4.7.1"
      const val LEAK_CANARY = "2.4"
    }

    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${LibVersions.LEAK_CANARY}"
    const val TIMBER = "com.jakewharton.timber:timber:${LibVersions.TIMBER}"
    const val HTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.5.0"
    const val LIFECYCLE_VIEWMODEL_KTX =
      "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibVersions.LIFECYCLE_VIEWMODEL}"
    const val RETROFIT_CONVERTER_MOSHI =
      "com.squareup.retrofit2:converter-moshi:${LibVersions.RETROFIT}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibVersions.RETROFIT}"
    const val KT_STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$KOTLIN_VERSION"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${LibVersions.APP_COMPAT}"
    const val KTX_CORE = "androidx.core:core-ktx:${LibVersions.KTX_CORE}"
    const val CONSTRAINT_LAYOUT =
      "androidx.constraintlayout:constraintlayout:${LibVersions.CONSTRAINT_LAYOUT}"
    const val MATERIAL_DESIGN =
      "com.google.android.material:material:${LibVersions.MATERIAL_DESIGN}"
    const val ROOM_KTX = "androidx.room:room-ktx:${LibVersions.ROOM}"
    const val WORK_KTX = "androidx.work:work-runtime-ktx:${LibVersions.WORK}"
    const val CRASHLYTICS_FIREBASE =
      "com.google.firebase:firebase-crashlytics:${LibVersions.CRASHLYTICS}"
    const val GLIDE = "com.github.bumptech.glide:glide:${LibVersions.GLIDE}"
    const val VIEWPAGER = "androidx.viewpager2:viewpager2:${LibVersions.VIEWPAGER}"
    const val DAGGER = "com.google.dagger:dagger:${LibVersions.DAGGER}"

    const val ROOM_KAPT = "androidx.room:room-compiler:${LibVersions.ROOM}"
    const val DAGGER_KAPT = "com.google.dagger:dagger-compiler:${LibVersions.DAGGER}"
    const val GLIDE_KAPT = "com.github.bumptech.glide:compiler:${LibVersions.GLIDE}"
  }

  /** Libraries for Tests */
  object TestLibs {

    private object TestLibVersions {
      const val CORE_TEST = "1.2.0"
      const val MOCKITO = "2.23.4"
      const val WORK = "2.3.4"
      const val JUNIT = "4.13"
    }

    const val WORK_TESTING = "androidx.work:work-testing:${TestLibVersions.WORK}"
    const val CORE_TEST = "androidx.test:core:${TestLibVersions.CORE_TEST}"
    const val MOCKITO = "org.mockito:mockito-core:${TestLibVersions.MOCKITO}"
    const val JUNIT = "junit:junit:${TestLibVersions.JUNIT}"
  }

  /** Libraries for Firebase */
  object FirebaseLibs {

    private object FirebaseLibsVersion {
      const val GRADLE_CRASHLYTICS = "2.0.0-beta04"
      const val GMS = "4.3.3"
      const val FCM = "19.0.1"
    }

    const val GRADLE_CRASHLYTICS =
      "com.google.firebase:firebase-crashlytics-gradle:${FirebaseLibsVersion.GRADLE_CRASHLYTICS}"
    const val GMS = "com.google.gms:google-services:${FirebaseLibsVersion.GMS}"
    const val FCM = "com.google.firebase:firebase-messaging:${FirebaseLibsVersion.FCM}"
    const val FIREBASE_CRASHLYTICS = "com.google.firebase.crashlytics"
    const val RAW_GMS = "com.google.gms.google-services"
  }

}