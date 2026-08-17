package com.scandit.datacapture.core.internal.sdk.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class BatterySavingManagerImpl implements BatterySavingManager {
    static final /* synthetic */ KProperty[] j = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(BatterySavingManagerImpl.class), "isBatterySavingEnabled", "isBatterySavingEnabled()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(BatterySavingManagerImpl.class), "isPowerSaveEnabled", "isPowerSaveEnabled()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(BatterySavingManagerImpl.class), "isRunningHot", "isRunningHot()Z"))};
    private final Context a;
    private boolean b;
    private final BatterySavingManagerImpl$special$$inlined$distinctObservable$1 c;
    private final BatterySavingManagerImpl$special$$inlined$distinctObservable$2 d;
    private final BatterySavingManagerImpl$special$$inlined$distinctObservable$3 e;
    private final Set f;
    private final PowerManager g;
    private final BatterySavingManagerImpl$powerReceiver$1 h;
    private final PowerManager.OnThermalStatusChangedListener i;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$special$$inlined$distinctObservable$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$special$$inlined$distinctObservable$2] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$special$$inlined$distinctObservable$3] */
    /* JADX WARN: Type inference failed for: r3v3, types: [com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$powerReceiver$1] */
    public BatterySavingManagerImpl(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
        final Boolean bool = Boolean.FALSE;
        Delegates delegates = Delegates.INSTANCE;
        this.c = new ObservableProperty<Boolean>(bool) { // from class: com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$special$$inlined$distinctObservable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                BatterySavingManagerImpl.a(this, newValue.booleanValue());
            }
        };
        this.d = new ObservableProperty<Boolean>(bool) { // from class: com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$special$$inlined$distinctObservable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                boolean booleanValue = newValue.booleanValue();
                BatterySavingManagerImpl batterySavingManagerImpl = this;
                BatterySavingManagerImpl.b(batterySavingManagerImpl, booleanValue | BatterySavingManagerImpl.d(batterySavingManagerImpl));
            }
        };
        this.e = new ObservableProperty<Boolean>(bool) { // from class: com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$special$$inlined$distinctObservable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty<?> property, Boolean oldValue, Boolean newValue) {
                Intrinsics.checkNotNullParameter(property, "property");
                if (Intrinsics.areEqual(oldValue, newValue)) {
                    return;
                }
                boolean booleanValue = newValue.booleanValue();
                BatterySavingManagerImpl batterySavingManagerImpl = this;
                BatterySavingManagerImpl.b(batterySavingManagerImpl, booleanValue | BatterySavingManagerImpl.c(batterySavingManagerImpl));
            }
        };
        Set newSetFromMap = Collections.newSetFromMap(new WeakHashMap());
        Intrinsics.checkNotNullExpressionValue(newSetFromMap, "newSetFromMap(...)");
        this.f = newSetFromMap;
        this.g = (PowerManager) context.getSystemService(PowerManager.class);
        this.h = new BroadcastReceiver() { // from class: com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$powerReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                PowerManager powerManager;
                BatterySavingManagerImpl batterySavingManagerImpl = BatterySavingManagerImpl.this;
                powerManager = batterySavingManagerImpl.g;
                BatterySavingManagerImpl.c(batterySavingManagerImpl, powerManager != null ? powerManager.isPowerSaveMode() : false);
                BatterySavingManagerImpl.a(BatterySavingManagerImpl.this);
            }
        };
        this.i = new PowerManager.OnThermalStatusChangedListener() { // from class: com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManagerImpl$$ExternalSyntheticLambda0
            @Override // android.os.PowerManager.OnThermalStatusChangedListener
            public final void onThermalStatusChanged(int i) {
                BatterySavingManagerImpl.a(BatterySavingManagerImpl.this, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(BatterySavingManagerImpl this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.e.setValue(this$0, j[2], Boolean.valueOf(i >= 3));
        if (this$0.f.isEmpty()) {
            this$0.deregisterContextListeners();
        }
    }

    public static final boolean c(BatterySavingManagerImpl batterySavingManagerImpl) {
        return batterySavingManagerImpl.d.getValue(batterySavingManagerImpl, j[1]).booleanValue();
    }

    public static final boolean d(BatterySavingManagerImpl batterySavingManagerImpl) {
        return batterySavingManagerImpl.e.getValue(batterySavingManagerImpl, j[2]).booleanValue();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager
    public final boolean addListener(Function1 listener) {
        PowerManager powerManager;
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (!this.f.add(listener)) {
            return false;
        }
        if (this.f.size() == 1) {
            synchronized (this) {
                if (!this.b) {
                    PowerManager powerManager2 = this.g;
                    boolean isPowerSaveMode = powerManager2 != null ? powerManager2.isPowerSaveMode() : false;
                    BatterySavingManagerImpl$special$$inlined$distinctObservable$2 batterySavingManagerImpl$special$$inlined$distinctObservable$2 = this.d;
                    KProperty<?>[] kPropertyArr = j;
                    batterySavingManagerImpl$special$$inlined$distinctObservable$2.setValue(this, kPropertyArr[1], Boolean.valueOf(isPowerSaveMode));
                    int i = Build.VERSION.SDK_INT;
                    PowerManager powerManager3 = this.g;
                    if (powerManager3 != null) {
                        setValue(this, kPropertyArr[2], Boolean.valueOf(powerManager3.getCurrentThermalStatus() >= 3));
                    }
                    if (i >= 33) {
                        this.a.registerReceiver(this.h, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"), 2);
                    } else {
                        this.a.registerReceiver(this.h, new IntentFilter("android.os.action.POWER_SAVE_MODE_CHANGED"));
                    }
                    PowerManager.OnThermalStatusChangedListener onThermalStatusChangedListener = this.i;
                    if (onThermalStatusChangedListener != null && (powerManager = this.g) != null) {
                        powerManager.addThermalStatusListener(onThermalStatusChangedListener);
                    }
                    this.b = true;
                }
            }
        }
        listener.invoke(Boolean.valueOf(getShouldEnableBatterySaving()));
        return true;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager
    public final synchronized void deregisterContextListeners() {
        PowerManager powerManager;
        if (this.b) {
            this.a.unregisterReceiver(this.h);
            PowerManager.OnThermalStatusChangedListener onThermalStatusChangedListener = this.i;
            if (onThermalStatusChangedListener != null && (powerManager = this.g) != null) {
                powerManager.removeThermalStatusListener(onThermalStatusChangedListener);
            }
            this.b = false;
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager
    public final boolean getShouldEnableBatterySaving() {
        return getValue(this, j[0]).booleanValue();
    }

    @Override // com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager
    public final boolean isListeningToPowerEvents() {
        return this.b;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.battery.BatterySavingManager
    public final boolean removeListener(Function1 listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        boolean remove = this.f.remove(listener);
        if (remove && this.f.isEmpty()) {
            deregisterContextListeners();
        }
        return remove;
    }

    public static final void b(BatterySavingManagerImpl batterySavingManagerImpl, boolean z) {
        batterySavingManagerImpl.c.setValue(batterySavingManagerImpl, j[0], Boolean.valueOf(z));
    }

    public static final void c(BatterySavingManagerImpl batterySavingManagerImpl, boolean z) {
        batterySavingManagerImpl.d.setValue(batterySavingManagerImpl, j[1], Boolean.valueOf(z));
    }

    public static final void a(BatterySavingManagerImpl batterySavingManagerImpl, boolean z) {
        Iterator it = batterySavingManagerImpl.f.iterator();
        while (it.hasNext()) {
            ((Function1) it.next()).invoke(Boolean.valueOf(z));
        }
    }

    public static final void a(BatterySavingManagerImpl batterySavingManagerImpl) {
        if (batterySavingManagerImpl.f.isEmpty()) {
            batterySavingManagerImpl.deregisterContextListeners();
        }
    }
}
