plugins {
    `kotlin-dsl`
}

group = "org.cm.official.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kotlin.gradleplugin)
}

gradlePlugin {
    plugins {
        create("android-application") {
            id = "org.cm.official.application"
            implementationClass = "org.cm.official.plugin.AndroidApplicationPlugin"
        }
        create("android-kotlin") {
            id = "org.cm.official.kotlin"
            implementationClass = "org.cm.official.plugin.AndroidKotlinPlugin"
        }
        create("android-hilt") {
            id = "org.cm.official.hilt"
            implementationClass = "org.cm.official.plugin.AndroidHiltPlugin"
        }
        create("kotlin-serialization") {
            id = "org.cm.official.serialization"
            implementationClass = "org.cm.official.plugin.KotlinSerializationPlugin"
        }
    }
}
