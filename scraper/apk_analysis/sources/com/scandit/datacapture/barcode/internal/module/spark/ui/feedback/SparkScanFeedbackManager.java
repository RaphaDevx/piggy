package com.scandit.datacapture.barcode.internal.module.spark.ui.feedback;

import android.content.Context;
import android.view.ViewGroup;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewSettings;
import com.scandit.datacapture.core.common.feedback.Feedback;
import com.scandit.datacapture.core.common.feedback.Sound;
import com.scandit.datacapture.core.common.feedback.Vibration;
import com.scandit.datacapture.core.time.TimeInterval;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class SparkScanFeedbackManager {
    private final SparkScanViewSettings a;
    private final SparkScanStateManager b;
    private ViewGroup c;
    private b d;
    private c e;
    private Feedback f;
    private Feedback g;

    public SparkScanFeedbackManager(SparkScanViewSettings settings, SparkScanStateManager stateManager) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        this.a = settings;
        this.b = stateManager;
    }

    public final void a(ViewGroup container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.c = container;
        Vibration vibration = new Vibration();
        vibration.setDuration(TimeInterval.INSTANCE.millis(40L));
        ViewGroup viewGroup = null;
        this.g = new Feedback(vibration, null, 2, null);
        ViewGroup viewGroup2 = this.c;
        if (viewGroup2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup2 = null;
        }
        viewGroup2.removeView(this.d);
        ViewGroup viewGroup3 = this.c;
        if (viewGroup3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup3 = null;
        }
        viewGroup3.removeView(this.e);
        ViewGroup viewGroup4 = this.c;
        if (viewGroup4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup4 = null;
        }
        Context context = viewGroup4.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        this.d = new b(context);
        ViewGroup viewGroup5 = this.c;
        if (viewGroup5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup5 = null;
        }
        Context context2 = viewGroup5.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        this.e = new c(context2);
        ViewGroup viewGroup6 = this.c;
        if (viewGroup6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup6 = null;
        }
        viewGroup6.addView(this.d);
        ViewGroup viewGroup7 = this.c;
        if (viewGroup7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
        } else {
            viewGroup = viewGroup7;
        }
        viewGroup.addView(this.e);
    }

    public final void b(int i) {
        c cVar;
        if (!this.a.getVisualFeedbackEnabled() || (cVar = this.e) == null) {
            return;
        }
        cVar.a(i);
    }

    public final void b() {
        Feedback feedback = this.g;
        if (feedback != null) {
            feedback.release();
        }
        Feedback feedback2 = this.f;
        if (feedback2 != null) {
            feedback2.release();
        }
        this.e = null;
        this.d = null;
    }

    public final void a() {
        Feedback feedback;
        if (this.b.e() && (feedback = this.g) != null) {
            feedback.emit();
        }
    }

    public final void a(int i) {
        b bVar;
        if (!this.a.getVisualFeedbackEnabled() || (bVar = this.d) == null) {
            return;
        }
        bVar.a(i);
    }

    public final void a(Feedback feedback) {
        boolean e = this.b.e();
        boolean v = this.b.v();
        Sound sound = null;
        Vibration vibration = (!e || feedback == null) ? null : feedback.getVibration();
        if (v && feedback != null) {
            sound = feedback.getSound();
        }
        Feedback feedback2 = new Feedback(vibration, sound);
        this.f = feedback2;
        feedback2.emit();
    }
}
