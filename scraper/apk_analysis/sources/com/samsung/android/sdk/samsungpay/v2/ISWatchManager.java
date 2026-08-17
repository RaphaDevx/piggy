package com.samsung.android.sdk.samsungpay.v2;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.samsungpay.v2.ISStatusListener;
import com.samsung.android.sdk.samsungpay.v2.card.AddCardInfo;
import com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener;
import com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener;
import java.util.List;

/* loaded from: classes2.dex */
public interface ISWatchManager extends IInterface {

    public static class Default implements ISWatchManager {
        @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
        public void activateSamsungPay() throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
        public void addCard(PartnerInfo partnerInfo, AddCardInfo addCardInfo, ISAddCardListener iSAddCardListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
        public void getAllCards(PartnerInfo partnerInfo, Bundle bundle, ISGetCardListener iSGetCardListener) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
        public Status getSamsungPayStatus(PartnerInfo partnerInfo) throws RemoteException {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
        public void getWalletInfo(PartnerInfo partnerInfo, List<String> list, ISStatusListener iSStatusListener) throws RemoteException {
        }
    }

    void activateSamsungPay() throws RemoteException;

    void addCard(PartnerInfo partnerInfo, AddCardInfo addCardInfo, ISAddCardListener iSAddCardListener) throws RemoteException;

    void getAllCards(PartnerInfo partnerInfo, Bundle bundle, ISGetCardListener iSGetCardListener) throws RemoteException;

    Status getSamsungPayStatus(PartnerInfo partnerInfo) throws RemoteException;

    void getWalletInfo(PartnerInfo partnerInfo, List<String> list, ISStatusListener iSStatusListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ISWatchManager {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.v2.ISWatchManager";
        static final int TRANSACTION_activateSamsungPay = 2;
        static final int TRANSACTION_addCard = 5;
        static final int TRANSACTION_getAllCards = 4;
        static final int TRANSACTION_getSamsungPayStatus = 1;
        static final int TRANSACTION_getWalletInfo = 3;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISWatchManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISWatchManager)) {
                return (ISWatchManager) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                Status samsungPayStatus = getSamsungPayStatus(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                if (samsungPayStatus != null) {
                    parcel2.writeInt(1);
                    samsungPayStatus.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                activateSamsungPay();
                parcel2.writeNoException();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                getWalletInfo(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.createStringArrayList(), ISStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                getAllCards(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, ISGetCardListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            if (i != 5) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            addCard(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AddCardInfo.CREATOR.createFromParcel(parcel) : null, ISAddCardListener.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy implements ISWatchManager {
            public static ISWatchManager sDefaultImpl;
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

            @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
            public Status getSamsungPayStatus(PartnerInfo partnerInfo) throws RemoteException {
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
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSamsungPayStatus(partnerInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Status.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
            public void activateSamsungPay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().activateSamsungPay();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
            public void getWalletInfo(PartnerInfo partnerInfo, List<String> list, ISStatusListener iSStatusListener) throws RemoteException {
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
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iSStatusListener != null ? iSStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getWalletInfo(partnerInfo, list, iSStatusListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
            public void getAllCards(PartnerInfo partnerInfo, Bundle bundle, ISGetCardListener iSGetCardListener) throws RemoteException {
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
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSGetCardListener != null ? iSGetCardListener.asBinder() : null);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAllCards(partnerInfo, bundle, iSGetCardListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.ISWatchManager
            public void addCard(PartnerInfo partnerInfo, AddCardInfo addCardInfo, ISAddCardListener iSAddCardListener) throws RemoteException {
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
                    if (addCardInfo != null) {
                        obtain.writeInt(1);
                        addCardInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSAddCardListener != null ? iSAddCardListener.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCard(partnerInfo, addCardInfo, iSAddCardListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISWatchManager iSWatchManager) {
            if (Proxy.sDefaultImpl != null || iSWatchManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSWatchManager;
            return true;
        }

        public static ISWatchManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
