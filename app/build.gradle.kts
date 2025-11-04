plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // El plugin de compose se gestiona a través del BOM, por lo que no siempre es necesario aquí.
    // Si tu proyecto funciona sin él, puedes quitarlo. Por ahora, lo dejamos.
    // alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.fitlife.fitlifeapp"
    // OJO: SDK 36 es una versión preview/beta (VanillaIceCream).
    // Para producción o estabilidad, se recomienda usar la última versión estable (SDK 34 o 35 si ya salió).
    // Lo mantendré en 36 como lo pediste, pero tenlo en cuenta si encuentras bugs inesperados.
    compileSdk = 36

    defaultConfig {
        applicationId = "com.fitlife.fitlifeapp"
        minSdk = 26
        targetSdk = 36 // Coincide con compileSdk
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
        // Se recomienda usar JavaVersion.VERSION_17 (o superior) para proyectos modernos de Compose.
        // Lo mantendré en 11 por ahora, pero considera actualizarlo.
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    // Para usar Jetpack Compose, es crucial añadir esto dentro del bloque 'android'
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // O la versión compatible con tu compilador de Kotlin
    }
}

// --- BLOQUE DE DEPENDENCIAS CORREGIDO ---
dependencies {
    // Core y Lifecycle
    implementation("androidx.core:core-ktx:1.13.1") // Reemplazamos libs por la dependencia directa
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1") // Reemplazamos libs
    implementation("androidx.activity:activity-compose:1.9.0") // Reemplazamos libs

    // Compose BOM (Bill of Materials) - Esta línea es especial y a menudo usa 'platform(libs...)'
    // Si la siguiente línea da error, la cambiaremos también. Por ahora, es probable que funcione.
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))

    // Dependencias de Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    // --- LÍNEA 66 CORREGIDA ---
    implementation("androidx.compose.material:material-icons-extended") // ¡CORREGIDO! Ya no usa 'libs'

    // Navegación con Compose
    implementation("androidx.navigation:navigation-compose:2.7.7") // Reemplazamos libs

    // ViewModel y Lifecycle (Estas ya estaban bien)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")

    // Networking con Retrofit y OkHttp (Estas ya estaban bien)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")

    // Dependencias de Testeo
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

