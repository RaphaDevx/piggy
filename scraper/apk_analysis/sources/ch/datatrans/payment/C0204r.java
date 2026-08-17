package ch.datatrans.payment;

import android.content.res.Resources;
import ch.datatrans.payment.models.repository.util.BINRangeIntermediate;
import ch.datatrans.payment.models.repository.util.CardsObject;
import ch.datatrans.payment.paymentmethods.CardNumberLength;
import ch.datatrans.payment.paymentmethods.PaymentMethodType;
import com.google.gson.Gson;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.r, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0204r {
    public static volatile ArrayList c;
    public final mg a;
    public final String b;

    public C0204r(R9 preferencesRepository, E4 fileRepository) {
        Intrinsics.checkNotNullParameter(preferencesRepository, "preferencesRepository");
        Intrinsics.checkNotNullParameter(fileRepository, "fileRepository");
        this.a = preferencesRepository;
        Intrinsics.checkNotNullParameter("bin_ranges", "fileName");
        Resources resources = fileRepository.a;
        InputStream openRawResource = resources.openRawResource(resources.getIdentifier("bin_ranges", "raw", fileRepository.b));
        Intrinsics.checkNotNullExpressionValue(openRawResource, "openRawResource(...)");
        this.b = C0207r5.a(openRawResource);
    }

    public static ArrayList a(String str) {
        C0189o c0189o;
        Object fromJson = new Gson().fromJson(str, (Class<Object>) CardsObject.class);
        Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
        List<BINRangeIntermediate> cards = ((CardsObject) fromJson).getCards();
        ArrayList arrayList = new ArrayList();
        for (BINRangeIntermediate bINRangeIntermediate : cards) {
            if (bINRangeIntermediate.getNumberLengths().isEmpty()) {
                throw new IllegalArgumentException("numberLengths cannot be empty");
            }
            PaymentMethodType paymentMethodType = PaymentMethodType.INSTANCE.fromIdentifier(bINRangeIntermediate.getType());
            if (paymentMethodType == null) {
                throw new IllegalArgumentException("payment method type not found");
            }
            List<List> numberRanges = bINRangeIntermediate.getNumberRanges();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(numberRanges, 10));
            for (List list : numberRanges) {
                int size = list.size();
                if (size == 1) {
                    int intValue = ((Number) CollectionsKt.first(list)).intValue();
                    List numberSpacing = bINRangeIntermediate.getNumberSpacing();
                    if (numberSpacing == null) {
                        numberSpacing = CollectionsKt.emptyList();
                    }
                    List spaces = numberSpacing;
                    CardNumberLength.IntList length = new CardNumberLength.IntList(bINRangeIntermediate.getNumberLengths());
                    Intrinsics.checkNotNullParameter(paymentMethodType, "paymentMethodType");
                    Intrinsics.checkNotNullParameter(spaces, "spaces");
                    Intrinsics.checkNotNullParameter(length, "length");
                    c0189o = new C0189o(paymentMethodType, intValue, intValue, spaces, length);
                } else {
                    if (size != 2) {
                        throw new IllegalArgumentException("numberRanges cannot be empty and must have a maximum of 2 elements");
                    }
                    int intValue2 = ((Number) CollectionsKt.first(list)).intValue();
                    int intValue3 = ((Number) CollectionsKt.last(list)).intValue();
                    List numberSpacing2 = bINRangeIntermediate.getNumberSpacing();
                    if (numberSpacing2 == null) {
                        numberSpacing2 = CollectionsKt.emptyList();
                    }
                    c0189o = new C0189o(paymentMethodType, intValue2, intValue3, numberSpacing2, new CardNumberLength.IntList(bINRangeIntermediate.getNumberLengths()));
                }
                arrayList2.add(c0189o);
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a5, code lost:
    
        if (r7 == r1) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0085, code lost:
    
        if (r8 != r1) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof ch.datatrans.payment.C0198q
            if (r0 == 0) goto L13
            r0 = r8
            ch.datatrans.payment.q r0 = (ch.datatrans.payment.C0198q) r0
            int r1 = r0.e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.e = r1
            goto L18
        L13:
            ch.datatrans.payment.q r0 = new ch.datatrans.payment.q
            r0.<init>(r7, r8)
        L18:
            java.lang.Object r8 = r0.c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.e
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L48
            if (r2 == r5) goto L44
            if (r2 == r4) goto L3c
            if (r2 != r3) goto L34
            java.lang.Object r7 = r0.a
            ch.datatrans.payment.p4 r7 = (ch.datatrans.payment.p4) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto La8
        L34:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3c:
            java.lang.Object r2 = r0.a
            ch.datatrans.payment.p4 r2 = (ch.datatrans.payment.p4) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L88
        L44:
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Exception -> Lbc ch.datatrans.payment.D7 -> Lbf
            goto L60
        L48:
            kotlin.ResultKt.throwOnFailure(r8)
            ch.datatrans.payment.C7 r8 = ch.datatrans.payment.r4.a()     // Catch: java.lang.Exception -> Lbc ch.datatrans.payment.D7 -> Lbf
            java.lang.String r2 = r7.a()     // Catch: java.lang.Exception -> Lbc ch.datatrans.payment.D7 -> Lbf
            if (r2 != 0) goto L57
            java.lang.String r2 = ""
        L57:
            r0.e = r5     // Catch: java.lang.Exception -> Lbc ch.datatrans.payment.D7 -> Lbf
            java.lang.Object r8 = r8.a(r2, r0)     // Catch: java.lang.Exception -> Lbc ch.datatrans.payment.D7 -> Lbf
            if (r8 != r1) goto L60
            goto La7
        L60:
            r2 = r8
            ch.datatrans.payment.p4 r2 = (ch.datatrans.payment.p4) r2     // Catch: java.lang.Exception -> Lbc ch.datatrans.payment.D7 -> Lbf
            java.lang.String r8 = r2.a     // Catch: java.lang.Exception -> Lab
            java.util.ArrayList r8 = a(r8)     // Catch: java.lang.Exception -> Lab
            ch.datatrans.payment.AbstractC0194p.b(r8)     // Catch: java.lang.Exception -> Lab
            java.lang.String r8 = r2.a
            r0.a = r2
            r0.e = r4
            ch.datatrans.payment.mg r4 = r7.a
            ch.datatrans.payment.R9 r4 = (ch.datatrans.payment.R9) r4
            java.lang.String r6 = "BIN_RANGES_PREFERENCES_KEY"
            kotlin.Unit r8 = r4.a(r6, r8, r5)
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r8 != r4) goto L83
            goto L85
        L83:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
        L85:
            if (r8 != r1) goto L88
            goto La7
        L88:
            java.lang.String r8 = r2.b
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.a = r2
            r0.e = r3
            ch.datatrans.payment.mg r7 = r7.a
            ch.datatrans.payment.R9 r7 = (ch.datatrans.payment.R9) r7
            java.lang.String r0 = "BIN_RANGES_ETAG_PREFERENCES_KEY"
            kotlin.Unit r7 = r7.a(r0, r8, r5)
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r7 != r8) goto La3
            goto La5
        La3:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
        La5:
            if (r7 != r1) goto La8
        La7:
            return r1
        La8:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        Lab:
            r7 = move-exception
            java.lang.String r8 = r7.getMessage()
            if (r8 != 0) goto Lb4
            java.lang.String r8 = "BIN Ranges error: the json is not valid"
        Lb4:
            java.lang.String r0 = "DTPL"
            android.util.Log.e(r0, r8, r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        Lbc:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        Lbf:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.datatrans.payment.C0204r.a(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final String a() {
        R9 r9 = (R9) this.a;
        r9.getClass();
        Intrinsics.checkNotNullParameter("BIN_RANGES_ETAG_PREFERENCES_KEY", "key");
        return r9.a.getString("BIN_RANGES_ETAG_PREFERENCES_KEY", null);
    }
}
