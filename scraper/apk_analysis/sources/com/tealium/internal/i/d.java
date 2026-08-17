package com.tealium.internal.i;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.DateAttribute;
import com.tealium.collect.listeners.DateUpdateListener;
import java.util.Iterator;

/* compiled from: DateUpdateMessenger.java */
/* loaded from: classes2.dex */
final class d extends a<DateUpdateListener, DateAttribute> {
    public d(AttributeGroup<DateAttribute> attributeGroup, AttributeGroup<DateAttribute> attributeGroup2) {
        super(DateUpdateListener.class, attributeGroup, attributeGroup2);
    }

    @Override // com.tealium.internal.j.n
    public void a(DateUpdateListener dateUpdateListener) {
        if (e() != null) {
            Iterator<DateAttribute> it = e().iterator();
            while (it.hasNext()) {
                dateUpdateListener.onDateUpdate(it.next(), null);
            }
        }
        if (c() != null) {
            for (DateAttribute dateAttribute : c()) {
                dateUpdateListener.onDateUpdate(d().get(dateAttribute.getId()), dateAttribute);
            }
        }
        if (b() != null) {
            Iterator<DateAttribute> it2 = b().iterator();
            while (it2.hasNext()) {
                dateUpdateListener.onDateUpdate(null, it2.next());
            }
        }
    }
}
