package com.salesforce.marketingcloud.messages.iam;

import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.media.o;
import java.util.Date;

/* loaded from: classes2.dex */
class k implements Parcelable {
    public static final Parcelable.Creator<k> CREATOR = new a();
    private static final String h = com.salesforce.marketingcloud.g.a("MessageHandler");
    private final InAppMessage b;
    private i c;
    private long d;
    private long e;
    private long f;
    private boolean g;

    class a implements Parcelable.Creator<k> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public k[] newArray(int i) {
            return new k[i];
        }
    }

    k(InAppMessage inAppMessage) {
        MarketingCloudSdk marketingCloudSdk;
        this.d = -1L;
        this.g = true;
        this.b = inAppMessage;
        if ((MarketingCloudSdk.isInitializing() || MarketingCloudSdk.isReady()) && (marketingCloudSdk = MarketingCloudSdk.getInstance()) != null) {
            this.c = (i) marketingCloudSdk.getInAppMessageManager();
        }
    }

    private void p() {
        if (this.g) {
            this.e += SystemClock.elapsedRealtime() - this.f;
        }
    }

    void a(j jVar) {
        i iVar = this.c;
        if (iVar != null) {
            InAppMessage inAppMessage = this.b;
            if (jVar == null) {
                jVar = j.m();
            }
            iVar.handleMessageFinished(inAppMessage, jVar);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    boolean i() {
        i iVar;
        InAppMessage inAppMessage = this.b;
        return (inAppMessage == null || (iVar = this.c) == null || !iVar.canDisplay(inAppMessage)) ? false : true;
    }

    public long j() {
        return this.e;
    }

    Date k() {
        return new Date(this.d);
    }

    public InAppMessage l() {
        return this.b;
    }

    public o m() {
        i iVar = this.c;
        if (iVar != null) {
            return iVar.imageHandler();
        }
        return null;
    }

    void n() {
        p();
    }

    void o() {
        if (this.d == -1) {
            this.d = System.currentTimeMillis();
        }
        this.f = SystemClock.elapsedRealtime();
    }

    int q() {
        i iVar = this.c;
        if (iVar != null) {
            return iVar.getStatusBarColor();
        }
        return 0;
    }

    void r() {
        p();
        this.g = false;
    }

    public Typeface s() {
        i iVar = this.c;
        if (iVar != null) {
            return iVar.getTypeface();
        }
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.b, i);
        parcel.writeLong(this.d);
        parcel.writeLong(this.e);
        parcel.writeInt(this.g ? 1 : 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000e, code lost:
    
        r2 = r2.c.urlHandler();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    android.app.PendingIntent a(android.content.Context r3, com.salesforce.marketingcloud.messages.iam.InAppMessage.Button r4) {
        /*
            r2 = this;
            java.lang.String r0 = r4.action()
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Button$ActionType r4 = r4.actionType()
            com.salesforce.marketingcloud.messages.iam.InAppMessage$Button$ActionType r1 = com.salesforce.marketingcloud.messages.iam.InAppMessage.Button.ActionType.url
            if (r4 != r1) goto L31
            if (r0 == 0) goto L31
            com.salesforce.marketingcloud.messages.iam.i r2 = r2.c
            com.salesforce.marketingcloud.UrlHandler r2 = r2.urlHandler()
            if (r2 == 0) goto L31
            java.lang.String r4 = "action"
            android.app.PendingIntent r2 = r2.handleUrl(r3, r0, r4)     // Catch: java.lang.Exception -> L1d
            goto L32
        L1d:
            r3 = move-exception
            java.lang.String r4 = com.salesforce.marketingcloud.messages.iam.k.h
            java.lang.Class r2 = r2.getClass()
            java.lang.String r2 = r2.getName()
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r0 = "Exception thrown by %s while handling url"
            com.salesforce.marketingcloud.g.b(r4, r3, r0, r2)
        L31:
            r2 = 0
        L32:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.iam.k.a(android.content.Context, com.salesforce.marketingcloud.messages.iam.InAppMessage$Button):android.app.PendingIntent");
    }

    protected k(Parcel parcel) {
        this((InAppMessage) parcel.readParcelable(InAppMessage.class.getClassLoader()));
        this.d = parcel.readLong();
        this.e = parcel.readLong();
        this.g = parcel.readInt() == 1;
    }
}
