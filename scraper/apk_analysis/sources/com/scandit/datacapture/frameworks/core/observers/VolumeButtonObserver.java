package com.scandit.datacapture.frameworks.core.observers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VolumeButtonObserver.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00112\u00020\u0001:\u0002\u0011\u0012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\u000eR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/observers/VolumeButtonObserver;", "", "context", "Landroid/content/Context;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/frameworks/core/observers/VolumeButtonObserver$VolumeButtonCallback;", "(Landroid/content/Context;Lcom/scandit/datacapture/frameworks/core/observers/VolumeButtonObserver$VolumeButtonCallback;)V", "isSubscribed", "", "lastVolumeChangeTime", "", "volumeReceiver", "Landroid/content/BroadcastReceiver;", "handleVolumeChange", "", "subscribe", "unsubscribe", "Companion", "VolumeButtonCallback", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class VolumeButtonObserver {
    private static final long MIN_INTERVAL_BETWEEN_UPDATES = 350;
    private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private final VolumeButtonCallback callback;
    private final Context context;
    private boolean isSubscribed;
    private long lastVolumeChangeTime;
    private BroadcastReceiver volumeReceiver;

    /* compiled from: VolumeButtonObserver.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/observers/VolumeButtonObserver$VolumeButtonCallback;", "", "onVolumeButtonPressed", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface VolumeButtonCallback {
        void onVolumeButtonPressed();
    }

    public VolumeButtonObserver(Context context, VolumeButtonCallback volumeButtonCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.callback = volumeButtonCallback;
    }

    public final synchronized void subscribe() {
        if (this.isSubscribed) {
            return;
        }
        this.volumeReceiver = new BroadcastReceiver() { // from class: com.scandit.datacapture.frameworks.core.observers.VolumeButtonObserver$subscribe$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                if (Intrinsics.areEqual(intent.getAction(), "android.media.VOLUME_CHANGED_ACTION")) {
                    VolumeButtonObserver.this.handleVolumeChange();
                }
            }
        };
        this.context.registerReceiver(this.volumeReceiver, new IntentFilter(VOLUME_CHANGED_ACTION));
        this.isSubscribed = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleVolumeChange() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastVolumeChangeTime > MIN_INTERVAL_BETWEEN_UPDATES) {
            VolumeButtonCallback volumeButtonCallback = this.callback;
            if (volumeButtonCallback != null) {
                volumeButtonCallback.onVolumeButtonPressed();
            }
            this.lastVolumeChangeTime = currentTimeMillis;
        }
    }

    public final synchronized void unsubscribe() {
        if (this.isSubscribed) {
            BroadcastReceiver broadcastReceiver = this.volumeReceiver;
            if (broadcastReceiver != null) {
                this.context.unregisterReceiver(broadcastReceiver);
            }
            this.isSubscribed = false;
        }
    }
}
