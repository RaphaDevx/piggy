package ch.datatrans.payment;

import java.io.StringReader;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes3.dex */
public abstract class He {
    public static final Map a(String tagName, String xmlData) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        Map createMapBuilder = MapsKt.createMapBuilder();
        try {
            NodeList b = b(xmlData, tagName);
            Node item = b.getLength() == 0 ? null : b.item(0);
            if (item != null) {
                int length = item.getChildNodes().getLength();
                for (int i = 0; i < length; i++) {
                    Node item2 = item.getChildNodes().item(i);
                    if (item2 instanceof Element) {
                        Element element = (Element) item2;
                        String nodeName = element.getNodeName();
                        if (element.getChildNodes().getLength() == 1) {
                            Intrinsics.checkNotNull(nodeName);
                            String nodeValue = element.getFirstChild().getNodeValue();
                            Intrinsics.checkNotNullExpressionValue(nodeValue, "getNodeValue(...)");
                            createMapBuilder.put(nodeName, nodeValue);
                        } else {
                            Intrinsics.checkNotNull(nodeName);
                            createMapBuilder.putAll(a(nodeName, xmlData));
                        }
                    }
                }
            }
            return MapsKt.build(createMapBuilder);
        } catch (SAXException e) {
            throw new Ie("Invalid XML.", e);
        } catch (Exception e2) {
            throw new Ie("Cannot parse XML.", e2);
        }
    }

    public static NodeList b(String str, String str2) {
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource();
        inputSource.setCharacterStream(new StringReader(str));
        NodeList elementsByTagName = newDocumentBuilder.parse(inputSource).getElementsByTagName(str2);
        Intrinsics.checkNotNullExpressionValue(elementsByTagName, "getElementsByTagName(...)");
        return elementsByTagName;
    }

    public static final String a(String xmlData, String tagName, boolean z) {
        Intrinsics.checkNotNullParameter(xmlData, "xmlData");
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        try {
            NodeList b = b(xmlData, tagName);
            Node item = (b.getLength() == 0 && z) ? null : b.item(0);
            if (item == null) {
                return null;
            }
            Node firstChild = item.getFirstChild();
            if (firstChild != null) {
                return firstChild.getNodeValue();
            }
            if (z) {
                return null;
            }
            throw new Ie();
        } catch (SAXException e) {
            throw new Ie("Invalid XML.", e);
        } catch (Exception e2) {
            throw new Ie("Cannot parse XML.", e2);
        }
    }
}
