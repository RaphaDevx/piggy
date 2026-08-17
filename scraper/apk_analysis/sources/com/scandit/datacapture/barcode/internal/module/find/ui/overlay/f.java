package com.scandit.datacapture.barcode.internal.module.find.ui.overlay;

import com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout;
import com.scandit.datacapture.barcode.internal.module.find.ui.u0;
import com.scandit.datacapture.core.internal.module.ui.NativeToastHintStyle;
import java.lang.ref.WeakReference;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f implements BarcodeFindGuidanceHandler {
    public final WeakReference a;
    public e b;

    public f(u0 viewLayout) {
        Intrinsics.checkNotNullParameter(viewLayout, "viewLayout");
        this.a = new WeakReference(viewLayout);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindGuidanceHandler
    public final void a(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        e eVar = e.b;
        if (z) {
            if (eVar != this.b) {
                BarcodeFindViewLayout barcodeFindViewLayout = (BarcodeFindViewLayout) this.a.get();
                if (barcodeFindViewLayout != null) {
                    barcodeFindViewLayout.b(text);
                    Unit unit = Unit.INSTANCE;
                }
                this.b = eVar;
                return;
            }
            return;
        }
        if (eVar == this.b) {
            BarcodeFindViewLayout barcodeFindViewLayout2 = (BarcodeFindViewLayout) this.a.get();
            if (barcodeFindViewLayout2 != null) {
                barcodeFindViewLayout2.l();
                Unit unit2 = Unit.INSTANCE;
            }
            this.b = null;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindGuidanceHandler
    public final void b(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        e eVar = e.a;
        if (z) {
            if (eVar != this.b) {
                BarcodeFindViewLayout barcodeFindViewLayout = (BarcodeFindViewLayout) this.a.get();
                if (barcodeFindViewLayout != null) {
                    barcodeFindViewLayout.a(text);
                    Unit unit = Unit.INSTANCE;
                }
                this.b = eVar;
                return;
            }
            return;
        }
        if (eVar == this.b) {
            BarcodeFindViewLayout barcodeFindViewLayout2 = (BarcodeFindViewLayout) this.a.get();
            if (barcodeFindViewLayout2 != null) {
                barcodeFindViewLayout2.l();
                Unit unit2 = Unit.INSTANCE;
            }
            this.b = null;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindGuidanceHandler
    public final void c(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (!z) {
            BarcodeFindViewLayout barcodeFindViewLayout = (BarcodeFindViewLayout) this.a.get();
            if (barcodeFindViewLayout != null) {
                barcodeFindViewLayout.c("item_list_updated");
                Unit unit = Unit.INSTANCE;
                return;
            }
            return;
        }
        WeakReference weakReference = this.a;
        NativeToastHintStyle nativeToastHintStyle = NativeToastHintStyle.INFO;
        BarcodeFindViewLayout barcodeFindViewLayout2 = (BarcodeFindViewLayout) weakReference.get();
        if (barcodeFindViewLayout2 != null) {
            barcodeFindViewLayout2.a(text, nativeToastHintStyle, "item_list_updated");
            Unit unit2 = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindGuidanceHandler
    public final void d(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        e eVar = e.d;
        if (z) {
            if (eVar != this.b) {
                BarcodeFindViewLayout barcodeFindViewLayout = (BarcodeFindViewLayout) this.a.get();
                if (barcodeFindViewLayout != null) {
                    barcodeFindViewLayout.a(text);
                    Unit unit = Unit.INSTANCE;
                }
                this.b = eVar;
                return;
            }
            return;
        }
        if (eVar == this.b) {
            BarcodeFindViewLayout barcodeFindViewLayout2 = (BarcodeFindViewLayout) this.a.get();
            if (barcodeFindViewLayout2 != null) {
                barcodeFindViewLayout2.l();
                Unit unit2 = Unit.INSTANCE;
            }
            this.b = null;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindGuidanceHandler
    public final void e(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        e eVar = e.c;
        if (z) {
            if (eVar != this.b) {
                BarcodeFindViewLayout barcodeFindViewLayout = (BarcodeFindViewLayout) this.a.get();
                if (barcodeFindViewLayout != null) {
                    barcodeFindViewLayout.a(text);
                    Unit unit = Unit.INSTANCE;
                }
                this.b = eVar;
                return;
            }
            return;
        }
        if (eVar == this.b) {
            BarcodeFindViewLayout barcodeFindViewLayout2 = (BarcodeFindViewLayout) this.a.get();
            if (barcodeFindViewLayout2 != null) {
                barcodeFindViewLayout2.l();
                Unit unit2 = Unit.INSTANCE;
            }
            this.b = null;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindGuidanceHandler
    public final void f(boolean z, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (!z) {
            BarcodeFindViewLayout barcodeFindViewLayout = (BarcodeFindViewLayout) this.a.get();
            if (barcodeFindViewLayout != null) {
                barcodeFindViewLayout.c("all_items_found");
                Unit unit = Unit.INSTANCE;
                return;
            }
            return;
        }
        WeakReference weakReference = this.a;
        NativeToastHintStyle nativeToastHintStyle = NativeToastHintStyle.SUCCESS;
        BarcodeFindViewLayout barcodeFindViewLayout2 = (BarcodeFindViewLayout) weakReference.get();
        if (barcodeFindViewLayout2 != null) {
            barcodeFindViewLayout2.a(text, nativeToastHintStyle, "all_items_found");
            Unit unit2 = Unit.INSTANCE;
        }
    }
}
