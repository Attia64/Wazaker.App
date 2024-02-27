// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    //  pass data between fragments
    id("androidx.navigation.safeargs") version "2.5.3" apply false
    // Dependency injection with Hilt
    id ("com.google.dagger.hilt.android") version "2.44" apply false
}