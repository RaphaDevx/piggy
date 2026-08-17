package javax.ws.rs.core;

import javax.ws.rs.ext.RuntimeDelegate;

/* loaded from: classes4.dex */
public class EntityTag {
    private static final RuntimeDelegate.HeaderDelegate<EntityTag> HEADER_DELEGATE = RuntimeDelegate.getInstance().createHeaderDelegate(EntityTag.class);
    private String value;
    private boolean weak;

    public EntityTag(String str) {
        this(str, false);
    }

    public EntityTag(String str, boolean z) {
        if (str == null) {
            throw new IllegalArgumentException("value==null");
        }
        this.value = str;
        this.weak = z;
    }

    public static EntityTag valueOf(String str) {
        return HEADER_DELEGATE.fromString(str);
    }

    public boolean isWeak() {
        return this.weak;
    }

    public String getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EntityTag)) {
            return super.equals(obj);
        }
        EntityTag entityTag = (EntityTag) obj;
        return this.value.equals(entityTag.getValue()) && this.weak == entityTag.isWeak();
    }

    public int hashCode() {
        String str = this.value;
        return ((51 + (str != null ? str.hashCode() : 0)) * 17) + (this.weak ? 1 : 0);
    }

    public String toString() {
        return HEADER_DELEGATE.toString(this);
    }
}
