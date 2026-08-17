package com.salesforce.marketingcloud.sfmcsdk.components.encryption;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import androidx.collection.ArraySet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: EncryptedSharedPreferences.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\f\u0018\u0000 ;2\u00020\u0001:\u0003;<=B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0011\u001a\u00020\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0096\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u0004H\u0002J\b\u0010 \u001a\u00020!H\u0016J\u0012\u0010\"\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0002J&\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040$2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010%\u001a\u00020\u000bH\u0002J\u0010\u0010&\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u000bH\u0002J\b\u0010'\u001a\u00020\u000bH\u0002J\u0014\u0010(\u001a\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0002\b\u00030)H\u0016J\u001a\u0010*\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u00020\u0014H\u0016J\u0014\u0010,\u001a\u0004\u0018\u00010\u00172\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0002J\u001a\u0010-\u001a\u00020.2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u00020.H\u0016J\u001a\u0010/\u001a\u0002002\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u000200H\u0016J\u001a\u00101\u001a\u0002022\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u000202H\u0016J\u001e\u00103\u001a\u0004\u0018\u00010\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\b\u0010+\u001a\u0004\u0018\u00010\u0004H\u0016J*\u00104\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u000e\u00105\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0007H\u0016J\u0010\u00106\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\u0010\u00107\u001a\u00020\u00122\u0006\u00108\u001a\u00020\u000eH\u0016J\u0010\u00109\u001a\u00020\u00122\u0006\u00108\u001a\u00020\u000eH\u0016J\u0006\u0010:\u001a\u00020\u0014R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptedSharedPreferences;", "Landroid/content/SharedPreferences;", "mSharedPreferences", "mEncryptionKey", "", "(Landroid/content/SharedPreferences;Ljava/lang/String;)V", "allKeys", "", "getAllKeys", "()Ljava/util/Set;", "iv", "", "mListeners", "", "Landroid/content/SharedPreferences$OnSharedPreferenceChangeListener;", "getMListeners", "()Ljava/util/List;", "clearInstallationPrefs", "", "contains", "", "key", "decryptBuffer", "", "buffer", "Ljava/nio/ByteBuffer;", "decryptKey", "encryptedKey", "decryptString", "decryptStringSet", "decryptValue", "encryptedValue", "edit", "Landroid/content/SharedPreferences$Editor;", "encryptKey", "encryptKeyValuePair", "Landroid/util/Pair;", "value", "encryptValue", "generateKeyIv", "getAll", "", "getBoolean", "defValue", "getDecryptedObject", "getFloat", "", "getInt", "", "getLong", "", "getString", "getStringSet", "defValues", "isReservedKey", "registerOnSharedPreferenceChangeListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "unregisterOnSharedPreferenceChangeListener", "verifyEncryption", "Companion", "Editor", "EncryptedType", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class EncryptedSharedPreferences implements SharedPreferences {
    private static final int BYTE_BYTES = 1;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int FLOAT_BYTES = 4;
    private static final int INTEGER_BYTES = 4;
    private static final String KEY_IV = "__iv__";
    private static final int LONG_BYTES = 8;
    private static final String NULL_VALUE = "__NULL__";
    private static final String TAG = "EncryptedSharedPrefs";
    private final byte[] iv;
    private final String mEncryptionKey;
    private final List<SharedPreferences.OnSharedPreferenceChangeListener> mListeners;
    private final SharedPreferences mSharedPreferences;

    /* compiled from: EncryptedSharedPreferences.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EncryptedType.values().length];
            try {
                iArr[EncryptedType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EncryptedType.INT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EncryptedType.LONG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EncryptedType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[EncryptedType.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[EncryptedType.STRING_SET.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ EncryptedSharedPreferences(SharedPreferences sharedPreferences, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(sharedPreferences, str);
    }

    private EncryptedSharedPreferences(SharedPreferences sharedPreferences, String str) {
        this.mSharedPreferences = sharedPreferences;
        this.mEncryptionKey = str;
        this.mListeners = new ArrayList();
        this.iv = generateKeyIv();
    }

    public final List<SharedPreferences.OnSharedPreferenceChangeListener> getMListeners() {
        return this.mListeners;
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        HashMap hashMap = new HashMap();
        Map<String, ?> all = this.mSharedPreferences.getAll();
        Intrinsics.checkNotNullExpressionValue(all, "getAll(...)");
        Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (!isReservedKey(key)) {
                String decryptKey = decryptKey(key);
                hashMap.put(decryptKey, getDecryptedObject(decryptKey));
            }
        }
        return hashMap;
    }

    public final Set<String> getAllKeys() {
        HashSet hashSet = new HashSet();
        Map<String, ?> all = this.mSharedPreferences.getAll();
        Intrinsics.checkNotNullExpressionValue(all, "getAll(...)");
        Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (!isReservedKey(key)) {
                Intrinsics.checkNotNull(key);
                hashSet.add(key);
            }
        }
        return hashSet;
    }

    @Override // android.content.SharedPreferences
    public String getString(String key, String defValue) {
        Object decryptedObject = getDecryptedObject(key);
        String str = decryptedObject instanceof String ? (String) decryptedObject : null;
        return str == null ? defValue : str;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String key, Set<String> defValues) {
        Object decryptedObject = getDecryptedObject(key);
        if (!(decryptedObject instanceof Set) || ((Collection) decryptedObject).isEmpty()) {
            return defValues;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : (Iterable) decryptedObject) {
            if (obj instanceof String) {
                arrayList.add(obj);
            }
        }
        return CollectionsKt.toSet(arrayList);
    }

    @Override // android.content.SharedPreferences
    public int getInt(String key, int defValue) {
        Object decryptedObject = getDecryptedObject(key);
        Integer num = decryptedObject instanceof Integer ? (Integer) decryptedObject : null;
        return num != null ? num.intValue() : defValue;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String key, long defValue) {
        Object decryptedObject = getDecryptedObject(key);
        Long l = decryptedObject instanceof Long ? (Long) decryptedObject : null;
        return l != null ? l.longValue() : defValue;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String key, float defValue) {
        Object decryptedObject = getDecryptedObject(key);
        Float f = decryptedObject instanceof Float ? (Float) decryptedObject : null;
        return f != null ? f.floatValue() : defValue;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String key, boolean defValue) {
        Object decryptedObject = getDecryptedObject(key);
        Boolean bool = decryptedObject instanceof Boolean ? (Boolean) decryptedObject : null;
        return bool != null ? bool.booleanValue() : defValue;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String key) {
        if (isReservedKey(key)) {
            throw new SecurityException(key + " is a reserved key for the encryption keyset.");
        }
        return this.mSharedPreferences.contains(encryptKey(key));
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
        return new Editor(this, edit);
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mListeners.add(listener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mListeners.remove(listener);
    }

    private final Object getDecryptedObject(String key) {
        if (key == null) {
            key = NULL_VALUE;
        }
        if (isReservedKey(key)) {
            throw new SecurityException(key + " is a reserved key for the encryption keyset.");
        }
        String string = this.mSharedPreferences.getString(encryptKey(key), null);
        if (string == null) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(decryptValue(string));
        wrap.position(0);
        Intrinsics.checkNotNull(wrap);
        return decryptBuffer(wrap);
    }

    private final Object decryptBuffer(ByteBuffer buffer) {
        EncryptedType fromId = EncryptedType.INSTANCE.fromId(buffer.getInt());
        switch (fromId == null ? -1 : WhenMappings.$EnumSwitchMapping$0[fromId.ordinal()]) {
            case 1:
                return decryptString(buffer);
            case 2:
                return Integer.valueOf(buffer.getInt());
            case 3:
                return Long.valueOf(buffer.getLong());
            case 4:
                return Float.valueOf(buffer.getFloat());
            case 5:
                return Boolean.valueOf(buffer.get() != 0);
            case 6:
                return decryptStringSet(buffer);
            default:
                throw new IllegalArgumentException("Unsupported type: " + fromId);
        }
    }

    private final String decryptString(ByteBuffer buffer) {
        int i = buffer.getInt();
        ByteBuffer slice = buffer.slice();
        buffer.limit(i);
        String charBuffer = StandardCharsets.UTF_8.decode(slice).toString();
        Intrinsics.checkNotNullExpressionValue(charBuffer, "toString(...)");
        if (Intrinsics.areEqual(charBuffer, NULL_VALUE)) {
            return null;
        }
        return charBuffer;
    }

    private final Set<String> decryptStringSet(ByteBuffer buffer) {
        ArraySet arraySet = new ArraySet();
        while (buffer.hasRemaining()) {
            int i = buffer.getInt();
            ByteBuffer slice = buffer.slice();
            slice.limit(i);
            buffer.position(buffer.position() + i);
            arraySet.add(StandardCharsets.UTF_8.decode(slice).toString());
        }
        if (arraySet.size() == 1 && Intrinsics.areEqual(NULL_VALUE, arraySet.valueAt(0))) {
            return null;
        }
        return arraySet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String encryptKey(String key) {
        if (key == null) {
            key = NULL_VALUE;
        }
        String encrypt = Encryptor.encrypt(key, this.mEncryptionKey, this.iv);
        Intrinsics.checkNotNullExpressionValue(encrypt, "encrypt(...)");
        return encrypt;
    }

    private final String decryptKey(String encryptedKey) {
        String decrypt = Encryptor.decrypt(encryptedKey, this.mEncryptionKey);
        if (Intrinsics.areEqual(decrypt, NULL_VALUE)) {
            return null;
        }
        return decrypt;
    }

    private final String encryptValue(byte[] value) {
        String encrypt = Encryptor.encrypt(Base64.encodeToString(value, 2), this.mEncryptionKey);
        Intrinsics.checkNotNullExpressionValue(encrypt, "encrypt(...)");
        return encrypt;
    }

    private final byte[] decryptValue(String encryptedValue) {
        byte[] decode = Base64.decode(Encryptor.decrypt(encryptedValue, this.mEncryptionKey), 2);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        return decode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<String, String> encryptKeyValuePair(String key, byte[] value) {
        return new Pair<>(encryptKey(key), encryptValue(value));
    }

    private final byte[] generateKeyIv() throws NoSuchAlgorithmException {
        String string = this.mSharedPreferences.getString(KEY_IV, null);
        if (string != null) {
            byte[] decode = Base64.decode(string, 2);
            Intrinsics.checkNotNull(decode);
            return decode;
        }
        byte[] generateInitVector = Encryptor.generateInitVector();
        this.mSharedPreferences.edit().putString(KEY_IV, Base64.encodeToString(generateInitVector, 2)).apply();
        Intrinsics.checkNotNull(generateInitVector);
        return generateInitVector;
    }

    public final boolean isReservedKey(String key) {
        return Intrinsics.areEqual(KEY_IV, key);
    }

    public final boolean verifyEncryption() {
        try {
            if (!getAllKeys().isEmpty()) {
                if (getAllKeys().isEmpty()) {
                    return false;
                }
                if (this.mSharedPreferences.getString(encryptKey("registrationId"), null) == null) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Exception while encrypting registrationId", e);
            return false;
        }
    }

    public final void clearInstallationPrefs() {
        SharedPreferences.Editor edit = edit();
        Intrinsics.checkNotNull(edit, "null cannot be cast to non-null type com.salesforce.marketingcloud.sfmcsdk.components.encryption.EncryptedSharedPreferences.Editor");
        Editor editor = (Editor) edit;
        Iterator<T> it = getAllKeys().iterator();
        while (it.hasNext()) {
            editor.remove((String) it.next(), false);
        }
        editor.apply();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: EncryptedSharedPreferences.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0082\u0081\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptedSharedPreferences$EncryptedType;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "STRING", "STRING_SET", "INT", "LONG", "FLOAT", "BOOLEAN", "Companion", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class EncryptedType {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ EncryptedType[] $VALUES;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE;
        private final int id;
        public static final EncryptedType STRING = new EncryptedType("STRING", 0, 0);
        public static final EncryptedType STRING_SET = new EncryptedType("STRING_SET", 1, 1);
        public static final EncryptedType INT = new EncryptedType("INT", 2, 2);
        public static final EncryptedType LONG = new EncryptedType("LONG", 3, 3);
        public static final EncryptedType FLOAT = new EncryptedType("FLOAT", 4, 4);
        public static final EncryptedType BOOLEAN = new EncryptedType("BOOLEAN", 5, 5);

        private static final /* synthetic */ EncryptedType[] $values() {
            return new EncryptedType[]{STRING, STRING_SET, INT, LONG, FLOAT, BOOLEAN};
        }

        public static EnumEntries<EncryptedType> getEntries() {
            return $ENTRIES;
        }

        public static EncryptedType valueOf(String str) {
            return (EncryptedType) Enum.valueOf(EncryptedType.class, str);
        }

        public static EncryptedType[] values() {
            return (EncryptedType[]) $VALUES.clone();
        }

        private EncryptedType(String str, int i, int i2) {
            this.id = i2;
        }

        public final int getId() {
            return this.id;
        }

        static {
            EncryptedType[] $values = $values();
            $VALUES = $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
            INSTANCE = new Companion(null);
        }

        /* compiled from: EncryptedSharedPreferences.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptedSharedPreferences$EncryptedType$Companion;", "", "()V", "fromId", "Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptedSharedPreferences$EncryptedType;", "id", "", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final EncryptedType fromId(int id) {
                if (id == 0) {
                    return EncryptedType.STRING;
                }
                if (id == 1) {
                    return EncryptedType.STRING_SET;
                }
                if (id == 2) {
                    return EncryptedType.INT;
                }
                if (id == 3) {
                    return EncryptedType.LONG;
                }
                if (id == 4) {
                    return EncryptedType.FLOAT;
                }
                if (id != 5) {
                    return null;
                }
                return EncryptedType.BOOLEAN;
            }
        }
    }

    /* compiled from: EncryptedSharedPreferences.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u0001H\u0016J\b\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\fH\u0002J\u001a\u0010\u0012\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u001a\u0010\u0015\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u0016H\u0002J\u001a\u0010\u0017\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u001cH\u0016J\u001c\u0010\u001d\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\nH\u0016J\"\u0010\u001e\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010 H\u0016J\u0012\u0010!\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016J\u0018\u0010!\u001a\u00020\u00012\b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\"\u001a\u00020\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptedSharedPreferences$Editor;", "Landroid/content/SharedPreferences$Editor;", "mEncryptedSharedPreferences", "Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptedSharedPreferences;", "mEditor", "(Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptedSharedPreferences;Landroid/content/SharedPreferences$Editor;)V", "mClearRequested", "Ljava/util/concurrent/atomic/AtomicBoolean;", "mKeysChanged", "", "", "apply", "", "clear", "clearKeysIfNeeded", "commit", "", "notifyListeners", "putBoolean", "key", "value", "putEncryptedObject", "", "putFloat", "", "putInt", "", "putLong", "", "putString", "putStringSet", "values", "", "remove", "encryptKey", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Editor implements SharedPreferences.Editor {
        private final AtomicBoolean mClearRequested;
        private final SharedPreferences.Editor mEditor;
        private final EncryptedSharedPreferences mEncryptedSharedPreferences;
        private final List<String> mKeysChanged;

        public Editor(EncryptedSharedPreferences mEncryptedSharedPreferences, SharedPreferences.Editor mEditor) {
            Intrinsics.checkNotNullParameter(mEncryptedSharedPreferences, "mEncryptedSharedPreferences");
            Intrinsics.checkNotNullParameter(mEditor, "mEditor");
            this.mEncryptedSharedPreferences = mEncryptedSharedPreferences;
            this.mEditor = mEditor;
            this.mClearRequested = new AtomicBoolean(false);
            this.mKeysChanged = new CopyOnWriteArrayList();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String key, String value) {
            if (value == null) {
                value = EncryptedSharedPreferences.NULL_VALUE;
            }
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            byte[] bytes = value.getBytes(UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
            allocate.putInt(EncryptedType.STRING.getId());
            allocate.putInt(bytes.length);
            allocate.put(bytes);
            byte[] array = allocate.array();
            Intrinsics.checkNotNullExpressionValue(array, "array(...)");
            putEncryptedObject(key, array);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
            ArrayList listOf;
            if (values == null) {
                byte[] bytes = EncryptedSharedPreferences.NULL_VALUE.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                listOf = CollectionsKt.listOf(bytes);
            } else {
                Set<String> set = values;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
                for (String str : set) {
                    Charset UTF_8 = StandardCharsets.UTF_8;
                    Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
                    byte[] bytes2 = str.getBytes(UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                    arrayList.add(bytes2);
                }
                listOf = arrayList;
            }
            List<byte[]> list = listOf;
            Iterator it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                i += ((byte[]) it.next()).length;
            }
            ByteBuffer allocate = ByteBuffer.allocate(i + ((listOf.size() + 1) * 4));
            allocate.putInt(EncryptedType.STRING_SET.getId());
            for (byte[] bArr : list) {
                allocate.putInt(bArr.length);
                allocate.put(bArr);
            }
            byte[] array = allocate.array();
            Intrinsics.checkNotNullExpressionValue(array, "array(...)");
            putEncryptedObject(key, array);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String key, int value) {
            ByteBuffer allocate = ByteBuffer.allocate(8);
            allocate.putInt(EncryptedType.INT.getId());
            allocate.putInt(value);
            byte[] array = allocate.array();
            Intrinsics.checkNotNullExpressionValue(array, "array(...)");
            putEncryptedObject(key, array);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String key, long value) {
            ByteBuffer allocate = ByteBuffer.allocate(12);
            allocate.putInt(EncryptedType.LONG.getId());
            allocate.putLong(value);
            byte[] array = allocate.array();
            Intrinsics.checkNotNullExpressionValue(array, "array(...)");
            putEncryptedObject(key, array);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String key, float value) {
            ByteBuffer allocate = ByteBuffer.allocate(8);
            allocate.putInt(EncryptedType.FLOAT.getId());
            allocate.putFloat(value);
            byte[] array = allocate.array();
            Intrinsics.checkNotNullExpressionValue(array, "array(...)");
            putEncryptedObject(key, array);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String key, boolean value) {
            ByteBuffer allocate = ByteBuffer.allocate(5);
            allocate.putInt(EncryptedType.BOOLEAN.getId());
            allocate.put(value ? (byte) 1 : (byte) 0);
            byte[] array = allocate.array();
            Intrinsics.checkNotNullExpressionValue(array, "array(...)");
            putEncryptedObject(key, array);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String key) {
            return remove(key, true);
        }

        public final SharedPreferences.Editor remove(String key, boolean encryptKey) {
            if (this.mEncryptedSharedPreferences.isReservedKey(key)) {
                throw new SecurityException(key + " is a reserved key for the encryption keyset.");
            }
            this.mEditor.remove(encryptKey ? this.mEncryptedSharedPreferences.encryptKey(key) : key);
            this.mKeysChanged.remove(key);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.mClearRequested.set(true);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            clearKeysIfNeeded();
            try {
                return this.mEditor.commit();
            } finally {
                notifyListeners();
                this.mKeysChanged.clear();
            }
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            clearKeysIfNeeded();
            this.mEditor.apply();
            notifyListeners();
            this.mKeysChanged.clear();
        }

        private final void clearKeysIfNeeded() {
            if (this.mClearRequested.getAndSet(false)) {
                for (String str : this.mEncryptedSharedPreferences.getAllKeys()) {
                    if (!this.mKeysChanged.contains(str) && !this.mEncryptedSharedPreferences.isReservedKey(str)) {
                        remove(str, false);
                    }
                }
            }
        }

        private final void putEncryptedObject(String key, byte[] value) {
            if (this.mEncryptedSharedPreferences.isReservedKey(key)) {
                throw new SecurityException(key + " is a reserved key for the encryption keyset.");
            }
            this.mKeysChanged.add(key);
            if (key == null) {
                key = EncryptedSharedPreferences.NULL_VALUE;
            }
            Pair encryptKeyValuePair = this.mEncryptedSharedPreferences.encryptKeyValuePair(key, value);
            this.mEditor.putString((String) encryptKeyValuePair.first, (String) encryptKeyValuePair.second);
        }

        private final void notifyListeners() {
            for (SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : this.mEncryptedSharedPreferences.getMListeners()) {
                Iterator<String> it = this.mKeysChanged.iterator();
                while (it.hasNext()) {
                    onSharedPreferenceChangeListener.onSharedPreferenceChanged(this.mEncryptedSharedPreferences, it.next());
                }
            }
        }
    }

    /* compiled from: EncryptedSharedPreferences.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/components/encryption/EncryptedSharedPreferences$Companion;", "", "()V", "BYTE_BYTES", "", "FLOAT_BYTES", "INTEGER_BYTES", "KEY_IV", "", "LONG_BYTES", "NULL_VALUE", "TAG", "create", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "fileName", "encryptionKey", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SharedPreferences create(Context context, String fileName, String encryptionKey) throws NoSuchAlgorithmException {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(encryptionKey, "encryptionKey");
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(fileName, 0);
            Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
            return new EncryptedSharedPreferences(sharedPreferences, encryptionKey, null);
        }
    }
}
