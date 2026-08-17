package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
final class StreamingAeadHelper implements StreamingAead {
    PrimitiveSet<StreamingAead> primitives;

    public StreamingAeadHelper(PrimitiveSet<StreamingAead> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet.getPrimary() == null) {
            throw new GeneralSecurityException("Missing primary primitive.");
        }
        this.primitives = primitiveSet;
    }

    @Override // com.google.crypto.tink.StreamingAead
    public WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return this.primitives.getPrimary().getPrimitive().newEncryptingChannel(writableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.StreamingAead
    public ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return new ReadableByteChannelDecrypter(this.primitives, readableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.StreamingAead
    public SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        return new SeekableByteChannelDecrypter(this.primitives, seekableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.StreamingAead
    public InputStream newDecryptingStream(InputStream inputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        return new InputStreamDecrypter(this.primitives, inputStream, bArr);
    }

    @Override // com.google.crypto.tink.StreamingAead
    public OutputStream newEncryptingStream(OutputStream outputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        return this.primitives.getPrimary().getPrimitive().newEncryptingStream(outputStream, bArr);
    }
}
