package com.samsung.android.sdk.samsungpay.v2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ISPartnerAppDeathDetector extends IInterface {

    public static class Default implements ISPartnerAppDeathDetector {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISPartnerAppDeathDetector {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.v2.ISPartnerAppDeathDetector";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISPartnerAppDeathDetector asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISPartnerAppDeathDetector)) {
                return (ISPartnerAppDeathDetector) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISPartnerAppDeathDetector {
            public static ISPartnerAppDeathDetector sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public static boolean setDefaultImpl(ISPartnerAppDeathDetector iSPartnerAppDeathDetector) {
            if (Proxy.sDefaultImpl != null || iSPartnerAppDeathDetector == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSPartnerAppDeathDetector;
            return true;
        }

        public static ISPartnerAppDeathDetector getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
