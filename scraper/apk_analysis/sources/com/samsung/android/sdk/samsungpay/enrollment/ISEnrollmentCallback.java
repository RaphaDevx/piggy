package com.samsung.android.sdk.samsungpay.enrollment;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public interface ISEnrollmentCallback extends IInterface {

    public static class Default implements ISEnrollmentCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback
        public void onCardEnrolled() throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback
        public void onInitiateCompleted(String str) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback
        public void onInitiateFailed(int i, String str) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback
        public void onReadyEnrollment(String str) throws RemoteException {
        }
    }

    void onCardEnrolled() throws RemoteException;

    void onInitiateCompleted(String str) throws RemoteException;

    void onInitiateFailed(int i, String str) throws RemoteException;

    void onReadyEnrollment(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ISEnrollmentCallback {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback";
        static final int TRANSACTION_onCardEnrolled = 4;
        static final int TRANSACTION_onInitiateCompleted = 1;
        static final int TRANSACTION_onInitiateFailed = 2;
        static final int TRANSACTION_onReadyEnrollment = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISEnrollmentCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISEnrollmentCallback)) {
                return (ISEnrollmentCallback) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onInitiateCompleted(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onInitiateFailed(parcel.readInt(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onReadyEnrollment(parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i != 4) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            onCardEnrolled();
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISEnrollmentCallback {
            public static ISEnrollmentCallback sDefaultImpl;
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

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback
            public void onInitiateCompleted(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInitiateCompleted(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback
            public void onInitiateFailed(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onInitiateFailed(i, str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback
            public void onReadyEnrollment(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onReadyEnrollment(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.enrollment.ISEnrollmentCallback
            public void onCardEnrolled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onCardEnrolled();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISEnrollmentCallback iSEnrollmentCallback) {
            if (Proxy.sDefaultImpl != null || iSEnrollmentCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSEnrollmentCallback;
            return true;
        }

        public static ISEnrollmentCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
