package uk.org.okapibarcode.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import uk.org.okapibarcode.graphics.Color;
import uk.org.okapibarcode.output.Java2DRenderer;

/* loaded from: classes4.dex */
public class DrawSymbol extends JPanel {
    private static final long serialVersionUID = 586946142379393170L;

    public Dimension getPreferredSize() {
        return new Dimension(OkapiUI.symbol.getWidth() * OkapiUI.factor, OkapiUI.symbol.getHeight() * OkapiUI.factor);
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        new Java2DRenderer(graphics2D, OkapiUI.factor, new Color(OkapiUI.paperColour.getRGB()), new Color(OkapiUI.inkColour.getRGB())).render(OkapiUI.symbol);
    }
}
