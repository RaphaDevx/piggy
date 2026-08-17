package com.salesforce.marketingcloud.messages;

import com.salesforce.marketingcloud.location.LatLon;

/* loaded from: classes2.dex */
public interface MessageResponse {
    LatLon getRefreshCenter();

    int getRefreshRadius();
}
