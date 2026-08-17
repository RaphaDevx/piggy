package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.util.Log;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.KeysetManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Hex;
import com.google.crypto.tink.subtle.Random;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
public final class AndroidKeysetManager {
    private static final String TAG = "AndroidKeysetManager";
    private final KeyTemplate keyTemplate;
    private KeysetManager keysetManager;
    private final Aead masterKey;
    private final KeysetReader reader;
    private final boolean useKeystore;
    private final KeysetWriter writer;

    private static boolean isAtLeastM() {
        return true;
    }

    /* synthetic */ AndroidKeysetManager(Builder builder, AnonymousClass1 anonymousClass1) throws GeneralSecurityException, IOException {
        this(builder);
    }

    private AndroidKeysetManager(Builder builder) throws GeneralSecurityException, IOException {
        KeysetReader keysetReader = builder.reader;
        this.reader = keysetReader;
        if (keysetReader == null) {
            throw new IllegalArgumentException("need to specify where to read the keyset from with Builder#withSharedPref");
        }
        KeysetWriter keysetWriter = builder.writer;
        this.writer = keysetWriter;
        if (keysetWriter == null) {
            throw new IllegalArgumentException("need to specify where to write the keyset to with Builder#withSharedPref");
        }
        if (builder.useKeystore && builder.masterKeyUri == null) {
            throw new IllegalArgumentException("need a master key URI, please set it with Builder#masterKeyUri");
        }
        this.useKeystore = builder.useKeystore && verifyAndroidKeystore();
        if (!shouldUseKeystore()) {
            this.masterKey = null;
        } else {
            this.masterKey = AndroidKeystoreKmsClient.getOrGenerateNewAeadKey(builder.masterKeyUri);
        }
        this.keyTemplate = builder.keyTemplate;
        this.keysetManager = readOrGenerateNewKeyset();
    }

    public static final class Builder {
        private KeysetReader reader = null;
        private KeysetWriter writer = null;
        private String masterKeyUri = null;
        private boolean useKeystore = true;
        private KeyTemplate keyTemplate = null;

        public Builder withSharedPref(Context context, String str, String str2) throws IOException {
            if (context == null) {
                throw new IllegalArgumentException("need an Android context");
            }
            if (str == null) {
                throw new IllegalArgumentException("need a keyset name");
            }
            this.reader = new SharedPrefKeysetReader(context, str, str2);
            this.writer = new SharedPrefKeysetWriter(context, str, str2);
            return this;
        }

        public Builder withMasterKeyUri(String str) {
            if (!str.startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                throw new IllegalArgumentException("key URI must start with android-keystore://");
            }
            this.masterKeyUri = str;
            return this;
        }

        @Deprecated
        public Builder withKeyTemplate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
            this.keyTemplate = KeyTemplate.create(keyTemplate.getTypeUrl(), keyTemplate.getValue().toByteArray(), AndroidKeysetManager.fromProto(keyTemplate.getOutputPrefixType()));
            return this;
        }

        public Builder withKeyTemplate(KeyTemplate keyTemplate) {
            this.keyTemplate = keyTemplate;
            return this;
        }

        public Builder doNotUseKeystore() {
            this.useKeystore = false;
            return this;
        }

