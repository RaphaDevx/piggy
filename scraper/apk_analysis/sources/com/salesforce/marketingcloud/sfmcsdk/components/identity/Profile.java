package com.salesforce.marketingcloud.sfmcsdk.components.identity;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Identity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0017\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005HÆ\u0003J+\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/identity/Profile;", "", "profileId", "", "attributes", "", "(Ljava/lang/String;Ljava/util/Map;)V", "getAttributes", "()Ljava/util/Map;", "getProfileId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "toString", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Profile {
    private final Map<String, String> attributes;
    private final String profileId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Profile copy$default(Profile profile, String str, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = profile.profileId;
        }
        if ((i & 2) != 0) {
            map = profile.attributes;
        }
        return profile.copy(str, map);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProfileId() {
        return this.profileId;
    }

    public final Map<String, String> component2() {
        return this.attributes;
    }

    public final Profile copy(String profileId, Map<String, String> attributes) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        return new Profile(profileId, attributes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) other;
        return Intrinsics.areEqual(this.profileId, profile.profileId) && Intrinsics.areEqual(this.attributes, profile.attributes);
    }

    public int hashCode() {
        return (this.profileId.hashCode() * 31) + this.attributes.hashCode();
    }

    public String toString() {
        return "Profile(profileId=" + this.profileId + ", attributes=" + this.attributes + ")";
    }

    public Profile(String profileId, Map<String, String> attributes) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        this.profileId = profileId;
        this.attributes = attributes;
    }

    public final Map<String, String> getAttributes() {
        return this.attributes;
    }

    public final String getProfileId() {
        return this.profileId;
    }
}
