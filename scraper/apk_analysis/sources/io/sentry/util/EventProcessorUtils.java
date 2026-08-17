package io.sentry.util;

import io.sentry.EventProcessor;
import io.sentry.internal.eventprocessor.EventProcessorAndOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public final class EventProcessorUtils {
    public static List<EventProcessor> unwrap(List<EventProcessorAndOrder> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<EventProcessorAndOrder> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getEventProcessor());
            }
        }
        return new CopyOnWriteArrayList(arrayList);
    }
}
