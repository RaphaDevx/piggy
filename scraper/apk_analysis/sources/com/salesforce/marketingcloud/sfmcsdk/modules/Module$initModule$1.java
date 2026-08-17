package com.salesforce.marketingcloud.sfmcsdk.modules;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.InitializationState;
import com.salesforce.marketingcloud.sfmcsdk.SFMCSdkComponents;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.sfmcsdk.components.utils.NamedRunnable;
import com.salesforce.marketingcloud.sfmcsdk.modules.cdp.CdpModuleInterface;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModule;
import com.salesforce.marketingcloud.sfmcsdk.modules.push.PushModuleInterface;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Module.kt */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0014¨\u0006\u0004"}, d2 = {"com/salesforce/marketingcloud/sfmcsdk/modules/Module$initModule$1", "Lcom/salesforce/marketingcloud/sfmcsdk/components/utils/NamedRunnable;", "execute", "", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Module$initModule$1 extends NamedRunnable {
    final /* synthetic */ SFMCSdkComponents $components;
    final /* synthetic */ Config $config;
    final /* synthetic */ Context $context;
    final /* synthetic */ ModuleReadyListener $listener;
    final /* synthetic */ Module this$0;

    /* compiled from: Module.kt */
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Module$initModule$1(Module module, Config config, Context context, SFMCSdkComponents sFMCSdkComponents, ModuleReadyListener moduleReadyListener, String str, Object[] objArr) {
        super(str, objArr);
        this.this$0 = module;
        this.$config = config;
        this.$context = context;
        this.$components = sFMCSdkComponents;
        this.$listener = moduleReadyListener;
    }

    @Override // com.salesforce.marketingcloud.sfmcsdk.components.utils.NamedRunnable
    protected void execute() {
        this.this$0.setInitializationState(InitializationState.INITIALIZING);
        SFMCSdkLogger sFMCSdkLogger = SFMCSdkLogger.INSTANCE;
        String name = getName();
        final Config config = this.$config;
        sFMCSdkLogger.d(name, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.Module$initModule$1$execute$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "~~ " + Config.this.getModuleIdentifier().name() + " Module Initialization Started ~~";
            }
        });
        final Config config2 = this.$config;
        Context context = this.$context;
        SFMCSdkComponents sFMCSdkComponents = this.$components;
        final Module module = this.this$0;
        final ModuleReadyListener moduleReadyListener = this.$listener;
        config2.init(context, sFMCSdkComponents, new ModuleReadyListener() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.Module$initModule$1$$ExternalSyntheticLambda0
            @Override // com.salesforce.marketingcloud.sfmcsdk.modules.ModuleReadyListener
            public final void ready(ModuleInterface moduleInterface) {
                Module$initModule$1.execute$lambda$2(Module$initModule$1.this, module, config2, moduleReadyListener, moduleInterface);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void execute$lambda$2(Module$initModule$1 this$0, Module this$1, final Config config, ModuleReadyListener listener, final ModuleInterface it) {
        PushModuleInterface pushModuleInterface;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this$1, "this$1");
        Intrinsics.checkNotNullParameter(config, "$config");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(it, "it");
        SFMCSdkLogger.INSTANCE.d(this$0.getName(), new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.Module$initModule$1$execute$2$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "~~ " + Config.this.getModuleIdentifier().name() + " Module Initialization Completed ~~";
            }
        });
        int i = WhenMappings.$EnumSwitchMapping$0[config.getModuleIdentifier().ordinal()];
        if (i == 1) {
            pushModuleInterface = (PushModuleInterface) it;
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            pushModuleInterface = (CdpModuleInterface) it;
        }
        this$1.setModule(pushModuleInterface);
        listener.ready(it);
        this$1.setInitializationState(InitializationState.READY);
        synchronized (this$1.getMODULE_INSTANCE_REQUESTS()) {
            for (final ModuleReadyHandler moduleReadyHandler : this$1.getMODULE_INSTANCE_REQUESTS()) {
                try {
                    moduleReadyHandler.deliverModule(it);
                } catch (Exception e) {
                    SFMCSdkLogger.INSTANCE.e(PushModule.TAG, e, new Function0<String>() { // from class: com.salesforce.marketingcloud.sfmcsdk.modules.Module$initModule$1$execute$2$2$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final String invoke() {
                            return "Failure during module " + ModuleInterface.this + " delivery for " + moduleReadyHandler + ".";
                        }
                    });
                }
            }
            this$1.getMODULE_INSTANCE_REQUESTS().clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
