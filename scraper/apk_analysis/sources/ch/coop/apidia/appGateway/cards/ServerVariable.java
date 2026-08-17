package ch.coop.apidia.appGateway.cards;

import java.util.HashSet;

/* loaded from: classes3.dex */
public class ServerVariable {
    public String defaultValue;
    public String description;
    public HashSet<String> enumValues;

    public ServerVariable(String str, String str2, HashSet<String> hashSet) {
        this.description = str;
        this.defaultValue = str2;
        this.enumValues = hashSet;
    }
}
