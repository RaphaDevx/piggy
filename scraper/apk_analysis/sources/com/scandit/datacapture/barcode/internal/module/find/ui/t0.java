package com.scandit.datacapture.barcode.internal.module.find.ui;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class t0 extends Lambda implements Function1 {
    public final /* synthetic */ u0 a;
    public final /* synthetic */ Map b;
    public final /* synthetic */ Map c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t0(u0 u0Var, LinkedHashMap linkedHashMap, Map map) {
        super(1);
        this.a = u0Var;
        this.b = linkedHashMap;
        this.c = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00cd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00bb  */
    @Override // kotlin.jvm.functions.Function1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invoke(java.lang.Object r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView r1 = (com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView) r1
            java.lang.String r2 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            com.scandit.datacapture.barcode.internal.module.find.ui.u0 r1 = r0.a
            com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView r2 = r1.i
            java.util.Map r3 = r0.b
            java.util.Map r0 = r0.c
            java.util.ArrayList r4 = new java.util.ArrayList
            int r5 = r3.size()
            r4.<init>(r5)
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L24:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto Le1
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r5 = r5.getValue()
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItem r5 = (com.scandit.datacapture.barcode.find.capture.BarcodeFindItem) r5
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent r6 = r5.getContent()
            r7 = 0
            if (r6 == 0) goto L42
            java.lang.String r6 = r6.getInfo()
            goto L43
        L42:
            r6 = r7
        L43:
            if (r6 != 0) goto L5c
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent r6 = r5.getContent()
            if (r6 == 0) goto L50
            java.lang.String r6 = r6.getAdditionalInfo()
            goto L51
        L50:
            r6 = r7
        L51:
            if (r6 != 0) goto L5c
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemSearchOptions r6 = r5.getSearchOptions()
            java.lang.String r6 = r6.getBarcodeData()
            goto L77
        L5c:
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent r6 = r5.getContent()
            java.lang.String r6 = r6.getInfo()
            if (r6 != 0) goto L6f
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent r6 = r5.getContent()
            java.lang.String r6 = r6.getAdditionalInfo()
            goto L77
        L6f:
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent r6 = r5.getContent()
            java.lang.String r6 = r6.getInfo()
        L77:
            r10 = r6
            int r6 = r5.hashCode()
            java.lang.String r9 = java.lang.String.valueOf(r6)
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent r6 = r5.getContent()
            if (r6 == 0) goto L97
            java.lang.String r6 = r6.getInfo()
            if (r6 == 0) goto L97
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent r6 = r5.getContent()
            java.lang.String r6 = r6.getAdditionalInfo()
            r16 = r6
            goto L99
        L97:
            r16 = r7
        L99:
            com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView r6 = r1.i
            android.content.Context r6 = r6.getContext()
            int r8 = com.scandit.datacapture.barcode.R.string.sc_barcode_find_card_content_description
            java.lang.Object[] r11 = new java.lang.Object[]{r10}
            java.lang.String r11 = r6.getString(r8, r11)
            java.lang.String r6 = "getString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r6)
            com.scandit.datacapture.barcode.find.capture.BarcodeFindItemContent r6 = r5.getContent()
            if (r6 == 0) goto Lbb
            android.graphics.Bitmap r6 = r6.getImage()
            r17 = r6
            goto Lbd
        Lbb:
            r17 = r7
        Lbd:
            java.util.Collection r6 = r0.values()
            boolean r5 = r6.contains(r5)
            if (r5 == 0) goto Lcd
            int r5 = com.scandit.datacapture.barcode.R.drawable.sc_magnifying_glass_check
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
        Lcd:
            r14 = r7
            int r12 = r1.z
            com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardData r5 = new com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.TextCardData
            r18 = 80
            r19 = 0
            r13 = 0
            r15 = 0
            r8 = r5
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            r4.add(r5)
            goto L24
        Le1:
            r2.setData(r4)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.find.ui.t0.invoke(java.lang.Object):java.lang.Object");
    }
}
