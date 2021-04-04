plugins {
  id("com.android.application")
  id("kotlin-android")
  id("dagger.hilt.android.plugin")
  kotlin("kapt")
}

android {
  compileSdk = 30
  buildToolsVersion = "30.0.3"

  defaultConfig {
    applicationId = "com.mito.stockarticle"
    minSdk = 23
    targetSdk = 30
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
    useIR = true
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    kotlinCompilerVersion = "1.4.30"
  }
}

dependencies {

  implementation("androidx.core:core-ktx:1.3.2")
  implementation("androidx.appcompat:appcompat:1.2.0")
  implementation("com.google.android.material:material:1.3.0")
  implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
  implementation("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
  implementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_version"]}")
  implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
  implementation("androidx.activity:activity-compose:1.3.0-alpha05")
  implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha05")

  implementation("androidx.navigation:navigation-compose:1.0.0-alpha09")

  implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
  kapt("androidx.lifecycle:lifecycle-compiler:2.3.1")
  androidTestImplementation("androidx.arch.core:core-testing:2.1.0")

  implementation("androidx.room:room-runtime:2.2.6")
  kapt("androidx.room:room-compiler:2.2.6")

  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha03")
  implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta03")

  // optional - Kotlin Extensions and Coroutines support for Room
  implementation("androidx.room:room-ktx:2.2.6")

  implementation("com.google.dagger:hilt-android:2.28-alpha")
  kapt("com.google.dagger:hilt-android-compiler:2.28-alpha")

  testImplementation("junit:junit:4.+")
  androidTestImplementation("androidx.test.ext:junit:1.1.2")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
  androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
}