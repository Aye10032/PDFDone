package util;

import java.awt.*;

public class LayoutUtil {

    public static void add(Container container, int fill, int anchor, int weightx, int weighty, int x, int y, int width, int heigth, Component component) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = fill;
        constraints.anchor = anchor;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = heigth;
        container.add(component, constraints);
    }

    public static void add(Container container, int fill, int anchor, int weightx, int weighty, int x, int y, int width, int heigth, Component component, Insets insets) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = insets;
        constraints.fill = fill;
        constraints.anchor = anchor;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = heigth;
        container.add(component, constraints);
    }

}
