package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArrayMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.util.ApplicationUtils;
import com.salesforce.marketingcloud.sfmcsdk.util.FileUtilsKt;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BehaviorManagerImpl.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 !2\u00020\u0001:\u0002 !B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0014J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0012H\u0002J\u001e\u0010\u0019\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0016J\r\u0010\u001d\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001eJ\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u000bH\u0016R\u0014\u0010\u0005\u001a\b\u0018\u00010\u0006R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManagerImpl;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManager;", "executorService", "Ljava/util/concurrent/ExecutorService;", "(Ljava/util/concurrent/ExecutorService;)V", "behaviorReceiver", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManagerImpl$BehaviorReceiver;", "behaviorTypeListeners", "Landroid/util/ArrayMap;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorType;", "", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorListener;", "getBehaviorTypeListeners$sfmcsdk_release", "()Landroid/util/ArrayMap;", "context", "Landroid/content/Context;", "stickyBehaviors", "", "Landroid/os/Bundle;", "initIfNecessary", "initIfNecessary$sfmcsdk_release", "onBehavior", "", "behaviorType", "extras", "registerForBehaviors", "behaviorTypes", "Ljava/util/EnumSet;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "tearDown", "tearDown$sfmcsdk_release", "unregisterForAllBehaviors", "BehaviorReceiver", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BehaviorManagerImpl implements BehaviorManager {
    public static final String BUNDLE_KEY_APP_NAME = "application_name";
    public static final String BUNDLE_KEY_CURRENT_VERSION = "current_version";
    public static final String BUNDLE_KEY_PREVIOUS_VERSION = "previous_version";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String KEY_PREFS_CAPTURED_APP_VERSION = "captured_app_version";
    private static final String TAG = "~$BehaviorManager";
    private BehaviorReceiver behaviorReceiver;
    private final ArrayMap<BehaviorType, Set<BehaviorListener>> behaviorTypeListeners;
    private Context context;
    private final ExecutorService executorService;
    private final Map<BehaviorType, Bundle> stickyBehaviors;

    public BehaviorManagerImpl(ExecutorService executorService) {
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        this.executorService = executorService;
        this.behaviorTypeListeners = new ArrayMap<>();
        this.stickyBehaviors = new LinkedHashMap();
    }

    /* compiled from: BehaviorManagerImpl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u00020\u00048\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManagerImpl$Companion;", "", "()V", "BUNDLE_KEY_APP_NAME", "", "BUNDLE_KEY_CURRENT_VERSION", "BUNDLE_KEY_PREVIOUS_VERSION", "KEY_PREFS_CAPTURED_APP_VERSION", "getKEY_PREFS_CAPTURED_APP_VERSION$sfmcsdk_release$annotations", "TAG", "notifyBehavior", "", "context", "Landroid/content/Context;", "behaviorType", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorType;", "extras", "Landroid/os/Bundle;", "notifyBehavior$sfmcsdk_release", "BehaviorRunnable", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getKEY_PREFS_CAPTURED_APP_VERSION$sfmcsdk_release$annotations() {
        }

        private Companion() {
        }

        public final void notifyBehavior$sfmcsdk_release(Context context, final BehaviorType behaviorType, final Bundle extras) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(behaviorType, "behaviorType");
            Intrinsics.checkNotNullParameter(extras, "extras");
            Intent intent = new Intent(behaviorType.getIntentFilter());
            extras.putString(BehaviorManagerImpl.BUNDLE_KEY_APP_NAME, ApplicationUtils.getApplicationName(context));
            extras.putString(BehaviorManagerImpl.BUNDLE_KEY_CURRENT_VERSION, ApplicationUtils.getApplicationVersion(context));
            intent.putExtras(extras);
            SFMCSdkLogger.INSTANCE.d(BehaviorManagerImpl.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$Companion$notifyBehavior$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Notified of behavior " + BehaviorType.this + " with " + extras + " extras.";
                }
            });
            context.sendBroadcast(intent.setPackage(context.getPackageName()));
        }

        /* compiled from: BehaviorManagerImpl.kt */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManagerImpl$Companion$BehaviorRunnable;", "Ljava/lang/Runnable;", "listeners", "", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorListener;", "behaviorType", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorType;", "data", "Landroid/os/Bundle;", "(Ljava/util/Set;Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorType;Landroid/os/Bundle;)V", "behavior", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior;", "run", "", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class BehaviorRunnable implements Runnable {
            private final Behavior behavior;
            private final BehaviorType behaviorType;
            private final Set<BehaviorListener> listeners;

            /* JADX WARN: Multi-variable type inference failed */
            public BehaviorRunnable(Set<? extends BehaviorListener> listeners, BehaviorType behaviorType, Bundle data) {
                Intrinsics.checkNotNullParameter(listeners, "listeners");
                Intrinsics.checkNotNullParameter(behaviorType, "behaviorType");
                Intrinsics.checkNotNullParameter(data, "data");
                this.listeners = listeners;
                this.behaviorType = behaviorType;
                this.behavior = behaviorType.toBehavior$sfmcsdk_release(data);
            }

            @Override // java.lang.Runnable
            public void run() {
                Behavior behavior = this.behavior;
                if (behavior != null) {
                    SFMCSdk.INSTANCE.track(BehaviorTypeKt.toEvent(behavior));
                    for (final BehaviorListener behaviorListener : this.listeners) {
                        if (behaviorListener != null) {
                            try {
                                SFMCSdkLogger.INSTANCE.d(BehaviorManagerImpl.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$Companion$BehaviorRunnable$run$1$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public final String invoke() {
                                        BehaviorType behaviorType;
                                        behaviorType = BehaviorManagerImpl.Companion.BehaviorRunnable.this.behaviorType;
                                        return "Delivering behavior " + behaviorType + " to " + behaviorListener + ".";
                                    }
                                });
                                behaviorListener.onBehavior(behavior);
                            } catch (Exception e) {
                                SFMCSdkLogger.INSTANCE.e(BehaviorManagerImpl.TAG, e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$Companion$BehaviorRunnable$run$1$1$1$2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public final String invoke() {
                                        return "Exception " + e.getLocalizedMessage() + " occurred.";
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
    }

    public final ArrayMap<BehaviorType, Set<BehaviorListener>> getBehaviorTypeListeners$sfmcsdk_release() {
        return this.behaviorTypeListeners;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManager
    public void registerForBehaviors(EnumSet<BehaviorType> behaviorTypes, final BehaviorListener listener) {
        Bundle bundle;
        Intrinsics.checkNotNullParameter(behaviorTypes, "behaviorTypes");
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.behaviorTypeListeners) {
            for (final BehaviorType behaviorType : behaviorTypes) {
                HashSet hashSet = this.behaviorTypeListeners.get(behaviorType);
                if (hashSet == null) {
                    hashSet = new HashSet();
                    this.behaviorTypeListeners.put(behaviorType, hashSet);
                }
                SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$registerForBehaviors$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "Listener " + BehaviorListener.this + " registering for " + behaviorType;
                    }
                });
                hashSet.add(listener);
            }
            Unit unit = Unit.INSTANCE;
        }
        synchronized (this.stickyBehaviors) {
            for (final BehaviorType behaviorType2 : behaviorTypes) {
                if (behaviorType2.getSticky() && (bundle = this.stickyBehaviors.get(behaviorType2)) != null) {
                    ExecutorService executorService = this.executorService;
                    Set of = SetsKt.setOf(listener);
                    Intrinsics.checkNotNull(behaviorType2);
                    executorService.submit(new Companion.BehaviorRunnable(of, behaviorType2, bundle));
                    SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$registerForBehaviors$2$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Delivering sticky behavior " + BehaviorType.this + " to " + listener;
                        }
                    });
                }
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManager
    public void unregisterForAllBehaviors(final BehaviorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.behaviorTypeListeners) {
            SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$unregisterForAllBehaviors$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "Unregistering " + BehaviorListener.this + " for all behaviors.";
                }
            });
            Set<Map.Entry<BehaviorType, Set<BehaviorListener>>> entrySet = this.behaviorTypeListeners.entrySet();
            Intrinsics.checkNotNullExpressionValue(entrySet, "<get-entries>(...)");
            Iterator<T> it = entrySet.iterator();
            while (it.hasNext()) {
                ((Set) ((Map.Entry) it.next()).getValue()).remove(listener);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBehavior(final BehaviorType behaviorType, final Bundle extras) {
        extras.putLong("timestamp", System.currentTimeMillis());
        synchronized (this.behaviorTypeListeners) {
            Set<BehaviorListener> set = this.behaviorTypeListeners.get(behaviorType);
            if (set != null) {
                Intrinsics.checkNotNull(set);
                if (!set.isEmpty()) {
                    try {
                        this.executorService.submit(new Companion.BehaviorRunnable(set, behaviorType, extras));
                    } catch (Exception e) {
                        SFMCSdkLogger.INSTANCE.e(TAG, e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$onBehavior$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "BehaviorRunnable failed for " + BehaviorType.this + " with " + extras;
                            }
                        });
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }
        synchronized (this.stickyBehaviors) {
            List<BehaviorType> behaviorTypesToClear$sfmcsdk_release = behaviorType.getBehaviorTypesToClear$sfmcsdk_release();
            if (behaviorTypesToClear$sfmcsdk_release != null) {
                Iterator<T> it = behaviorTypesToClear$sfmcsdk_release.iterator();
                while (it.hasNext()) {
                    this.stickyBehaviors.put((BehaviorType) it.next(), null);
                }
            }
            if (behaviorType.getSticky()) {
                this.stickyBehaviors.put(behaviorType, extras);
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    public final BehaviorManager initIfNecessary$sfmcsdk_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.context == null) {
            this.context = context;
        }
        if (this.behaviorReceiver == null) {
            SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$initIfNecessary$1
                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "BehaviorManager initializing.";
                }
            });
            BehaviorReceiver behaviorReceiver = new BehaviorReceiver();
            this.behaviorReceiver = behaviorReceiver;
            BehaviorReceiver behaviorReceiver2 = behaviorReceiver;
            IntentFilter intentFilter = new IntentFilter();
            Iterator<E> it = BehaviorType.getEntries().iterator();
            while (it.hasNext()) {
                intentFilter.addAction(((BehaviorType) it.next()).getIntentFilter());
            }
            Unit unit = Unit.INSTANCE;
            ContextCompat.registerReceiver(context, behaviorReceiver2, intentFilter, 4);
            SharedPreferences sharedPreferences = context.getSharedPreferences(FileUtilsKt.getFilenamePrefixForSFMCSdk("default"), 0);
            String string = sharedPreferences.getString(KEY_PREFS_CAPTURED_APP_VERSION, null);
            String applicationVersion = ApplicationUtils.getApplicationVersion(context);
            if (!Intrinsics.areEqual(applicationVersion, string) && applicationVersion != null) {
                sharedPreferences.edit().putString(KEY_PREFS_CAPTURED_APP_VERSION, applicationVersion).apply();
                Companion companion = INSTANCE;
                BehaviorType behaviorType = BehaviorType.APP_VERSION_CHANGED;
                Bundle bundle = new Bundle();
                bundle.putString(BUNDLE_KEY_PREVIOUS_VERSION, string);
                Unit unit2 = Unit.INSTANCE;
                companion.notifyBehavior$sfmcsdk_release(context, behaviorType, bundle);
            }
        }
        return this;
    }

    public final void tearDown$sfmcsdk_release() {
        BehaviorReceiver behaviorReceiver;
        Context context = this.context;
        if (context == null || (behaviorReceiver = this.behaviorReceiver) == null) {
            return;
        }
        context.unregisterReceiver(behaviorReceiver);
    }

    /* compiled from: BehaviorManagerImpl.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManagerImpl$BehaviorReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManagerImpl;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private final class BehaviorReceiver extends BroadcastReceiver {
        public BehaviorReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            if (intent != null) {
                BehaviorManagerImpl behaviorManagerImpl = BehaviorManagerImpl.this;
                String action = intent.getAction();
                if (action != null) {
                    final BehaviorType fromString = BehaviorType.INSTANCE.fromString(action);
                    if (fromString != null) {
                        SFMCSdkLogger.INSTANCE.d(BehaviorManagerImpl.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$BehaviorReceiver$onReceive$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return BehaviorType.this + " received with " + intent.getExtras() + " extras.";
                            }
                        });
                        Bundle extras = intent.getExtras();
                        if (extras == null) {
                            extras = new Bundle();
                        }
                        Intrinsics.checkNotNull(extras);
                        behaviorManagerImpl.onBehavior(fromString, extras);
                        return;
                    }
                    SFMCSdkLogger.INSTANCE.w(BehaviorManagerImpl.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl$BehaviorReceiver$onReceive$1$1$2
                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "An unknown behavior was received.";
                        }
                    });
                }
            }
        }
    }
}
