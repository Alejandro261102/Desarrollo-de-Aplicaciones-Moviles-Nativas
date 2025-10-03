plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.juegonarrativoaventura"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.juegonarrativoaventura"
        minSdk = 21
        // 3. CAMBIADO a la última SDK estable (34)
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true // Opcional, pero recomendado para vistas XML
    }
}

dependencies {

    // Dependencias base de Android (View-System)
    implementation(libs.androidx.core.ktx) // Mantuvimos la versión de 'libs'
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Dependencias de Ciclo de Vida (para ViewModels, etc.)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    // Dependencias de Fragmentos (MUY IMPORTANTE para tu proyecto)
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Librería para cargar imágenes (Coil)
    implementation("io.coil-kt:coil:2.4.0")

    val media3_version = "1.3.1" // <--- LÍNEA CORREGIDA
    implementation("androidx.media3:media3-exoplayer:$media3_version")
    implementation("androidx.media3:media3-ui:$media3_version")
    implementation("androidx.media3:media3-session:$media3_version")

    // Dependencias de Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}