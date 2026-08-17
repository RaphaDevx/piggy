package com.scandit.datacapture.core.internal.module.data;

import com.scandit.datacapture.core.common.buffer.EncodingRange;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends NativeDataDecoding {
    @Override // com.scandit.datacapture.core.internal.module.data.NativeDataDecoding
    public final String decode(byte[] rawData, ArrayList dataEncoding) {
        Intrinsics.checkNotNullParameter(rawData, "rawData");
        Intrinsics.checkNotNullParameter(dataEncoding, "dataEncoding");
        if (rawData.length != 0 && dataEncoding.isEmpty()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        CharBuffer allocate = CharBuffer.allocate(rawData.length * 4);
        Iterator it = dataEncoding.iterator();
        while (it.hasNext()) {
            EncodingRange encodingRange = (EncodingRange) it.next();
            try {
                Charset.forName(encodingRange.getIanaName()).newDecoder().decode(ByteBuffer.wrap(rawData, encodingRange.getStartIndex(), encodingRange.getEndIndex() - encodingRange.getStartIndex()), allocate, true);
            } catch (Exception unused) {
                return null;
            }
        }
        if (allocate.position() == 0 && rawData.length != 0) {
            return null;
        }
        char[] array = allocate.array();
        Intrinsics.checkNotNullExpressionValue(array, "array(...)");
        return new String(array, 0, allocate.position());
    }
}
