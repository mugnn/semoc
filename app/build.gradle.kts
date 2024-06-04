plugins {
  alias(libs.plugins.androidApplication)
}

android {
  namespace = "com.ucsal.semoc"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.ucsal.semoc"
    minSdk = 21
    targetSdk = 34
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
}

dependencies {

  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("androidx.viewpager2:viewpager2:1.0.0")
  implementation("com.github.bumptech.glide:glide:4.12.0")
  implementation(libs.appcompat)
  implementation(libs.material)
  implementation(libs.activity)
  implementation(libs.constraintlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.ext.junit)
  androidTestImplementation(libs.espresso.core)
}