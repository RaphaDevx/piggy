package com.tealium.internal.i;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.FlagAttribute;
import com.tealium.collect.listeners.FlagUpdateListener;
import java.util.Iterator;

/* compiled from: FlagUpdateMessenger.java */
/* loaded from: classes2.dex */
final class e extends a<FlagUpdateListener, FlagAttribute> {
    public e(AttributeGroup<FlagAttribute> attributeGroup, AttributeGroup<FlagAttribute> attributeGroup2) {
        super(FlagUpdateListener.class, attributeGroup, attributeGroup2);
    }

    @Override // com.tealium.internal.j.n
    public void a(FlagUpdateListener flagUpdateListener) {
        if (e() != null) {
            Iterator<FlagAttribute> it = e().iterator();
            while (it.hasNext()) {
                flagUpdateListener.onFlagUpdate(it.next(), null);
            }
        }
        if (c() != null) {
            for (FlagAttribute flagAttribute : c()) {
                flagUpdateListener.onFlagUpdate(d().get(flagAttribute.getId()), flagAttribute);
            }
        }
        if (b() != null) {
            Iterator<FlagAttribute> it2 = b().iterator();
            while (it2.hasNext()) {
                flagUpdateListener.onFlagUpdate(null, it2.next());
            }
        }
    }
}
