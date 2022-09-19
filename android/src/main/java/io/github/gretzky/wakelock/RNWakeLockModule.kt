package io.github.gretzky.wakelock

import android.content.Context
import android.net.wifi.WifiManager
import android.os.PowerManager

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class RNWakeLockModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private val mPowerManager: PowerManager
    private val mWifiManager: WifiManager

    private var wakeLock: PowerManager.WakeLock? = null
    private var wifiLock: WifiManager.WifiLock? = null
    private var isWakeLocked = false

    override fun getName(): String {
        return "RNWakeLock"
    }

    init {
        mPowerManager = reactContext.getSystemService(Context.POWER_SERVICE) as PowerManager
        mWifiManager = reactContext.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        wakeLock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.name)
        wifiLock = mWifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL, this.name)
    }

    @ReactMethod
    fun setWakeLock(promise: Promise) {
        if (isWakeLocked) {
            promise.resolve(isWakeLocked)
            return
        }
        this.wakeLock?.acquire()
        this.wifiLock?.acquire()
        isWakeLocked = true
        promise.resolve(isWakeLocked)
    }

    @ReactMethod
    fun releaseWakeLock(promise: Promise) {
        if (!isWakeLocked) {
            promise.resolve(isWakeLocked)
            return
        }
        this.wakeLock?.release()
        this.wifiLock?.release()
        isWakeLocked = false
        promise.resolve(isWakeLocked)
    }

    @ReactMethod
    fun isWakeLocked(promise: Promise) {
        promise.resolve(isWakeLocked)
    }
}
