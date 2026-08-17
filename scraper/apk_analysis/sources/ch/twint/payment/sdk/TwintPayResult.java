package ch.twint.payment.sdk;

import kotlin.Metadata;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001j\u0002\b\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lch/twint/payment/sdk/TwintPayResult;", "", "TW_B_SUCCESS", "TW_B_ERROR", "TW_B_APP_NOT_INSTALLED", "TwintSDK_release"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes3.dex */
public final class TwintPayResult {
    public static final TwintPayResult TW_B_APP_NOT_INSTALLED;
    public static final TwintPayResult TW_B_ERROR;
    public static final TwintPayResult TW_B_SUCCESS;
    public static final /* synthetic */ TwintPayResult[] a;

    static {
        TwintPayResult twintPayResult = new TwintPayResult("TW_B_SUCCESS", 0);
        TW_B_SUCCESS = twintPayResult;
        TwintPayResult twintPayResult2 = new TwintPayResult("TW_B_ERROR", 1);
        TW_B_ERROR = twintPayResult2;
        TwintPayResult twintPayResult3 = new TwintPayResult("TW_B_APP_NOT_INSTALLED", 2);
        TW_B_APP_NOT_INSTALLED = twintPayResult3;
        TwintPayResult[] twintPayResultArr = {twintPayResult, twintPayResult2, twintPayResult3};
        a = twintPayResultArr;
        EnumEntriesKt.enumEntries(twintPayResultArr);
    }

    public TwintPayResult(String str, int i) {
    }

    public static TwintPayResult valueOf(String str) {
        return (TwintPayResult) Enum.valueOf(TwintPayResult.class, str);
    }

    public static TwintPayResult[] values() {
        return (TwintPayResult[]) a.clone();
    }
}
