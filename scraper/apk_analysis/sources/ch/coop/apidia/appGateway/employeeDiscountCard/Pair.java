package ch.coop.apidia.appGateway.employeeDiscountCard;

/* loaded from: classes3.dex */
public class Pair {
    private String name = "";
    private String value = "";

    private boolean isValidString(String str) {
        return str != null;
    }

    public Pair(String str, String str2) {
        setName(str);
        setValue(str2);
    }

    private void setName(String str) {
        if (isValidString(str)) {
            this.name = str;
        }
    }

    private void setValue(String str) {
        if (isValidString(str)) {
            this.value = str;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }
}
