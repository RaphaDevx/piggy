package com.samsung.android.sdk.samsungpay.enrollment;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback;

/* loaded from: classes2.dex */
public interface ISEnrollmentManager extends IInterface {

    public static class Default implements ISEnrollmentManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
        public int enrollCard(String str) throws RemoteException {
            return 0;
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
        public void initiateSession(String str, String str2) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
        public void requestDetailInfo() throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
        public void setBinder(IBinder iBinder) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
        public void setSEnrollmentCallback(ISEnrollmentCallback iSEnrollmentCallback) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
        public void terminateSession(String str) throws RemoteException {
        }
    }

    int enrollCard(String str) throws RemoteException;

    void initiateSession(String str, String str2) throws RemoteException;

    void requestDetailInfo() throws RemoteException;

    void setBinder(IBinder iBinder) throws RemoteException;

    void setSEnrollmentCallback(ISEnrollmentCallback iSEnrollmentCallback) throws RemoteException;

    void terminateSession(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ISEnrollmentManager {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager";
        static final int TRANSACTION_enrollCard = 6;
        static final int TRANSACTION_initiateSession = 1;
        static final int TRANSACTION_requestDetailInfo = 3;
        static final int TRANSACTION_setBinder = 4;
        static final int TRANSACTION_setSEnrollmentCallback = 5;
        static final int TRANSACTION_terminateSession = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISEnrollmentManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISEnrollmentManager)) {
                return (ISEnrollmentManager) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    initiateSession(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    terminateSession(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestDetailInfo();
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBinder(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSEnrollmentCallback(ISEnrollmentCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int enrollCard = enrollCard(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(enrollCard);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISEnrollmentManager {
            public static ISEnrollmentManager sDefaultImpl;
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

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
            public void initiateSession(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initiateSession(str, str2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
            public void terminateSession(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().terminateSession(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
            public void requestDetailInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestDetailInfo();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
            public void setBinder(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBinder(iBinder);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
            public void setSEnrollmentCallback(ISEnrollmentCallback iSEnrollmentCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSEnrollmentCallback != null ? iSEnrollmentCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSEnrollmentCallback(iSEnrollmentCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentManager
            public int enrollCard(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enrollCard(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISEnrollmentManager iSEnrollmentManager) {
            if (Proxy.sDefaultImpl != null || iSEnrollmentManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSEnrollmentManager;
            return true;
        }

        public static ISEnrollmentManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
