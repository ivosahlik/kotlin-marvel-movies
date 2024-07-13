import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    // is need se apply in build.gradle.kts and kapt("com.google.dagger:hilt-compiler
    id("com.google.dagger.hilt.android")
}

val apikeyProperties = Properties()
apikeyProperties.load(project.rootProject.file("apikey.properties").reader())

android {
    namespace = "cz.ivosahlik.marvel_movies"
    compileSdk = 34

    defaultConfig {
        applicationId = "cz.ivosahlik.marvel_movies"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "MARVEL_KEY", apikeyProperties.getProperty("MARVEL_KEY"))
        buildConfigField("String", "MARVEL_SECRET", apikeyProperties.getProperty("MARVEL_SECRET"))
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

var coil_version = "2.1.0"
var retrofit_version = "2.9.0"
var hilt_version = "2.48"
var room_version = "2.4.2"

dependencies {

    // implementation
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    //dependency for the navigation.
    implementation("androidx.navigation:navigation-compose:2.7.0-rc01")

    // test implementation
    testImplementation("junit:junit:4.13.2")

    // android test implementation
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // debug implementation
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")

    // issue - https://github.com/square/okhttp/issues/5505
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    // added id("kotlin-kapt") plugins
    kapt("com.google.dagger:hilt-compiler:$hilt_version")

    // room
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    implementation("io.coil-kt:coil-compose:$coil_version")
}