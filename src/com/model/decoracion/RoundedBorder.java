package com.model.decoracion;

import javax.swing.border.Border;
import java.awt.*;

/**
 * La clase RoundedBorder se encarga de crear un borde redondeado para los componentes de la interfaz de usuario.
 */
public class RoundedBorder implements Border {
    int radius;
    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }
    public boolean isBorderOpaque() {
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}