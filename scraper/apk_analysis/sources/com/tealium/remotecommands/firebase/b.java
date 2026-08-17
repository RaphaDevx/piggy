package com.tealium.remotecommands.firebase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class b implements a {
    private static Map<String, String> b;
    private static Map<String, String> c;
    private FirebaseAnalytics a;

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        hashMap.put("event_add_payment_info", FirebaseAnalytics.Event.ADD_PAYMENT_INFO);
        b.put("event_add_shipping_info", FirebaseAnalytics.Event.ADD_SHIPPING_INFO);
        b.put("event_add_to_cart", FirebaseAnalytics.Event.ADD_TO_CART);
        b.put("event_add_to_wishlist", FirebaseAnalytics.Event.ADD_TO_WISHLIST);
        b.put("event_app_open", "app_open");
        b.put("event_begin_checkout", FirebaseAnalytics.Event.BEGIN_CHECKOUT);
        b.put("event_campaign_details", FirebaseAnalytics.Event.CAMPAIGN_DETAILS);
        b.put("event_earn_virtual_currency", FirebaseAnalytics.Event.EARN_VIRTUAL_CURRENCY);
        b.put("event_generate_lead", FirebaseAnalytics.Event.GENERATE_LEAD);
        b.put("event_join_group", FirebaseAnalytics.Event.JOIN_GROUP);
        b.put("event_level_end", FirebaseAnalytics.Event.LEVEL_END);
        b.put("event_level_start", FirebaseAnalytics.Event.LEVEL_START);
        b.put("event_level_up", FirebaseAnalytics.Event.LEVEL_UP);
        b.put("event_login", "login");
        b.put("event_post_score", FirebaseAnalytics.Event.POST_SCORE);
        b.put("event_purchase", FirebaseAnalytics.Event.PURCHASE);
        b.put("event_refund", "refund");
        b.put("event_remove_cart", FirebaseAnalytics.Event.REMOVE_FROM_CART);
        b.put("event_search", "search");
        b.put("event_select_content", FirebaseAnalytics.Event.SELECT_CONTENT);
        b.put("event_select_item", FirebaseAnalytics.Event.SELECT_ITEM);
        b.put("event_select_promotion", FirebaseAnalytics.Event.SELECT_PROMOTION);
        b.put("event_share", FirebaseAnalytics.Event.SHARE);
        b.put("event_signup", FirebaseAnalytics.Event.SIGN_UP);
        b.put("event_spend_virtual_currency", FirebaseAnalytics.Event.SPEND_VIRTUAL_CURRENCY);
        b.put("event_tutorial_begin", FirebaseAnalytics.Event.TUTORIAL_BEGIN);
        b.put("event_tutorial_complete", FirebaseAnalytics.Event.TUTORIAL_COMPLETE);
        b.put("event_unlock_achievement", FirebaseAnalytics.Event.UNLOCK_ACHIEVEMENT);
        b.put("event_view_cart", FirebaseAnalytics.Event.VIEW_CART);
        b.put("event_view_item", FirebaseAnalytics.Event.VIEW_ITEM);
        b.put("event_view_item_list", FirebaseAnalytics.Event.VIEW_ITEM_LIST);
        b.put("event_view_promotion", FirebaseAnalytics.Event.VIEW_PROMOTION);
        b.put("event_view_search_results", FirebaseAnalytics.Event.VIEW_SEARCH_RESULTS);
        HashMap hashMap2 = new HashMap();
        c = hashMap2;
        hashMap2.put("param_achievement_id", FirebaseAnalytics.Param.ACHIEVEMENT_ID);
        c.put("param_ad_network_click_id", FirebaseAnalytics.Param.ACLID);
        c.put("param_affiliation", FirebaseAnalytics.Param.AFFILIATION);
        c.put("param_cp1", FirebaseAnalytics.Param.CP1);
        c.put("param_campaign", "campaign");
        c.put("param_character", FirebaseAnalytics.Param.CHARACTER);
        c.put("param_content", FirebaseAnalytics.Param.CONTENT);
        c.put("param_content_type", "content_type");
        c.put("param_coupon", FirebaseAnalytics.Param.COUPON);
        c.put("param_creative_name", FirebaseAnalytics.Param.CREATIVE_NAME);
        c.put("param_creative_slot", FirebaseAnalytics.Param.CREATIVE_SLOT);
        c.put("param_currency", "currency");
        c.put("param_destination", FirebaseAnalytics.Param.DESTINATION);
        c.put("param_discount", FirebaseAnalytics.Param.DISCOUNT);
        c.put("param_end_date", "end_date");
        c.put("param_extend_session", FirebaseAnalytics.Param.EXTEND_SESSION);
        c.put("param_flight_number", FirebaseAnalytics.Param.FLIGHT_NUMBER);
        c.put("param_group_id", FirebaseAnalytics.Param.GROUP_ID);
        c.put(FirebaseConstants.ItemProperties.INDEX, FirebaseAnalytics.Param.INDEX);
        c.put("param_items", "items");
        c.put(FirebaseConstants.ItemProperties.BRAND, FirebaseAnalytics.Param.ITEM_BRAND);
        c.put(FirebaseConstants.ItemProperties.CATEGORY, FirebaseAnalytics.Param.ITEM_CATEGORY);
        c.put(FirebaseConstants.ItemProperties.ID, FirebaseAnalytics.Param.ITEM_ID);
        c.put("param_item_list_id", FirebaseAnalytics.Param.ITEM_LIST_ID);
        c.put("param_item_list_name", FirebaseAnalytics.Param.ITEM_LIST_NAME);
        c.put(FirebaseConstants.ItemProperties.NAME, FirebaseAnalytics.Param.ITEM_NAME);
        c.put(FirebaseConstants.ItemProperties.VARIANT, FirebaseAnalytics.Param.ITEM_VARIANT);
        c.put("param_level", "level");
        c.put("param_level_name", FirebaseAnalytics.Param.LEVEL_NAME);
        c.put("param_location", "location");
        c.put("param_location_id", FirebaseAnalytics.Param.LOCATION_ID);
        c.put("param_medium", "medium");
        c.put("param_method", "method");
        c.put("param_number_nights", FirebaseAnalytics.Param.NUMBER_OF_NIGHTS);
        c.put("param_number_pax", FirebaseAnalytics.Param.NUMBER_OF_PASSENGERS);
        c.put("param_number_rooms", FirebaseAnalytics.Param.NUMBER_OF_ROOMS);
        c.put("param_origin", "origin");
        c.put("param_payment_type", FirebaseAnalytics.Param.PAYMENT_TYPE);
        c.put(FirebaseConstants.ItemProperties.PRICE, FirebaseAnalytics.Param.PRICE);
        c.put("param_promotion_id", FirebaseAnalytics.Param.PROMOTION_ID);
        c.put("param_promotion_name", FirebaseAnalytics.Param.PROMOTION_NAME);
        c.put(FirebaseConstants.ItemProperties.QUANTITY, "quantity");
        c.put("param_score", FirebaseAnalytics.Param.SCORE);
        c.put("param_search_term", FirebaseAnalytics.Param.SEARCH_TERM);
        c.put("param_shipping", FirebaseAnalytics.Param.SHIPPING);
        c.put("param_shipping_tier", FirebaseAnalytics.Param.SHIPPING_TIER);
        c.put("param_source", "source");
        c.put("param_start_date", "start_date");
        c.put("param_success", "success");
        c.put("param_tax", FirebaseAnalytics.Param.TAX);
        c.put("param_term", FirebaseAnalytics.Param.TERM);
        c.put("param_transaction_id", "transaction_id");
        c.put("param_travel_class", FirebaseAnalytics.Param.TRAVEL_CLASS);
        c.put("param_value", "value");
        c.put("param_virtual_currency_name", FirebaseAnalytics.Param.VIRTUAL_CURRENCY_NAME);
    }

    public b(Context context) {
        this.a = FirebaseAnalytics.getInstance(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x010d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0009 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static android.os.Bundle a(org.json.JSONObject r8) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tealium.remotecommands.firebase.b.a(org.json.JSONObject):android.os.Bundle");
    }

    private static String b(String str) {
        String str2 = b.get(str);
        return str2 == null ? str : str2;
    }

    private static String c(String str) {
        String str2 = c.get(str);
        return str2 == null ? str : str2;
    }

    @Override // com.tealium.remotecommands.firebase.a
    public void a() {
        this.a.resetAnalyticsData();
    }

    @Override // com.tealium.remotecommands.firebase.a
    public void a(Activity activity, String str, String str2) {
        if (str != null) {
            this.a.setCurrentScreen(activity, str, str2);
        }
    }

    @Override // com.tealium.remotecommands.firebase.a
    public void a(Integer num, Integer num2, Boolean bool) {
        if (num.intValue() > 0) {
            this.a.setSessionTimeoutDuration(num.intValue());
        }
        if (num2.intValue() > 0) {
            this.a.setMinimumSessionDuration(num2.longValue());
        }
        if (bool != null) {
            this.a.setAnalyticsCollectionEnabled(bool.booleanValue());
        }
    }

    @Override // com.tealium.remotecommands.firebase.a
    public void a(String str) {
        if (str != null) {
            this.a.setUserId(str);
        }
    }

    @Override // com.tealium.remotecommands.firebase.a
    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.a.setUserProperty(str, str2);
    }

    @Override // com.tealium.remotecommands.firebase.a
    public void a(String str, JSONObject jSONObject) {
        Bundle bundle;
        try {
            bundle = a(jSONObject);
        } catch (NullPointerException | JSONException unused) {
            bundle = null;
        }
        if (str != null) {
            this.a.logEvent(b(str), bundle);
        }
    }
}
