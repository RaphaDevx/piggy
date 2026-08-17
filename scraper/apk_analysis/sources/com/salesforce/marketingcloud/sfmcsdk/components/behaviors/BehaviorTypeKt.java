package com.salesforce.marketingcloud.sfmcsdk.components.behaviors;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.sfmcsdk.components.behaviors.Behavior;
import com.salesforce.marketingcloud.sfmcsdk.components.events.Event;
import com.salesforce.marketingcloud.sfmcsdk.components.events.EventManager;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: BehaviorType.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"INTENT_FILTER_APPLICATION_BACKGROUNDED", "", "INTENT_FILTER_APPLICATION_FOREGROUNDED", "INTENT_FILTER_APP_VERSION_CHANGED", "INTENT_FILTER_SCREEN_ENTRY", "toEvent", "Lcom/salesforce/marketingcloud/sfmcsdk/components/events/Event;", "Lcom/salesforce/marketingcloud/sfmcsdk/components/behaviors/Behavior;", "sfmcsdk_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BehaviorTypeKt {
    private static final String INTENT_FILTER_APPLICATION_BACKGROUNDED = "com.salesforce.marketingcloud.sfmcsdk.sdk.APPLICATION_BACKGROUNDED";
    private static final String INTENT_FILTER_APPLICATION_FOREGROUNDED = "com.salesforce.marketingcloud.sfmcsdk.sdk.APPLICATION_FOREGROUNDED";
    private static final String INTENT_FILTER_APP_VERSION_CHANGED = "com.salesforce.marketingcloud.sfmcsdk.sdk.APP_VERSION_CHANGED";
    private static final String INTENT_FILTER_SCREEN_ENTRY = "com.salesforce.marketingcloud.sfmcsdk.sdk.SCREEN_ENTRY";

    public static final Event toEvent(Behavior behavior) {
        String valueOf;
        Intrinsics.checkNotNullParameter(behavior, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String appVersion = behavior.getAppVersion();
        if (appVersion != null) {
            linkedHashMap.put(BehaviorManagerImpl.BUNDLE_KEY_CURRENT_VERSION, appVersion);
        }
        String appName = behavior.getAppName();
        if (appName != null) {
            linkedHashMap.put(BehaviorManagerImpl.BUNDLE_KEY_APP_NAME, appName);
        }
        if (behavior instanceof Behavior.AppVersionChanged) {
            String previousVersion = behavior.getPreviousVersion();
            if (previousVersion != null) {
                linkedHashMap.put(BehaviorManagerImpl.BUNDLE_KEY_PREVIOUS_VERSION, previousVersion);
            }
            valueOf = String.valueOf(Reflection.getOrCreateKotlinClass(behavior.getClass()).getSimpleName());
        } else if (behavior instanceof Behavior.ScreenEntry) {
            linkedHashMap.put("screen_name", ((Behavior.ScreenEntry) behavior).getName());
            valueOf = String.valueOf(Reflection.getOrCreateKotlinClass(behavior.getClass()).getSimpleName());
        } else {
            valueOf = String.valueOf(Reflection.getOrCreateKotlinClass(behavior.getClass()).getSimpleName());
        }
        return EventManager.INSTANCE.customEvent(valueOf, linkedHashMap, Event.Producer.SFMC_SDK, Event.Category.SYSTEM);
    }
}
