package ch.datatrans.payment.exception;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.AbstractC0210s;
import ch.datatrans.payment.models.BackendErrorRule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 #2\u00060\u0001j\u0002`\u0002:\u0001#BE\b\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0018\u0010\u0010R#\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t8\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0010R\u0014\u0010\"\u001a\u00020\u001f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006$"}, d2 = {"Lch/datatrans/payment/exception/BackendException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "", "errorMessage", "detail", "", "errorCode", "transactionId", "", "info", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;)V", "a", "Ljava/lang/String;", "getErrorMessage$lib_release", "()Ljava/lang/String;", "b", "getDetail$lib_release", "c", "I", "getErrorCode", "()I", "d", "getTransactionId$lib_release", "e", "Ljava/util/Map;", "getInfo", "()Ljava/util/Map;", "getName", "name", "", "isHttpConflictCancelled$lib_release", "()Z", "isHttpConflictCancelled", "Companion", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class BackendException extends Exception {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DEFAULT_ERROR_CODE = -1;

    /* renamed from: a, reason: from kotlin metadata */
    public final String errorMessage;

    /* renamed from: b, reason: from kotlin metadata */
    public final String detail;

    /* renamed from: c, reason: from kotlin metadata */
    public final int errorCode;

    /* renamed from: d, reason: from kotlin metadata */
    public final String transactionId;

    /* renamed from: e, reason: from kotlin metadata */
    public final Map info;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0006\u001a\u00020\u00058\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lch/datatrans/payment/exception/BackendException$Companion;", "", "", "DEFAULT_NAME", "Ljava/lang/String;", "", "DEFAULT_ERROR_CODE", "I", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static final String access$formatMessage(Companion companion, String str, String str2, int i) {
            companion.getClass();
            if (str == null || str2 == null) {
                if (str == null) {
                    str = str2 == null ? "Datatrans backend error" : str2;
                }
                return str + ", error code: " + i;
            }
            return str + " (" + str2 + ", error code: " + i + ')';
        }
    }

    public /* synthetic */ BackendException(String str, String str2, int i, String str3, Map map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, str3, (i2 & 16) != 0 ? new LinkedHashMap() : map);
    }

    public static List a() {
        ClassLoader classLoader = BackendException.class.getClassLoader();
        Intrinsics.checkNotNull(classLoader);
        String next = new Scanner(classLoader.getResourceAsStream("DT_error_mapping_rules.json")).useDelimiter("\\A").next();
        Intrinsics.checkNotNullExpressionValue(next, "next(...)");
        Type type = new TypeToken<List<? extends BackendErrorRule>>() { // from class: ch.datatrans.payment.exception.BackendException$getBackendErrorRules$listType$1
        }.getType();
        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
        Object fromJson = new Gson().fromJson(next, type);
        Intrinsics.checkNotNullExpressionValue(fromJson, "fromJson(...)");
        return (List) fromJson;
    }

    /* renamed from: getDetail$lib_release, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    /* renamed from: getErrorMessage$lib_release, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final Map<String, String> getInfo() {
        return this.info;
    }

    public final String getName() {
        String str;
        try {
            Iterator it = a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = null;
                    break;
                }
                str = AbstractC0210s.a((BackendErrorRule) it.next(), this);
                if (str != null) {
                    break;
                }
            }
            if (str != null) {
                return str;
            }
            throw new NoSuchElementException("No element of the collection was transformed to a non-null value.");
        } catch (NoSuchElementException unused) {
            return "UNKNOWN_ERROR";
        }
    }

    /* renamed from: getTransactionId$lib_release, reason: from getter */
    public final String getTransactionId() {
        return this.transactionId;
    }

    public final boolean isHttpConflictCancelled$lib_release() {
        return this.errorCode == 409;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BackendException(String str, String str2, int i, String str3, Map<String, String> info) {
        super(Companion.access$formatMessage(INSTANCE, str, str2, i));
        Intrinsics.checkNotNullParameter(info, "info");
        this.errorMessage = str;
        this.detail = str2;
        this.errorCode = i;
        this.transactionId = str3;
        this.info = info;
    }
}
