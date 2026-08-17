package com.scandit.datacapture.frameworks.core.lifecycle;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: ActivityLifecycle.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\bJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycle;", "", "attach", "", "observer", "Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycle$Observer;", "clearObservers", "detach", "Observer", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface ActivityLifecycle {

    /* compiled from: ActivityLifecycle.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/lifecycle/ActivityLifecycle$Observer;", "", "onActivityCreate", "", "onActivityDestroy", "onActivityPause", "onActivityResume", "onActivityStart", "onActivityStop", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface Observer {

        /* compiled from: ActivityLifecycle.kt */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class DefaultImpls {
            public static void onActivityCreate(Observer observer) {
            }

            public static void onActivityDestroy(Observer observer) {
            }

            public static void onActivityPause(Observer observer) {
            }

            public static void onActivityResume(Observer observer) {
            }

            public static void onActivityStart(Observer observer) {
            }

            public static void onActivityStop(Observer observer) {
            }
        }

        void onActivityCreate();

        void onActivityDestroy();

        void onActivityPause();

        void onActivityResume();

        void onActivityStart();

        void onActivityStop();
    }

    void attach(Observer observer);

    void clearObservers();

    void detach(Observer observer);
}
