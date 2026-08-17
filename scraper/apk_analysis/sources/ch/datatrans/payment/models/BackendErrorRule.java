package ch.datatrans.payment.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import ch.datatrans.payment.N7;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0080\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\f\u0010\n\u001a\u0004\b\r\u0010\u000bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\f\u0010\u000bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u000e\u0010\n\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u000f"}, d2 = {"Lch/datatrans/payment/models/BackendErrorRule;", "", "", "code", "message", "detail", "name", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "a", "Ljava/lang/String;", "()Ljava/lang/String;", "b", "c", "d", "lib_release"}, k = 1, mv = {2, 2, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final /* data */ class BackendErrorRule {

    /* renamed from: a, reason: from kotlin metadata */
    @SerializedName("Code")
    private final String code;

    /* renamed from: b, reason: from kotlin metadata */
    @SerializedName("Message")
    private final String message;

    /* renamed from: c, reason: from kotlin metadata */
    @SerializedName("Detail")
    private final String detail;

    /* renamed from: d, reason: from kotlin metadata */
    @SerializedName("Name")
    private final String name;

    public BackendErrorRule(String str, String str2, String str3, String str4) {
        this.code = str;
        this.message = str2;
        this.detail = str3;
        this.name = str4;
    }

    /* renamed from: a, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* renamed from: b, reason: from getter */
    public final String getDetail() {
        return this.detail;
    }

    /* renamed from: c, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: d, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BackendErrorRule)) {
            return false;
        }
        BackendErrorRule backendErrorRule = (BackendErrorRule) obj;
        return Intrinsics.areEqual(this.code, backendErrorRule.code) && Intrinsics.areEqual(this.message, backendErrorRule.message) && Intrinsics.areEqual(this.detail, backendErrorRule.detail) && Intrinsics.areEqual(this.name, backendErrorRule.name);
    }

    public final int hashCode() {
        String str = this.code;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.message;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.detail;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.name;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BackendErrorRule(code=");
        sb.append(this.code);
        sb.append(", message=");
        sb.append(this.message);
        sb.append(", detail=");
        sb.append(this.detail);
        sb.append(", name=");
        return N7.a(sb, this.name, ')');
    }
}
