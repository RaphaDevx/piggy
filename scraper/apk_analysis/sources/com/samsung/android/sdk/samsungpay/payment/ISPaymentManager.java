package com.samsung.android.sdk.samsungpay.payment;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.samsungpay.payment.ISPaymentCallback;
import com.samsung.android.sdk.samsungpay.payment.PaymentInfo;
import java.util.List;

/* loaded from: classes2.dex */
public interface ISPaymentManager extends IInterface {

    public static class Default implements ISPaymentManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
        public void initiateSession(String str, String str2, PaymentInfo paymentInfo, ISPaymentCallback iSPaymentCallback, IBinder iBinder) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
        public int invalidAddress(int i) throws RemoteException {
            return 0;
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
        public boolean isSamsungPaySetupCompleted() throws RemoteException {
            return false;
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
        public List<CardInfo> requestCardType() throws RemoteException {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
        public void terminateSession() throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
        public int updateAmount(PaymentInfo.Amount amount) throws RemoteException {
            return 0;
        }
    }

    void initiateSession(String str, String str2, PaymentInfo paymentInfo, ISPaymentCallback iSPaymentCallback, IBinder iBinder) throws RemoteException;

    int invalidAddress(int i) throws RemoteException;

    boolean isSamsungPaySetupCompleted() throws RemoteException;

    List<CardInfo> requestCardType() throws RemoteException;

    void terminateSession() throws RemoteException;

    int updateAmount(PaymentInfo.Amount amount) throws RemoteException;

    public static abstract class Stub extends Binder implements ISPaymentManager {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.payment.ISPaymentManager";
        static final int TRANSACTION_initiateSession = 1;
        static final int TRANSACTION_invalidAddress = 4;
        static final int TRANSACTION_isSamsungPaySetupCompleted = 6;
        static final int TRANSACTION_requestCardType = 5;
        static final int TRANSACTION_terminateSession = 2;
        static final int TRANSACTION_updateAmount = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISPaymentManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISPaymentManager)) {
                return (ISPaymentManager) queryLocalInterface;
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
                    initiateSession(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? PaymentInfo.CREATOR.createFromParcel(parcel) : null, ISPaymentCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    terminateSession();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int updateAmount = updateAmount(parcel.readInt() != 0 ? PaymentInfo.Amount.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(updateAmount);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int invalidAddress = invalidAddress(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(invalidAddress);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<CardInfo> requestCardType = requestCardType();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(requestCardType);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isSamsungPaySetupCompleted = isSamsungPaySetupCompleted();
                    parcel2.writeNoException();
                    parcel2.writeInt(isSamsungPaySetupCompleted ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISPaymentManager {
            public static ISPaymentManager sDefaultImpl;
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

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
            public void initiateSession(String str, String str2, PaymentInfo paymentInfo, ISPaymentCallback iSPaymentCallback, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (paymentInfo != null) {
                        obtain.writeInt(1);
                        paymentInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSPaymentCallback != null ? iSPaymentCallback.asBinder() : null);
                    obtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initiateSession(str, str2, paymentInfo, iSPaymentCallback, iBinder);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
            public void terminateSession() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().terminateSession();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
            public int updateAmount(PaymentInfo.Amount amount) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (amount != null) {
                        obtain.writeInt(1);
                        amount.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateAmount(amount);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
            public int invalidAddress(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().invalidAddress(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
            public List<CardInfo> requestCardType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestCardType();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(CardInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.payment.ISPaymentManager
            public boolean isSamsungPaySetupCompleted() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSamsungPaySetupCompleted();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISPaymentManager iSPaymentManager) {
            if (Proxy.sDefaultImpl != null || iSPaymentManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSPaymentManager;
            return true;
        }

        public static ISPaymentManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
