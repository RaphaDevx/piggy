package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IntFloatMap.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\u0006\u0010\b\u001a\u00020\u0007J\b\u0010\t\u001a\u00020\u0007H\u0002J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0002J\"\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0086\bø\u0001\u0000J\b\u0010\u0012\u001a\u00020\u0007H\u0002J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0003H\u0002J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0011\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0086\nJ\u0011\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0019H\u0086\nJ\u0011\u0010\u0016\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0003H\u0086\nJ\u0011\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u001aH\u0086\nJ\u0011\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0001H\u0086\nJ\u0016\u0010\u001d\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u000fJ\u001e\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000fJ\u000e\u0010 \u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0001J\u000e\u0010!\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0003J\u0016\u0010!\u001a\u00020\"2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u000fJ&\u0010#\u001a\u00020\u00072\u0018\u0010$\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\"0%H\u0086\bø\u0001\u0000J\u0010\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0003H\u0001J\u0010\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u0003H\u0002J\u0019\u0010*\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u000fH\u0086\u0002J\u0006\u0010+\u001a\u00020\u0003J\u0019\u0010,\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020-H\u0082\bR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006."}, d2 = {"Landroidx/collection/MutableIntFloatMap;", "Landroidx/collection/IntFloatMap;", "initialCapacity", "", "(I)V", "growthLimit", "adjustStorage", "", "clear", "dropDeletes", "findFirstAvailableSlot", "hash1", "findInsertIndex", "key", "getOrPut", "", "defaultValue", "Lkotlin/Function0;", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "keys", "Landroidx/collection/IntList;", "Landroidx/collection/IntSet;", "", "plusAssign", "from", "put", "value", "default", "putAll", "remove", "", "removeIf", "predicate", "Lkotlin/Function2;", "removeValueAt", FirebaseAnalytics.Param.INDEX, "resizeStorage", "newCapacity", "set", "trim", "writeMetadata", "", "collection"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MutableIntFloatMap extends IntFloatMap {
    private int growthLimit;

    public MutableIntFloatMap() {
        this(0, 1, null);
    }

    public /* synthetic */ MutableIntFloatMap(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 6 : i);
    }

    public MutableIntFloatMap(int i) {
        super(null);
        if (!(i >= 0)) {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
        }
        initializeStorage(ScatterMapKt.unloadedCapacity(i));
    }

    private final void initializeStorage(int initialCapacity) {
        int max = initialCapacity > 0 ? Math.max(7, ScatterMapKt.normalizeCapacity(initialCapacity)) : 0;
        this._capacity = max;
        initializeMetadata(max);
        this.keys = new int[max];
        this.values = new float[max];
    }

    private final void initializeMetadata(int capacity) {
        long[] jArr;
        if (capacity == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            jArr = new long[((capacity + 15) & (-8)) >> 3];
            ArraysKt.fill$default(jArr, -9187201950435737472L, 0, 0, 6, (Object) null);
        }
        this.metadata = jArr;
        long[] jArr2 = this.metadata;
        int i = capacity >> 3;
        long j = 255 << ((capacity & 7) << 3);
        jArr2[i] = (jArr2[i] & (~j)) | j;
        initializeGrowth();
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(get_capacity()) - this._size;
    }

    public final float getOrPut(int key, Function0<Float> defaultValue) {
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        int findKeyIndex = findKeyIndex(key);
        if (findKeyIndex < 0) {
            float floatValue = defaultValue.invoke().floatValue();
            put(key, floatValue);
            return floatValue;
        }
        return this.values[findKeyIndex];
    }

    public final void set(int key, float value) {
        int findInsertIndex = findInsertIndex(key);
        if (findInsertIndex < 0) {
            findInsertIndex = ~findInsertIndex;
        }
        this.keys[findInsertIndex] = key;
        this.values[findInsertIndex] = value;
    }

    public final void put(int key, float value) {
        set(key, value);
    }

    public final float put(int key, float value, float r5) {
        int findInsertIndex = findInsertIndex(key);
        if (findInsertIndex < 0) {
            findInsertIndex = ~findInsertIndex;
        } else {
            r5 = this.values[findInsertIndex];
        }
        this.keys[findInsertIndex] = key;
        this.values[findInsertIndex] = value;
        return r5;
    }

    public final void plusAssign(IntFloatMap from) {
        Intrinsics.checkNotNullParameter(from, "from");
        putAll(from);
    }

    public final void remove(int key) {
        int findKeyIndex = findKeyIndex(key);
        if (findKeyIndex >= 0) {
            removeValueAt(findKeyIndex);
        }
    }

    public final boolean remove(int key, float value) {
        int findKeyIndex = findKeyIndex(key);
        if (findKeyIndex < 0 || this.values[findKeyIndex] != value) {
            return false;
        }
        removeValueAt(findKeyIndex);
        return true;
    }

    public final void removeIf(Function2<? super Integer, ? super Float, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        if (predicate.invoke(Integer.valueOf(this.keys[i4]), Float.valueOf(this.values[i4])).booleanValue()) {
                            removeValueAt(i4);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void minusAssign(int key) {
        remove(key);
    }

    public final void minusAssign(int[] keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        for (int i : keys) {
            remove(i);
        }
    }

    public final void removeValueAt(int index) {
        this._size--;
        long[] jArr = this.metadata;
        int i = this._capacity;
        int i2 = index >> 3;
        int i3 = (index & 7) << 3;
        long j = (jArr[i2] & (~(255 << i3))) | (254 << i3);
        jArr[i2] = j;
        jArr[(((index - 7) & i) + (i & 7)) >> 3] = j;
    }

    public final void clear() {
        this._size = 0;
        if (this.metadata != ScatterMapKt.EmptyGroup) {
            ArraysKt.fill$default(this.metadata, -9187201950435737472L, 0, 0, 6, (Object) null);
            long[] jArr = this.metadata;
            int i = this._capacity;
            int i2 = i >> 3;
            long j = 255 << ((i & 7) << 3);
            jArr[i2] = (jArr[i2] & (~j)) | j;
        }
        initializeGrowth();
    }

    private final int findFirstAvailableSlot(int hash1) {
        int i = this._capacity;
        int i2 = hash1 & i;
        int i3 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i4 = i2 >> 3;
            int i5 = (i2 & 7) << 3;
            long j = ((jArr[i4 + 1] << (64 - i5)) & ((-i5) >> 63)) | (jArr[i4] >>> i5);
            long j2 = j & ((~j) << 7) & (-9187201950435737472L);
            if (j2 != 0) {
                return (i2 + (Long.numberOfTrailingZeros(j2) >> 3)) & i;
            }
            i3 += 8;
            i2 = (i2 + i3) & i;
        }
    }

    public final int trim() {
        int i = this._capacity;
        int normalizeCapacity = ScatterMapKt.normalizeCapacity(ScatterMapKt.unloadedCapacity(this._size));
        if (normalizeCapacity >= i) {
            return 0;
        }
        resizeStorage(normalizeCapacity);
        return i - this._capacity;
    }

    private final void adjustStorage() {
        if (this._capacity > 8 && Long.compareUnsigned(ULong.m4119constructorimpl(ULong.m4119constructorimpl(this._size) * 32), ULong.m4119constructorimpl(ULong.m4119constructorimpl(this._capacity) * 25)) <= 0) {
            dropDeletes();
        } else {
            resizeStorage(ScatterMapKt.nextCapacity(this._capacity));
        }
    }

    private final void dropDeletes() {
        int[] iArr;
        int i;
        int i2;
        long[] jArr = this.metadata;
        int i3 = this._capacity;
        int[] iArr2 = this.keys;
        float[] fArr = this.values;
        ScatterMapKt.convertMetadataForCleanup(jArr, i3);
        char c = 0;
        int i4 = 0;
        int i5 = -1;
        while (i4 != i3) {
            int i6 = i4 >> 3;
            int i7 = (i4 & 7) << 3;
            long j = (jArr[i6] >> i7) & 255;
            if (j == 128) {
                i5 = i4;
                i4++;
            } else {
                if (j == 254) {
                    int hashCode = Integer.hashCode(iArr2[i4]) * ScatterMapKt.MurmurHashC1;
                    int i8 = (hashCode ^ (hashCode << 16)) >>> 7;
                    int findFirstAvailableSlot = findFirstAvailableSlot(i8);
                    int i9 = i8 & i3;
                    if (((findFirstAvailableSlot - i9) & i3) / 8 == ((i4 - i9) & i3) / 8) {
                        jArr[i6] = ((r10 & 127) << i7) | ((~(255 << i7)) & jArr[i6]);
                        jArr[ArraysKt.getLastIndex(jArr)] = (jArr[c] & 72057594037927935L) | Long.MIN_VALUE;
                    } else {
                        int i10 = findFirstAvailableSlot >> 3;
                        long j2 = jArr[i10];
                        int i11 = (findFirstAvailableSlot & 7) << 3;
                        if (((j2 >> i11) & 255) == 128) {
                            int i12 = i4;
                            iArr = iArr2;
                            jArr[i10] = (j2 & (~(255 << i11))) | ((r10 & 127) << i11);
                            jArr[i6] = (jArr[i6] & (~(255 << i7))) | (128 << i7);
                            iArr[findFirstAvailableSlot] = iArr[i12];
                            iArr[i12] = 0;
                            fArr[findFirstAvailableSlot] = fArr[i12];
                            fArr[i12] = 0.0f;
                            i2 = i12;
                            i5 = i2;
                            i = i3;
                        } else {
                            int i13 = i3;
                            iArr = iArr2;
                            int i14 = i4;
                            jArr[i10] = ((r10 & 127) << i11) | (j2 & (~(255 << i11)));
                            if (i5 == -1) {
                                i = i13;
                                i5 = ScatterMapKt.findEmptySlot(jArr, i14 + 1, i);
                            } else {
                                i = i13;
                            }
                            iArr[i5] = iArr[findFirstAvailableSlot];
                            iArr[findFirstAvailableSlot] = iArr[i14];
                            iArr[i14] = iArr[i5];
                            fArr[i5] = fArr[findFirstAvailableSlot];
                            fArr[findFirstAvailableSlot] = fArr[i14];
                            fArr[i14] = fArr[i5];
                            i2 = i14 - 1;
                        }
                        c = 0;
                        jArr[ArraysKt.getLastIndex(jArr)] = (jArr[0] & 72057594037927935L) | Long.MIN_VALUE;
                        i4 = i2 + 1;
                        i3 = i;
                        iArr2 = iArr;
                    }
                }
                i4++;
            }
        }
        initializeGrowth();
    }

    private final void resizeStorage(int newCapacity) {
        long[] jArr;
        MutableIntFloatMap mutableIntFloatMap = this;
        long[] jArr2 = mutableIntFloatMap.metadata;
        int[] iArr = mutableIntFloatMap.keys;
        float[] fArr = mutableIntFloatMap.values;
        int i = mutableIntFloatMap._capacity;
        initializeStorage(newCapacity);
        long[] jArr3 = mutableIntFloatMap.metadata;
        int[] iArr2 = mutableIntFloatMap.keys;
        float[] fArr2 = mutableIntFloatMap.values;
        int i2 = mutableIntFloatMap._capacity;
        int i3 = 0;
        while (i3 < i) {
            if (((jArr2[i3 >> 3] >> ((i3 & 7) << 3)) & 255) < 128) {
                int i4 = iArr[i3];
                int hashCode = Integer.hashCode(i4) * ScatterMapKt.MurmurHashC1;
                int i5 = hashCode ^ (hashCode << 16);
                int findFirstAvailableSlot = mutableIntFloatMap.findFirstAvailableSlot(i5 >>> 7);
                long j = i5 & 127;
                int i6 = findFirstAvailableSlot >> 3;
                int i7 = (findFirstAvailableSlot & 7) << 3;
                jArr = jArr2;
                long j2 = (jArr3[i6] & (~(255 << i7))) | (j << i7);
                jArr3[i6] = j2;
                jArr3[(((findFirstAvailableSlot - 7) & i2) + (i2 & 7)) >> 3] = j2;
                iArr2[findFirstAvailableSlot] = i4;
                fArr2[findFirstAvailableSlot] = fArr[i3];
            } else {
                jArr = jArr2;
            }
            i3++;
            mutableIntFloatMap = this;
            jArr2 = jArr;
        }
    }

    private final void writeMetadata(int index, long value) {
        long[] jArr = this.metadata;
        int i = index >> 3;
        int i2 = (index & 7) << 3;
        jArr[i] = (jArr[i] & (~(255 << i2))) | (value << i2);
        int i3 = this._capacity;
        int i4 = ((index - 7) & i3) + (i3 & 7);
        int i5 = i4 >> 3;
        int i6 = (i4 & 7) << 3;
        jArr[i5] = (value << i6) | (jArr[i5] & (~(255 << i6)));
    }

    public final void putAll(IntFloatMap from) {
        Intrinsics.checkNotNullParameter(from, "from");
        int[] iArr = from.keys;
        float[] fArr = from.values;
        long[] jArr = from.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        set(iArr[i4], fArr[i4]);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void minusAssign(IntSet keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        int[] iArr = keys.elements;
        long[] jArr = keys.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        remove(iArr[(i << 3) + i3]);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void minusAssign(IntList keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        int[] iArr = keys.content;
        int i = keys._size;
        for (int i2 = 0; i2 < i; i2++) {
            remove(iArr[i2]);
        }
    }

    private final int findInsertIndex(int key) {
        int hashCode = Integer.hashCode(key) * ScatterMapKt.MurmurHashC1;
        int i = hashCode ^ (hashCode << 16);
        int i2 = i >>> 7;
        int i3 = i & 127;
        int i4 = this._capacity;
        int i5 = i2 & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j2 = i3;
            int i9 = i6;
            long j3 = j ^ (j2 * ScatterMapKt.BitmaskLsb);
            for (long j4 = (~j3) & (j3 - ScatterMapKt.BitmaskLsb) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int numberOfTrailingZeros = ((Long.numberOfTrailingZeros(j4) >> 3) + i5) & i4;
                if (this.keys[numberOfTrailingZeros] == key) {
                    return numberOfTrailingZeros;
                }
            }
            if ((((~j) << 6) & j & (-9187201950435737472L)) != 0) {
                int findFirstAvailableSlot = findFirstAvailableSlot(i2);
                if (this.growthLimit == 0 && ((this.metadata[findFirstAvailableSlot >> 3] >> ((findFirstAvailableSlot & 7) << 3)) & 255) != 254) {
                    adjustStorage();
                    findFirstAvailableSlot = findFirstAvailableSlot(i2);
                }
                this._size++;
                int i10 = findFirstAvailableSlot >> 3;
                int i11 = (findFirstAvailableSlot & 7) << 3;
                this.growthLimit -= ((this.metadata[i10] >> i11) & 255) == 128 ? 1 : 0;
                long[] jArr2 = this.metadata;
                int i12 = this._capacity;
                long j5 = ((~(255 << i11)) & jArr2[i10]) | (j2 << i11);
                jArr2[i10] = j5;
                jArr2[(((findFirstAvailableSlot - 7) & i12) + (i12 & 7)) >> 3] = j5;
                return ~findFirstAvailableSlot;
            }
            i6 = i9 + 8;
            i5 = (i5 + i6) & i4;
        }
    }
}
