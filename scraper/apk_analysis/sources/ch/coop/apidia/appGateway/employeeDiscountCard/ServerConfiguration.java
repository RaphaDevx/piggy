package ch.coop.apidia.appGateway.employeeDiscountCard;

import java.util.Map;

/* loaded from: classes3.dex */
public class ServerConfiguration {
    public String URL;
    public String description;
    public Map<String, ServerVariable> variables;

    public ServerConfiguration(String str, String str2, Map<String, ServerVariable> map) {
        this.URL = str;
        this.description = str2;
        this.variables = map;
    }

    public String URL(Map<String, String> map) {
        String str = this.URL;
        for (Map.Entry<String, ServerVariable> entry : this.variables.entrySet()) {
            String key = entry.getKey();
            ServerVariable value = entry.getValue();
            String str2 = value.defaultValue;
            if (map != null && map.containsKey(key)) {
                str2 = map.get(key);
                if (value.enumValues.size() > 0 && !value.enumValues.contains(str2)) {
                    throw new IllegalArgumentException("The variable " + key + " in the server URL has invalid value " + str2 + ".");
                }
            }
            str = str.replace("{" + key + "}", str2);
        }
        return str;
    }

    public String URL() {
        return URL(null);
    }
}
