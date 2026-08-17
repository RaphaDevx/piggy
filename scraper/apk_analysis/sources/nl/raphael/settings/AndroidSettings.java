package nl.raphael.settings;

import com.google.firebase.messaging.Constants;

/* loaded from: classes4.dex */
public enum AndroidSettings {
    Accessibility("accessibility", "android.settings.ACCESSIBILITY_SETTINGS"),
    Account("account", "android.settings.ADD_ACCOUNT_SETTINGS"),
    AirplaneMode("airplane_mode", "android.settings.AIRPLANE_MODE_SETTINGS"),
    Apn("apn", "android.settings.APN_SETTINGS"),
    ApplicationDetails("application_details", "android.settings.APPLICATION_DETAILS_SETTINGS"),
    ApplicationDevelopment("application_development", "android.settings.APPLICATION_DEVELOPMENT_SETTINGS"),
    Application("application", "android.settings.APPLICATION_SETTINGS"),
    AppNotification("app_notification", "android.settings.APP_NOTIFICATION_SETTINGS"),
    AppNotifcationPolicy("app_notification_policy", "android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"),
    BatteryOptimization("battery_optimization", "android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS"),
    Bluetooth("bluetooth", "android.settings.BLUETOOTH_SETTINGS"),
    Captioning("captioning", "android.settings.CAPTIONING_SETTINGS"),
    Cast("cast", "android.settings.CAST_SETTINGS"),
    DataRoaming("data_roaming", "android.settings.DATA_ROAMING_SETTINGS"),
    Date("date", "android.settings.DATE_SETTINGS"),
    Display(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, "android.settings.DISPLAY_SETTINGS"),
    Dream("dream", "android.settings.DREAM_SETTINGS"),
    Home("home", "android.settings.HOME_SETTINGS"),
    Keyboard("keyboard", "android.settings.INPUT_METHOD_SETTINGS"),
    KeyboardSubType("keyboard_subtype", "android.settings.INPUT_METHOD_SUBTYPE_SETTINGS"),
    Locale("locale", "android.settings.LOCALE_SETTINGS"),
    Location("location", "android.settings.LOCATION_SOURCE_SETTINGS"),
    ManageApplications("manage_applications", "android.settings.MANAGE_APPLICATIONS_SETTINGS"),
    ManageAllApplications("manage_all_applications", "android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS"),
    MemoryCard("memory_card", "android.settings.MEMORY_CARD_SETTINGS"),
    Network("network", "android.settings.NETWORK_OPERATOR_SETTINGS"),
    NfcSharing("nfcsharing", "android.settings.NFCSHARING_SETTINGS"),
    NfcPayment("nfc_payment", "android.settings.NFC_PAYMENT_SETTINGS"),
    NfcSettings("nfc_settings", "android.settings.NFC_SETTINGS"),
    Print("print", "android.settings.ACTION_PRINT_SETTINGS"),
    Privacy("privacy", "android.settings.PRIVACY_SETTINGS"),
    QuickLaunch("quick_launch", "android.settings.QUICK_LAUNCH_SETTINGS"),
    Search("search", "android.search.action.SEARCH_SETTINGS"),
    Security("security", "android.settings.SECURITY_SETTINGS"),
    Settings("settings", "android.settings.SETTINGS"),
    ShowRegulatoryInfo("show_regulatory_info", "android.settings.SHOW_REGULATORY_INFO"),
    Sound("sound", "android.settings.SOUND_SETTINGS"),
    Storage("storage", "android.settings.INTERNAL_STORAGE_SETTINGS"),
    Sync("sync", "android.settings.SYNC_SETTINGS"),
    TextToSpeech("text_to_speech", CustomAndroidSettings.ACTION_TTS_SETTINGS),
    Usage("usage", "android.settings.USAGE_ACCESS_SETTINGS"),
    UserDictionary("user_dictionary", "android.settings.USER_DICTIONARY_SETTINGS"),
    VoiceInput("voice_input", "android.settings.VOICE_INPUT_SETTINGS"),
    VPN("vpn", "android.settings.VPN_SETTINGS"),
    Wifi("wifi", "android.settings.WIFI_SETTINGS"),
    WifiIp("wifi_ip", "android.settings.WIFI_IP_SETTINGS"),
    Wireless("wireless", "android.settings.WIRELESS_SETTINGS"),
    ZenMode("zen_mode", CustomAndroidSettings.ACTION_ZEN_MODE_SETTINGS),
    ZenModePriority("zen_mode_priority", "android.settings.ZEN_MODE_PRIORITY_SETTINGS"),
    ZenModeBlockedEffects("zen_mode_blocked_effects", CustomAndroidSettings.ACTION_ZEN_MODE_BLOCKED_EFFECTS_SETTINGS);

    private final String setting;
    private final String value;

    AndroidSettings(String str, String str2) {
        this.value = str;
        this.setting = str2;
    }

    public static String getAction(String str) {
        for (AndroidSettings androidSettings : values()) {
            if (androidSettings.value.equals(str)) {
                return androidSettings.setting;
            }
        }
        return null;
    }
}
