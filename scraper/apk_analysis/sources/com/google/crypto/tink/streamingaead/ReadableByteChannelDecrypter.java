package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.subtle.RewindableReadableByteChannel;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Iterator;

/* loaded from: classes2.dex */
final class ReadableByteChannelDecrypter implements ReadableByteChannel {
    byte[] associatedData;
    RewindableReadableByteChannel ciphertextChannel;
    PrimitiveSet<StreamingAead> primitives;
    boolean attemptedMatching = false;
    ReadableByteChannel matchingChannel = null;

    public ReadableByteChannelDecrypter(PrimitiveSet<StreamingAead> primitiveSet, ReadableByteChannel readableByteChannel, byte[] bArr) {
        this.primitives = primitiveSet;
        this.ciphertextChannel = new RewindableReadableByteChannel(readableByteChannel);
        this.associatedData = (byte[]) bArr.clone();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() == 0) {
            return 0;
        }
        ReadableByteChannel readableByteChannel = this.matchingChannel;
        if (readableByteChannel != null) {
            return readableByteChannel.read(byteBuffer);
        }
        if (this.attemptedMatching) {
            throw new IOException("No matching key found for the ciphertext in the stream.");
        }
        this.attemptedMatching = true;
        Iterator<PrimitiveSet.Entry<StreamingAead>> it = this.primitives.getRawPrimitives().iterator();
        while (it.hasNext()) {
            try {
                try {
                    ReadableByteChannel newDecryptingChannel = it.next().getPrimitive().newDecryptingChannel(this.ciphertextChannel, this.associatedData);
                    int read = newDecryptingChannel.read(byteBuffer);
                    if (read > 0) {
                        this.matchingChannel = newDecryptingChannel;
                        this.ciphertextChannel.disableRewinding();
                    } else if (read == 0) {
                        this.ciphertextChannel.rewind();
                        this.attemptedMatching = false;
                    }
                    return read;
                } catch (IOException unused) {
                    this.ciphertextChannel.rewind();
                }
            } catch (GeneralSecurityException unused2) {
                this.ciphertextChannel.rewind();
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.ciphertextChannel.isOpen();
    }
}
