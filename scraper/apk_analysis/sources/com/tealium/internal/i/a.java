package com.tealium.internal.i;

import com.tealium.collect.attribute.AttributeGroup;
import com.tealium.collect.attribute.BaseAttribute;
import com.tealium.internal.j.n;
import com.tealium.internal.listeners.MainListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: AttributeUpdateMessenger.java */
/* loaded from: classes2.dex */
abstract class a<L extends MainListener, A extends BaseAttribute> extends n<L> {
    private final AttributeGroup<A> b;
    private List<A> c;
    private List<A> d;
    private List<A> e;

    a(Class<L> cls, AttributeGroup<A> attributeGroup, AttributeGroup<A> attributeGroup2) {
        super(cls);
        this.b = attributeGroup;
        a(attributeGroup, attributeGroup2);
    }

    private void a(AttributeGroup<A> attributeGroup, AttributeGroup<A> attributeGroup2) {
        if (attributeGroup == null && attributeGroup2 == null) {
            return;
        }
        if (attributeGroup == null) {
            Iterator<A> it = attributeGroup2.iterator();
            while (it.hasNext()) {
                g().add(it.next());
            }
            return;
        }
        if (attributeGroup2 == null) {
            Iterator<A> it2 = attributeGroup.iterator();
            while (it2.hasNext()) {
                i().add(it2.next());
            }
            return;
        }
        Iterator<A> it3 = attributeGroup.iterator();
        while (it3.hasNext()) {
            A next = it3.next();
            A a = attributeGroup2.get(next.getId());
            if (a == null) {
                i().add(next);
            } else if (!next.equals(a)) {
                h().add(a);
            }
        }
        Iterator<A> it4 = attributeGroup2.iterator();
        while (it4.hasNext()) {
            A next2 = it4.next();
            if (!attributeGroup.containsId((AttributeGroup<A>) next2)) {
                g().add(next2);
            }
        }
    }

    private List<A> g() {
        List<A> list = this.d;
        if (list != null) {
            return list;
        }
        LinkedList linkedList = new LinkedList();
        this.d = linkedList;
        return linkedList;
    }

    private List<A> h() {
        List<A> list = this.e;
        if (list != null) {
            return list;
        }
        LinkedList linkedList = new LinkedList();
        this.e = linkedList;
        return linkedList;
    }

    private List<A> i() {
        List<A> list = this.c;
        if (list != null) {
            return list;
        }
        LinkedList linkedList = new LinkedList();
        this.c = linkedList;
        return linkedList;
    }

    protected final List<A> b() {
        return this.d;
    }

    protected final List<A> c() {
        return this.e;
    }

    protected final AttributeGroup<A> d() {
        return this.b;
    }

    protected final List<A> e() {
        return this.c;
    }

    public final boolean f() {
        return (this.c == null && this.e == null && this.d == null) ? false : true;
    }
}
