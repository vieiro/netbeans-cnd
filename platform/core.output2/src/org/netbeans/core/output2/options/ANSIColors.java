/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.core.output2.options;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ANSI Colors for the OutputWindow.
 *
 * @see
 * <a href="https://en.wikipedia.org/wiki/ANSI_escape_code#3-bit_and_4-bit">ANSI
 * 3-4 color escape codes</a>
 */
public final class ANSIColors {

    private static final Logger LOG = Logger.getLogger(ANSIColors.class.getName());

    private static ANSIColors INSTANCE;

    public static synchronized ANSIColors getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ANSIColors();
        }
        return INSTANCE;
    }

    // Same as output window foreground ("COLOR_STANDARD")
    private Color BLACK;
    // Same as output window background ("COLOR_BACKGROUND")
    private Color WHITE;
    private Color RED;
    private Color YELLOW;
    private Color GREEN;
    private Color BLUE;
    private Color CYAN;
    private Color MAGENTA;

    /**
     * 16 Colors in the specified ANSI order: (Normal) BLACK, RED, GREEN,
     * YELLOW, BLUE, MAGENTA, CYAN, WHITE (Bright) BLACK, RED, GREEN, YELLOW,
     * BLUE, MAGENTA, CYAN, WHITE
     */
    private Color[] COLORS = new Color[16];

    private ANSIColors() {
        OutputOptions.getDefault().addPropertyChangeListener(this::outputOptionsPropertyChanged);
        computeColorsFromForegroundAndBackground();
    }

    private void outputOptionsPropertyChanged(PropertyChangeEvent event) {
        if (OutputOptions.PROP_COLOR_BACKGROUND.equals(event.getPropertyName())) {
            computeColorsFromForegroundAndBackground();
        } else if (OutputOptions.PROP_COLOR_STANDARD.equals(event.getPropertyName())) {
            computeColorsFromForegroundAndBackground();
        }
    }

    private ANSIColors computeColorsFromForegroundAndBackground() {
        // See if our "white" equivalent (i.e., the background) is "light" or "dark"
        Color outputWindowBackground = OutputOptions.getDefault().getColorBackground();
        boolean isLight = isLightBackground(outputWindowBackground);
        return computeColorsFromForegroundAndBackground(isLight);
    }

    synchronized ANSIColors computeColorsFromForegroundAndBackground(boolean isLightBackground) {
        // Apply colors attending to "isLight"
        LOG.log(Level.INFO, "ANSIColors on {0} theme", (isLightBackground ? "LIGHT" : "DARK"));
        if (isLightBackground) {
            BLACK = OutputOptions.getDefault().getColorStandard();
            WHITE = OutputOptions.getDefault().getColorBackground();
            RED = Color.RED.darker();
            GREEN = Color.GREEN.darker();
            YELLOW = new Color(242, 174, 57);
            BLUE = Color.BLUE.darker();
            MAGENTA = Color.MAGENTA.darker();
            CYAN = Color.CYAN.darker();
        } else {
            BLACK = OutputOptions.getDefault().getColorBackground();
            WHITE = OutputOptions.getDefault().getColorStandard();
            RED = new Color(231, 66, 53);
            GREEN = Color.GREEN;
            YELLOW = new Color(242, 174, 57);
            BLUE = new Color(91, 135, 205);
            MAGENTA = Color.MAGENTA;
            CYAN = Color.CYAN;
        }

        // 16 Colors in the specified ANSI order:
        // (Normal) BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE
        // (Bright) BLACK, RED, GREEN, YELLOW, BLUE, MAGENTA, CYAN, WHITE
        COLORS[0] = BLACK;
        COLORS[1] = RED;
        COLORS[2] = GREEN;
        COLORS[3] = YELLOW;
        COLORS[4] = BLUE;
        COLORS[5] = MAGENTA;
        COLORS[6] = CYAN;
        COLORS[7] = WHITE;

        COLORS[8] = BLACK.brighter();
        COLORS[9] = RED.brighter();
        COLORS[10] = GREEN.brighter();
        COLORS[11] = YELLOW.brighter();
        COLORS[12] = BLUE.brighter();
        COLORS[13] = MAGENTA.brighter();
        COLORS[14] = CYAN.brighter();
        COLORS[15] = WHITE.brighter();

        return this;
    }

    private boolean isLightBackground(Color c) {
        int luminance = (299 * c.getRed() + 587 * c.getGreen() + 114 * c.getBlue()) / 1000;
        return luminance > 128;
    }

    public String getDemoString() {
        String[] names = {"BLK", "RED", "GRN", "YEL", "BLU", "MAG", "CYN", "WHI"}; // NOI18N
        int[] fg = {30, 31, 32, 33, 34, 35, 36, 37, 90, 91, 92, 93, 94, 95, 96, 97,};
        int[] bg = {40, 41, 42, 43, 44, 45, 46, 47, 100, 101, 102, 103, 104, 105, 106, 107};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            String bgName = names[i % 8] + (i > 7 ? "b" : " ");
            for (int j = 0; j < COLORS.length; j++) {
                String fgName = names[j % 8] + (j > 7 ? "b" : " ");
                String combination = String.format("%s on %s", fgName, bgName);
                String colored = String.format("\u001b[%d;%dm %-11s:", fg[j], bg[i], combination);
                sb.append(colored);
            }
            sb.append("\u001b[0m\n");
        }
        return sb.toString();
    }

    /**
     * Returns an array of 16 ANSI colors (black, red, green, yellow, blu,
     * magenta, cyan, white) and their bright equivalents.
     * 
     * @return An array of 16 ANSI colors.
     */
    public Color[] getColors() {
        return COLORS;
    }

}
