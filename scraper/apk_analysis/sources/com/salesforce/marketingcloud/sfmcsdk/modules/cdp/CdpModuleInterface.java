package com.salesforce.marketingcloud.sfmcsdk.modules.cdp;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.cdp.consent.Consent;
import com.salesforce.marketingcloud.cdp.events.Event;
import com.salesforce.marketingcloud.cdp.location.Coordinates;
import com.salesforce.marketingcloud.sfmcsdk.modules.ModuleInterface;
import kotlin.Metadata;
import net.openid.appauth.AuthorizationRequest;

/* compiled from: CdpModuleInterface.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&R\u0018\u0010\u0003\u001a\u00020\u0004X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/salesforce/marketingcloud/sfmcsdk/modules/cdp/CdpModuleInterface;", "Lcom/salesforce/marketingcloud/sfmcsdk/modules/ModuleInterface;", "()V", AuthorizationRequest.Prompt.CONSENT, "Lcom/salesforce/marketingcloud/cdp/consent/Consent;", "getConsent", "()Lcom/salesforce/marketingcloud/cdp/consent/Consent;", "setConsent", "(Lcom/salesforce/marketingcloud/cdp/consent/Consent;)V", "setLocation", "", "coordinates", "Lcom/salesforce/marketingcloud/cdp/location/Coordinates;", "expiresIn", "", "track", "event", "Lcom/salesforce/marketingcloud/cdp/events/Event;", "sfmcsdk_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class CdpModuleInterface implements ModuleInterface {
    public abstract Consent getConsent();

    public abstract void setConsent(Consent consent);

    public abstract void setLocation(Coordinates coordinates, long expiresIn);

    public abstract void track(Event event);
}
