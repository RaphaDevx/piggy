package com.capacitorjs.plugins.geolocation;

import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: GeolocationErrors.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001 B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000bR\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000bR\u0011\u0010\u001c\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000bR\u0011\u0010\u001e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000b¨\u0006!"}, d2 = {"Lcom/capacitorjs/plugins/geolocation/GeolocationErrors;", "", "<init>", "()V", "formatErrorCode", "", "number", "", "POSITION_UNAVAILABLE", "Lcom/capacitorjs/plugins/geolocation/GeolocationErrors$ErrorInfo;", "getPOSITION_UNAVAILABLE", "()Lcom/capacitorjs/plugins/geolocation/GeolocationErrors$ErrorInfo;", "LOCATION_PERMISSIONS_DENIED", "getLOCATION_PERMISSIONS_DENIED", "LOCATION_DISABLED", "getLOCATION_DISABLED", "LOCATION_ENABLE_REQUEST_DENIED", "getLOCATION_ENABLE_REQUEST_DENIED", "GET_LOCATION_TIMEOUT", "getGET_LOCATION_TIMEOUT", "INVALID_TIMEOUT", "getINVALID_TIMEOUT", "WATCH_ID_NOT_FOUND", "getWATCH_ID_NOT_FOUND", "WATCH_ID_NOT_PROVIDED", "getWATCH_ID_NOT_PROVIDED", "GOOGLE_SERVICES_RESOLVABLE", "getGOOGLE_SERVICES_RESOLVABLE", "GOOGLE_SERVICES_ERROR", "getGOOGLE_SERVICES_ERROR", "LOCATION_SETTINGS_ERROR", "getLOCATION_SETTINGS_ERROR", "ErrorInfo", "capacitor-geolocation_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class GeolocationErrors {
    private static final ErrorInfo GET_LOCATION_TIMEOUT;
    private static final ErrorInfo GOOGLE_SERVICES_ERROR;
    private static final ErrorInfo GOOGLE_SERVICES_RESOLVABLE;
    public static final GeolocationErrors INSTANCE;
    private static final ErrorInfo INVALID_TIMEOUT;
    private static final ErrorInfo LOCATION_DISABLED;
    private static final ErrorInfo LOCATION_ENABLE_REQUEST_DENIED;
    private static final ErrorInfo LOCATION_PERMISSIONS_DENIED;
    private static final ErrorInfo LOCATION_SETTINGS_ERROR;
    private static final ErrorInfo POSITION_UNAVAILABLE;
    private static final ErrorInfo WATCH_ID_NOT_FOUND;
    private static final ErrorInfo WATCH_ID_NOT_PROVIDED;

    private GeolocationErrors() {
    }

    private final String formatErrorCode(int number) {
        return "OS-PLUG-GLOC-" + StringsKt.padStart(String.valueOf(number), 4, '0');
    }

    /* compiled from: GeolocationErrors.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/capacitorjs/plugins/geolocation/GeolocationErrors$ErrorInfo;", "", "code", "", "message", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getMessage", "component1", "component2", "copy", "equals", "", Request.JsonKeys.OTHER, "hashCode", "", "toString", "capacitor-geolocation_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class ErrorInfo {
        private final String code;
        private final String message;

        public static /* synthetic */ ErrorInfo copy$default(ErrorInfo errorInfo, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = errorInfo.code;
            }
            if ((i & 2) != 0) {
                str2 = errorInfo.message;
            }
            return errorInfo.copy(str, str2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getCode() {
            return this.code;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final ErrorInfo copy(String code, String message) {
            Intrinsics.checkNotNullParameter(code, "code");
            Intrinsics.checkNotNullParameter(message, "message");
            return new ErrorInfo(code, message);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ErrorInfo)) {
                return false;
            }
            ErrorInfo errorInfo = (ErrorInfo) other;
            return Intrinsics.areEqual(this.code, errorInfo.code) && Intrinsics.areEqual(this.message, errorInfo.message);
        }

        public int hashCode() {
            return (this.code.hashCode() * 31) + this.message.hashCode();
        }

        public String toString() {
            return "ErrorInfo(code=" + this.code + ", message=" + this.message + ")";
        }

        public ErrorInfo(String code, String message) {
            Intrinsics.checkNotNullParameter(code, "code");
            Intrinsics.checkNotNullParameter(message, "message");
            this.code = code;
            this.message = message;
        }

        public final String getCode() {
            return this.code;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    static {
        GeolocationErrors geolocationErrors = new GeolocationErrors();
        INSTANCE = geolocationErrors;
        POSITION_UNAVAILABLE = new ErrorInfo(geolocationErrors.formatErrorCode(2), "There was en error trying to obtain the location.");
        LOCATION_PERMISSIONS_DENIED = new ErrorInfo(geolocationErrors.formatErrorCode(3), "Location permission request was denied.");
        LOCATION_DISABLED = new ErrorInfo(geolocationErrors.formatErrorCode(7), "Location services are not enabled.");
        LOCATION_ENABLE_REQUEST_DENIED = new ErrorInfo(geolocationErrors.formatErrorCode(9), "Request to enable location was denied.");
        GET_LOCATION_TIMEOUT = new ErrorInfo(geolocationErrors.formatErrorCode(10), "Could not obtain location in time. Try with a higher timeout.");
        INVALID_TIMEOUT = new ErrorInfo(geolocationErrors.formatErrorCode(11), "Timeout needs to be a positive value.");
        WATCH_ID_NOT_FOUND = new ErrorInfo(geolocationErrors.formatErrorCode(12), "WatchId not found.");
        WATCH_ID_NOT_PROVIDED = new ErrorInfo(geolocationErrors.formatErrorCode(13), "WatchId needs to be provided.");
        GOOGLE_SERVICES_RESOLVABLE = new ErrorInfo(geolocationErrors.formatErrorCode(14), "Google Play Services error user resolvable.");
        GOOGLE_SERVICES_ERROR = new ErrorInfo(geolocationErrors.formatErrorCode(15), "Google Play Services error.");
        LOCATION_SETTINGS_ERROR = new ErrorInfo(geolocationErrors.formatErrorCode(16), "Location settings error.");
    }

    public final ErrorInfo getPOSITION_UNAVAILABLE() {
        return POSITION_UNAVAILABLE;
    }

    public final ErrorInfo getLOCATION_PERMISSIONS_DENIED() {
        return LOCATION_PERMISSIONS_DENIED;
    }

    public final ErrorInfo getLOCATION_DISABLED() {
        return LOCATION_DISABLED;
    }

    public final ErrorInfo getLOCATION_ENABLE_REQUEST_DENIED() {
        return LOCATION_ENABLE_REQUEST_DENIED;
    }

    public final ErrorInfo getGET_LOCATION_TIMEOUT() {
        return GET_LOCATION_TIMEOUT;
    }

    public final ErrorInfo getINVALID_TIMEOUT() {
        return INVALID_TIMEOUT;
    }

    public final ErrorInfo getWATCH_ID_NOT_FOUND() {
        return WATCH_ID_NOT_FOUND;
    }

    public final ErrorInfo getWATCH_ID_NOT_PROVIDED() {
        return WATCH_ID_NOT_PROVIDED;
    }

    public final ErrorInfo getGOOGLE_SERVICES_RESOLVABLE() {
        return GOOGLE_SERVICES_RESOLVABLE;
    }

    public final ErrorInfo getGOOGLE_SERVICES_ERROR() {
        return GOOGLE_SERVICES_ERROR;
    }

    public final ErrorInfo getLOCATION_SETTINGS_ERROR() {
        return LOCATION_SETTINGS_ERROR;
    }
}
