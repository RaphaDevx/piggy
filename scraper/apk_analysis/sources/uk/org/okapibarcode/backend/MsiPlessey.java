package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class MsiPlessey extends Symbol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String[] MSI_PLESS_TABLE = {"12121212", "12121221", "12122112", "12122121", "12211212", "12211221", "12212112", "12212121", "21121212", "21121221"};
    private CheckDigit checkDigit = CheckDigit.NONE;
    private boolean checkDigitInHumanReadableText = true;
    private double moduleWidthRatio = 2.0d;

    public enum CheckDigit {
        NONE,
        MOD10,
        MOD10_MOD10,
        MOD11,
        MOD11_MOD10
    }

    public void setCheckDigit(CheckDigit checkDigit) {
        this.checkDigit = checkDigit;
    }

    public CheckDigit getCheckDigit() {
        return this.checkDigit;
    }

    public void setCheckDigitInHumanReadableText(boolean z) {
        this.checkDigitInHumanReadableText = z;
    }

    public boolean getCheckDigitInHumanReadableText() {
        return this.checkDigitInHumanReadableText;
    }

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        int length = this.content.length();
        StringBuilder sb = new StringBuilder(((maxCheckDigits(this.checkDigit) + length) * 8) + 5);
        sb.append("21");
        for (int i = 0; i < length; i++) {
            sb.append(MSI_PLESS_TABLE[Character.getNumericValue(this.content.charAt(i))]);
        }
        String str = this.content;
        if (this.checkDigit == CheckDigit.MOD10 || this.checkDigit == CheckDigit.MOD10_MOD10) {
            int calcMod10 = calcMod10(this.content);
            sb.append(MSI_PLESS_TABLE[calcMod10]);
            str = str + calcMod10;
        }
        if (this.checkDigit == CheckDigit.MOD11 || this.checkDigit == CheckDigit.MOD11_MOD10) {
            int calcMod11 = calcMod11(this.content);
            str = str + calcMod11;
            if (calcMod11 == 10) {
                String[] strArr = MSI_PLESS_TABLE;
                sb.append(strArr[1]);
                sb.append(strArr[0]);
            } else {
                sb.append(MSI_PLESS_TABLE[calcMod11]);
            }
        }
        if (this.checkDigit == CheckDigit.MOD10_MOD10 || this.checkDigit == CheckDigit.MOD11_MOD10) {
            int calcMod102 = calcMod10(str);
            sb.append(MSI_PLESS_TABLE[calcMod102]);
            str = str + calcMod102;
        }
        sb.append("121");
        if (!this.checkDigitInHumanReadableText) {
            str = this.content;
        }
        this.readable = str;
        this.pattern = new String[]{sb.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    /* renamed from: uk.org.okapibarcode.backend.MsiPlessey$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$MsiPlessey$CheckDigit;

        static {
            int[] iArr = new int[CheckDigit.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$MsiPlessey$CheckDigit = iArr;
            try {
                iArr[CheckDigit.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MsiPlessey$CheckDigit[CheckDigit.MOD10.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MsiPlessey$CheckDigit[CheckDigit.MOD11.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MsiPlessey$CheckDigit[CheckDigit.MOD10_MOD10.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MsiPlessey$CheckDigit[CheckDigit.MOD11_MOD10.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static int maxCheckDigits(CheckDigit checkDigit) {
        int i = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$MsiPlessey$CheckDigit[checkDigit.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3 || i == 4) {
            return 2;
        }
        if (i == 5) {
            return 3;
        }
        throw new IllegalStateException("Unknown check digit scheme: " + checkDigit);
    }

    private static int calcMod10(String str) {
        int length = str.length() % 2;
        int i = 0;
        for (int length2 = str.length() - 1; length2 >= 0; length2--) {
            int charAt = str.charAt(length2) - '0';
            if (length2 % 2 != length) {
                int i2 = charAt * 2;
                i += i2 % 10;
                charAt = i2 / 10;
            }
            i += charAt;
        }
        int i3 = 10 - (i % 10);
        if (i3 == 10) {
            return 0;
        }
        return i3;
    }

    private static int calcMod11(String str) {
        int i = 0;
        int i2 = 2;
        for (int length = str.length() - 1; length >= 0; length--) {
            i += (str.charAt(length) - '0') * i2;
            i2++;
            if (i2 == 8) {
                i2 = 2;
            }
        }
        int i3 = 11 - (i % 11);
        if (i3 == 11) {
            return 0;
        }
        return i3;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected double getModuleWidth(int i) {
        if (i == 1) {
            return 1.0d;
        }
        return this.moduleWidthRatio;
    }
}
