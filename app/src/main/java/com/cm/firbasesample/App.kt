package com.cm.firbasesample

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


lateinit var appContext: Context


@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Timber.plant(if (BuildConfig.DEBUG) CMDebugTree() else CMTree())
    }
}

private class CMDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "CM://${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}

private class CMTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.WARN, Log.ERROR -> t?.let {
                val newThrowable = Throwable().initCause(t).apply {
                    stackTrace = Thread.currentThread().stackTrace
                }
                FirebaseCrashlytics.getInstance().recordException(newThrowable)
            }
            else -> FirebaseCrashlytics.getInstance().log("$tag | $message")
        }
    }
}