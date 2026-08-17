package com.samsung.android.sdk.samsungpay.v2.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback;

/* loaded from: classes2.dex */
public interface ISServiceManager extends IInterface {

    public static class Default implements ISServiceManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.service.ISServiceManager
        public void getUserInfo(PartnerInfo partnerInfo, RequestType[] requestTypeArr, ISUserInfoCallback iSUserInfoCallback) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.service.ISServiceManager
        public void notifyUserSignUpResult(PartnerInfo partnerInfo, String str, boolean z, ISUserInfoCallback iSUserInfoCallback) throws RemoteException {
        }
    }

    void getUserInfo(PartnerInfo partnerInfo, RequestType[] requestTypeArr, ISUserInfoCallback iSUserInfoCallback) throws RemoteException;

    void notifyUserSignUpResult(PartnerInfo partnerInfo, String str, boolean z, ISUserInfoCallback iSUserInfoCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ISServiceManager {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.v2.service.ISServiceManager";
        static final int TRANSACTION_getUserInfo = 1;
        static final int TRANSACTION_notifyUserSignUpResult = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISServiceManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISServiceManager)) {
                return (ISServiceManager) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                getUserInfo(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, (RequestType[]) parcel.createTypedArray(RequestType.CREATOR), ISUserInfoCallback.Stub.asInterface(parcel.readStrongBinder()));
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
            notifyUserSignUpResult(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt() != 0, ISUserInfoCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISServiceManager {
            public static ISServiceManager sDefaultImpl;
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

            @Override // com.samsung.android.sdk.samsungpay.v2.service.ISServiceManager
            public void getUserInfo(PartnerInfo partnerInfo, RequestType[] requestTypeArr, ISUserInfoCallback iSUserInfoCallback) throws RemoteException {
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
                    obtain.writeTypedArray(requestTypeArr, 0);
                    obtain.writeStrongBinder(iSUserInfoCallback != null ? iSUserInfoCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getUserInfo(partnerInfo, requestTypeArr, iSUserInfoCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.service.ISServiceManager
            public void notifyUserSignUpResult(PartnerInfo partnerInfo, String str, boolean z, ISUserInfoCallback iSUserInfoCallback) throws RemoteException {
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
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iSUserInfoCallback != null ? iSUserInfoCallback.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyUserSignUpResult(partnerInfo, str, z, iSUserInfoCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISServiceManager iSServiceManager) {
            if (Proxy.sDefaultImpl != null || iSServiceManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSServiceManager;
            return true;
        }

        public static ISServiceManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
