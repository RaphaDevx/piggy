package uk.org.okapibarcode.gui;

/* loaded from: classes4.dex */
public class SymbolType {
    String guiLabel;
    Encoding symbology;

    public enum Encoding {
        CHANNEL_CODE,
        CODABAR,
        CODE_11,
        CODE25_MATRIX,
        CODE25_IATA,
        CODE25_INDUSTRY,
        CODE25_INTERLEAVED,
        CODE25_DATALOGIC,
        ITF14,
        CODE39,
        CODE39_EXTENDED,
        CODE93,
        DOD_LOGMARS,
        CODE_128,
        NVE18,
        EAN,
        MSI_PLESSEY,
        TELEPEN,
        TELEPEN_NUMERIC,
        UPC_A,
        UPC_E,
        CODABLOCK_F,
        CODE16K,
        CODE49,
        PDF417,
        PDF417_TRUNCATED,
        PDF417_MICRO,
        AZTEC,
        AZTEC_RUNE,
        DATAMATRIX,
        CODE_ONE,
        GRIDMATRIX,
        MAXICODE,
        QR,
        QR_MICRO,
        DB14,
        DB14_STACKED,
        DB14_STACKED_OMNIDIRECT,
        DB_LIMITED,
        DB_EXPANDED,
        DB_EXPANDED_STACKED,
        AUSPOST,
        AUSPOST_REPLY,
        AUSPOST_REROUTE,
        AUSPOST_REDIRECT,
        BRAZIL_CEPNET,
        DP_LEITCODE,
        DP_IDENTCODE,
        KIX_CODE,
        JAPAN_POST,
        KOREA_POST,
        RM4SCC,
        USPS_IMAIL,
        CODE39_HIBC,
        USPS_POSTNET,
        USPS_PLANET,
        CODE_32,
        AZTEC_HIBC,
        CODABLOCK_HIBC,
        CODE_128_HIBC,
        DATAMATRIX_HIBC,
        PDF417_HIBC,
        PDF417_MICRO_HIBC,
        QR_HIBC,
        PHARMA,
        PHARMA_TWOTRACK,
        PZN,
        USPS_IMPB
    }

    public SymbolType(String str, Encoding encoding) {
        this.guiLabel = str;
        this.symbology = encoding;
    }

    public String toString() {
        return this.guiLabel;
    }
}
