package com.tealium.internal.j;

import com.tealium.internal.listeners.EvalJavaScriptListener;

/* compiled from: EvalJavaScriptMessenger.java */
/* loaded from: classes2.dex */
public final class m extends n<EvalJavaScriptListener> {
    private final String b;

    public m(String str) {
        super(EvalJavaScriptListener.class);
        this.b = str;
    }

    @Override // com.tealium.internal.j.n
    public void a(EvalJavaScriptListener evalJavaScriptListener) {
        evalJavaScriptListener.onEvalJavaScript(this.b);
    }
}
