# react-native-android-wake-lock

Native Module for setting a [wake lock](https://developer.android.com/training/scheduling/wakelock) on an Android device. Utilizes both `PowerManager` and `WifiManager`.

## Installation

**Note:** This package requires React Native >=0.60.

Install with npm/yarn.

```bash
npm install react-native-android-wake-lock --save
// or
yarn add react-native-android-wake-lock
```

Since this package requires RN 0.60 or higher, this package will be linked automatically. If you run into an issue, you can try `react-native link react-native-android-wake-lock`.

### Additional setup

This library requires your `minSdkVersion` to be set to `>= 23`. So make sure to edit your gradle build file (`android/build.gradle`) file with:

```gradle
buildscript {
  ext {
    ...
    minSdkVersion = 23 // or higher
    ...
  }
}
```

Also, make sure to request the wake lock permission. Add the following inside the project manifest (`android/app/src/main/AndroidManifest.xml`):

```xml
<uses-permission android:name="android.permission.WAKE_LOCK" />
```

## Usage

This module exposes 2 possible methods for accessing the wake lock: via an interface object or a React hook. Depending on your use case, one or the other should suffice.

```js
import React from "react";
import { WakeLockInterface, useWakeLock } from "react-native-android-wake-lock";

// the interface exposes all 3 (async) methods
const isWakeLocked = await WakeLockInterface.isWakeLocked();
// these 2 aren't necessary if you're using the hook
const setWakeLock = await WakeLockInterface.setWakeLock();
const releaseWakeLock = await WakeLockInterface.releaseWakeLock();

// you can also use the hook to set/release a wake lock on component mount/unmount
const Component = () => {
  const [wakeLocked, setWakeLocked] = useState(isWakeLocked);

  // you can use the `isWakeLocked` method from the interface to check whether or not the wake lock is set
  useEffect(() => {
    console.log(wakeLocked);
  }, [wakeLocked]);

  useWakeLock();

  return <View />;
};
```

## API

The interface has 3 methods:

- **`setWakeLock(): Promise<boolean>`** - sets both the WakeLock and WifiLock
- **`releaseWakeLock(): Promise<boolean>`** - releases both the WakeLock and WifiLock
- **`isWakeLocked(): Promise<boolean>`** - whether or not the wake/wifi locks are held

**:warning: On platforms other than Android, methods will always return `false`.**
