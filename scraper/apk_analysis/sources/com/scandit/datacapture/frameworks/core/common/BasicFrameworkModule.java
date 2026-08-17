package com.scandit.datacapture.frameworks.core.common;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.salesforce.marketingcloud.UrlHandler;
import com.scandit.datacapture.frameworks.core.deserialization.DeserializationLifecycleObserver;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import com.scandit.datacapture.frameworks.core.mode.FrameworksBaseMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: BasicFrameworkModule.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0004J\u001e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0004J\u0017\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\bH\u0004¢\u0006\u0002\u0010\u0019J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0004J\u0017\u0010\u001c\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\bH\u0004¢\u0006\u0002\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0017\u001a\u00020\bH\u0004J\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0!2\u0006\u0010\u0011\u001a\u00020\bH\u0004J\u001c\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0!2\u0006\u0010\u0017\u001a\u00020\bH\u0004J\u000f\u0010#\u001a\u0004\u0018\u00018\u0000H\u0004¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\u000fH\u0004J\u0017\u0010&\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\bH\u0004¢\u0006\u0002\u0010\u001dR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\nX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/common/BasicFrameworkModule;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/scandit/datacapture/frameworks/core/common/BaseFrameworkModule;", "deserializationLifecycleObserver", "Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;", "(Lcom/scandit/datacapture/frameworks/core/deserialization/DeserializationLifecycleObserver;)V", "createdModes", "", "", "modesCache", "", "postModeCreationActions", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lkotlin/Function0;", "", "addModeToCache", ExtentionsKt.MODE_ID_KEY, "mode", "(ILjava/lang/Object;)V", "addPostModeCreationAction", UrlHandler.ACTION, "addPostModeCreationActionByParent", "parentId", "clearPostModeCreationActions", "(Ljava/lang/Integer;)V", "getAllModesInCache", "", "getModeFromCache", "(I)Ljava/lang/Object;", "getModeFromCacheByParent", "Lcom/scandit/datacapture/frameworks/core/mode/FrameworksBaseMode;", "getPostModeCreationActions", "Lkotlin/sequences/Sequence;", "getPostModeCreationActionsByParent", "getTopmostMode", "()Ljava/lang/Object;", "removeAllModesFromCache", "removeModeFromCache", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class BasicFrameworkModule<T> extends BaseFrameworkModule {
    private final List<Integer> createdModes;
    private final Map<Integer, T> modesCache;
    private final ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Function0<Unit>>> postModeCreationActions;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicFrameworkModule(DeserializationLifecycleObserver deserializationLifecycleObserver) {
        super(deserializationLifecycleObserver, null, 2, null);
        Intrinsics.checkNotNullParameter(deserializationLifecycleObserver, "deserializationLifecycleObserver");
        this.postModeCreationActions = new ConcurrentHashMap<>();
        this.modesCache = new ConcurrentHashMap();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList());
        Intrinsics.checkNotNullExpressionValue(synchronizedList, "synchronizedList(...)");
        this.createdModes = synchronizedList;
    }

    protected final void addModeToCache(int modeId, T mode) {
        this.modesCache.put(Integer.valueOf(modeId), mode);
        this.createdModes.add(Integer.valueOf(modeId));
    }

    protected final T getModeFromCache(int modeId) {
        return this.modesCache.get(Integer.valueOf(modeId));
    }

    protected final FrameworksBaseMode getModeFromCacheByParent(int parentId) {
        T t;
        Collection<T> values = this.modesCache.values();
        ArrayList arrayList = new ArrayList();
        for (T t2 : values) {
            if (t2 instanceof FrameworksBaseMode) {
                arrayList.add(t2);
            }
        }
        Iterator<T> it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            t = it.next();
            Integer parentId2 = ((FrameworksBaseMode) t).getParentId();
            if (parentId2 != null && parentId2.intValue() == parentId) {
                break;
            }
        }
        return (FrameworksBaseMode) t;
    }

    protected final List<T> getAllModesInCache() {
        return CollectionsKt.toList(this.modesCache.values());
    }

    protected final T removeModeFromCache(int modeId) {
        this.createdModes.remove(Integer.valueOf(modeId));
        return this.modesCache.remove(Integer.valueOf(modeId));
    }

    protected final T getTopmostMode() {
        Map<Integer, T> map = this.modesCache;
        Integer num = (Integer) CollectionsKt.lastOrNull((List) this.createdModes);
        return map.get(Integer.valueOf(num != null ? num.intValue() : 0));
    }

    protected final void addPostModeCreationAction(int modeId, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (this.postModeCreationActions.get(Integer.valueOf(modeId)) == null) {
            this.postModeCreationActions.put(Integer.valueOf(modeId), new ConcurrentLinkedQueue<>());
        }
        ConcurrentLinkedQueue<Function0<Unit>> concurrentLinkedQueue = this.postModeCreationActions.get(Integer.valueOf(modeId));
        if (concurrentLinkedQueue != null) {
            concurrentLinkedQueue.add(action);
        }
    }

    protected final void addPostModeCreationActionByParent(int parentId, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        if (this.postModeCreationActions.get(Integer.valueOf(parentId)) == null) {
            this.postModeCreationActions.put(Integer.valueOf(parentId), new ConcurrentLinkedQueue<>());
        }
        ConcurrentLinkedQueue<Function0<Unit>> concurrentLinkedQueue = this.postModeCreationActions.get(Integer.valueOf(parentId));
        if (concurrentLinkedQueue != null) {
            concurrentLinkedQueue.add(action);
        }
    }

    protected final void removeAllModesFromCache() {
        this.modesCache.clear();
        this.createdModes.clear();
    }

    protected final Sequence<Function0<Unit>> getPostModeCreationActions(int modeId) {
        return SequencesKt.sequence(new BasicFrameworkModule$getPostModeCreationActions$1(this.postModeCreationActions.get(Integer.valueOf(modeId)), null));
    }

    protected final Sequence<Function0<Unit>> getPostModeCreationActionsByParent(int parentId) {
        return SequencesKt.sequence(new BasicFrameworkModule$getPostModeCreationActionsByParent$1(this.postModeCreationActions.get(Integer.valueOf(parentId)), null));
    }

    protected final void clearPostModeCreationActions(Integer modeId) {
        if (modeId == null) {
            this.postModeCreationActions.clear();
            return;
        }
        Iterator<Map.Entry<Integer, ConcurrentLinkedQueue<Function0<Unit>>>> it = this.postModeCreationActions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, ConcurrentLinkedQueue<Function0<Unit>>> next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            if (Intrinsics.areEqual(next.getKey(), modeId)) {
                it.remove();
            }
        }
    }
}
