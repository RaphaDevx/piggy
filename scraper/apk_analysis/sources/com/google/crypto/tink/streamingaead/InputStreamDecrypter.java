package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Iterator;

/* loaded from: classes2.dex */
final class InputStreamDecrypter extends InputStream {
    byte[] associatedData;
    InputStream ciphertextStream;
    PrimitiveSet<StreamingAead> primitives;
    boolean attemptedMatching = false;
    InputStream matchingStream = null;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public InputStreamDecrypter(PrimitiveSet<StreamingAead> primitiveSet, InputStream inputStream, byte[] bArr) {
        this.primitives = primitiveSet;
        if (inputStream.markSupported()) {
            this.ciphertextStream = inputStream;
        } else {
            this.ciphertextStream = new BufferedInputStream(inputStream);
        }
        this.ciphertextStream.mark(Integer.MAX_VALUE);
        this.associatedData = (byte[]) bArr.clone();
    }

    private void rewind() throws IOException {
        this.ciphertextStream.reset();
    }

    private void disableRewinding() throws IOException {
        this.ciphertextStream.mark(0);
    }

    @Override // java.io.InputStream
    public synchronized int available() throws IOException {
        InputStream inputStream = this.matchingStream;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr) != 1) {
            return -1;
        }
        return bArr[0];
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        InputStream inputStream = this.matchingStream;
        if (inputStream != null) {
            return inputStream.read(bArr, i, i2);
        }
        if (this.attemptedMatching) {
            throw new IOException("No matching key found for the ciphertext in the stream.");
        }
        this.attemptedMatching = true;
        Iterator<PrimitiveSet.Entry<StreamingAead>> it = this.primitives.getRawPrimitives().iterator();
        while (it.hasNext()) {
            try {
                try {
                    InputStream newDecryptingStream = it.next().getPrimitive().newDecryptingStream(this.ciphertextStream, this.associatedData);
                    int read = newDecryptingStream.read(bArr, i, i2);
                    if (read == 0) {
                        rewind();
                        this.attemptedMatching = false;
                    } else {
                        this.matchingStream = newDecryptingStream;
                        disableRewinding();
                    }
                    return read;
                } catch (GeneralSecurityException unused) {
                    rewind();
                }
            } catch (IOException unused2) {
                rewind();
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.ciphertextStream.close();
    }
}
