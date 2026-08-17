package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0004¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0005\u0010\u0010R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0016\u001a\u0004\b\u0006\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0016\u001a\u0004\b\u0007\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\b\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarSettings;", "", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarShowMode;", "buttonBarShowMode", "", "isCollapseButtonVisible", "isAcceptScanButtonVisible", "isCancelScanButtonVisible", "isRestartScanButtonVisible", "<init>", "(Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarShowMode;ZZZZ)V", "isCarouselExpanded", "", "buttonBarVisibility", "(Z)I", "buttonBarVisibilityCanChange", "()Z", "a", "Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarShowMode;", "getButtonBarShowMode", "()Lcom/scandit/datacapture/core/internal/sdk/ui/cardscarousel/ButtonBarShowMode;", "b", "Z", "c", "d", "e", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class ButtonBarSettings {

    /* renamed from: a, reason: from kotlin metadata */
    private final ButtonBarShowMode buttonBarShowMode;

    /* renamed from: b, reason: from kotlin metadata */
    private final boolean isCollapseButtonVisible;

    /* renamed from: c, reason: from kotlin metadata */
    private final boolean isAcceptScanButtonVisible;

    /* renamed from: d, reason: from kotlin metadata */
    private final boolean isCancelScanButtonVisible;

    /* renamed from: e, reason: from kotlin metadata */
    private final boolean isRestartScanButtonVisible;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ButtonBarShowMode.values().length];
            try {
                iArr[ButtonBarShowMode.Never.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ButtonBarShowMode.WhileExpanded.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ButtonBarShowMode.Always.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ButtonBarSettings() {
        this(null, false, false, false, false, 31, null);
    }

    public final int buttonBarVisibility(boolean isCarouselExpanded) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.buttonBarShowMode.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
            } else if (isCarouselExpanded) {
            }
            return 0;
        }
        return 4;
    }

    public final boolean buttonBarVisibilityCanChange() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.buttonBarShowMode.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return true;
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
        }
        return false;
    }

    public final ButtonBarShowMode getButtonBarShowMode() {
        return this.buttonBarShowMode;
    }

    /* renamed from: isAcceptScanButtonVisible, reason: from getter */
    public final boolean getIsAcceptScanButtonVisible() {
        return this.isAcceptScanButtonVisible;
    }

    /* renamed from: isCancelScanButtonVisible, reason: from getter */
    public final boolean getIsCancelScanButtonVisible() {
        return this.isCancelScanButtonVisible;
    }

    /* renamed from: isCollapseButtonVisible, reason: from getter */
    public final boolean getIsCollapseButtonVisible() {
        return this.isCollapseButtonVisible;
    }

    /* renamed from: isRestartScanButtonVisible, reason: from getter */
    public final boolean getIsRestartScanButtonVisible() {
        return this.isRestartScanButtonVisible;
    }

    public ButtonBarSettings(ButtonBarShowMode buttonBarShowMode, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(buttonBarShowMode, "buttonBarShowMode");
        this.buttonBarShowMode = buttonBarShowMode;
        this.isCollapseButtonVisible = z;
        this.isAcceptScanButtonVisible = z2;
        this.isCancelScanButtonVisible = z3;
        this.isRestartScanButtonVisible = z4;
    }

    public /* synthetic */ ButtonBarSettings(ButtonBarShowMode buttonBarShowMode, boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ButtonBarShowMode.Never : buttonBarShowMode, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2, (i & 8) != 0 ? false : z3, (i & 16) == 0 ? z4 : false);
    }
}
