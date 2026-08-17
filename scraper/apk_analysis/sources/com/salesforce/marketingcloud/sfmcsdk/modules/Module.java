package com.salesforce.marketingcloud.sfmcsdk.modules;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.salesforce.marketingcloud.sfmcsdk.InitializationState;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.identity.ModuleIdentity;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModule;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: Module.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ&\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020&J\u0006\u0010(\u001a\u00020\u001eR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0084\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0012\u0010\u0015\u001a\u00020\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/modules/Module;", "", "()V", "MODULE_INSTANCE_REQUESTS", "", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleReadyHandler;", "getMODULE_INSTANCE_REQUESTS$annotations", "getMODULE_INSTANCE_REQUESTS", "()Ljava/util/List;", "initializationState", "Lcom/salesforce/marketingcloud/sfmcsdk/InitializationState;", "getInitializationState", "()Lcom/salesforce/marketingcloud/sfmcsdk/InitializationState;", "setInitializationState", "(Lcom/salesforce/marketingcloud/sfmcsdk/InitializationState;)V", "module", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleInterface;", "getModule", "()Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleInterface;", "setModule", "(Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleInterface;)V", "name", "", "getName", "()Ljava/lang/String;", "getIdentity", "Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/ModuleIdentity;", "getState", "Lorg/json/JSONObject;", "initModule", "", "context", "Landroid/content/Context;", FirebaseConstants.Commands.CONFIGURE, "Lcom/salesforce/marketingcloud/sfmcsdk/modules/Config;", "components", "Lcom/salesforce/marketingcloud/sfmcsdk/SFMCSdkComponents;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleReadyListener;", "requestModule", "tearDown", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class Module {
    private final List<ModuleReadyHandler> MODULE_INSTANCE_REQUESTS = new ArrayList();
    private InitializationState initializationState = InitializationState.NONE;
    private ModuleInterface module;

    /* compiled from: Module.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InitializationState.values().length];
            try {
                iArr[InitializationState.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InitializationState.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InitializationState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    protected static /* synthetic */ void getMODULE_INSTANCE_REQUESTS$annotations() {
    }

    public abstract String getName();

    protected final List<ModuleReadyHandler> getMODULE_INSTANCE_REQUESTS() {
        return this.MODULE_INSTANCE_REQUESTS;
    }

    protected final InitializationState getInitializationState() {
        return this.initializationState;
    }

    protected final void setInitializationState(InitializationState initializationState) {
        Intrinsics.checkNotNullParameter(initializationState, "<set-?>");
        this.initializationState = initializationState;
    }

    protected final ModuleInterface getModule() {
        return this.module;
    }

    protected final void setModule(ModuleInterface moduleInterface) {
        this.module = moduleInterface;
    }

    public final void requestModule(ModuleReadyListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        final ModuleReadyHandler moduleReadyHandler = new ModuleReadyHandler(listener);
        synchronized (this.MODULE_INSTANCE_REQUESTS) {
            if (WhenMappings.$EnumSwitchMapping$0[this.initializationState.ordinal()] == 1) {
                try {
                    ModuleInterface moduleInterface = this.module;
                    if (moduleInterface != null) {
                        moduleReadyHandler.deliverModule(moduleInterface);
                        Unit unit = Unit.INSTANCE;
                    }
                } catch (Exception e) {
                    SFMCSdkLogger.INSTANCE.e(PushModule.TAG, e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.Module$requestModule$1$2
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Failure during requestPush() delivery for " + ModuleReadyHandler.this + ".";
                        }
                    });
                    Unit unit2 = Unit.INSTANCE;
                }
            } else {
                Boolean.valueOf(this.MODULE_INSTANCE_REQUESTS.add(moduleReadyHandler));
            }
        }
    }

    public final void tearDown() {
        this.MODULE_INSTANCE_REQUESTS.clear();
        this.module = null;
        this.initializationState = InitializationState.NONE;
    }

    public final ModuleIdentity getIdentity() {
        ModuleIdentity moduleIdentity;
        ModuleInterface moduleInterface = this.module;
        if (moduleInterface == null || (moduleIdentity = moduleInterface.getModuleIdentity()) == null) {
            throw new NotImplementedError("An operation is not implemented: Your module must implement getIdentity().");
        }
        return moduleIdentity;
    }

    public final JSONObject getState() {
        JSONObject put;
        JSONObject state;
        ModuleInterface moduleInterface = this.module;
        if (moduleInterface != null && (state = moduleInterface.getState()) != null) {
            return state;
        }
        JSONObject jSONObject = new JSONObject();
        int i = WhenMappings.$EnumSwitchMapping$0[this.initializationState.ordinal()];
        if (i == 2) {
            put = jSONObject.put("INITIALIZATION_STATUS", "NOT IMPLEMENTED OR NOT INITIALIZED");
        } else if (i == 3) {
            put = jSONObject.put("INITIALIZATION_STATUS", "ERROR");
        } else {
            put = jSONObject.put("INITIALIZATION_STATUS", "NOT READY");
        }
        JSONObject jSONObject2 = put;
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "run(...)");
        return jSONObject2;
    }

    public final void initModule(Context context, Config config, SFMCSdkComponents components, ModuleReadyListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(components, "components");
        Intrinsics.checkNotNullParameter(listener, "listener");
        try {
            components.getExecutors().getDiskIO().execute(new Module$initModule$1(this, config, context, components, listener, config.getModuleIdentifier().name() + "_init_thread", new Object[0]));
        } catch (Error e) {
            SFMCSdkLogger.INSTANCE.w(getName(), e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.Module$initModule$3
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "An error occurred while initializing module " + Module.this;
                }
            });
        } catch (Exception e2) {
            SFMCSdkLogger.INSTANCE.w(getName(), e2, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.Module$initModule$2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "An exception occurred while initializing module " + Module.this;
                }
            });
        }
    }
}
