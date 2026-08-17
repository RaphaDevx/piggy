package com.samsung.android.sdk.samsungpay.v2;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.samsungpay.v2.SpaySdk;
import com.samsung.android.sdk.samsungpay.v2.card.AddCardInfo;
import com.samsung.android.sdk.samsungpay.v2.card.Card;
import com.samsung.android.sdk.samsungpay.v2.card.IdvVerifyInfo;
import com.samsung.android.sdk.samsungpay.v2.card.TransitCard;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager;
import com.samsung.android.sdk.samsungpay.v2.payment.sheet.AmountConstants;
import com.samsung.android.sdk.samsungpay.v2.service.RequestType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class ApiLevelTable {
    public static final String TAG = "SPAYSDK:ApiLevelTable";
    private static ApiLevelTable sInstance;
    private static final Class[] STATIC_FIELD_MAPPING_CLASS = {SpaySdk.class, PaymentManager.class, WatchManager.class, AddCardInfo.class, Card.class, TransitCard.class, IdvVerifyInfo.class, AmountConstants.class, RequestType.ALL.getClass()};
    private static final Class[] VARIABLE_CHECKING_CLASS = {AddCardInfo.class};
    private HashMap<String, FieldInfo> mStaticFieldTable = new HashMap<>();
    private HashMap<String, ArrayList<String>> mClassVariableTable = new HashMap<>();
    private SpaySdk.SdkApiLevel mPartnerDefinedApiLevel = SpaySdk.SdkApiLevel.LEVEL_UNKNOWN;

    private static class FieldInfo {
        SpaySdk.SdkApiLevel apiLevel;
        boolean checkValue;
        String name;

        public FieldInfo(String str, ParamInfo paramInfo) {
            this.apiLevel = paramInfo.since();
            this.checkValue = paramInfo.checkValue();
            this.name = str;
        }

        public String toString() {
            return this.name + " (since: " + this.apiLevel + ")";
        }
    }

    private ApiLevelTable() {
        for (Class cls : STATIC_FIELD_MAPPING_CLASS) {
            createStaticFieldTable(cls);
        }
        for (Class cls2 : VARIABLE_CHECKING_CLASS) {
            createClassVariableTable(cls2);
        }
    }

    public static synchronized ApiLevelTable getInstance() {
        ApiLevelTable apiLevelTable;
        synchronized (ApiLevelTable.class) {
            if (sInstance == null) {
                sInstance = new ApiLevelTable();
            }
            apiLevelTable = sInstance;
        }
        return apiLevelTable;
    }

    public void setPartnerDefinedApiLevel(SpaySdk.SdkApiLevel sdkApiLevel) {
        this.mPartnerDefinedApiLevel = sdkApiLevel;
    }

    public SpaySdk.SdkApiLevel getPartnerDefinedApiLevel() {
        return this.mPartnerDefinedApiLevel;
    }

    private void createStaticFieldTable(Class cls) {
        String createStringKeyForEnum;
        for (Field field : cls.getDeclaredFields()) {
            ParamInfo paramInfo = (ParamInfo) field.getAnnotation(ParamInfo.class);
            if (paramInfo != null) {
                try {
                    if (field.getType() == String.class) {
                        createStringKeyForEnum = field.get(cls).toString();
                    } else if (field.getType().isEnum()) {
                        createStringKeyForEnum = createStringKeyForEnum(field.get(cls));
                    }
                    FieldInfo fieldInfo = new FieldInfo(field.getName(), paramInfo);
                    if (this.mStaticFieldTable.containsKey(createStringKeyForEnum)) {
                        throwException("Field " + fieldInfo + " with value '" + createStringKeyForEnum + "' is defined twice");
                    } else {
                        this.mStaticFieldTable.put(createStringKeyForEnum, fieldInfo);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createClassVariableTable(Class cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.getAnnotation(CheckApiLevel.class) != null) {
                    Log.v(TAG, "createClassVariableTable - " + cls.getSimpleName() + ": field: " + field.getName());
                    if (field.getType() != String.class && field.getType() != Bundle.class) {
                        throwException("Only String and Bundle variable are supported");
                    } else {
                        arrayList.add(field.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.mClassVariableTable.put(cls.getSimpleName(), arrayList);
    }

    public boolean valueNotDefinedInPartnerApiLevel(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass().isEnum()) {
            obj = createStringKeyForEnum(obj);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str) && this.mStaticFieldTable.containsKey(str)) {
                FieldInfo fieldInfo = this.mStaticFieldTable.get(str);
                if (fieldInfo.apiLevel.compareTo(this.mPartnerDefinedApiLevel) > 0) {
                    Log.e(TAG, "Parameter: " + fieldInfo.toString() + " is not defined in " + this.mPartnerDefinedApiLevel);
                    return true;
                }
            }
        } else if (obj instanceof Bundle) {
            Bundle bundle = (Bundle) obj;
            for (String str2 : bundle.keySet()) {
                FieldInfo fieldInfo2 = this.mStaticFieldTable.get(str2);
                if (fieldInfo2 != null) {
                    if (valueNotDefinedInPartnerApiLevel(str2)) {
                        return true;
                    }
                    if (fieldInfo2.checkValue) {
                        Log.v(TAG, "Checking value for " + fieldInfo2);
                        if (valueNotDefinedInPartnerApiLevel(bundle.getString(str2, null))) {
                            return true;
                        }
                    } else {
                        continue;
                    }
                }
            }
        } else {
            throwException("Not checking support for " + obj.getClass());
        }
        return false;
    }

    public boolean findFieldContainsNotDefinedValue(Object obj) {
        if (obj == null) {
            Log.e(TAG, "findFieldContainsNotDefinedValue - param is NULL");
            return false;
        }
        if (!this.mClassVariableTable.containsKey(obj.getClass().getSimpleName())) {
            throwException("Not checking support for " + obj.getClass());
            return false;
        }
        Class<?> cls = obj.getClass();
        try {
            Iterator<String> it = this.mClassVariableTable.get(cls.getSimpleName()).iterator();
            while (it.hasNext()) {
                Field declaredField = cls.getDeclaredField(it.next());
                declaredField.setAccessible(true);
                if (declaredField.getType() == String.class || declaredField.getType() == Bundle.class) {
                    if (valueNotDefinedInPartnerApiLevel(declaredField.get(obj))) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void throwException(String str) {
        Log.e(TAG, str);
    }

    private String createStringKeyForEnum(Object obj) {
        if (obj == null || !obj.getClass().isEnum()) {
            throwException(obj + "is not an enum");
            return "";
        }
        return obj.getClass().getSimpleName() + "." + obj.toString();
    }
}
