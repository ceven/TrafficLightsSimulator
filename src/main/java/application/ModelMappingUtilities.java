package application;

import javafx.scene.paint.Color;
import light.Colour;

public class ModelMappingUtilities {

    public static Color getColor(final Colour colour) {
        return Color.valueOf(colour.name());
    }
}
