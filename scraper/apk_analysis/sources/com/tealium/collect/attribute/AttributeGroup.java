package com.tealium.collect.attribute;

import com.tealium.collect.attribute.BaseAttribute;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public final class AttributeGroup<T extends BaseAttribute> implements Iterable<T> {
    private final BaseAttribute[] a;
    private volatile int b;

    class a implements Iterator<T> {
        private int a = 0;

        a() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.a < AttributeGroup.this.a.length;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Removal is not supported.");
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BaseAttribute[] baseAttributeArr = AttributeGroup.this.a;
            int i = this.a;
            this.a = i + 1;
            return (T) baseAttributeArr[i];
        }
    }

    public AttributeGroup() {
        this.b = 0;
        this.a = new BaseAttribute[0];
    }

    public boolean contains(T t) {
        for (BaseAttribute baseAttribute : this.a) {
            if (baseAttribute.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean containsAll(AttributeGroup<T> attributeGroup) {
        if (attributeGroup == null || attributeGroup.a.length > this.a.length) {
            return false;
        }
        int i = 0;
        while (true) {
            BaseAttribute[] baseAttributeArr = attributeGroup.a;
            if (i >= baseAttributeArr.length) {
                return true;
            }
            if (!contains(baseAttributeArr[i])) {
                return false;
            }
            i++;
        }
    }

    public boolean containsAllIds(AttributeGroup<T> attributeGroup) {
        if (attributeGroup == null || attributeGroup.a.length > this.a.length) {
            return false;
        }
        int i = 0;
        while (true) {
            BaseAttribute[] baseAttributeArr = attributeGroup.a;
            if (i >= baseAttributeArr.length) {
                return true;
            }
            if (!containsId(baseAttributeArr[i].getId())) {
                return false;
            }
            i++;
        }
    }

    public boolean containsId(String str) {
        for (BaseAttribute baseAttribute : this.a) {
            if (baseAttribute.getId().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean equalIds(AttributeGroup<T> attributeGroup) {
        if (attributeGroup == null || attributeGroup.size() != size()) {
            return false;
        }
        for (BaseAttribute baseAttribute : this.a) {
            if (!attributeGroup.containsId(baseAttribute.getId())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0028, code lost:
    
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            boolean r1 = r8 instanceof com.tealium.collect.attribute.AttributeGroup
            if (r1 != 0) goto L9
            return r0
        L9:
            com.tealium.collect.attribute.AttributeGroup r8 = (com.tealium.collect.attribute.AttributeGroup) r8
            com.tealium.collect.attribute.BaseAttribute[] r7 = r7.a
            int r1 = r7.length
            com.tealium.collect.attribute.BaseAttribute[] r2 = r8.a
            int r2 = r2.length
            if (r1 == r2) goto L14
            return r0
        L14:
            int r1 = r7.length
            r2 = r0
        L16:
            if (r2 >= r1) goto L2f
            r3 = r7[r2]
            r4 = r0
        L1b:
            com.tealium.collect.attribute.BaseAttribute[] r5 = r8.a
            int r6 = r5.length
            if (r4 >= r6) goto L2e
            r5 = r5[r4]
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L2b
            int r2 = r2 + 1
            goto L16
        L2b:
            int r4 = r4 + 1
            goto L1b
        L2e:
            return r0
        L2f:
            r7 = 1
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tealium.collect.attribute.AttributeGroup.equals(java.lang.Object):boolean");
    }

    public T get(String str) {
        for (BaseAttribute baseAttribute : this.a) {
            T t = (T) baseAttribute;
            if (t.getId().equals(str)) {
                return t;
            }
        }
        return null;
    }

    public int hashCode() {
        int i = this.b;
        if (i != 0) {
            return i;
        }
        int i2 = 17;
        for (BaseAttribute baseAttribute : this.a) {
            i2 = (i2 * 31) + baseAttribute.hashCode();
        }
        this.b = i2;
        return i2;
    }

    public boolean isEmpty() {
        return this.a.length == 0;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new a();
    }

    public int size() {
        return this.a.length;
    }

    public T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((BaseAttribute[]) Array.newInstance(tArr.getClass().getComponentType(), size));
        }
        for (int i = 0; i < size; i++) {
            tArr[i] = this.a[i];
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    public String toString(String str) {
        String str2;
        String str3;
        Iterator<T> it = iterator();
        if (str == null || str.length() == 0) {
            str = "";
            str2 = "";
            str3 = str2;
        } else {
            str2 = System.getProperty("line.separator");
            str3 = str + str;
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append(str2);
        while (it.hasNext()) {
            sb.append(str3);
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(',');
            }
            sb.append(str2);
        }
        sb.append(str);
        sb.append(']');
        return sb.toString();
    }

    public boolean containsId(T t) {
        return t != null && containsId(t.getId());
    }

    public AttributeGroup(Collection<T> collection) {
        int i = 0;
        this.b = 0;
        if (collection == null) {
            this.a = new BaseAttribute[0];
            return;
        }
        Iterator<T> it = collection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (it.next() != null) {
                i2++;
            }
        }
        BaseAttribute[] baseAttributeArr = new BaseAttribute[i2];
        for (T t : collection) {
            if (t != null) {
                for (int i3 = i - 1; i3 >= 0 && i > 0; i3--) {
                    if (baseAttributeArr[i3].getId().equals(t.getId())) {
                        throw new IllegalArgumentException("The provided collection is not valid. There are duplicate entries with the same ids.");
                    }
                }
                baseAttributeArr[i] = t;
                i++;
            }
        }
        this.a = baseAttributeArr;
    }

    public Object[] toArray() {
        BaseAttribute[] baseAttributeArr = this.a;
        Object[] objArr = new Object[baseAttributeArr.length];
        System.arraycopy(baseAttributeArr, 0, objArr, 0, baseAttributeArr.length);
        return objArr;
    }

    public String toString() {
        return toString(null);
    }
}
