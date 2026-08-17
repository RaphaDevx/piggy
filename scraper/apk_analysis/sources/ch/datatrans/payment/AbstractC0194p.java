package ch.datatrans.payment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: ch.datatrans.payment.p, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0194p {
    public static final int a(C0189o c0189o, C0189o c0189o2) {
        int i = c0189o.f;
        int i2 = c0189o2.f;
        if (i != i2) {
            return Intrinsics.compare(i, i2);
        }
        int i3 = c0189o.g;
        int i4 = c0189o2.g;
        if (i3 != i4) {
            return Intrinsics.compare(i4, i3);
        }
        throw new IllegalArgumentException(("Duplicated range: " + c0189o).toString());
    }

    public static void b(ArrayList ranges) {
        Intrinsics.checkNotNullParameter(ranges, "ranges");
        List<C0189o> a = a(ranges);
        LinkedList linkedList = new LinkedList();
        for (C0189o c0189o : a) {
            LinkedList linkedList2 = new LinkedList(linkedList);
            while (true) {
                if (linkedList2.isEmpty()) {
                    break;
                }
                if (c0189o.f > ((C0189o) linkedList2.getLast()).g) {
                } else if (c0189o.g > ((C0189o) linkedList2.getLast()).g) {
                    throw new IllegalArgumentException("Partially overlapping ranges: " + linkedList2.getLast() + ", " + c0189o);
                }
            }
            linkedList2.addLast(c0189o);
            linkedList = linkedList2;
        }
    }

    public static List a(ArrayList arrayList) {
        return CollectionsKt.sortedWith(new ArrayList(arrayList), new Comparator() { // from class: ch.datatrans.payment.p$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return AbstractC0194p.a((C0189o) obj, (C0189o) obj2);
            }
        });
    }
}
