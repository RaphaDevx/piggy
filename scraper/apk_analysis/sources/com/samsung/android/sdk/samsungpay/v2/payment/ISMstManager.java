package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.ISEncryptionKeyListener;
import com.samsung.android.sdk.samsungpay.v2.payment.ISMstPaymentListener;
import com.samsung.android.sdk.samsungpay.v2.payment.ISTransactionResultListener;

/* loaded from: classes2.dex */
public interface ISMstManager extends IInterface {

    public static class Default implements ISMstManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager
        public void getTokenEncryptionKey(PartnerInfo partnerInfo, ISEncryptionKeyListener iSEncryptionKeyListener) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager
        public void sendTransactionResultInfo(PartnerInfo partnerInfo, TransactionResultInfo transactionResultInfo, ISTransactionResultListener iSTransactionResultListener) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager
        public void startMST(PartnerInfo partnerInfo, MstPaymentInfo mstPaymentInfo, ISMstPaymentListener iSMstPaymentListener) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager
        public void stopMST(PartnerInfo partnerInfo, ISMstPaymentListener iSMstPaymentListener) throws RemoteException {
        }
    }

    void getTokenEncryptionKey(PartnerInfo partnerInfo, ISEncryptionKeyListener iSEncryptionKeyListener) throws RemoteException;

    void sendTransactionResultInfo(PartnerInfo partnerInfo, TransactionResultInfo transactionResultInfo, ISTransactionResultListener iSTransactionResultListener) throws RemoteException;

    void startMST(PartnerInfo partnerInfo, MstPaymentInfo mstPaymentInfo, ISMstPaymentListener iSMstPaymentListener) throws RemoteException;

    void stopMST(PartnerInfo partnerInfo, ISMstPaymentListener iSMstPaymentListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ISMstManager {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager";
        static final int TRANSACTION_getTokenEncryptionKey = 1;
        static final int TRANSACTION_sendTransactionResultInfo = 4;
        static final int TRANSACTION_startMST = 2;
        static final int TRANSACTION_stopMST = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISMstManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISMstManager)) {
                return (ISMstManager) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                getTokenEncryptionKey(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, ISEncryptionKeyListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                startMST(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? MstPaymentInfo.CREATOR.createFromParcel(parcel) : null, ISMstPaymentListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                stopMST(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, ISMstPaymentListener.Stub.asInterface(parcel.readStrongBinder()));
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
            sendTransactionResultInfo(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? TransactionResultInfo.CREATOR.createFromParcel(parcel) : null, ISTransactionResultListener.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISMstManager {
            public static ISMstManager sDefaultImpl;
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

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager
            public void getTokenEncryptionKey(PartnerInfo partnerInfo, ISEncryptionKeyListener iSEncryptionKeyListener) throws RemoteException {
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
                    obtain.writeStrongBinder(iSEncryptionKeyListener != null ? iSEncryptionKeyListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getTokenEncryptionKey(partnerInfo, iSEncryptionKeyListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager
            public void startMST(PartnerInfo partnerInfo, MstPaymentInfo mstPaymentInfo, ISMstPaymentListener iSMstPaymentListener) throws RemoteException {
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
                    if (mstPaymentInfo != null) {
                        obtain.writeInt(1);
                        mstPaymentInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSMstPaymentListener != null ? iSMstPaymentListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startMST(partnerInfo, mstPaymentInfo, iSMstPaymentListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager
            public void stopMST(PartnerInfo partnerInfo, ISMstPaymentListener iSMstPaymentListener) throws RemoteException {
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
                    obtain.writeStrongBinder(iSMstPaymentListener != null ? iSMstPaymentListener.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopMST(partnerInfo, iSMstPaymentListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISMstManager
            public void sendTransactionResultInfo(PartnerInfo partnerInfo, TransactionResultInfo transactionResultInfo, ISTransactionResultListener iSTransactionResultListener) throws RemoteException {
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
                    if (transactionResultInfo != null) {
                        obtain.writeInt(1);
                        transactionResultInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSTransactionResultListener != null ? iSTransactionResultListener.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendTransactionResultInfo(partnerInfo, transactionResultInfo, iSTransactionResultListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISMstManager iSMstManager) {
            if (Proxy.sDefaultImpl != null || iSMstManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSMstManager;
            return true;
        }

        public static ISMstManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
