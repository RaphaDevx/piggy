package com.samsung.android.sdk.samsungpay.v2.payment;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.samsung.android.sdk.samsungpay.v2.ISStatusListener;
import com.samsung.android.sdk.samsungpay.v2.PartnerInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.ISGetCardBrandListener;
import com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentCallback;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentInfo;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.CustomSheet;
import com.samsung.android.sdk.samsungpay.v2.service.ISUserInfoCallback;
import com.samsung.android.sdk.samsungpay.v2.service.RequestType;
import java.util.List;

/* loaded from: classes2.dex */
public interface ISPaymentManager extends IInterface {

    public static class Default implements ISPaymentManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public void getFastCheckoutStatus(PartnerInfo partnerInfo, ISStatusListener iSStatusListener) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public void initiateSession(PartnerInfo partnerInfo, PaymentInfo paymentInfo, ISPaymentCallback iSPaymentCallback) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public void initiateSessionWithSheetInfo(PartnerInfo partnerInfo, ISPaymentCallback iSPaymentCallback, CustomSheetPaymentInfo customSheetPaymentInfo, RequestType[] requestTypeArr, ISUserInfoCallback iSUserInfoCallback) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public int invalidAddress(int i) throws RemoteException {
            return 0;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public boolean isSamsungPaySetupCompleted() throws RemoteException {
            return false;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public List<CardInfo> requestCardType(Bundle bundle, PartnerInfo partnerInfo, ISGetCardBrandListener iSGetCardBrandListener) throws RemoteException {
            return null;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public void startSimplePay(PartnerInfo partnerInfo, CardInfo cardInfo, ISStatusListener iSStatusListener) throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public void terminateSession() throws RemoteException {
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public int updateAmount(PaymentInfo.Amount amount) throws RemoteException {
            return 0;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public int updateSheet(CustomSheet customSheet) throws RemoteException {
            return 0;
        }

        @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
        public int updateSheetWithCustomMessage(CustomSheet customSheet, int i, String str) throws RemoteException {
            return 0;
        }
    }

    void getFastCheckoutStatus(PartnerInfo partnerInfo, ISStatusListener iSStatusListener) throws RemoteException;

    void initiateSession(PartnerInfo partnerInfo, PaymentInfo paymentInfo, ISPaymentCallback iSPaymentCallback) throws RemoteException;

    void initiateSessionWithSheetInfo(PartnerInfo partnerInfo, ISPaymentCallback iSPaymentCallback, CustomSheetPaymentInfo customSheetPaymentInfo, RequestType[] requestTypeArr, ISUserInfoCallback iSUserInfoCallback) throws RemoteException;

    int invalidAddress(int i) throws RemoteException;

    boolean isSamsungPaySetupCompleted() throws RemoteException;

    List<CardInfo> requestCardType(Bundle bundle, PartnerInfo partnerInfo, ISGetCardBrandListener iSGetCardBrandListener) throws RemoteException;

    void startSimplePay(PartnerInfo partnerInfo, CardInfo cardInfo, ISStatusListener iSStatusListener) throws RemoteException;

    void terminateSession() throws RemoteException;

    int updateAmount(PaymentInfo.Amount amount) throws RemoteException;

    int updateSheet(CustomSheet customSheet) throws RemoteException;

    int updateSheetWithCustomMessage(CustomSheet customSheet, int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ISPaymentManager {
        private static final String DESCRIPTOR = "com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager";
        static final int TRANSACTION_getFastCheckoutStatus = 11;
        static final int TRANSACTION_initiateSession = 1;
        static final int TRANSACTION_initiateSessionWithSheetInfo = 8;
        static final int TRANSACTION_invalidAddress = 4;
        static final int TRANSACTION_isSamsungPaySetupCompleted = 6;
        static final int TRANSACTION_requestCardType = 5;
        static final int TRANSACTION_startSimplePay = 7;
        static final int TRANSACTION_terminateSession = 2;
        static final int TRANSACTION_updateAmount = 3;
        static final int TRANSACTION_updateSheet = 9;
        static final int TRANSACTION_updateSheetWithCustomMessage = 10;

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
                    initiateSession(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PaymentInfo.CREATOR.createFromParcel(parcel) : null, ISPaymentCallback.Stub.asInterface(parcel.readStrongBinder()));
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
                    List<CardInfo> requestCardType = requestCardType(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, ISGetCardBrandListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeTypedList(requestCardType);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isSamsungPaySetupCompleted = isSamsungPaySetupCompleted();
                    parcel2.writeNoException();
                    parcel2.writeInt(isSamsungPaySetupCompleted ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    startSimplePay(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? CardInfo.CREATOR.createFromParcel(parcel) : null, ISStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    initiateSessionWithSheetInfo(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, ISPaymentCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? CustomSheetPaymentInfo.CREATOR.createFromParcel(parcel) : null, (RequestType[]) parcel.createTypedArray(RequestType.CREATOR), ISUserInfoCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int updateSheet = updateSheet(parcel.readInt() != 0 ? CustomSheet.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(updateSheet);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int updateSheetWithCustomMessage = updateSheetWithCustomMessage(parcel.readInt() != 0 ? CustomSheet.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(updateSheetWithCustomMessage);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    getFastCheckoutStatus(parcel.readInt() != 0 ? PartnerInfo.CREATOR.createFromParcel(parcel) : null, ISStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
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

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
            public void initiateSession(PartnerInfo partnerInfo, PaymentInfo paymentInfo, ISPaymentCallback iSPaymentCallback) throws RemoteException {
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
                    if (paymentInfo != null) {
                        obtain.writeInt(1);
                        paymentInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSPaymentCallback != null ? iSPaymentCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initiateSession(partnerInfo, paymentInfo, iSPaymentCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
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

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
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

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
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

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
            public List<CardInfo> requestCardType(Bundle bundle, PartnerInfo partnerInfo, ISGetCardBrandListener iSGetCardBrandListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (partnerInfo != null) {
                        obtain.writeInt(1);
                        partnerInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSGetCardBrandListener != null ? iSGetCardBrandListener.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestCardType(bundle, partnerInfo, iSGetCardBrandListener);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(CardInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
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

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
            public void startSimplePay(PartnerInfo partnerInfo, CardInfo cardInfo, ISStatusListener iSStatusListener) throws RemoteException {
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
                    if (cardInfo != null) {
                        obtain.writeInt(1);
                        cardInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iSStatusListener != null ? iSStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startSimplePay(partnerInfo, cardInfo, iSStatusListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
            public void initiateSessionWithSheetInfo(PartnerInfo partnerInfo, ISPaymentCallback iSPaymentCallback, CustomSheetPaymentInfo customSheetPaymentInfo, RequestType[] requestTypeArr, ISUserInfoCallback iSUserInfoCallback) throws RemoteException {
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
                    obtain.writeStrongBinder(iSPaymentCallback != null ? iSPaymentCallback.asBinder() : null);
                    if (customSheetPaymentInfo != null) {
                        obtain.writeInt(1);
                        customSheetPaymentInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedArray(requestTypeArr, 0);
                    obtain.writeStrongBinder(iSUserInfoCallback != null ? iSUserInfoCallback.asBinder() : null);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initiateSessionWithSheetInfo(partnerInfo, iSPaymentCallback, customSheetPaymentInfo, requestTypeArr, iSUserInfoCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
            public int updateSheet(CustomSheet customSheet) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (customSheet != null) {
                        obtain.writeInt(1);
                        customSheet.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateSheet(customSheet);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
            public int updateSheetWithCustomMessage(CustomSheet customSheet, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (customSheet != null) {
                        obtain.writeInt(1);
                        customSheet.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateSheetWithCustomMessage(customSheet, i, str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.samsung.android.sdk.samsungpay.v2.payment.ISPaymentManager
            public void getFastCheckoutStatus(PartnerInfo partnerInfo, ISStatusListener iSStatusListener) throws RemoteException {
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
                    obtain.writeStrongBinder(iSStatusListener != null ? iSStatusListener.asBinder() : null);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getFastCheckoutStatus(partnerInfo, iSStatusListener);
                    } else {
                        obtain2.readException();
                    }
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
