package androidx.webkit;

import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class CustomHeader {
    private final String mName;
    private final Set<String> mRules;
    private final String mValue;

    public CustomHeader(String str, String str2, Set<String> set) {
        this.mName = str;
        this.mValue = str2;
        this.mRules = set;
    }

    public String getName() {
        return this.mName;
    }

    public String getValue() {
        return this.mValue;
    }

    public Set<String> getRules() {
        return this.mRules;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mValue, this.mRules);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomHeader)) {
            return false;
        }
        CustomHeader customHeader = (CustomHeader) obj;
        return Objects.equals(this.mName, customHeader.mName) && Objects.equals(this.mValue, customHeader.mValue) && Objects.equals(this.mRules, customHeader.mRules);
    }
}
