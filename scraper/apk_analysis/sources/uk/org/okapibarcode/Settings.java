package uk.org.okapibarcode;

import com.beust.jcommander.Parameter;
import uk.org.okapibarcode.backend.HumanReadableLocation;
import uk.org.okapibarcode.graphics.Color;

/* loaded from: classes4.dex */
public class Settings {

    @Parameter(description = "Supress GUI loading", names = {"-cli"}, required = false)
    private boolean supressGui = false;

    @Parameter(description = "Display table of barcode types", names = {"-t", "--types"}, required = false)
    private boolean displayTypes = false;

    @Parameter(description = "Read data from file", names = {"-i", "--input"}, required = false)
    private String inputFile = "";

    @Parameter(description = "Write image to file", names = {"-o", "--output"}, required = false)
    private String outputFile = "out.png";

    @Parameter(description = "Barcode content", names = {"-d", "--data"}, required = false)
    private String inputData = "";

    @Parameter(description = "Select barcode type", names = {"-b", "--barcode"}, required = false)
    private int symbolType = 20;

    @Parameter(description = "Height of the symbol in multiples of x-dimension", names = {"--height"}, required = false)
    private int symbolHeight = 0;

    @Parameter(description = "Reverse colours (white on black)", names = {"-r", "--reverse"}, required = false)
    private boolean reverseColour = false;

    @Parameter(description = "Specify a foreground (ink) colour", names = {"--fg"}, required = false)
    private String foregroundColour = "000000";

    @Parameter(description = "Specify a background (paper) colour", names = {"--bg"}, required = false)
    private String backgroundColour = "FFFFFF";

    @Parameter(description = "Adjust size of output image", names = {"--scale"}, required = false)
    private int symbolScale = 0;

    @Parameter(description = "Number of columns in PDF417", names = {"--cols"}, required = false)
    private int symbolColumns = 0;

    @Parameter(description = "Set QR Code version number", names = {"--vers"}, required = false)
    private int symbolVersion = 0;

    @Parameter(description = "Set error correction level", names = {"--secure"}, required = false)
    private int symbolECC = 0;

    @Parameter(description = "Add structured primary message", names = {"--primary"}, required = false)
    private String primaryData = "";

    @Parameter(description = "Set encoding mode", names = {"--mode"}, required = false)
    private int encodeMode = 0;

    @Parameter(description = "Treat input as GS1 data", names = {"--gs1"}, required = false)
    private boolean dataGs1Mode = false;

    @Parameter(description = "Treat input as binary data", names = {"--binary"}, required = false)
    private boolean dataBinaryMode = false;

    @Parameter(description = "Remove human readable text", names = {"--notext"}, required = false)
    private boolean supressHrt = false;

    @Parameter(description = "Place human readable text above symbol", names = {"--textabove"}, required = false)
    private boolean superHrt = false;

    @Parameter(description = "Force Data Matrix symbols to be square", names = {"--square"}, required = false)
    private boolean makeSquare = false;

    @Parameter(description = "Add reader initialisation code", names = {"--init"}, required = false)
    private boolean addReaderInit = false;

    @Parameter(description = "Treat each line of input as a separate data set", names = {"--batch"}, required = false)
    private boolean batchMode = false;

    public boolean isGuiSupressed() {
        return this.supressGui;
    }

    public boolean isDisplayTypes() {
        return this.displayTypes;
    }

    public String getInputFile() {
        return this.inputFile;
    }

    public String getOutputFile() {
        return this.outputFile;
    }

    public String getInputData() {
        return this.inputData;
    }

    public int getSymbolType() {
        return this.symbolType;
    }

    public int getSymbolHeight() {
        return this.symbolHeight;
    }

    public boolean isReverseColour() {
        return this.reverseColour;
    }

    public Color getForegroundColour() {
        Color color = Color.BLACK;
        String upperCase = this.foregroundColour.toUpperCase();
        return upperCase.matches("[0-9A-F]{6}") ? new Color(Integer.parseInt(upperCase, 16)) : color;
    }

    public Color getBackgroundColour() {
        Color color = Color.WHITE;
        String upperCase = this.backgroundColour.toUpperCase();
        return upperCase.matches("[0-9A-F]{6}") ? new Color(Integer.parseInt(upperCase, 16)) : color;
    }

    public int getSymbolScale() {
        return this.symbolScale;
    }

    public int getSymbolColumns() {
        return this.symbolColumns;
    }

    public int getSymbolVersion() {
        return this.symbolVersion;
    }

    public int getSymbolECC() {
        return this.symbolECC;
    }

    public String getPrimaryData() {
        return this.primaryData;
    }

    public int getEncodeMode() {
        return this.encodeMode;
    }

    public boolean isDataGs1Mode() {
        return this.dataGs1Mode;
    }

    public boolean isDataBinaryMode() {
        return this.dataBinaryMode;
    }

    public HumanReadableLocation getHrtPosition() {
        HumanReadableLocation humanReadableLocation = HumanReadableLocation.BOTTOM;
        if (this.superHrt) {
            humanReadableLocation = HumanReadableLocation.TOP;
        }
        return this.supressHrt ? HumanReadableLocation.NONE : humanReadableLocation;
    }

    public boolean isMakeSquare() {
        return this.makeSquare;
    }

    public boolean isReaderInit() {
        return this.addReaderInit;
    }

    public boolean isBatchMode() {
        return this.batchMode;
    }
}
