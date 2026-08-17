package com.salesforce.marketingcloud.internal;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Iterator;
import java.util.zip.Inflater;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public interface a {

    /* renamed from: com.salesforce.marketingcloud.internal.a$a, reason: collision with other inner class name */
    public static final class C0059a implements a {
        public static final C0059a a = new C0059a();
        private static final String b = com.salesforce.marketingcloud.g.a("Deflate");

        /* renamed from: com.salesforce.marketingcloud.internal.a$a$a, reason: collision with other inner class name */
        static final class C0060a extends Lambda implements Function0<String> {
            final /* synthetic */ boolean b;
            final /* synthetic */ String c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0060a(boolean z, String str) {
                super(0);
                this.b = z;
                this.c = str;
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return "Successful decompression with nowrap=" + this.b + ", " + this.c;
            }
        }

        /* renamed from: com.salesforce.marketingcloud.internal.a$a$b */
        static final class b extends Lambda implements Function0<String> {
            final /* synthetic */ boolean b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            b(boolean z) {
                super(0);
                this.b = z;
            }

            @Override // kotlin.jvm.functions.Function0
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return "Error decompressing data with nowrap=" + this.b;
            }
        }

        private C0059a() {
        }

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof C0059a);
        }

        public int hashCode() {
            return 2019606530;
        }

        public String toString() {
            return "Deflate";
        }

        @Override // com.salesforce.marketingcloud.internal.a
        public String a(String input) {
            Intrinsics.checkNotNullParameter(input, "input");
            byte[] decode = Base64.getDecoder().decode(input);
            Iterator it = CollectionsKt.listOf((Object[]) new Boolean[]{Boolean.FALSE, Boolean.TRUE}).iterator();
            while (it.hasNext()) {
                boolean booleanValue = ((Boolean) it.next()).booleanValue();
                try {
                    Inflater inflater = new Inflater(booleanValue);
                    try {
                        inflater.setInput(decode);
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            byte[] bArr = new byte[1024];
                            while (!inflater.finished()) {
                                byteArrayOutputStream.write(bArr, 0, inflater.inflate(bArr));
                            }
                            String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                            Intrinsics.checkNotNullExpressionValue(byteArrayOutputStream2, "toString(...)");
                            com.salesforce.marketingcloud.g.a.d(b, (Throwable) null, new C0060a(booleanValue, byteArrayOutputStream2));
                            CloseableKt.closeFinally(byteArrayOutputStream, null);
                            return byteArrayOutputStream2;
                        } finally {
                        }
                    } finally {
                        inflater.end();
                    }
                } catch (Exception e) {
                    com.salesforce.marketingcloud.g.a.b(b, e, new b(booleanValue));
                }
            }
            throw new com.salesforce.marketingcloud.push.c(input);
        }
    }

    String a(String str);
}
