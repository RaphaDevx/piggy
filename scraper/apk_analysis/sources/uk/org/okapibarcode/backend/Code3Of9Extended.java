package uk.org.okapibarcode.backend;

import androidx.exifinterface.media.ExifInterface;
import com.tealium.library.BuildConfig;
import org.apache.commons.lang3.StringUtils;
import uk.org.okapibarcode.backend.Code3Of9;

/* loaded from: classes4.dex */
public class Code3Of9Extended extends Symbol {
    private static final String[] E_CODE_39 = {"%U", "$A", "$B", "$C", "$D", "$E", "$F", "$G", "$H", "$I", "$J", "$K", "$L", "$M", "$N", "$O", "$P", "$Q", "$R", "$S", "$T", "$U", "$V", "$W", "$X", "$Y", "$Z", "%A", "%B", "%C", "%D", "%E", StringUtils.SPACE, "/A", "/B", "/C", "/D", "/E", "/F", "/G", "/H", "/I", "/J", "/K", "/L", "-", ".", "/O", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", BuildConfig.PUBLISH_SETTINGS_VERSION, "6", "7", "8", "9", "/Z", "%F", "%G", "%H", "%I", "%J", "%V", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "%K", "%L", "%M", "%N", "%O", "%W", "+A", "+B", "+C", "+D", "+E", "+F", "+G", "+H", "+I", "+J", "+K", "+L", "+M", "+N", "+O", "+P", "+Q", "+R", "+S", "+T", "+U", "+V", "+W", "+X", "+Y", "+Z", "%P", "%Q", "%R", "%S", "%T"};
    private CheckDigit checkDigit = CheckDigit.NONE;

    public enum CheckDigit {
        NONE,
        MOD43
    }

    public void setCheckDigit(CheckDigit checkDigit) {
        this.checkDigit = checkDigit;
    }

    public CheckDigit getCheckDigit() {
        return this.checkDigit;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[\u0000-\u007f]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        StringBuilder sb = new StringBuilder(this.content.length() * 2);
        for (int i = 0; i < this.content.length(); i++) {
            sb.append(E_CODE_39[this.content.charAt(i)]);
        }
        Code3Of9 code3Of9 = new Code3Of9();
        if (this.checkDigit == CheckDigit.MOD43) {
            code3Of9.setCheckDigit(Code3Of9.CheckDigit.MOD43);
        }
        code3Of9.setContent(sb.toString());
        this.readable = this.content;
        this.pattern = new String[]{code3Of9.pattern[0]};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }
}
