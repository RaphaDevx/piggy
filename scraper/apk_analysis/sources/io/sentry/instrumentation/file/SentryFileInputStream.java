package io.sentry.instrumentation.file;

import io.sentry.IScopes;
import io.sentry.ISpan;
import io.sentry.ScopesAdapter;
import io.sentry.instrumentation.file.FileIOSpanManager;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public final class SentryFileInputStream extends FileInputStream {
    private final FileInputStream delegate;
    private final FileIOSpanManager spanManager;

    public SentryFileInputStream(String str) throws FileNotFoundException {
        this(str != null ? new File(str) : null, ScopesAdapter.getInstance());
    }

    public SentryFileInputStream(File file) throws FileNotFoundException {
        this(file, ScopesAdapter.getInstance());
    }

    public SentryFileInputStream(FileDescriptor fileDescriptor) {
        this(fileDescriptor, ScopesAdapter.getInstance());
    }

    SentryFileInputStream(File file, IScopes iScopes) throws FileNotFoundException {
        this(init(file, (FileInputStream) null, iScopes));
    }

    SentryFileInputStream(FileDescriptor fileDescriptor, IScopes iScopes) {
        this(init(fileDescriptor, (FileInputStream) null, iScopes), fileDescriptor);
    }

    private SentryFileInputStream(FileInputStreamInitData fileInputStreamInitData, FileDescriptor fileDescriptor) {
        super(fileDescriptor);
        this.spanManager = new FileIOSpanManager(fileInputStreamInitData.span, fileInputStreamInitData.file, fileInputStreamInitData.options);
        this.delegate = fileInputStreamInitData.delegate;
    }

    private SentryFileInputStream(FileInputStreamInitData fileInputStreamInitData) throws FileNotFoundException {
        super(getFileDescriptor(fileInputStreamInitData.delegate));
        this.spanManager = new FileIOSpanManager(fileInputStreamInitData.span, fileInputStreamInitData.file, fileInputStreamInitData.options);
        this.delegate = fileInputStreamInitData.delegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FileInputStreamInitData init(File file, FileInputStream fileInputStream, IScopes iScopes) throws FileNotFoundException {
        ISpan startSpan = FileIOSpanManager.startSpan(iScopes, "file.read");
        if (fileInputStream == null) {
            fileInputStream = new FileInputStream(file);
        }
        return new FileInputStreamInitData(file, startSpan, fileInputStream, iScopes.getOptions());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static FileInputStreamInitData init(FileDescriptor fileDescriptor, FileInputStream fileInputStream, IScopes iScopes) {
        ISpan startSpan = FileIOSpanManager.startSpan(iScopes, "file.read");
        if (fileInputStream == null) {
            fileInputStream = new FileInputStream(fileDescriptor);
        }
        return new FileInputStreamInitData(null, startSpan, fileInputStream, iScopes.getOptions());
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read() throws IOException {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.SentryFileInputStream$$ExternalSyntheticLambda3
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return SentryFileInputStream.this.m3922xfe779029(atomicInteger);
            }
        });
        return atomicInteger.get();
    }

    /* renamed from: lambda$read$0$io-sentry-instrumentation-file-SentryFileInputStream, reason: not valid java name */
    /* synthetic */ Integer m3922xfe779029(AtomicInteger atomicInteger) throws IOException {
        int read = this.delegate.read();
        atomicInteger.set(read);
        return Integer.valueOf(read != -1 ? 1 : 0);
    }

    /* renamed from: lambda$read$1$io-sentry-instrumentation-file-SentryFileInputStream, reason: not valid java name */
    /* synthetic */ Integer m3923x18930ec8(byte[] bArr) throws IOException {
        return Integer.valueOf(this.delegate.read(bArr));
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(final byte[] bArr) throws IOException {
        return ((Integer) this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.SentryFileInputStream$$ExternalSyntheticLambda2
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return SentryFileInputStream.this.m3923x18930ec8(bArr);
            }
        })).intValue();
    }

    /* renamed from: lambda$read$2$io-sentry-instrumentation-file-SentryFileInputStream, reason: not valid java name */
    /* synthetic */ Integer m3924x32ae8d67(byte[] bArr, int i, int i2) throws IOException {
        return Integer.valueOf(this.delegate.read(bArr, i, i2));
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public int read(final byte[] bArr, final int i, final int i2) throws IOException {
        return ((Integer) this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.SentryFileInputStream$$ExternalSyntheticLambda1
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return SentryFileInputStream.this.m3924x32ae8d67(bArr, i, i2);
            }
        })).intValue();
    }

    /* renamed from: lambda$skip$3$io-sentry-instrumentation-file-SentryFileInputStream, reason: not valid java name */
    /* synthetic */ Long m3925xe63a8dfd(long j) throws IOException {
        return Long.valueOf(this.delegate.skip(j));
    }

    @Override // java.io.FileInputStream, java.io.InputStream
    public long skip(final long j) throws IOException {
        return ((Long) this.spanManager.performIO(new FileIOSpanManager.FileIOCallable() { // from class: io.sentry.instrumentation.file.SentryFileInputStream$$ExternalSyntheticLambda0
            @Override // io.sentry.instrumentation.file.FileIOSpanManager.FileIOCallable
            public final Object call() {
                return SentryFileInputStream.this.m3925xe63a8dfd(j);
            }
        })).longValue();
    }

    @Override // java.io.FileInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.spanManager.finish(this.delegate);
        super.close();
    }

    private static FileDescriptor getFileDescriptor(FileInputStream fileInputStream) throws FileNotFoundException {
        try {
            return fileInputStream.getFD();
        } catch (IOException unused) {
            throw new FileNotFoundException("No file descriptor");
        }
    }

    public static final class Factory {
        public static FileInputStream create(FileInputStream fileInputStream, String str) throws FileNotFoundException {
            ScopesAdapter scopesAdapter = ScopesAdapter.getInstance();
            if (isTracingEnabled(scopesAdapter)) {
                return new SentryFileInputStream(SentryFileInputStream.init(str != null ? new File(str) : null, fileInputStream, scopesAdapter));
            }
            return fileInputStream;
        }

        public static FileInputStream create(FileInputStream fileInputStream, File file) throws FileNotFoundException {
            ScopesAdapter scopesAdapter = ScopesAdapter.getInstance();
            return isTracingEnabled(scopesAdapter) ? new SentryFileInputStream(SentryFileInputStream.init(file, fileInputStream, scopesAdapter)) : fileInputStream;
        }

        public static FileInputStream create(FileInputStream fileInputStream, FileDescriptor fileDescriptor) {
            ScopesAdapter scopesAdapter = ScopesAdapter.getInstance();
            return isTracingEnabled(scopesAdapter) ? new SentryFileInputStream(SentryFileInputStream.init(fileDescriptor, fileInputStream, scopesAdapter), fileDescriptor) : fileInputStream;
        }

        static FileInputStream create(FileInputStream fileInputStream, File file, IScopes iScopes) throws FileNotFoundException {
            return isTracingEnabled(iScopes) ? new SentryFileInputStream(SentryFileInputStream.init(file, fileInputStream, iScopes)) : fileInputStream;
        }

        private static boolean isTracingEnabled(IScopes iScopes) {
            return iScopes.getOptions().isTracingEnabled();
        }
    }
}
