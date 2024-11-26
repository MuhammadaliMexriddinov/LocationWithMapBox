plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "uz.macdroid.mapboxwithandroid"
    compileSdk = 35

    defaultConfig {
        applicationId = "uz.macdroid.mapboxwithandroid"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resValue("string", "mapbox_access_token", "sk.eyJ1IjoibXVoYW1tYWRhbGkxMiIsImEiOiJjbTNza2ltYmwwamE2MmpzYzRwb3dvZ3N1In0.FZGyDEhy4S6PcrQfSyvMwQ")
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

    bundle {
        language {
            enableSplit= false
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.core)
    implementation(libs.play.services.location)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Lottie animation
    implementation ("com.airbnb.android:lottie:5.2.0")

    //coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    //fragment ktx
    implementation ("androidx.fragment:fragment-ktx:1.5.7")

    // reflection-based flavor
    implementation ("com.github.kirich1409:viewbindingpropertydelegate:1.5.6")

    implementation ("de.hdodenhof:circleimageview:3.1.0")


    implementation ("com.google.code.gson:gson:2.9.1")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")//Okhttp3
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")//Retrofit2
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")//Retrofit2

    debugImplementation ("com.github.chuckerteam.chucker:library:4.0.0")
    releaseImplementation ("com.github.chuckerteam.chucker:library-no-op:4.0.0")

    //MapBox
   // implementation("com.mapbox.maps:android:11.8.0")

    implementation("com.mapbox.maps:android:11.8.0") {
        exclude(group = "com.google.android.gms", module = "play-services-cronet")
    }

    implementation ("com.mapbox.maps:android:10.0.0") // MapBox Maps SDK
    implementation ("com.mapbox.mapboxsdk:mapbox-sdk-turf:6.1.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1") // Google Location API
    implementation ("implementation 'com.mapbox.mapboxsdk:mapbox-android-sdk:9.6.0'")

    implementation ("com.mapbox.navigation:android:2.10.1")
    implementation ("com.mapbox.navigation:ui-dropin:2.10.1")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.android.gms:play-services-location:20.0.0")
    implementation ("com.squareup.leakcanary:leakcanary-android:2.9.1")

    implementation ("com.mapbox.navigationcore:ui-components:3.5.2")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.0")







}