package com.samsung.android.sdk.samsungpay.v2.card;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import java.util.List;

/* loaded from: classes2.dex */
public interface ISGetCardListener extends IInterface {

    public static class Default implements ISGetCardListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener
        public void onFail(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener
        public void onSuccess(PartnerInfo partnerInfo, List<Card> list) throws RemoteException {
        }
    }

    void onFail(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException;

    void onSuccess(PartnerInfo partnerInfo, List<Card> list) throws RemoteException;

    public static abstract class Stub extends Binder implements ISGetCardListener {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener";
        static final int TRANSACTION_onFail = 2;
        static final int TRANSACTION_onSuccess = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISGetCardListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISGetCardListener)) {
                return (ISGetCardListener) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onSuccess(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.createTypedArrayList(Card.CREATOR));
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onFail(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISGetCardListener {
            public static ISGetCardListener sDefaultImpl;
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

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener
            public void onSuccess(PartnerInfo partnerInfo, List<Card> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (partnerInfo != null) {
                        obtain.writeInt(1);
                        partnerInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onSuccess(partnerInfo, list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener
            public void onFail(PartnerInfo partnerInfo, int i, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (partnerInfo != null) {
                        obtain.writeInt(1);
                        partnerInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onFail(partnerInfo, i, bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISGetCardListener iSGetCardListener) {
            if (Proxy.sDefaultImpl != null || iSGetCardListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSGetCardListener;
            return true;
        }

        public static ISGetCardListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
