package com.tealium.internal.i;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.BadgeAttribute;
import com.tealium.collect.listeners.BadgeUpdateListener;
import java.util.Iterator;

/* compiled from: BadgeUpdateMessenger.java */
/* loaded from: classes2.dex */
final class c extends a<BadgeUpdateListener, BadgeAttribute> {
    public c(AttributeGroup<BadgeAttribute> attributeGroup, AttributeGroup<BadgeAttribute> attributeGroup2) {
        super(BadgeUpdateListener.class, attributeGroup, attributeGroup2);
    }

    @Override // com.tealium.internal.j.n
    public void a(BadgeUpdateListener badgeUpdateListener) {
        if (e() != null) {
            Iterator<BadgeAttribute> it = e().iterator();
            while (it.hasNext()) {
                badgeUpdateListener.onBadgeUpdate(it.next(), null);
            }
        }
        if (c() != null) {
            for (BadgeAttribute badgeAttribute : c()) {
                badgeUpdateListener.onBadgeUpdate(d().get(badgeAttribute.getId()), badgeAttribute);
            }
        }
        if (b() != null) {
            Iterator<BadgeAttribute> it2 = b().iterator();
            while (it2.hasNext()) {
                badgeUpdateListener.onBadgeUpdate(null, it2.next());
            }
        }
    }
}
