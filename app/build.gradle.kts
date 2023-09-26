
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.giphytesttask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.giphytesttask"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {

        release {
            isMinifyEnabled = false
            buildConfigField ("String", "API_KEY", "\"YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL\"")
            buildConfigField ("String", "BASE_URL", "\"\"https://api.giphy.com/v1/gifs/\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField ("String", "API_KEY", "\"YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL\"")
            buildConfigField ("String", "BASE_URL", "\"https://api.giphy.com/v1/gifs/\"")
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
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //hilt
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-compiler:2.46")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("androidx.core:core-ktx:1.12.0")
    //life
    val lifecycleVersion = "2.6.2"
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
    //navigation
    val navVersion = "2.7.3"
    implementation ("androidx.navigation:navigation-compose:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")
    //coil
    val coilVersion = "2.3.0"
    implementation ("io.coil-kt:coil-compose:$coilVersion")
    implementation ("io.coil-kt:coil-gif:$coilVersion")
    //paging
    implementation("androidx.paging:paging-compose:3.2.1")

    val retrofitVersion = "2.9.0"
    //retrofit2
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
    val moshiVersion = "1.15.0"
    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
    implementation("com.squareup.moshi:moshi-adapters:$moshiVersion")
    val okHttpVersion = "4.10.0"
    //okhttp
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.09.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}