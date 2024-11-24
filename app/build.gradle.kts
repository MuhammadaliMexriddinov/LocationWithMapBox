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

    implementation ("com.mapbox.maps:android:10.15.0") // MapBox Maps SDK
    implementation ("com.mapbox.navigation:android:2.14.0")



}