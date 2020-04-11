export interface WakeLockModule {
  setWakeLock: () => Promise<boolean>;
  releaseWakeLock: () => Promise<boolean>;
  isWakeLocked: () => Promise<boolean>;
}
