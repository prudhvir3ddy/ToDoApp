/** This file contains versions of all the dependencies used in the module  */
package com.prudhvir3ddy.todo_app_gettingthingsdone.buildsrc

import com.prudhvir3ddy.todo_app_gettingthingsdone.buildsrc.Lib.LibVersion

object Lib {

  object LibVersion {
    const val APP_COMPAT = "1.1.0"
    const val KTX_CORE = "1.2.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val MATERIAL_DESIGN = "1.1.0"
    const val ROOM = "2.2.5"
    const val WORK = "2.4.0"
    const val CRASHLYTICS = "17.3.0"
    const val GLIDE = "4.11.0"
    const val VIEWPAGER = "1.0.0"
    const val RETROFIT = "2.9.0"
    const val LIFECYCLE = "2.2.0"
    const val TIMBER = "4.7.1"
    const val LEAK_CANARY = "2.4"
    const val FCM = "19.0.1"
    const val ANDROID_HILT = "1.0.0-alpha03"
    const val NAVIGATION = "2.3.2"
  }

  const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${LibVersion.LEAK_CANARY}"
  const val TIMBER = "com.jakewharton.timber:timber:${LibVersion.TIMBER}"
  const val HTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.5.0"
  const val LIFECYCLE_VIEWMODEL_KTX =
    "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibVersion.LIFECYCLE}"
  const val LIFECYCLE_LIVEDATA_KTX =
    "androidx.lifecycle:lifecycle-livedata-ktx:${LibVersion.LIFECYCLE}"
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

  const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.2.0"
  const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:1.3.0"

  const val NAVIGATION_FRAGMENT_KTX =
    "androidx.navigation:navigation-fragment-ktx:${LibVersion.NAVIGATION}"
  const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${LibVersion.NAVIGATION}"

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
  const val HILT_WORKER = "androidx.hilt:hilt-work:1.0.0-alpha02"
  const val FCM = "com.google.firebase:firebase-messaging:${LibVersion.FCM}"

  object Compose {
    private const val COMPOSE_VERSION = "1.0.0-beta02"
    const val RUNTIME = "androidx.compose.runtime:runtime:$COMPOSE_VERSION"
    const val UI = "androidx.compose.ui:ui:$COMPOSE_VERSION"
    const val FOUNDATION = "androidx.compose.foundation:foundation:$COMPOSE_VERSION"
    const val FOUNDATION_LAYOUT = "androidx.compose.foundation:foundation-layout:$COMPOSE_VERSION"
    const val MATERIAL = "androidx.compose.material:material:$COMPOSE_VERSION"
    const val RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata:$COMPOSE_VERSION"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$COMPOSE_VERSION"
    const val MATERIAL_COMPOSE_THEME_ADAPTER =
      "com.google.android.material:compose-theme-adapter:$COMPOSE_VERSION"
    const val ACTIVITY = "androidx.activity:activity-compose:1.3.0-alpha04"
  }

}

object TestLibs {

  private object TestLibVersions {
    const val CORE_TEST = "1.2.0"
    const val MOCKITO = "2.23.4"
    const val JUNIT = "4.12"
  }

  const val WORK_TESTING = "androidx.work:work-testing:${LibVersion.WORK}"
  const val CORE_TEST = "androidx.test:core:${TestLibVersions.CORE_TEST}"
  const val MOCKITO = "org.mockito:mockito-core:${TestLibVersions.MOCKITO}"
  const val EXT_JUNIT = "androidx.test.ext:junit-ktx:1.1.1"
  const val JUNIT = "junit:junit:${TestLibVersions.JUNIT}"
  const val RUNNER = "androidx.test:runner:1.1.0"
  const val HILT_ANDROID_TESTING =
    "com.google.dagger:hilt-android-testing:${RootVersion.GOOGLE_DAGGER_HILT_ANDROID}"
  const val ANDROIDX_TRUTH = "androidx.test.ext:truth:1.0.0"
  const val GOOGLE_TRUTH = "com.google.truth:truth:0.42"
  const val RULES = "androidx.test:rules:1.1.0"

}

object RootVersion {
  const val COMPOSE_VERSION = "1.0.0-beta02"
  const val KOTLIN_VERSION = "1.4.31"
  const val GOOGLE_DAGGER_HILT_ANDROID = "2.32-alpha"
}

object GradleLib {

  private object GradleLibVersion {
    const val BUILD_VERSION = "7.0.0-alpha12"
    const val GRADLE_CRASHLYTICS = "2.4.1"
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
  const val NAVIGATION_SAFE_ARGS =
    "androidx.navigation:navigation-safe-args-gradle-plugin:${LibVersion.NAVIGATION}"
}
