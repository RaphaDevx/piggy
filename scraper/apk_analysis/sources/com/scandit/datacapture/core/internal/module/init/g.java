package com.scandit.datacapture.core.internal.module.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public abstract class g {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.util.zip.ZipFile] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.util.zip.ZipFile] */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3 */
    public static final boolean a(File apkBaseDir, File file, String str, List list, d storeLog) {
        Iterator it;
        File[] fileArr;
        Throwable th;
        File extractionDir = file;
        String fullSoFilename = str;
        List archs = list;
        Intrinsics.checkNotNullParameter(apkBaseDir, "apkBaseDir");
        Intrinsics.checkNotNullParameter(extractionDir, "extractionDir");
        Intrinsics.checkNotNullParameter(fullSoFilename, "fullSoFilename");
        Intrinsics.checkNotNullParameter(archs, "archs");
        Intrinsics.checkNotNullParameter(storeLog, "storeLog");
        File[] listFiles = apkBaseDir.listFiles(new FilenameFilter() { // from class: com.scandit.datacapture.core.internal.module.init.g$$ExternalSyntheticLambda0
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str2) {
                return g.a(file2, str2);
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            storeLog.invoke("\t\tNo \"*.apk\" files in " + apkBaseDir.getAbsolutePath());
            return false;
        }
        Intrinsics.checkNotNull(listFiles);
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            ?? zipFile = new ZipFile(listFiles[i]);
            if (!(archs instanceof Collection) || !list.isEmpty()) {
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    String str2 = (String) it2.next();
                    StringBuilder sb = new StringBuilder("lib");
                    String str3 = File.separator;
                    sb.append(str3);
                    sb.append(str2);
                    sb.append(str3);
                    sb.append(fullSoFilename);
                    String sb2 = sb.toString();
                    ZipEntry entry = zipFile.getEntry(sb2);
                    if (entry == null) {
                        storeLog.invoke("\t\tCannot find \"" + sb2 + "\" inside " + zipFile.getName());
                    } else {
                        storeLog.invoke("\t\tFound \"" + sb2 + "\" inside " + zipFile.getName());
                        if (file.exists() || file.mkdirs()) {
                            File file2 = new File(extractionDir, fullSoFilename);
                            if (file2.exists()) {
                                FilesKt.deleteRecursively(file2);
                            }
                            file2.createNewFile();
                            String fullSoPath = file2.getAbsolutePath();
                            try {
                                InputStream inputStream = zipFile.getInputStream(entry);
                                try {
                                    try {
                                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                        try {
                                            Intrinsics.checkNotNull(inputStream);
                                            fileArr = listFiles;
                                            apkBaseDir = zipFile;
                                            it = it2;
                                            try {
                                                long copyTo$default = ByteStreamsKt.copyTo$default(inputStream, fileOutputStream, 0, 2, null);
                                                fileOutputStream.flush();
                                                Unit unit = Unit.INSTANCE;
                                                CloseableKt.closeFinally(fileOutputStream, null);
                                                CloseableKt.closeFinally(inputStream, null);
                                                storeLog.invoke("\t\t\tExtracted " + entry.getName() + " into \"" + fullSoPath + "\", " + copyTo$default + " bytes");
                                                try {
                                                    Intrinsics.checkNotNull(fullSoPath);
                                                    Intrinsics.checkNotNullParameter(fullSoPath, "fullSoPath");
                                                    System.load(fullSoPath);
                                                    storeLog.invoke("\t\t\tSystem.load(" + fullSoPath + ") successful");
                                                    try {
                                                        file2.delete();
                                                    } catch (IOException unused) {
                                                        storeLog.invoke("\t\t\tError deleting extracted cache file \"" + fullSoPath + '\"');
                                                    }
                                                    return true;
                                                } catch (Throwable th2) {
                                                    storeLog.invoke("\t\t\tSystem.load(" + fullSoPath + ") failed with message: " + th2.getMessage());
                                                    zipFile = apkBaseDir;
                                                    extractionDir = file;
                                                    fullSoFilename = str;
                                                    listFiles = fileArr;
                                                    it2 = it;
                                                }
                                            } catch (Throwable th3) {
                                                th = th3;
                                                Throwable th4 = th;
                                                try {
                                                    throw th4;
                                                } catch (Throwable th5) {
                                                    CloseableKt.closeFinally(fileOutputStream, th4);
                                                    throw th5;
                                                }
                                            }
                                        } catch (Throwable th6) {
                                            th = th6;
                                            fileArr = listFiles;
                                            apkBaseDir = zipFile;
                                            it = it2;
                                        }
                                    } catch (Throwable th7) {
                                        th = th7;
                                        th = th;
                                        try {
                                            try {
                                                throw th;
                                            } catch (Throwable th8) {
                                                CloseableKt.closeFinally(inputStream, th);
                                                throw th8;
                                            }
                                        } catch (IOException e) {
                                            e = e;
                                            storeLog.invoke("\t\t\tError extracting " + entry.getName() + " into \"" + fullSoPath + "\" -> " + e.getMessage());
                                            zipFile = apkBaseDir;
                                            extractionDir = file;
                                            fullSoFilename = str;
                                            listFiles = fileArr;
                                            it2 = it;
                                        }
                                    }
                                } catch (Throwable th9) {
                                    th = th9;
                                    fileArr = listFiles;
                                    apkBaseDir = zipFile;
                                    it = it2;
                                    th = th;
                                    throw th;
                                }
                            } catch (IOException e2) {
                                e = e2;
                                fileArr = listFiles;
                                apkBaseDir = zipFile;
                                it = it2;
                            }
                        } else {
                            storeLog.invoke("\t\t\tCannot create " + extractionDir);
                        }
                    }
                }
            }
            fileArr = listFiles;
            i++;
            extractionDir = file;
            fullSoFilename = str;
            archs = list;
            listFiles = fileArr;
        }
        return false;
    }

    public static final boolean a(File file, String str) {
        Intrinsics.checkNotNull(str);
        return StringsKt.endsWith$default(str, ".apk", false, 2, (Object) null);
    }
}
