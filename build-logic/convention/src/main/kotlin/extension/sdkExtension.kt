package extension
import org.gradle.api.Project

val Project.COMPILE_SDK: Int
    get() = libs.findVersion("android-compileSdk")
        .orElseThrow { NoSuchElementException("Compile SDK version not found") }.requiredVersion.toInt()

val Project.MIN_SDK: Int
    get() = libs.findVersion("android-minSdk")
        .orElseThrow { NoSuchElementException("Min SDK version not found") }.requiredVersion.toInt()

val Project.TARGET_SDK: Int
    get() = libs.findVersion("android-targetSdk")
        .orElseThrow { NoSuchElementException("Target SDK version not found") }.requiredVersion.toInt()