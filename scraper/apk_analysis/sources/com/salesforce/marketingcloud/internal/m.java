package com.salesforce.marketingcloud.internal;

import com.salesforce.marketingcloud.registration.Registration;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class m {
    public static final a a = new a(null);

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Registration a(JSONObject json) {
            Intrinsics.checkNotNullParameter(json, "json");
            return new Registration(json);
        }

        @JvmStatic
        public final int b(Registration registration) {
            Intrinsics.checkNotNullParameter(registration, "registration");
            return registration.getId$sdk_release();
        }

        @JvmStatic
        public final JSONObject c(Registration registration) {
            Intrinsics.checkNotNullParameter(registration, "registration");
            return registration.toJson$sdk_release();
        }

        @JvmStatic
        public final String d(Registration registration) {
            Intrinsics.checkNotNullParameter(registration, "registration");
            return registration.getUuid$sdk_release();
        }

        private a() {
        }

        @JvmStatic
        public final void a(Registration registration, int i) {
            Intrinsics.checkNotNullParameter(registration, "registration");
            registration.setId$sdk_release(i);
        }

        @JvmStatic
        public final String a(Registration registration) {
            Registration copy;
            Intrinsics.checkNotNullParameter(registration, "registration");
            copy = registration.copy((r38 & 1) != 0 ? registration.id : 0, (r38 & 2) != 0 ? registration.uuid : "", (r38 & 4) != 0 ? registration.signedString : null, (r38 & 8) != 0 ? registration.deviceId : null, (r38 & 16) != 0 ? registration.systemToken : null, (r38 & 32) != 0 ? registration.sdkVersion : null, (r38 & 64) != 0 ? registration.appVersion : null, (r38 & 128) != 0 ? registration.dst : false, (r38 & 256) != 0 ? registration.locationEnabled : false, (r38 & 512) != 0 ? registration.proximityEnabled : false, (r38 & 1024) != 0 ? registration.platformVersion : null, (r38 & 2048) != 0 ? registration.pushEnabled : false, (r38 & 4096) != 0 ? registration.timeZone : 0, (r38 & 8192) != 0 ? registration.contactKey : null, (r38 & 16384) != 0 ? registration.platform : null, (r38 & 32768) != 0 ? registration.hwid : null, (r38 & 65536) != 0 ? registration.appId : null, (r38 & 131072) != 0 ? registration.locale : null, (r38 & 262144) != 0 ? registration.tags : null, (r38 & 524288) != 0 ? registration.attributes : null);
            String jSONObject = copy.toJson$sdk_release().toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
            return jSONObject;
        }
    }

    @JvmStatic
    public static final Registration a(JSONObject jSONObject) {
        return a.a(jSONObject);
    }

    @JvmStatic
    public static final int b(Registration registration) {
        return a.b(registration);
    }

    @JvmStatic
    public static final JSONObject c(Registration registration) {
        return a.c(registration);
    }

    @JvmStatic
    public static final String d(Registration registration) {
        return a.d(registration);
    }

    @JvmStatic
    public static final String a(Registration registration) {
        return a.a(registration);
    }

    @JvmStatic
    public static final void a(Registration registration, int i) {
        a.a(registration, i);
    }
}
