package com.security.library

import android.app.Activity
import android.os.Build
import android.os.Debug
import com.google.android.play.integrity.IntegrityManager
import com.google.android.play.integrity.IntegrityManagerFactory
import com.google.android.play.integrity.model.IntegrityTokenRequest
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

object SecurityChecks {
    fun isRooted(): Boolean {
        val paths = arrayOf(
            "/sbin/su", "/system/bin/su", "/system/xbin/su",
            "/data/local/xbin/su", "/data/local/bin/su",
            "/system/sd/xbin/su", "/system/bin/failsafe/su",
            "/data/local/su"
        )
        if (paths.any { File(it).exists() }) return true
        return try {
            val p = Runtime.getRuntime().exec("which su")
            BufferedReader(InputStreamReader(p.inputStream)).readLine() != null
        } catch (e: Exception) { false }
    }

    fun isMagiskPresent(): Boolean {
        val magiskPaths = arrayOf("/sbin/magisk", "/data/adb/magisk", "/data/adb/modules")
        if (magiskPaths.any { File(it).exists() }) return true
        return try {
            val p = Runtime.getRuntime().exec("which magisk")
            BufferedReader(InputStreamReader(p.inputStream)).readLine() != null
        } catch (e: Exception) { false }
        return false
    }

    fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.contains("generic") ||
                Build.MODEL.contains("Emulator") ||
                Build.MANUFACTURER.contains("Genymotion") ||
                Build.BRAND.startsWith("generic") ||
                Build.DEVICE.startsWith("generic"))
    }

    fun isDebuggerConnected(): Boolean = Debug.isDebuggerConnected()

    fun hasFridaOrXposed(): Boolean {
        val suspicious = listOf("frida-server", "xposed")
        return suspicious.any { File("/data/local/tmp/$it").exists() }
    }

    fun requestIntegrityToken(activity: Activity, nonce: String, callback: (String?) -> Unit) {
        val integrityManager: IntegrityManager = IntegrityManagerFactory.create(activity)
        val request = IntegrityTokenRequest.builder().setNonce(nonce).build()
        integrityManager.requestIntegrityToken(request)
            .addOnSuccessListener { callback(it.token()) }
            .addOnFailureListener { callback(null) }
    }
}
