/** This file contains versions of all the dependencies used in the module  */

object BuildPlugins {

  const val ANDROID_APPLICATION = "com.android.application"
  const val KOTLIN_ANDROID = "kotlin-android"
  const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
  const val KOTLIN_KAPT = "kotlin-kapt"
  const val FIREBASE_CRASHLYTICS = "com.google.firebase.crashlytics"
  const val RAW_GMS = "com.google.gms.google-services"
  const val DAGGER_HILT = "dagger.hilt.android.plugin"

  object RootVersion {
    const val KOTLIN_VERSION = "1.3.71"
    const val GOOGLE_DAGGER_HILT_ANDROID = "2.28-alpha"
  }

  object GradleLib {

    private object GradleLibVersion {
      const val BUILD_VERSION = "3.6.2"
      const val GRADLE_CRASHLYTICS = "2.0.0-beta04"
      const val GMS = "4.3.3"
    }

    const val DAGGER_HILT =
      "com.google.dagger:hilt-android-gradle-plugin:${RootVersion.GOOGLE_DAGGER_HILT_ANDROID}"
    const val TOOLS_GRADLE = "com.android.tools.build:gradle:${GradleLibVersion.BUILD_VERSION}"
    const val KOTLIN_GRADLE =
      "org.jetbrains.kotlin:kotlin-gradle-plugin:${RootVersion.KOTLIN_VERSION}"
    const val GMS = "com.google.gms:google-services:${GradleLibVersion.GMS}"
    const val GRADLE_CRASHLYTICS =
      "com.google.firebase:firebase-crashlytics-gradle:${GradleLibVersion.GRADLE_CRASHLYTICS}"
  }

  object AndroidSdk {

    const val COMPILE = 29
    const val MIN = 21
    const val TARGET = 29

  }

  /** General Libraries */
  object Lib {

    object LibVersion {
      const val APP_COMPAT = "1.1.0"
      const val KTX_CORE = "1.2.0"
      const val CONSTRAINT_LAYOUT = "1.1.3"
      const val MATERIAL_DESIGN = "1.1.0"
      const val ROOM = "2.2.5"
      const val WORK = "2.4.0-beta01"
      const val CRASHLYTICS = "17.0.0-beta04"
      const val GLIDE = "4.11.0"
      const val VIEWPAGER = "1.0.0"
      const val RETROFIT = "2.8.1"
      const val LIFECYCLE_VIEWMODEL = "2.2.0"
      const val TIMBER = "4.7.1"
      const val LEAK_CANARY = "2.4"
      const val FCM = "19.0.1"
      const val ANDROID_HILT = "1.0.0-alpha01"
    }

    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${LibVersion.LEAK_CANARY}"
    const val TIMBER = "com.jakewharton.timber:timber:${LibVersion.TIMBER}"
    const val HTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.5.0"
    const val LIFECYCLE_VIEWMODEL_KTX =
      "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibVersion.LIFECYCLE_VIEWMODEL}"
    const val RETROFIT_CONVERTER_MOSHI =
      "com.squareup.retrofit2:converter-moshi:${LibVersion.RETROFIT}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibVersion.RETROFIT}"
    const val KT_STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${RootVersion.KOTLIN_VERSION}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${LibVersion.APP_COMPAT}"
    const val KTX_CORE = "androidx.core:core-ktx:${LibVersion.KTX_CORE}"
    const val CONSTRAINT_LAYOUT =
      "androidx.constraintlayout:constraintlayout:${LibVersion.CONSTRAINT_LAYOUT}"
    const val MATERIAL_DESIGN =
      "com.google.android.material:material:${LibVersion.MATERIAL_DESIGN}"

    const val ROOM_KTX = "androidx.room:room-ktx:${LibVersion.ROOM}"
    const val ROOM_KAPT = "androidx.room:room-compiler:${LibVersion.ROOM}"

    const val WORK_KTX = "androidx.work:work-runtime-ktx:${LibVersion.WORK}"
    const val CRASHLYTICS_FIREBASE =
      "com.google.firebase:firebase-crashlytics:${LibVersion.CRASHLYTICS}"

    const val GLIDE = "com.github.bumptech.glide:glide:${LibVersion.GLIDE}"
    const val GLIDE_KAPT = "com.github.bumptech.glide:compiler:${LibVersion.GLIDE}"

    const val VIEWPAGER = "androidx.viewpager2:viewpager2:${LibVersion.VIEWPAGER}"

    const val DAGGER_HILT_ANDROID =
      "com.google.dagger:hilt-android:${RootVersion.GOOGLE_DAGGER_HILT_ANDROID}"
    const val DAGGER_HILT_ANDROID_COMPILER =
      "com.google.dagger:hilt-android-compiler:${RootVersion.GOOGLE_DAGGER_HILT_ANDROID}"
    const val HILT_LIFECYCLE_VIEWMODEL =
      "androidx.hilt:hilt-lifecycle-viewmodel:${LibVersion.ANDROID_HILT}"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${LibVersion.ANDROID_HILT}"

    const val FCM = "com.google.firebase:firebase-messaging:${LibVersion.FCM}"

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

}