package com.samsung.android.sdk.samsungpay.v2.card;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.samsungpay.v2.ISStatusListener;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.card.ISAddCardListener;
import com.samsung.android.sdk.samsungpay.v2.card.ISGetCardListener;
import com.samsung.android.sdk.samsungpay.v2.card.ISInitiateSessionCallback;

/* loaded from: classes2.dex */
public interface ISCardManager extends IInterface {

    public static class Default implements ISCardManager {
        @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
        public void addCard(PartnerInfo partnerInfo, AddCardInfo addCardInfo, ISAddCardListener iSAddCardListener) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
        public void getAllCards(PartnerInfo partnerInfo, Bundle bundle, ISGetCardListener iSGetCardListener) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
        public void initiateSessionWithCardInfo(PartnerInfo partnerInfo, ISInitiateSessionCallback iSInitiateSessionCallback, Card card) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
        public void terminateSession() throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
        public void updateCard(PartnerInfo partnerInfo, Card card, ISStatusListener iSStatusListener) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
        public void verifyCardIdv(PartnerInfo partnerInfo, IdvVerifyInfo idvVerifyInfo, ISStatusListener iSStatusListener) throws RemoteException {
        }
    }

    void addCard(PartnerInfo partnerInfo, AddCardInfo addCardInfo, ISAddCardListener iSAddCardListener) throws RemoteException;

    void getAllCards(PartnerInfo partnerInfo, Bundle bundle, ISGetCardListener iSGetCardListener) throws RemoteException;

    void initiateSessionWithCardInfo(PartnerInfo partnerInfo, ISInitiateSessionCallback iSInitiateSessionCallback, Card card) throws RemoteException;

    void terminateSession() throws RemoteException;

    void updateCard(PartnerInfo partnerInfo, Card card, ISStatusListener iSStatusListener) throws RemoteException;

    void verifyCardIdv(PartnerInfo partnerInfo, IdvVerifyInfo idvVerifyInfo, ISStatusListener iSStatusListener) throws RemoteException;

    public static abstract class Stub extends Binder implements ISCardManager {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.v2.card.ISCardManager";
        static final int TRANSACTION_addCard = 3;
        static final int TRANSACTION_getAllCards = 1;
        static final int TRANSACTION_initiateSessionWithCardInfo = 4;
        static final int TRANSACTION_terminateSession = 5;
        static final int TRANSACTION_updateCard = 6;
        static final int TRANSACTION_verifyCardIdv = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISCardManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISCardManager)) {
                return (ISCardManager) queryLocalInterface;
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
                    getAllCards(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, ISGetCardListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    verifyCardIdv(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? IdvVerifyInfo.CREATOR.createFromParcel(parcel) : null, ISStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    addCard(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? AddCardInfo.CREATOR.createFromParcel(parcel) : null, ISAddCardListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    initiateSessionWithCardInfo(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, ISInitiateSessionCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? Card.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    terminateSession();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCard(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? Card.CREATOR.createFromParcel(parcel) : null, ISStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ISCardManager {
            public static ISCardManager sDefaultImpl;
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

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
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
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getAllCards(partnerInfo, bundle, iSGetCardListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
            public void verifyCardIdv(PartnerInfo partnerInfo, IdvVerifyInfo idvVerifyInfo, ISStatusListener iSStatusListener) throws RemoteException {
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
                    if (idvVerifyInfo != null) {
                        obtain.writeInt(1);
                        idvVerifyInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSStatusListener != null ? iSStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().verifyCardIdv(partnerInfo, idvVerifyInfo, iSStatusListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
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
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addCard(partnerInfo, addCardInfo, iSAddCardListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
            public void initiateSessionWithCardInfo(PartnerInfo partnerInfo, ISInitiateSessionCallback iSInitiateSessionCallback, Card card) throws RemoteException {
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
                    obtain.writeStrongBinder(iSInitiateSessionCallback != null ? iSInitiateSessionCallback.asBinder() : null);
                    if (card != null) {
                        obtain.writeInt(1);
                        card.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initiateSessionWithCardInfo(partnerInfo, iSInitiateSessionCallback, card);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
            public void terminateSession() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().terminateSession();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.card.ISCardManager
            public void updateCard(PartnerInfo partnerInfo, Card card, ISStatusListener iSStatusListener) throws RemoteException {
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
                    if (card != null) {
                        obtain.writeInt(1);
                        card.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSStatusListener != null ? iSStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateCard(partnerInfo, card, iSStatusListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISCardManager iSCardManager) {
            if (Proxy.sDefaultImpl != null || iSCardManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSCardManager;
            return true;
        }

        public static ISCardManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
