package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public final class BinaryKeysetWriter implements KeysetWriter {
    private final OutputStream outputStream;

    private BinaryKeysetWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream) {
        return new BinaryKeysetWriter(outputStream);
    }

    public static KeysetWriter withFile(File file) throws IOException {
        return new BinaryKeysetWriter(new FileOutputStream(file));
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) throws IOException {
        this.outputStream.write(keyset.toByteArray());
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset) throws IOException {
        this.outputStream.write(encryptedKeyset.toByteArray());
    }
}
