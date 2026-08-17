package ch.datatrans.payment;

import android.os.Bundle;
import ch.datatrans.payment.paymentmethods.SamsungPayConfig;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.payment.CardInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.SafeContinuation;

/* loaded from: classes3.dex */
public final class ha implements PaymentManager.CardInfoListener {
    public final /* synthetic */ C0193oa a;
    public final /* synthetic */ SafeContinuation b;

    public ha(C0193oa c0193oa, SafeContinuation safeContinuation) {
        this.a = c0193oa;
        this.b = safeContinuation;
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager.CardInfoListener
    public final void onFailure(int i, Bundle bundle) {
        this.a.a(this.b, bundle);
    }

    @Override // com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager.CardInfoListener
    public final void onResult(List list) {
        if (list == null) {
            SafeContinuation safeContinuation = this.b;
            Result.Companion companion = Result.INSTANCE;
            safeContinuation.resumeWith(Result.m3945constructorimpl(Boolean.FALSE));
            return;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((CardInfo) it.next()).getBrand());
        }
        SamsungPayConfig samsungPayConfig = this.a.b;
        List<SpaySdk.Brand> cardBrands$lib_release = samsungPayConfig.getCardBrands$lib_release(samsungPayConfig.getSupportedNetworks$lib_release());
        SafeContinuation safeContinuation2 = this.b;
        Result.Companion companion2 = Result.INSTANCE;
        safeContinuation2.resumeWith(Result.m3945constructorimpl(Boolean.valueOf(!CollectionsKt.intersect(arrayList, CollectionsKt.toSet(cardBrands$lib_release)).isEmpty())));
    }
}
