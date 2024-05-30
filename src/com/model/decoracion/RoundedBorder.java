package com.model.decoracion;

import javax.swing.border.Border;
import java.awt.*;

/**
 * La clase {@code RoundedBorder} se encarga de crear un borde redondeado para los componentes de la interfaz de usuario.
 * <p>
 * Esta clase implementa la interfaz {@code Border} y proporciona un borde con esquinas redondeadas de un radio especificado.
 */
public class RoundedBorder implements Border {
    private int radius;

    /**
     * Constructor que inicializa el borde redondeado con el radio especificado.
     *
     * @param radius el radio de las esquinas redondeadas.
     */
    public RoundedBorder(int radius) {
        this.radius = radius;
    }

    /**
     * Devuelve los insets del borde, que definen el espacio entre el borde y el contenido del componente.
     *
     * @param c el componente al que se le aplicará el borde.
     * @return los insets del borde.
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    /**
     * Indica si el borde es opaco.
     *
     * @return {@code true} si el borde es opaco; de lo contrario, {@code false}.
     */
    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    /**
     * Pinta el borde redondeado alrededor del componente.
     *
     * @param c el componente al que se le aplicará el borde.
     * @param g el contexto gráfico en el que se dibuja el borde.
     * @param x la coordenada X del borde.
     * @param y la coordenada Y del borde.
     * @param width el ancho del borde.
     * @param height la altura del borde.
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}
