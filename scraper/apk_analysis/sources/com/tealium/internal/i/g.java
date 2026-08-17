package com.tealium.internal.i;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.PropertyAttribute;
import com.tealium.collect.listeners.PropertyUpdateListener;
import java.util.Iterator;

/* compiled from: PropertyUpdateMessenger.java */
/* loaded from: classes2.dex */
final class g extends a<PropertyUpdateListener, PropertyAttribute> {
    public g(AttributeGroup<PropertyAttribute> attributeGroup, AttributeGroup<PropertyAttribute> attributeGroup2) {
        super(PropertyUpdateListener.class, attributeGroup, attributeGroup2);
    }

    @Override // com.tealium.internal.j.n
    public void a(PropertyUpdateListener propertyUpdateListener) {
        if (e() != null) {
            Iterator<PropertyAttribute> it = e().iterator();
            while (it.hasNext()) {
                propertyUpdateListener.onPropertyUpdate(it.next(), null);
            }
        }
        if (c() != null) {
            for (PropertyAttribute propertyAttribute : c()) {
                propertyUpdateListener.onPropertyUpdate(d().get(propertyAttribute.getId()), propertyAttribute);
            }
        }
        if (b() != null) {
            Iterator<PropertyAttribute> it2 = b().iterator();
            while (it2.hasNext()) {
                propertyUpdateListener.onPropertyUpdate(null, it2.next());
            }
        }
    }
}
