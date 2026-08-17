package com.scandit.datacapture.frameworks.core.events;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.config.a;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Emitter.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "", "emit", "", a.h, "", "payload", "", "hasListenersForEvent", "", "hasViewSpecificListenersForEvent", "viewId", "", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface Emitter {

    /* compiled from: Emitter.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        public static boolean hasListenersForEvent(Emitter emitter, String eventName) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            return true;
        }

        public static boolean hasViewSpecificListenersForEvent(Emitter emitter, int i, String eventName) {
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            return true;
        }
    }

    void emit(String eventName, Map<String, Object> payload);

    boolean hasListenersForEvent(String eventName);

    boolean hasViewSpecificListenersForEvent(int viewId, String eventName);
}
