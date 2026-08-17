package com.tealium.internal.i;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.AudienceAttribute;
import com.tealium.collect.listeners.AudienceUpdateListener;
import java.util.Iterator;

/* compiled from: AudienceUpdateMessenger.java */
/* loaded from: classes2.dex */
final class b extends a<AudienceUpdateListener, AudienceAttribute> {
    public b(AttributeGroup<AudienceAttribute> attributeGroup, AttributeGroup<AudienceAttribute> attributeGroup2) {
        super(AudienceUpdateListener.class, attributeGroup, attributeGroup2);
    }

    @Override // com.tealium.internal.j.n
    public void a(AudienceUpdateListener audienceUpdateListener) {
        if (e() != null) {
            Iterator<AudienceAttribute> it = e().iterator();
            while (it.hasNext()) {
                audienceUpdateListener.onAudienceUpdate(it.next(), null);
            }
        }
        if (c() != null) {
            for (AudienceAttribute audienceAttribute : c()) {
                audienceUpdateListener.onAudienceUpdate(d().get(audienceAttribute.getId()), audienceAttribute);
            }
        }
        if (b() != null) {
            Iterator<AudienceAttribute> it2 = b().iterator();
            while (it2.hasNext()) {
                audienceUpdateListener.onAudienceUpdate(null, it2.next());
            }
        }
    }
}
