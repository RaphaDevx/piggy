package com.salesforce.marketingcloud.util;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class d implements Closeable {
    private static final String A = "READ";
    static final String o = "journal";
    static final String p = "journal.tmp";
    static final String q = "journal.bkp";
    static final String r = "libcore.io.DiskLruCache";
    static final String s = "1";
    static final long t = -1;
    static final String u = "[a-z0-9_-]{1,120}";
    static final Pattern v = Pattern.compile(u);
    static final OutputStream w = new a();
    private static final String x = "CLEAN";
    private static final String y = "DIRTY";
    private static final String z = "REMOVE";
    final File b;
    final int c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    Writer i;
    int j;
    private long k;
    private long l;
    private long m;
    final ThreadPoolExecutor a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final LinkedHashMap<String, C0100d> h = new LinkedHashMap<>(0, 0.75f, true);
    private final Callable<Void> n = new b();

    class a extends OutputStream {
        a() {
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
        }
    }

    class b implements Callable<Void> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            synchronized (d.this) {
                d dVar = d.this;
                if (dVar.i == null) {
                    return null;
                }
                dVar.l();
                if (d.this.g()) {
                    d.this.j();
                    d.this.j = 0;
                }
                return null;
            }
        }
    }

    public final class e implements Closeable {
        private final String a;
        private final long b;
        private final InputStream[] c;
        private final long[] d;

        e(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.a = str;
            this.b = j;
            this.c = inputStreamArr;
            this.d = jArr;
        }

        public c a() throws IOException {
            return d.this.a(this.a, this.b);
        }

        public long b(int i) {
            return this.d[i];
        }

        public String c(int i) throws IOException {
            return d.a(a(i));
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (InputStream inputStream : this.c) {
                com.salesforce.marketingcloud.util.e.a((Closeable) inputStream);
            }
        }

        public InputStream a(int i) {
            return this.c[i];
        }
    }

    private d(File file, int i, int i2, long j) {
        this.b = file;
        this.g = i;
        this.d = new File(file, o);
        this.e = new File(file, p);
        this.f = new File(file, q);
        this.c = i2;
        this.k = j;
    }

    public static d a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, q);
        if (file2.exists()) {
            File file3 = new File(file, o);
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        d dVar = new d(file, i, i2, j);
        if (dVar.d.exists()) {
            try {
                dVar.i();
                dVar.h();
                return dVar;
            } catch (IOException e2) {
                com.salesforce.marketingcloud.g.b("DiskLruCache", e2, "DiskLruCache %s is corrupt, removing.", file);
                dVar.b();
            }
        }
        file.mkdirs();
        d dVar2 = new d(file, i, i2, j);
        dVar2.j();
        return dVar2;
    }

    private void c(String str) throws IOException {
        String substring;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            substring = str.substring(i);
            if (indexOf == 6 && str.startsWith(z)) {
                this.h.remove(substring);
                return;
            }
        } else {
            substring = str.substring(i, indexOf2);
        }
        C0100d c0100d = this.h.get(substring);
        if (c0100d == null) {
            c0100d = new C0100d(substring);
            this.h.put(substring, c0100d);
        }
        if (indexOf2 != -1 && indexOf == 5 && str.startsWith(x)) {
            String[] split = str.substring(indexOf2 + 1).split(StringUtils.SPACE);
            c0100d.c = true;
            c0100d.d = null;
            c0100d.b(split);
            return;
        }
        if (indexOf2 == -1 && indexOf == 5 && str.startsWith(y)) {
            c0100d.d = new c(c0100d);
        } else {
            if (indexOf2 == -1 && indexOf == 4 && str.startsWith(A)) {
                return;
            }
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void h() throws IOException {
        a(this.e);
        Iterator<C0100d> it = this.h.values().iterator();
        while (it.hasNext()) {
            C0100d next = it.next();
            int i = 0;
            if (next.d == null) {
                while (i < this.c) {
                    this.l += next.b[i];
                    i++;
                }
            } else {
                next.d = null;
                while (i < this.c) {
                    a(next.a(i));
                    a(next.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    private void i() throws IOException {
        i iVar = new i(new FileInputStream(this.d), com.salesforce.marketingcloud.util.e.a);
        try {
            String d = iVar.d();
            String d2 = iVar.d();
            String d3 = iVar.d();
            String d4 = iVar.d();
            String d5 = iVar.d();
            if (!r.equals(d) || !s.equals(d2) || !Integer.toString(this.g).equals(d3) || !Integer.toString(this.c).equals(d4) || !"".equals(d5)) {
                throw new IOException("unexpected journal header: [" + d + ", " + d2 + ", " + d4 + ", " + d5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    c(iVar.d());
                    i++;
                } catch (EOFException unused) {
                    this.j = i - this.h.size();
                    if (iVar.b()) {
                        j();
                    } else {
                        this.i = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), com.salesforce.marketingcloud.util.e.a));
                    }
                    com.salesforce.marketingcloud.util.e.a(iVar);
                    return;
                }
            }
        } catch (Throwable th) {
            com.salesforce.marketingcloud.util.e.a(iVar);
            throw th;
        }
    }

    public synchronized e b(String str) throws IOException {
        InputStream inputStream;
        a();
        e(str);
        C0100d c0100d = this.h.get(str);
        if (c0100d == null) {
            return null;
        }
        if (!c0100d.c) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.c];
        for (int i = 0; i < this.c; i++) {
            try {
                inputStreamArr[i] = new FileInputStream(c0100d.a(i));
            } catch (FileNotFoundException unused) {
                for (int i2 = 0; i2 < this.c && (inputStream = inputStreamArr[i2]) != null; i2++) {
                    com.salesforce.marketingcloud.util.e.a((Closeable) inputStream);
                }
                return null;
            }
        }
        this.j++;
        this.i.append((CharSequence) ("READ " + str + '\n'));
        if (g()) {
            this.a.submit(this.n);
        }
        return new e(str, c0100d.e, inputStreamArr, c0100d.b);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.i == null) {
            return;
        }
        Iterator it = new ArrayList(this.h.values()).iterator();
        while (it.hasNext()) {
            c cVar = ((C0100d) it.next()).d;
            if (cVar != null) {
                cVar.a();
            }
        }
        l();
        this.i.close();
        this.i = null;
    }

    public File d() {
        return this.b;
    }

    public synchronized long e() {
        return this.k;
    }

    public synchronized boolean f() {
        return this.i == null;
    }

    boolean g() {
        int i = this.j;
        return i >= 2000 && i >= this.h.size();
    }

    synchronized void j() throws IOException {
        Writer writer = this.i;
        if (writer != null) {
            writer.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), com.salesforce.marketingcloud.util.e.a));
        try {
            bufferedWriter.write(r);
            bufferedWriter.write(StringUtils.LF);
            bufferedWriter.write(s);
            bufferedWriter.write(StringUtils.LF);
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write(StringUtils.LF);
            bufferedWriter.write(Integer.toString(this.c));
            bufferedWriter.write(StringUtils.LF);
            bufferedWriter.write(StringUtils.LF);
            for (C0100d c0100d : this.h.values()) {
                if (c0100d.d != null) {
                    bufferedWriter.write("DIRTY " + c0100d.a + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + c0100d.a + c0100d.a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.i = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), com.salesforce.marketingcloud.util.e.a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    public synchronized long k() {
        return this.l;
    }

    void l() throws IOException {
        while (this.l > this.k) {
            d(this.h.entrySet().iterator().next().getKey());
        }
    }

    public final class c {
        final C0100d a;
        final boolean[] b;
        boolean c;
        private boolean d;

        c(C0100d c0100d) {
            this.a = c0100d;
            this.b = c0100d.c ? null : new boolean[d.this.c];
        }

        public String a(int i) throws IOException {
            InputStream b = b(i);
            if (b != null) {
                return d.a(b);
            }
            return null;
        }

        public InputStream b(int i) throws IOException {
            synchronized (d.this) {
                C0100d c0100d = this.a;
                if (c0100d.d != this) {
                    throw new IllegalStateException();
                }
                if (!c0100d.c) {
                    return null;
                }
                try {
                    return new FileInputStream(this.a.a(i));
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }
        }

        public OutputStream c(int i) throws IOException {
            FileOutputStream fileOutputStream;
            a aVar;
            if (i >= 0) {
                d dVar = d.this;
                if (i < dVar.c) {
                    synchronized (dVar) {
                        C0100d c0100d = this.a;
                        if (c0100d.d != this) {
                            throw new IllegalStateException();
                        }
                        if (!c0100d.c) {
                            this.b[i] = true;
                        }
                        File b = c0100d.b(i);
                        try {
                            fileOutputStream = new FileOutputStream(b);
                        } catch (FileNotFoundException unused) {
                            d.this.b.mkdirs();
                            try {
                                fileOutputStream = new FileOutputStream(b);
                            } catch (FileNotFoundException unused2) {
                                return d.w;
                            }
                        }
                        aVar = new a(fileOutputStream);
                    }
                    return aVar;
                }
            }
            throw new IllegalArgumentException("Expected index " + i + " to be greater than 0 and less than the maximum value count of " + d.this.c);
        }

        private class a extends FilterOutputStream {
            a(OutputStream outputStream) {
                super(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                try {
                    ((FilterOutputStream) this).out.close();
                } catch (IOException unused) {
                    c.this.c = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() {
                try {
                    ((FilterOutputStream) this).out.flush();
                } catch (IOException unused) {
                    c.this.c = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) {
                try {
                    ((FilterOutputStream) this).out.write(i);
                } catch (IOException unused) {
                    c.this.c = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                try {
                    ((FilterOutputStream) this).out.write(bArr, i, i2);
                } catch (IOException unused) {
                    c.this.c = true;
                }
            }
        }

        public void a(int i, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(c(i), com.salesforce.marketingcloud.util.e.c);
                try {
                    outputStreamWriter2.write(str);
                    com.salesforce.marketingcloud.util.e.a(outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    com.salesforce.marketingcloud.util.e.a(outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }

        public void a() throws IOException {
            d.this.a(this, false);
        }

        public void b() {
            if (this.d) {
                return;
            }
            try {
                a();
            } catch (IOException unused) {
            }
        }

        public void c() throws IOException {
            if (this.c) {
                d.this.a(this, false);
                d.this.d(this.a.a);
            } else {
                d.this.a(this, true);
            }
            this.d = true;
        }
    }

    private void e(String str) {
        if (v.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    public synchronized boolean d(String str) throws IOException {
        a();
        e(str);
        C0100d c0100d = this.h.get(str);
        if (c0100d != null && c0100d.d == null) {
            for (int i = 0; i < this.c; i++) {
                File a2 = c0100d.a(i);
                if (a2.exists() && !a2.delete()) {
                    throw new IOException("failed to delete " + a2);
                }
                long j = this.l;
                long[] jArr = c0100d.b;
                this.l = j - jArr[i];
                jArr[i] = 0;
            }
            this.j++;
            this.i.append((CharSequence) ("REMOVE " + str + '\n'));
            this.h.remove(str);
            if (g()) {
                this.a.submit(this.n);
            }
            return true;
        }
        return false;
    }

    /* renamed from: com.salesforce.marketingcloud.util.d$d, reason: collision with other inner class name */
    private final class C0100d {
        final String a;
        final long[] b;
        boolean c;
        c d;
        long e;

        C0100d(String str) {
            this.a = str;
            this.b = new long[d.this.c];
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long j : this.b) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        void b(String[] strArr) throws IOException {
            if (strArr.length != d.this.c) {
                throw a(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.b[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    throw a(strArr);
                }
            }
        }

        private IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File a(int i) {
            return new File(d.this.b, this.a + "." + i);
        }

        public File b(int i) {
            return new File(d.this.b, this.a + "." + i + ".tmp");
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z2) throws IOException {
        if (z2) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    static String a(InputStream inputStream) throws IOException {
        return com.salesforce.marketingcloud.util.e.a((Reader) new InputStreamReader(inputStream, com.salesforce.marketingcloud.util.e.c));
    }

    public synchronized void c() throws IOException {
        a();
        l();
        this.i.flush();
    }

    public c a(String str) throws IOException {
        return a(str, -1L);
    }

    synchronized c a(String str, long j) throws IOException {
        a();
        e(str);
        C0100d c0100d = this.h.get(str);
        if (j != -1 && (c0100d == null || c0100d.e != j)) {
            return null;
        }
        if (c0100d == null) {
            c0100d = new C0100d(str);
            this.h.put(str, c0100d);
        } else if (c0100d.d != null) {
            return null;
        }
        c cVar = new c(c0100d);
        c0100d.d = cVar;
        this.i.write("DIRTY " + str + '\n');
        this.i.flush();
        return cVar;
    }

    public void b() throws IOException {
        close();
        com.salesforce.marketingcloud.util.e.a(this.b);
    }

    public synchronized void a(long j) {
        this.k = j;
        this.a.submit(this.n);
    }

    synchronized void a(c cVar, boolean z2) throws IOException {
        C0100d c0100d = cVar.a;
        if (c0100d.d == cVar) {
            if (z2 && !c0100d.c) {
                for (int i = 0; i < this.c; i++) {
                    if (cVar.b[i]) {
                        if (!c0100d.b(i).exists()) {
                            cVar.a();
                            return;
                        }
                    } else {
                        cVar.a();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    }
                }
            }
            for (int i2 = 0; i2 < this.c; i2++) {
                File b2 = c0100d.b(i2);
                if (z2) {
                    if (b2.exists()) {
                        File a2 = c0100d.a(i2);
                        b2.renameTo(a2);
                        long j = c0100d.b[i2];
                        long length = a2.length();
                        c0100d.b[i2] = length;
                        this.l = (this.l - j) + length;
                    }
                } else {
                    a(b2);
                }
            }
            this.j++;
            c0100d.d = null;
            if (c0100d.c | z2) {
                c0100d.c = true;
                this.i.write("CLEAN " + c0100d.a + c0100d.a() + '\n');
                if (z2) {
                    long j2 = this.m;
                    this.m = 1 + j2;
                    c0100d.e = j2;
                }
            } else {
                this.h.remove(c0100d.a);
                this.i.write("REMOVE " + c0100d.a + '\n');
            }
            this.i.flush();
            if (this.l > this.k || g()) {
                this.a.submit(this.n);
            }
            return;
        }
        throw new IllegalStateException();
    }

    private void a() {
        if (this.i == null) {
            throw new IllegalStateException("cache is closed");
        }
    }
}
