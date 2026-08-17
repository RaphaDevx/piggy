package com.scandit.datacapture.core.framesave;

import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0011\u0010\u0001\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u0011\u0010\u0003\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0002¨\u0006\u0004"}, d2 = {"Ljava/io/File;", "getNextFrameSequenceDirectoryName", "(Ljava/io/File;)Ljava/io/File;", "getLastFrameSequenceDirectoryName", "scandit-capture-core"}, k = 2, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class FileExtensionsKt {
    public static final File getLastFrameSequenceDirectoryName(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Iterator it = SequencesKt.map(CollectionsKt.asSequence(RangesKt.until(0, com.salesforce.marketingcloud.config.a.n)), new d(file)).iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            Object next = it.next();
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (!((File) next).exists()) {
                break;
            }
            i++;
        }
        return FilesKt.resolve(file, new File("sequence_" + StringsKt.padStart(String.valueOf(RangesKt.coerceAtLeast(i - 1, 0)), 3, '0')));
    }

    public static final File getNextFrameSequenceDirectoryName(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        for (File file2 : SequencesKt.map(CollectionsKt.asSequence(RangesKt.until(0, com.salesforce.marketingcloud.config.a.n)), new e(file))) {
            if (!file2.exists()) {
                return file2;
            }
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }
}
