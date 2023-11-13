
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.gms.google-services")
}


android {
    namespace = "com.example.fitx"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.fitx"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("com.google.firebase:firebase-firestore-ktx:24.8.1")
    // implementation("androidx.databinding:baseLibrary:3.2.0-alpha11")
    // implementation ("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.3.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation(files("libs/core-12.1.0.aar"))
    implementation ("com.google.firebase:firebase-messaging:23.0.0")



    /*
        val lifecycle_version = "2.6.2"
        val arch_version = "2.2.0"

        // ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
        // ViewModel utilities for Compose
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
        // LiveData
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
        // Lifecycles only (without ViewModel or LiveData)
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
        // Lifecycle utilities for Compose
        implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

        // Saved state module for ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
        // Annotation processor
        //kapt("androidx.lifecycle:lifecycle-compiler:$lifecycle_version")
        // alternately - if using Java8, use the following instead of lifecycle-compiler
        implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")

        // optional - helpers for implementing LifecycleOwner in a Service
        implementation("androidx.lifecycle:lifecycle-service:$lifecycle_version")

        // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
        implementation("androidx.lifecycle:lifecycle-process:$lifecycle_version")

        // optional - ReactiveStreams support for LiveData
        implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")

        // optional - Test helpers for LiveData
        testImplementation("androidx.arch.core:core-testing:$arch_version")

        // optional - Test helpers for Lifecycle runtime
        testImplementation ("androidx.lifecycle:lifecycle-runtime-testing:$lifecycle_version")

        implementation ("androidx.navigation:navigation-fragment-ktx:2.3.5")
        */

}