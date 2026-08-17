package io.sentry.cache.tape;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.UByte;

/* loaded from: classes2.dex */
public final class QueueFile implements Closeable, Iterable<byte[]> {
    static final int INITIAL_LENGTH = 4096;
    private static final int VERSIONED_HEADER = -2147483647;
    private static final byte[] ZEROES = new byte[4096];
    boolean closed;
    int elementCount;
    final File file;
    long fileLength;
    Element first;
    private Element last;
    private final int maxElements;
    RandomAccessFile raf;
    private final boolean zero;
    final int headerLength = 32;
    private final byte[] buffer = new byte[32];
    int modCount = 0;

    static RandomAccessFile initializeFromFile(File file) throws IOException {
        if (!file.exists()) {
            File file2 = new File(file.getPath() + ".tmp");
            RandomAccessFile open = open(file2);
            try {
                open.setLength(4096L);
                open.seek(0L);
                open.writeInt(VERSIONED_HEADER);
                open.writeLong(4096L);
                open.close();
                if (!file2.renameTo(file)) {
                    throw new IOException("Rename failed!");
                }
            } catch (Throwable th) {
                open.close();
                throw th;
            }
        }
        return open(file);
    }

    private static RandomAccessFile open(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    QueueFile(File file, RandomAccessFile randomAccessFile, boolean z, int i) throws IOException {
        this.file = file;
        this.raf = randomAccessFile;
        this.zero = z;
        this.maxElements = i;
        readInitialData();
    }

    private void readInitialData() throws IOException {
        this.raf.seek(0L);
        this.raf.readFully(this.buffer);
        this.fileLength = readLong(this.buffer, 4);
        this.elementCount = readInt(this.buffer, 12);
        long readLong = readLong(this.buffer, 16);
        long readLong2 = readLong(this.buffer, 24);
        if (this.fileLength > this.raf.length()) {
            throw new IOException("File is truncated. Expected length: " + this.fileLength + ", Actual length: " + this.raf.length());
        }
        if (this.fileLength <= 32) {
            throw new IOException("File is corrupt; length stored in header (" + this.fileLength + ") is invalid.");
        }
        this.first = readElement(readLong);
        this.last = readElement(readLong2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetFile() throws IOException {
        this.raf.close();
        this.file.delete();
        this.raf = initializeFromFile(this.file);
        readInitialData();
    }

    private static void writeInt(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static int readInt(byte[] bArr, int i) {
        return ((bArr[i] & UByte.MAX_VALUE) << 24) + ((bArr[i + 1] & UByte.MAX_VALUE) << 16) + ((bArr[i + 2] & UByte.MAX_VALUE) << 8) + (bArr[i + 3] & UByte.MAX_VALUE);
    }

    private static void writeLong(byte[] bArr, int i, long j) {
        bArr[i] = (byte) (j >> 56);
        bArr[i + 1] = (byte) (j >> 48);
        bArr[i + 2] = (byte) (j >> 40);
        bArr[i + 3] = (byte) (j >> 32);
        bArr[i + 4] = (byte) (j >> 24);
        bArr[i + 5] = (byte) (j >> 16);
        bArr[i + 6] = (byte) (j >> 8);
        bArr[i + 7] = (byte) j;
    }

    private static long readLong(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 56) + ((bArr[i + 1] & 255) << 48) + ((bArr[i + 2] & 255) << 40) + ((bArr[i + 3] & 255) << 32) + ((bArr[i + 4] & 255) << 24) + ((bArr[i + 5] & 255) << 16) + ((bArr[i + 6] & 255) << 8) + (bArr[i + 7] & 255);
    }

    private void writeHeader(long j, int i, long j2, long j3) throws IOException {
        this.raf.seek(0L);
        writeInt(this.buffer, 0, VERSIONED_HEADER);
        writeLong(this.buffer, 4, j);
        writeInt(this.buffer, 12, i);
        writeLong(this.buffer, 16, j2);
        writeLong(this.buffer, 24, j3);
        this.raf.write(this.buffer, 0, 32);
    }

    Element readElement(long j) throws IOException {
        if (j == 0) {
            return Element.NULL;
        }
        if (!ringRead(j, this.buffer, 0, 4)) {
            return Element.NULL;
        }
        return new Element(j, readInt(this.buffer, 0));
    }

    long wrapPosition(long j) {
        long j2 = this.fileLength;
        return j < j2 ? j : (j + 32) - j2;
    }

    private void ringWrite(long j, byte[] bArr, int i, int i2) throws IOException {
        long wrapPosition = wrapPosition(j);
        long j2 = i2 + wrapPosition;
        long j3 = this.fileLength;
        if (j2 <= j3) {
            this.raf.seek(wrapPosition);
            this.raf.write(bArr, i, i2);
            return;
        }
        int i3 = (int) (j3 - wrapPosition);
        this.raf.seek(wrapPosition);
        this.raf.write(bArr, i, i3);
        this.raf.seek(32L);
        this.raf.write(bArr, i + i3, i2 - i3);
    }

    private void ringErase(long j, long j2) throws IOException {
        while (j2 > 0) {
            byte[] bArr = ZEROES;
            int min = (int) Math.min(j2, bArr.length);
            ringWrite(j, bArr, 0, min);
            long j3 = min;
            j2 -= j3;
            j += j3;
        }
    }

    boolean ringRead(long j, byte[] bArr, int i, int i2) throws IOException {
        try {
            long wrapPosition = wrapPosition(j);
            long j2 = i2 + wrapPosition;
            long j3 = this.fileLength;
            if (j2 <= j3) {
                this.raf.seek(wrapPosition);
                this.raf.readFully(bArr, i, i2);
                return true;
            }
            int i3 = (int) (j3 - wrapPosition);
            this.raf.seek(wrapPosition);
            this.raf.readFully(bArr, i, i3);
            this.raf.seek(32L);
            this.raf.readFully(bArr, i + i3, i2 - i3);
            return true;
        } catch (EOFException unused) {
            resetFile();
            return false;
        } catch (IOException e) {
            throw e;
        } catch (Throwable unused2) {
            resetFile();
            return false;
        }
    }

    public void add(byte[] bArr) throws IOException {
        add(bArr, 0, bArr.length);
    }

    public void add(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("data == null");
        }
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (isAtFullCapacity()) {
            remove();
        }
        expandIfNecessary(i2);
        boolean isEmpty = isEmpty();
        Element element = new Element(isEmpty ? 32L : wrapPosition(this.last.position + 4 + this.last.length), i2);
        writeInt(this.buffer, 0, i2);
        ringWrite(element.position, this.buffer, 0, 4);
        ringWrite(element.position + 4, bArr, i, i2);
        writeHeader(this.fileLength, this.elementCount + 1, isEmpty ? element.position : this.first.position, element.position);
        this.last = element;
        this.elementCount++;
        this.modCount++;
        if (isEmpty) {
            this.first = element;
        }
    }

    private long usedBytes() {
        if (this.elementCount == 0) {
            return 32L;
        }
        if (this.last.position >= this.first.position) {
            return (this.last.position - this.first.position) + 4 + this.last.length + 32;
        }
        return (((this.last.position + 4) + this.last.length) + this.fileLength) - this.first.position;
    }

    private long remainingBytes() {
        return this.fileLength - usedBytes();
    }

    public boolean isEmpty() {
        return this.elementCount == 0;
    }

    private void expandIfNecessary(long j) throws IOException {
        long j2;
        long j3;
        long j4 = j + 4;
        long remainingBytes = remainingBytes();
        if (remainingBytes >= j4) {
            return;
        }
        long j5 = this.fileLength;
        while (true) {
            remainingBytes += j5;
            j2 = j5 << 1;
            if (remainingBytes >= j4) {
                break;
            } else {
                j5 = j2;
            }
        }
        setLength(j2);
        long wrapPosition = wrapPosition(this.last.position + 4 + this.last.length);
        if (wrapPosition <= this.first.position) {
            FileChannel channel = this.raf.getChannel();
            channel.position(this.fileLength);
            j3 = wrapPosition - 32;
            if (channel.transferTo(32L, j3, channel) != j3) {
                throw new AssertionError("Copied insufficient number of bytes!");
            }
        } else {
            j3 = 0;
        }
        long j6 = j3;
        if (this.last.position < this.first.position) {
            long j7 = (this.fileLength + this.last.position) - 32;
            writeHeader(j2, this.elementCount, this.first.position, j7);
            this.last = new Element(j7, this.last.length);
        } else {
            writeHeader(j2, this.elementCount, this.first.position, this.last.position);
        }
        this.fileLength = j2;
        if (this.zero) {
            ringErase(32L, j6);
        }
    }

    private void setLength(long j) throws IOException {
        this.raf.setLength(j);
        this.raf.getChannel().force(true);
    }

    public byte[] peek() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (isEmpty()) {
            return null;
        }
        int i = this.first.length;
        byte[] bArr = new byte[i];
        if (ringRead(this.first.position + 4, bArr, 0, i)) {
            return bArr;
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator<byte[]> iterator() {
        return new ElementIterator();
    }

    private final class ElementIterator implements Iterator<byte[]> {
        int expectedModCount;
        int nextElementIndex = 0;
        private long nextElementPosition;

        ElementIterator() {
            this.nextElementPosition = QueueFile.this.first.position;
            this.expectedModCount = QueueFile.this.modCount;
        }

        private void checkForComodification() {
            if (QueueFile.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (QueueFile.this.closed) {
                throw new IllegalStateException("closed");
            }
            checkForComodification();
            return this.nextElementIndex != QueueFile.this.elementCount;
        }

        @Override // java.util.Iterator
        public byte[] next() {
            if (QueueFile.this.closed) {
                throw new IllegalStateException("closed");
            }
            checkForComodification();
            if (QueueFile.this.isEmpty()) {
                throw new NoSuchElementException();
            }
            if (this.nextElementIndex >= QueueFile.this.elementCount) {
                throw new NoSuchElementException();
            }
            try {
                try {
                    Element readElement = QueueFile.this.readElement(this.nextElementPosition);
                    byte[] bArr = new byte[readElement.length];
                    long wrapPosition = QueueFile.this.wrapPosition(readElement.position + 4);
                    this.nextElementPosition = wrapPosition;
                    if (!QueueFile.this.ringRead(wrapPosition, bArr, 0, readElement.length)) {
                        this.nextElementIndex = QueueFile.this.elementCount;
                        return QueueFile.ZEROES;
                    }
                    this.nextElementPosition = QueueFile.this.wrapPosition(readElement.position + 4 + readElement.length);
                    this.nextElementIndex++;
                    return bArr;
                } catch (IOException e) {
                    throw ((Error) QueueFile.getSneakyThrowable(e));
                }
            } catch (IOException e2) {
                throw ((Error) QueueFile.getSneakyThrowable(e2));
            } catch (OutOfMemoryError unused) {
                QueueFile.this.resetFile();
                this.nextElementIndex = QueueFile.this.elementCount;
                return QueueFile.ZEROES;
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForComodification();
            if (QueueFile.this.isEmpty()) {
                throw new NoSuchElementException();
            }
            if (this.nextElementIndex != 1) {
                throw new UnsupportedOperationException("Removal is only permitted from the head.");
            }
            try {
                QueueFile.this.remove();
                this.expectedModCount = QueueFile.this.modCount;
                this.nextElementIndex--;
            } catch (IOException e) {
                throw ((Error) QueueFile.getSneakyThrowable(e));
            }
        }
    }

    public int size() {
        return this.elementCount;
    }

    public void remove() throws IOException {
        remove(1);
    }

    public void remove(int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("Cannot remove negative (" + i + ") number of elements.");
        }
        if (i == 0) {
            return;
        }
        if (i == this.elementCount) {
            clear();
            return;
        }
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (i > this.elementCount) {
            throw new IllegalArgumentException("Cannot remove more elements (" + i + ") than present in queue (" + this.elementCount + ").");
        }
        long j = this.first.position;
        long j2 = this.first.position;
        int i2 = this.first.length;
        long j3 = 0;
        int i3 = 0;
        while (i3 < i) {
            long j4 = j3 + i2 + 4;
            j2 = wrapPosition(j2 + 4 + i2);
            if (!ringRead(j2, this.buffer, 0, 4)) {
                return;
            }
            i2 = readInt(this.buffer, 0);
            i3++;
            j3 = j4;
        }
        long j5 = j3;
        writeHeader(this.fileLength, this.elementCount - i, j2, this.last.position);
        this.elementCount -= i;
        this.modCount++;
        this.first = new Element(j2, i2);
        if (this.zero) {
            ringErase(j, j5);
        }
    }

    public void clear() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        writeHeader(4096L, 0, 0L, 0L);
        if (this.zero) {
            this.raf.seek(32L);
            this.raf.write(ZEROES, 0, 4064);
        }
        this.elementCount = 0;
        this.first = Element.NULL;
        this.last = Element.NULL;
        if (this.fileLength > 4096) {
            setLength(4096L);
        }
        this.fileLength = 4096L;
        this.modCount++;
    }

    public boolean isAtFullCapacity() {
        return this.maxElements != -1 && size() == this.maxElements;
    }

    public File file() {
        return this.file;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
        this.raf.close();
    }

    public String toString() {
        return "QueueFile{file=" + this.file + ", zero=" + this.zero + ", length=" + this.fileLength + ", size=" + this.elementCount + ", first=" + this.first + ", last=" + this.last + '}';
    }

    static final class Element {
        static final int HEADER_LENGTH = 4;
        static final Element NULL = new Element(0, 0);
        final int length;
        final long position;

        Element(long j, int i) {
            this.position = j;
            this.length = i;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position=" + this.position + ", length=" + this.length + "]";
        }
    }

    public static final class Builder {
        final File file;
        boolean zero = true;
        int size = -1;

        public Builder(File file) {
            if (file == null) {
                throw new NullPointerException("file == null");
            }
            this.file = file;
        }

        public Builder zero(boolean z) {
            this.zero = z;
            return this;
        }

        public Builder size(int i) {
            this.size = i;
            return this;
        }

        public QueueFile build() throws IOException {
            RandomAccessFile initializeFromFile = QueueFile.initializeFromFile(this.file);
            try {
                return new QueueFile(this.file, initializeFromFile, this.zero, this.size);
            } catch (Throwable th) {
                initializeFromFile.close();
                throw th;
            }
        }
    }

    static <T extends Throwable> T getSneakyThrowable(Throwable th) throws Throwable {
        throw th;
    }
}
