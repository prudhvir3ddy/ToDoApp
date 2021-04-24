package com.prudhvir3ddy.todo_app_gettingthingsdone.buildsrc

object AppConfig {
  const val APPLICATION_ID = "com.prudhvir3ddy.todo_app_gettingthingsdone"

  const val COMPILE_SDK = 30
  const val MIN_SDK = 21
  const val TARGET_SDK = 30

  object AppVersion {
    private const val VERSION_MAJOR = 2
    private const val VERSION_MINOR = 1
    private const val VERSION_PATCH = 2
    const val VERSION_NAME = "${VERSION_MAJOR}.${VERSION_MINOR}.${VERSION_PATCH}"
    const val VERSION_CODE = VERSION_MAJOR * 1000 + VERSION_MINOR * 100 + VERSION_PATCH
  }

}