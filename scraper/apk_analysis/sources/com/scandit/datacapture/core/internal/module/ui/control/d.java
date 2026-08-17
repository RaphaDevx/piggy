package com.scandit.datacapture.core.internal.module.ui.control;

import android.graphics.Bitmap;
import com.scandit.datacapture.core.internal.module.utils.j;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function1 {
    public final /* synthetic */ Bitmap a;
    public final /* synthetic */ ToggleImageButton b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Bitmap bitmap, ToggleImageButton toggleImageButton) {
        super(1);
        this.a = bitmap;
        this.b = toggleImageButton;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Lazy lazy;
        ToggleImageButton it = (ToggleImageButton) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        if (this.a != null) {
            ToggleImageButton toggleImageButton = this.b;
            Bitmap bitmap = this.a;
            lazy = ToggleImageButton.e;
            toggleImageButton.setImageDrawable(new j(bitmap, ((Number) lazy.getValue()).floatValue()));
        } else {
            this.b.setImageDrawable(null);
        }
        return Unit.INSTANCE;
    }
}
