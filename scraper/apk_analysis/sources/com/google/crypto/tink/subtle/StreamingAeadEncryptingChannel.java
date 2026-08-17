package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

/* loaded from: classes2.dex */
class StreamingAeadEncryptingChannel implements WritableByteChannel {
    private WritableByteChannel ciphertextChannel;
    ByteBuffer ctBuffer;
    private StreamSegmentEncrypter encrypter;
    boolean open = true;
    private int plaintextSegmentSize;
    ByteBuffer ptBuffer;

    public StreamingAeadEncryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead, WritableByteChannel writableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        this.ciphertextChannel = writableByteChannel;
        this.encrypter = nonceBasedStreamingAead.newStreamSegmentEncrypter(bArr);
        int plaintextSegmentSize = nonceBasedStreamingAead.getPlaintextSegmentSize();
        this.plaintextSegmentSize = plaintextSegmentSize;
        ByteBuffer allocate = ByteBuffer.allocate(plaintextSegmentSize);
        this.ptBuffer = allocate;
        allocate.limit(this.plaintextSegmentSize - nonceBasedStreamingAead.getCiphertextOffset());
        ByteBuffer allocate2 = ByteBuffer.allocate(nonceBasedStreamingAead.getCiphertextSegmentSize());
        this.ctBuffer = allocate2;
        allocate2.put(this.encrypter.getHeader());
        this.ctBuffer.flip();
        writableByteChannel.write(this.ctBuffer);
    }

    @Override // java.nio.channels.WritableByteChannel
    public synchronized int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.open) {
            throw new ClosedChannelException();
        }
        if (this.ctBuffer.remaining() > 0) {
            this.ciphertextChannel.write(this.ctBuffer);
        }
        int position = byteBuffer.position();
        while (byteBuffer.remaining() > this.ptBuffer.remaining()) {
            if (this.ctBuffer.remaining() > 0) {
                return byteBuffer.position() - position;
            }
            int remaining = this.ptBuffer.remaining();
            ByteBuffer slice = byteBuffer.slice();
            slice.limit(remaining);
            byteBuffer.position(byteBuffer.position() + remaining);
            try {
                this.ptBuffer.flip();
                this.ctBuffer.clear();
                if (slice.remaining() != 0) {
                    this.encrypter.encryptSegment(this.ptBuffer, slice, false, this.ctBuffer);
                } else {
                    this.encrypter.encryptSegment(this.ptBuffer, false, this.ctBuffer);
                }
                this.ctBuffer.flip();
                this.ciphertextChannel.write(this.ctBuffer);
                this.ptBuffer.clear();
                this.ptBuffer.limit(this.plaintextSegmentSize);
            } catch (GeneralSecurityException e) {
                throw new IOException(e);
            }
        }
        this.ptBuffer.put(byteBuffer);
        return byteBuffer.position() - position;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.open) {
            while (this.ctBuffer.remaining() > 0) {
                if (this.ciphertextChannel.write(this.ctBuffer) <= 0) {
                    throw new IOException("Failed to write ciphertext before closing");
                }
            }
            try {
                this.ctBuffer.clear();
                this.ptBuffer.flip();
                this.encrypter.encryptSegment(this.ptBuffer, true, this.ctBuffer);
                this.ctBuffer.flip();
                while (this.ctBuffer.remaining() > 0) {
                    if (this.ciphertextChannel.write(this.ctBuffer) <= 0) {
                        throw new IOException("Failed to write ciphertext before closing");
                    }
                }
                this.ciphertextChannel.close();
                this.open = false;
            } catch (GeneralSecurityException e) {
                throw new IOException(e);
            }
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.open;
    }
}
