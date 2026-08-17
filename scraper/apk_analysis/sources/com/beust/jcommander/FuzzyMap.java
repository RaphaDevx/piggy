package com.beust.jcommander;

import com.beust.jcommander.internal.Maps;
import java.util.Map;

/* loaded from: classes3.dex */
public class FuzzyMap {

    interface IKey {
        String getName();
    }

    public static <V> V findInMap(Map<? extends IKey, V> map, IKey iKey, boolean z, boolean z2) {
        if (z2) {
            return (V) findAbbreviatedValue(map, iKey, z);
        }
        if (z) {
            return map.get(iKey);
        }
        for (IKey iKey2 : map.keySet()) {
            if (iKey2.getName().equalsIgnoreCase(iKey.getName())) {
                return map.get(iKey2);
            }
        }
        return null;
    }

    private static <V> V findAbbreviatedValue(Map<? extends IKey, V> map, IKey iKey, boolean z) {
        String name = iKey.getName();
        Map newHashMap = Maps.newHashMap();
        for (IKey iKey2 : map.keySet()) {
            String name2 = iKey2.getName();
            if ((z && name2.startsWith(name)) || (!z && name2.toLowerCase().startsWith(name.toLowerCase()))) {
                newHashMap.put(name2, map.get(iKey2));
            }
        }
        if (newHashMap.size() > 1) {
            throw new ParameterException("Ambiguous option: " + iKey + " matches " + newHashMap.keySet());
        }
        if (newHashMap.size() == 1) {
            return newHashMap.values().iterator().next();
        }
        return null;
    }
}
