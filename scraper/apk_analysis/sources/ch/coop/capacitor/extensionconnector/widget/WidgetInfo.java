package ch.coop.capacitor.extensionconnector.widget;

import java.util.Objects;

/* loaded from: classes3.dex */
public class WidgetInfo {
    private final WidgetCardType cardType;
    private final WidgetSize size;
    private final WidgetType type;

    public WidgetInfo(WidgetType widgetType, WidgetSize widgetSize, WidgetCardType widgetCardType) {
        this.type = widgetType;
        this.size = widgetSize;
        this.cardType = widgetCardType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WidgetInfo widgetInfo = (WidgetInfo) obj;
        return this.type == widgetInfo.type && this.size == widgetInfo.size && this.cardType == widgetInfo.cardType;
    }

    public int hashCode() {
        return Objects.hash(this.type, this.size, this.cardType);
    }

    public WidgetType getType() {
        return this.type;
    }

    public WidgetSize getSize() {
        return this.size;
    }

    public WidgetCardType getCardType() {
        return this.cardType;
    }
}
