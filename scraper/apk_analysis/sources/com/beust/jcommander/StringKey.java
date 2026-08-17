package com.beust.jcommander;

import com.beust.jcommander.FuzzyMap;

/* loaded from: classes3.dex */
public class StringKey implements FuzzyMap.IKey {
    private String name;

    public StringKey(String str) {
        this.name = str;
    }

    @Override // com.beust.jcommander.FuzzyMap.IKey
    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public int hashCode() {
        String str = this.name;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StringKey stringKey = (StringKey) obj;
        String str = this.name;
        if (str == null) {
            if (stringKey.name != null) {
                return false;
            }
        } else if (!str.equals(stringKey.name)) {
            return false;
        }
        return true;
    }
}
