package com.tealium.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/* compiled from: ConnectivityHelper.java */
/* loaded from: classes2.dex */
public abstract class a {
    public static a a(Context context) {
        return new C0101a((ConnectivityManager) context.getSystemService("connectivity"));
    }

    public abstract boolean a();

    public abstract boolean b();

    /* compiled from: ConnectivityHelper.java */
    /* renamed from: com.tealium.internal.a$a, reason: collision with other inner class name */
    class C0101a extends a {
        final /* synthetic */ ConnectivityManager a;

        C0101a(ConnectivityManager connectivityManager) {
            this.a = connectivityManager;
        }

        @Override // com.tealium.internal.a
        public boolean a() {
            NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        @Override // com.tealium.internal.a
        public boolean b() {
            for (Network network : this.a.getAllNetworks()) {
                if (this.a.getNetworkInfo(network).getType() == 1) {
                    return true;
                }
            }
            return false;
        }
    }
}
