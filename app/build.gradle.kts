plugins {
    id("org.cm.official.application")
    alias(libs.plugins.google.services)
    alias(libs.plugins.ksp)
    alias(libs.plugins.crashlytics)
}

android {
    namespace = "com.cm.firbasesample"


    defaultConfig {
        applicationId = "com.cm.firbasesample"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()

        if (project.hasProperty("dev")) {
            versionNameSuffix = "-Dev"
        }

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

    applicationVariants.all {
        val variant = this
        kotlin {
            sourceSets {
                getByName(variant.name) {
                    kotlin.srcDir("build/generated/ksp/${variant.name}/kotlin")
                }
            }
        }
    }

}


dependencies {


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.lifecycle.process)


    implementation(libs.kotlin.coroutines.google.play)

    implementation(platform(libs.firebase))
    implementation(libs.bundles.firebase)

    implementation(libs.firebase.messaging.lifecycle.ktx)

    implementation(libs.ssp)
    implementation(libs.sdp)
}