package io.sentry.android.replay;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReplayLifecycle.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u0006\u0010\f\u001a\u00020\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lio/sentry/android/replay/ReplayLifecycle;", "", "()V", "currentState", "Lio/sentry/android/replay/ReplayState;", "getCurrentState$sentry_android_replay_release", "()Lio/sentry/android/replay/ReplayState;", "setCurrentState$sentry_android_replay_release", "(Lio/sentry/android/replay/ReplayState;)V", "isAllowed", "", "newState", "isTouchRecordingAllowed", "sentry-android-replay_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ReplayLifecycle {
    public static final int $stable = 8;
    private volatile ReplayState currentState = ReplayState.INITIAL;

    /* compiled from: ReplayLifecycle.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReplayState.values().length];
            try {
                iArr[ReplayState.INITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReplayState.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReplayState.RESUMED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReplayState.PAUSED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReplayState.STOPPED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ReplayState.CLOSED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* renamed from: getCurrentState$sentry_android_replay_release, reason: from getter */
    public final ReplayState getCurrentState() {
        return this.currentState;
    }

    public final void setCurrentState$sentry_android_replay_release(ReplayState replayState) {
        Intrinsics.checkNotNullParameter(replayState, "<set-?>");
        this.currentState = replayState;
    }

    public final boolean isAllowed(ReplayState newState) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        switch (WhenMappings.$EnumSwitchMapping$0[this.currentState.ordinal()]) {
            case 1:
                if (newState == ReplayState.STARTED || newState == ReplayState.CLOSED) {
                    return true;
                }
                break;
            case 2:
                if (newState == ReplayState.PAUSED || newState == ReplayState.STOPPED || newState == ReplayState.CLOSED) {
                    return true;
                }
                break;
            case 3:
                if (newState == ReplayState.PAUSED || newState == ReplayState.STOPPED || newState == ReplayState.CLOSED) {
                    return true;
                }
                break;
            case 4:
                if (newState == ReplayState.RESUMED || newState == ReplayState.STOPPED || newState == ReplayState.CLOSED) {
                    return true;
                }
                break;
            case 5:
                if (newState == ReplayState.STARTED || newState == ReplayState.CLOSED) {
                    return true;
                }
                break;
            case 6:
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return false;
    }

    public final boolean isTouchRecordingAllowed() {
        return this.currentState == ReplayState.STARTED || this.currentState == ReplayState.RESUMED;
    }
}
