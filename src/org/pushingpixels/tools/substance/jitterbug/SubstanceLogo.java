package org.pushingpixels.tools.substance.jitterbug;

import java.awt.image.BufferedImage;

import org.pushingpixels.demo.substance.main.check.svg.substance;
import org.pushingpixels.substance.api.colorscheme.SubstanceColorScheme;
import org.pushingpixels.substance.api.icon.SubstanceIcon;

public class SubstanceLogo {
    public static SubstanceIcon getLogoIcon(SubstanceColorScheme scheme) {
        // Step 1 - create a new instance of the transcoded Substance logo with
        // base size of 16x16
        SubstanceIcon hiDpiAwareIcon = substance.of(16, 16);
        // Step 2 - return the colorized version of the icon
        return hiDpiAwareIcon.colorize(scheme.getForegroundColor());
    }
    
    public static BufferedImage getLogoImage(SubstanceColorScheme scheme) {
        return getLogoIcon(scheme).toImage();
    }
}
