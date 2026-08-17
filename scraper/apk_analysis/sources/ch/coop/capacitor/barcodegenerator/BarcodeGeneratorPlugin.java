package ch.coop.capacitor.barcodegenerator;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import uk.org.okapibarcode.backend.DataMatrix;
import uk.org.okapibarcode.backend.OkapiException;
import uk.org.okapibarcode.backend.Symbol;

@CapacitorPlugin(name = "BarcodeGenerator")
/* loaded from: classes3.dex */
public class BarcodeGeneratorPlugin extends Plugin {
    static final String TAG = "BarcodeGeneratorPlugin";

    @PluginMethod
    public void gs1DatamatrixSvg(PluginCall pluginCall) {
        String string = pluginCall.getString("code");
        if (string == null) {
            pluginCall.reject("No 'code' provided", "INVALID_ARGUMENTS", null, new JSObject().put("message", "No 'code' provided"));
            return;
        }
        DataMatrix dataMatrix = new DataMatrix();
        dataMatrix.setDataType(Symbol.DataType.GS1);
        try {
            dataMatrix.setContent(string);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                new SvgViewBoxRenderer(byteArrayOutputStream).render(dataMatrix);
                JSObject jSObject = new JSObject();
                jSObject.put("svg", byteArrayOutputStream.toString());
                pluginCall.resolve(jSObject);
            } catch (IOException e) {
                pluginCall.reject("Error occured while generating: " + e.getMessage(), "IO_EXCEPTION", null, new JSObject().put("message", e.getMessage()));
            }
        } catch (OkapiException e2) {
            pluginCall.reject("Error occured while generating: " + e2.getMessage(), "OKAPI_EXCEPTION", null, new JSObject().put("message", e2.getMessage()));
        }
    }
}
