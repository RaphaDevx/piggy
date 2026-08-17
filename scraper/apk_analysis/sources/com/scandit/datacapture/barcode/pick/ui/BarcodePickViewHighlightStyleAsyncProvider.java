package com.scandit.datacapture.barcode.pick.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import io.sentry.SentryBaseEvent;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleAsyncProvider;", "", "styleForRequest", "", SentryBaseEvent.JsonKeys.REQUEST, "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleRequest;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleAsyncCallback;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface BarcodePickViewHighlightStyleAsyncProvider {
    void styleForRequest(BarcodePickViewHighlightStyleRequest request, BarcodePickViewHighlightStyleAsyncCallback callback);
}
