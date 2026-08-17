package ch.coop.capacitor.extensionconnector;

import ch.coop.apidia.appGateway.loyalty.model.LoyaltyDeviceMediumRequestBody;
import ch.coop.capacitor.extensionconnector.widget.WidgetCardType;
import ch.coop.capacitor.extensionconnector.widget.WidgetInfo;
import ch.coop.capacitor.extensionconnector.widget.WidgetSize;
import ch.coop.capacitor.extensionconnector.widget.WidgetType;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import io.sentry.Sentry;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

@CapacitorPlugin(name = "ExtensionConnector")
/* loaded from: classes3.dex */
public class ExtensionConnectorPlugin extends Plugin {
    static final String TAG = "ExtensionConnectorPlugin";
    private static DataUpdateManagerInterface dataUpdateManager;

    public interface DataUpdateManagerInterface {
        Set<WidgetInfo> getInstalledWidgetInfo();

        void startOrRestartPeriodicDataUpdate();

        void updateAppLanguage(String str);

        void updateGiftcardsBalance(Map<String, Integer> map);

        void updateSuperpoints(OffsetDateTime offsetDateTime, int i);
    }

    public static void setDataUpdateManager(DataUpdateManagerInterface dataUpdateManagerInterface) {
        dataUpdateManager = dataUpdateManagerInterface;
    }

    @PluginMethod
    public void updateApplicationContext(PluginCall pluginCall) {
        String string;
        if (hasDataUpdateManager() && (string = pluginCall.getString(LoyaltyDeviceMediumRequestBody.SERIALIZED_NAME_APP_LANGUAGE)) != null) {
            dataUpdateManager.updateAppLanguage(string);
        }
        pluginCall.resolve();
    }

    @PluginMethod
    public void updateSupercardData(PluginCall pluginCall) {
        Integer num = pluginCall.getInt("points");
        if (num != null && hasDataUpdateManager()) {
            dataUpdateManager.updateSuperpoints(OffsetDateTime.now(), num.intValue());
        }
        pluginCall.resolve();
    }

    @PluginMethod
    public void updateGiftcardData(PluginCall pluginCall) {
        JSArray array = pluginCall.getArray("cards");
        if (array != null && array.length() > 0 && hasDataUpdateManager()) {
            try {
                final HashMap hashMap = new HashMap();
                array.toList().forEach(new Consumer() { // from class: ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ExtensionConnectorPlugin.lambda$updateGiftcardData$0(hashMap, (JSONObject) obj);
                    }
                });
                dataUpdateManager.updateGiftcardsBalance(hashMap);
            } catch (JSONException e) {
                Sentry.captureException(e);
            }
        }
        pluginCall.resolve();
    }

    static /* synthetic */ void lambda$updateGiftcardData$0(Map map, JSONObject jSONObject) {
        String optString = jSONObject.optString("cardNr");
        int optInt = jSONObject.optInt("balance");
        if (optString != null) {
            map.put(optString, Integer.valueOf(optInt));
        }
    }

    @PluginMethod
    public void triggerWidgetUpdate(PluginCall pluginCall) {
        if (hasDataUpdateManager()) {
            dataUpdateManager.startOrRestartPeriodicDataUpdate();
        }
        pluginCall.resolve();
    }

    @PluginMethod
    public void triggerCardsDataUpdate(PluginCall pluginCall) {
        triggerWidgetUpdate(pluginCall);
    }

    @PluginMethod
    public void triggerSupercardPayDataUpdate(PluginCall pluginCall) {
        pluginCall.resolve();
    }

    @PluginMethod
    public void getWidgetInfoForAnalytics(PluginCall pluginCall) {
        if (hasDataUpdateManager()) {
            List list = (List) dataUpdateManager.getInstalledWidgetInfo().stream().map(new Function() { // from class: ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ExtensionConnectorPlugin.lambda$getWidgetInfoForAnalytics$1((WidgetInfo) obj);
                }
            }).collect(Collectors.toList());
            JSObject jSObject = new JSObject();
            jSObject.put("widgetInfo", (Object) new JSArray((Collection) list));
            pluginCall.resolve(jSObject);
            return;
        }
        pluginCall.reject("No DataUpdateManager.");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ com.getcapacitor.JSObject lambda$getWidgetInfoForAnalytics$1(ch.coop.capacitor.extensionconnector.widget.WidgetInfo r8) {
        /*
            int[] r0 = ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.AnonymousClass1.$SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType
            ch.coop.capacitor.extensionconnector.widget.WidgetType r1 = r8.getType()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            java.lang.String r1 = "NA"
            java.lang.String r2 = "GK"
            r3 = 3
            r4 = 2
            r5 = 1
            if (r0 == r5) goto L20
            if (r0 == r4) goto L1e
            if (r0 == r3) goto L1b
            r0 = r1
            goto L22
        L1b:
            java.lang.String r0 = "DZK"
            goto L22
        L1e:
            r0 = r2
            goto L22
        L20:
            java.lang.String r0 = "SC"
        L22:
            int[] r6 = ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.AnonymousClass1.$SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetSize
            ch.coop.capacitor.extensionconnector.widget.WidgetSize r7 = r8.getSize()
            int r7 = r7.ordinal()
            r6 = r6[r7]
            if (r6 == r5) goto L36
            if (r6 == r4) goto L33
            goto L38
        L33:
            java.lang.String r1 = "M"
            goto L38
        L36:
            java.lang.String r1 = "S"
        L38:
            ch.coop.capacitor.extensionconnector.widget.WidgetCardType r8 = r8.getCardType()
            if (r8 == 0) goto L53
            int[] r6 = ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.AnonymousClass1.$SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetCardType
            int r8 = r8.ordinal()
            r8 = r6[r8]
            if (r8 == r5) goto L54
            if (r8 == r4) goto L50
            if (r8 == r3) goto L4d
            goto L53
        L4d:
            java.lang.String r2 = "CPRK"
            goto L54
        L50:
            java.lang.String r2 = "MAGK"
            goto L54
        L53:
            r2 = 0
        L54:
            com.getcapacitor.JSObject r8 = new com.getcapacitor.JSObject
            r8.<init>()
            java.lang.String r3 = "type"
            r8.put(r3, r0)
            java.lang.String r0 = "size"
            r8.put(r0, r1)
            if (r2 == 0) goto L6a
            java.lang.String r0 = "cardType"
            r8.put(r0, r2)
        L6a:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin.lambda$getWidgetInfoForAnalytics$1(ch.coop.capacitor.extensionconnector.widget.WidgetInfo):com.getcapacitor.JSObject");
    }

    /* renamed from: ch.coop.capacitor.extensionconnector.ExtensionConnectorPlugin$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetCardType;
        static final /* synthetic */ int[] $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetSize;
        static final /* synthetic */ int[] $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType;

        static {
            int[] iArr = new int[WidgetCardType.values().length];
            $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetCardType = iArr;
            try {
                iArr[WidgetCardType.GIFTCARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetCardType[WidgetCardType.EMPLOYEE_GIFTCARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetCardType[WidgetCardType.EMPLOYEE_DISCOUNT_CARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[WidgetSize.values().length];
            $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetSize = iArr2;
            try {
                iArr2[WidgetSize.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetSize[WidgetSize.MEDIUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[WidgetType.values().length];
            $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType = iArr3;
            try {
                iArr3[WidgetType.SUPERCARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType[WidgetType.GIFTCARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$ch$coop$capacitor$extensionconnector$widget$WidgetType[WidgetType.PAYMENTCARD.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private boolean hasDataUpdateManager() {
        return dataUpdateManager != null;
    }
}
