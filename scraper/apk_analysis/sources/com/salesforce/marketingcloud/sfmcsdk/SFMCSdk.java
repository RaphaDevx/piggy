package com.salesforce.marketingcloud.sfmcsdk;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.BehaviorManagerImpl;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.Identity;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogLevel;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.LogListener;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutors;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.SdkExecutorsKt;
import com.salesforce.marketingcloud.sfmcsdk.modules.Config;
import com.salesforce.marketingcloud.sfmcsdk.modules.Module;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleIdentifier;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModule;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModuleConfig;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModuleReadyListener;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModule;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleConfig;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleReadyListener;
import com.tealium.library.ConsentManager;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import io.sentry.SentryEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: SFMCSdk.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J%\u0010\u0018\u001a\u00020\u00132\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001b0\u001a\"\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdk;", "", FirebaseConstants.Commands.CONFIGURE, "Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkModuleConfig;", "(Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkModuleConfig;)V", "getConfig", "()Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkModuleConfig;", "executors", "Lcom/salesforce/marketingcloud/sfmcsdk/components/utils/SdkExecutors;", "identity", "Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/Identity;", "getIdentity", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/Identity;", "setIdentity", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/Identity;)V", SentryEvent.JsonKeys.MODULES, "", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/Module;", ConsentManager.ConsentCategory.CDP, "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/salesforce/marketingcloud/sfmcsdk/modules/cdp/CdpModuleReadyListener;", "getSdkState", "Lorg/json/JSONObject;", "internalTrack", "events", "", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event;", "([Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event;)V", "mp", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/push/PushModuleReadyListener;", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SFMCSdk {
    private static final Object SDK_LOCK;
    public static final String SDK_VERSION_NAME = "1.0.5";
    private static final String TAG = "~$SFMCSdk";
    private static final List<WhenReadyHandler> UNIFIED_SDK_INSTANCE_REQUESTS;
    private static final BehaviorManagerImpl behaviorManager;
    private static volatile InitializationState initializationState;
    private static SFMCSdk instance;
    private final SFMCSdkModuleConfig config;
    private final SdkExecutors executors;
    public Identity identity;
    private final List<Module> modules;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static PushModule pushModule = new PushModule();
    private static CdpModule cdpModule = new CdpModule();

    /* compiled from: SFMCSdk.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ModuleIdentifier.values().length];
            try {
                iArr[ModuleIdentifier.PUSH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ModuleIdentifier.CDP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ SFMCSdk(SFMCSdkModuleConfig sFMCSdkModuleConfig, DefaultConstructorMarker defaultConstructorMarker) {
        this(sFMCSdkModuleConfig);
    }

    @JvmStatic
    public static final void configure(Context context, SFMCSdkModuleConfig sFMCSdkModuleConfig) {
        INSTANCE.configure(context, sFMCSdkModuleConfig);
    }

    @JvmStatic
    public static final void configure(Context context, SFMCSdkModuleConfig sFMCSdkModuleConfig, Function1<? super InitializationStatus, Unit> function1) {
        INSTANCE.configure(context, sFMCSdkModuleConfig, function1);
    }

    @JvmStatic
    public static final void requestSdk(SFMCSdkReadyListener sFMCSdkReadyListener) {
        INSTANCE.requestSdk(sFMCSdkReadyListener);
    }

    @JvmStatic
    public static final void setLogging(LogLevel logLevel, LogListener logListener) {
        INSTANCE.setLogging(logLevel, logListener);
    }

    @JvmStatic
    public static final void track(Event... eventArr) {
        INSTANCE.track(eventArr);
    }

    private SFMCSdk(SFMCSdkModuleConfig sFMCSdkModuleConfig) {
        this.config = sFMCSdkModuleConfig;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(newCachedThreadPool, "newCachedThreadPool(...)");
        this.executors = new SdkExecutors(newCachedThreadPool, null, 2, null);
        this.modules = new ArrayList();
        Iterator<T> it = sFMCSdkModuleConfig.getConfigs$sfmcsdk_release().iterator();
        while (it.hasNext()) {
            int i = WhenMappings.$EnumSwitchMapping$0[((Config) it.next()).getModuleIdentifier().ordinal()];
            if (i == 1) {
                if (this.config.getPushModuleConfig() != null) {
                    this.modules.add(pushModule);
                }
            } else if (i == 2 && this.config.getCdpModuleConfig() != null) {
                this.modules.add(cdpModule);
            }
        }
    }

    public final SFMCSdkModuleConfig getConfig() {
        return this.config;
    }

    /* compiled from: SFMCSdk.kt */
    @Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u001c\b\u0002\u0010%\u001a\u0016\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020 \u0018\u00010&j\u0004\u0018\u0001`(H\u0007J,\u0010)\u001a\u00020 2\u001a\u0010%\u001a\u0016\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020 \u0018\u00010&j\u0004\u0018\u0001`(2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010,\u001a\u00020 2\u0006\u0010%\u001a\u00020-H\u0007J\u001a\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u0002002\b\u0010%\u001a\u0004\u0018\u000101H\u0007J\b\u00102\u001a\u00020 H\u0002J%\u00103\u001a\u00020 2\u0016\u00104\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010605\"\u0004\u0018\u000106H\u0007¢\u0006\u0002\u00107R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u00020\f8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u00068"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdk$Companion;", "", "()V", "SDK_LOCK", "Ljava/lang/Object;", "SDK_VERSION_NAME", "", "TAG", "UNIFIED_SDK_INSTANCE_REQUESTS", "", "Lcom/salesforce/marketingcloud/sfmcsdk/WhenReadyHandler;", "behaviorManager", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManagerImpl;", "getBehaviorManager$sfmcsdk_release", "()Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/BehaviorManagerImpl;", "cdpModule", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/cdp/CdpModule;", "getCdpModule$sfmcsdk_release", "()Lcom/salesforce/marketingcloud/sfmcsdk/modules/cdp/CdpModule;", "setCdpModule$sfmcsdk_release", "(Lcom/salesforce/marketingcloud/sfmcsdk/modules/cdp/CdpModule;)V", "initializationState", "Lcom/salesforce/marketingcloud/sfmcsdk/InitializationState;", "instance", "Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdk;", "pushModule", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/push/PushModule;", "getPushModule$sfmcsdk_release", "()Lcom/salesforce/marketingcloud/sfmcsdk/modules/push/PushModule;", "setPushModule$sfmcsdk_release", "(Lcom/salesforce/marketingcloud/sfmcsdk/modules/push/PushModule;)V", "configure", "", "context", "Landroid/content/Context;", FirebaseConstants.Commands.CONFIGURE, "Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkModuleConfig;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "Lcom/salesforce/marketingcloud/sfmcsdk/InitializationStatus;", "Lcom/salesforce/marketingcloud/sfmcsdk/InitializationListener;", "notifyInitializationStatusListener", "isSuccessful", "", "requestSdk", "Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkReadyListener;", "setLogging", "level", "Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogLevel;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/logging/LogListener;", "staticTearDown", "track", "events", "", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event;", "([Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event;)V", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {

        /* compiled from: SFMCSdk.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[InitializationState.values().length];
                try {
                    iArr[InitializationState.READY.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void configure(Context context, SFMCSdkModuleConfig config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            configure$default(this, context, config, null, 4, null);
        }

        private Companion() {
        }

        public final PushModule getPushModule$sfmcsdk_release() {
            return SFMCSdk.pushModule;
        }

        public final void setPushModule$sfmcsdk_release(PushModule pushModule) {
            Intrinsics.checkNotNullParameter(pushModule, "<set-?>");
            SFMCSdk.pushModule = pushModule;
        }

        public final CdpModule getCdpModule$sfmcsdk_release() {
            return SFMCSdk.cdpModule;
        }

        public final void setCdpModule$sfmcsdk_release(CdpModule cdpModule) {
            Intrinsics.checkNotNullParameter(cdpModule, "<set-?>");
            SFMCSdk.cdpModule = cdpModule;
        }

        public final BehaviorManagerImpl getBehaviorManager$sfmcsdk_release() {
            return SFMCSdk.behaviorManager;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void configure$default(Companion companion, Context context, SFMCSdkModuleConfig sFMCSdkModuleConfig, Function1 function1, int i, Object obj) {
            if ((i & 4) != 0) {
                function1 = null;
            }
            companion.configure(context, sFMCSdkModuleConfig, function1);
        }

        @JvmStatic
        public final void configure(final Context context, final SFMCSdkModuleConfig config, final Function1<? super InitializationStatus, Unit> listener) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            synchronized (SFMCSdk.SDK_LOCK) {
                SFMCSdk sFMCSdk = SFMCSdk.instance;
                if (sFMCSdk != null && ((SFMCSdk.initializationState == InitializationState.READY || SFMCSdk.initializationState == InitializationState.INITIALIZING) && Intrinsics.areEqual(config, sFMCSdk.getConfig()))) {
                    SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$1$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "SDK already initialized for config " + SFMCSdkModuleConfig.this;
                        }
                    });
                    if (SFMCSdk.initializationState == InitializationState.READY && listener != null) {
                        listener.invoke(new SFMCSdkInitializationStatus(true));
                    }
                    return;
                }
                Companion companion = SFMCSdk.INSTANCE;
                SFMCSdk.initializationState = InitializationState.INITIALIZING;
                SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$2
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "~~ SFMCSdk v1.0.5 Initialization Started ~~";
                    }
                });
                new Thread(new Runnable() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SFMCSdk.Companion.configure$lambda$10$lambda$9(SFMCSdkModuleConfig.this, listener, context);
                    }
                }).start();
                SFMCSdk.SDK_LOCK.notifyAll();
                Unit unit = Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void configure$lambda$10$lambda$9(final SFMCSdkModuleConfig config, Function1 function1, Context context) {
            Intrinsics.checkNotNullParameter(config, "$config");
            Intrinsics.checkNotNullParameter(context, "$context");
            String name = Thread.currentThread().getName();
            Thread.currentThread().setName("SFMCSdk_Init");
            try {
                try {
                    if (SFMCSdk.instance != null) {
                        SFMCSdk.INSTANCE.staticTearDown();
                    }
                    Companion companion = SFMCSdk.INSTANCE;
                    SFMCSdk.instance = new SFMCSdk(config, null);
                    final long currentTimeMillis = System.currentTimeMillis();
                    CountDownLatch countDownLatch = new CountDownLatch(config.getConfigs$sfmcsdk_release().size());
                    SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$3$moduleInitLatch$1$1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Initializing " + SFMCSdkModuleConfig.this.getConfigs$sfmcsdk_release().size() + " modules.";
                        }
                    });
                    for (final Config config2 : config.getConfigs$sfmcsdk_release()) {
                        SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$3$2$1
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "Module (" + Config.this.getModuleIdentifier() + ") init started. Current Version: " + Config.this.getVersion() + " && Max Supported Version: " + Config.this.getMAX_SUPPORTED_VERSION();
                            }
                        });
                        SFMCSdkComponents sFMCSdkComponents = new SFMCSdkComponents(context, config2.getModuleIdentifier().name(), config2.getModuleApplicationId(), SFMCSdk.INSTANCE.getBehaviorManager$sfmcsdk_release().initIfNecessary$sfmcsdk_release(context), new EventManager(config2.getModuleIdentifier().name()));
                        SFMCSdk sFMCSdk = SFMCSdk.instance;
                        if (sFMCSdk != null && sFMCSdk.identity == null) {
                            sFMCSdk.setIdentity(Identity.INSTANCE.getInstance());
                        }
                        if (config2 instanceof PushModuleConfig) {
                            SdkExecutorsKt.namedRunnable(new SdkExecutors(null, null, 3, null).getDiskIO(), config2.getModuleIdentifier().name(), new SFMCSdk$Companion$configure$1$3$2$3(context, config2, sFMCSdkComponents, countDownLatch));
                        } else if (config2 instanceof CdpModuleConfig) {
                            SdkExecutorsKt.namedRunnable(new SdkExecutors(null, null, 3, null).getDiskIO(), config2.getModuleIdentifier().name(), new SFMCSdk$Companion$configure$1$3$2$4(context, config2, sFMCSdkComponents, countDownLatch));
                        }
                    }
                    final boolean await = countDownLatch.await(5L, TimeUnit.SECONDS);
                    SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$3$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Module init latch time exceeded: " + (!await);
                        }
                    });
                    Companion companion2 = SFMCSdk.INSTANCE;
                    SFMCSdk.initializationState = InitializationState.READY;
                    SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$3$4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "SFMC SDK Ready took " + (System.currentTimeMillis() - currentTimeMillis) + "ms.";
                        }
                    });
                    synchronized (SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS) {
                        for (final WhenReadyHandler whenReadyHandler : SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS) {
                            try {
                                SFMCSdk sFMCSdk2 = SFMCSdk.instance;
                                if (sFMCSdk2 != null) {
                                    whenReadyHandler.deliverSdk(sFMCSdk2);
                                }
                            } catch (Exception e) {
                                SFMCSdkLogger.INSTANCE.e(SFMCSdk.TAG, e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$3$5$1$2
                                    {
                                        super(0);
                                    }

                                    @Override // kotlin.jvm.functions.Function0
                                    public final String invoke() {
                                        return "Failure during requestSdk() delivery for " + WhenReadyHandler.this + ".";
                                    }
                                });
                            }
                        }
                        SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS.clear();
                        SFMCSdk.INSTANCE.notifyInitializationStatusListener(function1, true);
                        Unit unit = Unit.INSTANCE;
                    }
                } finally {
                    Thread.currentThread().setName(name);
                    SFMCSdkLogger.INSTANCE.d(SFMCSdk.TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$3$7
                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "~~ SFMCSdk Initialization Complete ~~";
                        }
                    });
                }
            } catch (Exception e2) {
                SFMCSdk.INSTANCE.staticTearDown();
                SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS.clear();
                SFMCSdk.INSTANCE.notifyInitializationStatusListener(function1, false);
                SFMCSdkLogger.INSTANCE.e(SFMCSdk.TAG, e2, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$configure$1$3$6
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "An error occurred during SDK initialization.";
                    }
                });
            }
        }

        private final void notifyInitializationStatusListener(final Function1<? super InitializationStatus, Unit> listener, boolean isSuccessful) {
            if (listener != null) {
                try {
                    listener.invoke(new SFMCSdkInitializationStatus(isSuccessful));
                } catch (Exception e) {
                    SFMCSdkLogger.INSTANCE.e(SFMCSdk.TAG, e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$notifyInitializationStatusListener$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Failed to delivery initialization state to listener " + listener + ".";
                        }
                    });
                }
            }
        }

        private final void staticTearDown() {
            SFMCSdk sFMCSdk = SFMCSdk.instance;
            if (sFMCSdk != null) {
                Iterator it = sFMCSdk.modules.iterator();
                while (it.hasNext()) {
                    ((Module) it.next()).tearDown();
                }
            }
            EventManager.INSTANCE.staticTearDown$sfmcsdk_release();
            SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS.clear();
            SFMCSdk.instance = null;
            SFMCSdk.initializationState = InitializationState.NONE;
        }

        @JvmStatic
        public final void requestSdk(SFMCSdkReadyListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            final WhenReadyHandler whenReadyHandler = new WhenReadyHandler(listener);
            synchronized (SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS) {
                if (WhenMappings.$EnumSwitchMapping$0[SFMCSdk.initializationState.ordinal()] == 1) {
                    try {
                        SFMCSdk sFMCSdk = SFMCSdk.instance;
                        if (sFMCSdk != null) {
                            whenReadyHandler.deliverSdk(sFMCSdk);
                            Unit unit = Unit.INSTANCE;
                        }
                    } catch (Exception e) {
                        SFMCSdkLogger.INSTANCE.e(SFMCSdk.TAG, e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$requestSdk$1$2
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final String invoke() {
                                return "Failure during requestSdk() delivery for " + WhenReadyHandler.this + ".";
                            }
                        });
                        Unit unit2 = Unit.INSTANCE;
                    }
                } else {
                    Boolean.valueOf(SFMCSdk.UNIFIED_SDK_INSTANCE_REQUESTS.add(whenReadyHandler));
                }
            }
        }

        @JvmStatic
        public final void track(final Event... events) {
            Intrinsics.checkNotNullParameter(events, "events");
            requestSdk(new SFMCSdkReadyListener() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion$$ExternalSyntheticLambda0
                @Override // com.salesforce.marketingcloud.sfmcsdk.SFMCSdkReadyListener
                public final void ready(SFMCSdk sFMCSdk) {
                    SFMCSdk.Companion.track$lambda$16(events, sFMCSdk);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void track$lambda$16(Event[] events, SFMCSdk sdk) {
            Intrinsics.checkNotNullParameter(events, "$events");
            Intrinsics.checkNotNullParameter(sdk, "sdk");
            sdk.internalTrack((Event[]) Arrays.copyOf(events, events.length));
        }

        @JvmStatic
        public final void setLogging(LogLevel level, LogListener listener) {
            Intrinsics.checkNotNullParameter(level, "level");
            SFMCSdkLogger.INSTANCE.setLogLevel(level);
            SFMCSdkLogger.INSTANCE.setListener(listener);
        }
    }

    static {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(newSingleThreadExecutor, "newSingleThreadExecutor(...)");
        behaviorManager = new BehaviorManagerImpl(newSingleThreadExecutor);
        initializationState = InitializationState.NONE;
        UNIFIED_SDK_INSTANCE_REQUESTS = new ArrayList();
        SDK_LOCK = new Object();
    }

    public final JSONObject getSdkState() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("sfmcSDKVersion", "1.0.5");
        for (Module module : this.modules) {
            jSONObject.put(module.getName(), module.getState());
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void internalTrack(final Event... events) {
        if (events != null) {
            try {
                SFMCSdkLogger.INSTANCE.d(TAG, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$internalTrack$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        String str = "";
                        for (Event event : ArraysKt.filterNotNull(events)) {
                            String str2 = StringsKt.isBlank(str) ? "" : ", ";
                            str = str + str2 + Reflection.getOrCreateKotlinClass(event.getClass()).getSimpleName() + "( " + event.getName() + " )";
                        }
                        return "Tracking events: " + ((Object) str);
                    }
                });
            } catch (Exception unused) {
            }
            EventManager.INSTANCE.publish$sfmcsdk_release(this.executors, (Event[]) Arrays.copyOf(events, events.length));
        }
    }

    public final void mp(PushModuleReadyListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        pushModule.requestModule(listener);
    }

    public final void cdp(CdpModuleReadyListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        cdpModule.requestModule(listener);
    }

    public final Identity getIdentity() {
        Identity identity = this.identity;
        if (identity != null) {
            return identity;
        }
        Intrinsics.throwUninitializedPropertyAccessException("identity");
        return null;
    }

    public final void setIdentity(Identity identity) {
        Intrinsics.checkNotNullParameter(identity, "<set-?>");
        this.identity = identity;
    }
}
