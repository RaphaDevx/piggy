package com.samsung.android.sdk.samsungpay.v2;

import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
abstract class BindRetry {
    private static final int BIND_COUNTER_MAX = 4;
    private static final String TAG = "SPAYSDK:BindRetry";
    private long SPAY_BIND_TIMEOUT;
    private BindRetryTimerTask bindRetryTimerTask;
    protected boolean isScheduled;
    private int mBindCounter;
    private final Object scheduleLock;

    abstract void runTimer();

    protected class BindRetryTimerTask extends TimerTask {
        protected BindRetryTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Log.d(BindRetry.TAG, "run : BindRetryTimerTask");
            synchronized (BindRetry.this.scheduleLock) {
                BindRetry.this.isScheduled = false;
            }
            BindRetry.this.runTimer();
        }
    }

    protected BindRetry(long j) {
        this.SPAY_BIND_TIMEOUT = 30000L;
        this.scheduleLock = new Object();
        if (j > 0) {
            this.SPAY_BIND_TIMEOUT = j;
        }
        init();
    }

    protected BindRetry() {
        this.SPAY_BIND_TIMEOUT = 30000L;
        this.scheduleLock = new Object();
        init();
    }

    private void init() {
        this.isScheduled = false;
        this.mBindCounter = 4;
    }

    protected boolean isOverCounter() {
        return getBindCounter() <= 0;
    }

    private int getBindCounter() {
        int i;
        synchronized (this.scheduleLock) {
            i = this.mBindCounter;
        }
        return i;
    }

    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone instance of this class");
    }

    private void setIsScheduled(boolean z) {
        this.isScheduled = z;
    }

    protected boolean isScheduled() {
        boolean z;
        synchronized (this.scheduleLock) {
            z = this.isScheduled;
        }
        return z;
    }

    protected boolean scheduleBindTimer() {
        Log.d(TAG, "scheduleBindTimer: scheduling bind timer");
        synchronized (this.scheduleLock) {
            if (isOverCounter()) {
                releaseBindTimerTask();
                return false;
            }
            this.mBindCounter--;
            Log.d(TAG, "scheduleBindTimer: count = " + this.mBindCounter);
            try {
                this.bindRetryTimerTask = new BindRetryTimerTask();
                new Timer().schedule(this.bindRetryTimerTask, this.SPAY_BIND_TIMEOUT);
                setIsScheduled(true);
                return true;
            } catch (Exception e) {
                Log.d(TAG, "scheduleBindTimer: Exception in scheduling bind timer ");
                e.printStackTrace();
                setIsScheduled(false);
                return false;
            }
        }
    }

    protected void releaseBindTimerTask() {
        Log.d(TAG, "releaseBindTimerTask: cleanup binder timer");
        synchronized (this.scheduleLock) {
            try {
                try {
                    if (this.bindRetryTimerTask.cancel()) {
                        Log.d(TAG, "releaseBindTimerTask: timerTask cancel return true ");
                    } else {
                        Log.d(TAG, "releaseBindTimerTask: timerTask cancel return false ");
                    }
                } catch (Exception e) {
                    Log.d(TAG, "releaseBindTimerTask: Exception in canceling bind timer ");
                    e.printStackTrace();
                }
            } finally {
                init();
            }
        }
    }
}