        public AndroidKeysetManager build() throws GeneralSecurityException, IOException {
            return new AndroidKeysetManager(this, null);
        }
    }

    public synchronized KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        return this.keysetManager.getKeysetHandle();
    }

    @Deprecated
    public synchronized AndroidKeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager rotate = this.keysetManager.rotate(keyTemplate);
        this.keysetManager = rotate;
        write(rotate);
        return this;
    }

    @Deprecated
    public synchronized AndroidKeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager add = this.keysetManager.add(keyTemplate);
        this.keysetManager = add;
        write(add);
        return this;
    }

    public synchronized AndroidKeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager add = this.keysetManager.add(keyTemplate);
        this.keysetManager = add;
        write(add);
        return this;
    }

    public synchronized AndroidKeysetManager setPrimary(int i) throws GeneralSecurityException {
        KeysetManager primary = this.keysetManager.setPrimary(i);
        this.keysetManager = primary;
        write(primary);
        return this;
    }

    @Deprecated
    public synchronized AndroidKeysetManager promote(int i) throws GeneralSecurityException {
        return setPrimary(i);
    }

    public synchronized AndroidKeysetManager enable(int i) throws GeneralSecurityException {
        KeysetManager enable = this.keysetManager.enable(i);
        this.keysetManager = enable;
        write(enable);
        return this;
    }

    public synchronized AndroidKeysetManager disable(int i) throws GeneralSecurityException {
        KeysetManager disable = this.keysetManager.disable(i);
        this.keysetManager = disable;
        write(disable);
        return this;
    }

    public synchronized AndroidKeysetManager delete(int i) throws GeneralSecurityException {
        KeysetManager delete = this.keysetManager.delete(i);
        this.keysetManager = delete;
        write(delete);
        return this;
    }

    public synchronized AndroidKeysetManager destroy(int i) throws GeneralSecurityException {
        KeysetManager destroy = this.keysetManager.destroy(i);
        this.keysetManager = destroy;
        write(destroy);
        return this;
    }

    public synchronized boolean isUsingKeystore() {
        return shouldUseKeystore();
    }

    private KeysetManager readOrGenerateNewKeyset() throws GeneralSecurityException, IOException {
        try {
            return read();
        } catch (IOException e) {
            Log.i(TAG, "cannot read keyset: " + e);
            if (this.keyTemplate != null) {
                KeysetManager add = KeysetManager.withEmptyKeyset().add(this.keyTemplate);
                KeysetManager primary = add.setPrimary(add.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId());
                this.write(primary);
                return primary;
            }
            throw new GeneralSecurityException("cannot obtain keyset handle");
        }
    }

    private KeysetManager read() throws GeneralSecurityException, IOException {
        if (shouldUseKeystore()) {
            try {
                return KeysetManager.withKeysetHandle(KeysetHandle.read(this.reader, this.masterKey));
            } catch (InvalidProtocolBufferException | GeneralSecurityException e) {
                Log.i(TAG, "cannot decrypt keyset: " + e);
            }
        }
        KeysetHandle read = CleartextKeysetHandle.read(this.reader);
        if (this.shouldUseKeystore()) {
            read.write(this.writer, this.masterKey);
        }
        return KeysetManager.withKeysetHandle(read);
    }

    private void write(KeysetManager keysetManager) throws GeneralSecurityException {
        try {
            if (shouldUseKeystore()) {
                keysetManager.getKeysetHandle().write(this.writer, this.masterKey);
            } else {
                CleartextKeysetHandle.write(keysetManager.getKeysetHandle(), this.writer);
            }
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    private boolean shouldUseKeystore() {
        return this.useKeystore && isAtLeastM();
    }

    /* renamed from: com.google.crypto.tink.integration.android.AndroidKeysetManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTemplate.OutputPrefixType fromProto(OutputPrefixType outputPrefixType) {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i == 1) {
            return KeyTemplate.OutputPrefixType.TINK;
        }
        if (i == 2) {
            return KeyTemplate.OutputPrefixType.LEGACY;
        }
        if (i == 3) {
            return KeyTemplate.OutputPrefixType.RAW;
        }
        if (i == 4) {
            return KeyTemplate.OutputPrefixType.CRUNCHY;
        }
        throw new IllegalArgumentException("Unknown output prefix type");
    }

    private static boolean verifyAndroidKeystore() {
        if (!isAtLeastM()) {
            return false;
        }
        try {
            String concat = AndroidKeystoreKmsClient.PREFIX.concat(new String(Random.randBytes(16), Charset.forName("UTF-8")));
            Aead orGenerateNewAeadKey = AndroidKeystoreKmsClient.getOrGenerateNewAeadKey(concat);
            byte[] bArr = new byte[0];
            if (orGenerateNewAeadKey.decrypt(orGenerateNewAeadKey.encrypt(new byte[0], bArr), bArr).length != 0) {
                Log.i(TAG, "cannot use Android Keystore: encryption/decryption of empty message and empty aad returns incorrect results");
                return false;
            }
            byte[] randBytes = Random.randBytes(10);
            byte[] bArr2 = new byte[0];
            if (!Hex.encode(orGenerateNewAeadKey.decrypt(orGenerateNewAeadKey.encrypt(randBytes, bArr2), bArr2)).equals(Hex.encode(randBytes))) {
                Log.i(TAG, "cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns incorrect results");
                return false;
            }
            byte[] randBytes2 = Random.randBytes(10);
            byte[] randBytes3 = Random.randBytes(10);
            if (!Hex.encode(orGenerateNewAeadKey.decrypt(orGenerateNewAeadKey.encrypt(randBytes2, randBytes3), randBytes3)).equals(Hex.encode(randBytes2))) {
                Log.i(TAG, "cannot use Android Keystore: encryption/decryption of non-empty message and non-empty aad returns incorrect results");
                return false;
            }
            AndroidKeystoreKmsClient.delete(concat);
            return true;
        } catch (Exception e) {
            Log.i(TAG, "cannot use Android Keystore: " + e);
            return false;
        }
    }
}
