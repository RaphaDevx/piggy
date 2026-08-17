package com.getcapacitor.community.inappreview;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "InAppReview")
/* loaded from: classes3.dex */
public class InAppReviewPlugin extends Plugin {
    private InAppReview implementation = new InAppReview();

    @PluginMethod
    public void requestReview(PluginCall pluginCall) {
        this.implementation.requestReview(pluginCall, getActivity());
    }
}
