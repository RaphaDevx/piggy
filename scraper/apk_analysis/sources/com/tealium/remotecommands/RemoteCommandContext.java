package com.tealium.remotecommands;

import java.util.Map;

/* loaded from: classes2.dex */
public interface RemoteCommandContext {
    void track(String str, String str2, Map<String, ?> map);

    void track(String str, Map<String, ?> map);
}
