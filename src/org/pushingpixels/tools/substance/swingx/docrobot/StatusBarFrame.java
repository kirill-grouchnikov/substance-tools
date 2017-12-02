/*
 * Copyright 2005-2008 Kirill Grouchnikov, based on work by
 * Sun Microsystems, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.pushingpixels.tools.substance.swingx.docrobot;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXStatusBar;
import org.pushingpixels.demo.substance.main.SubstanceLogo;
import org.pushingpixels.demo.substance.main.check.svg.tango.edit_copy;
import org.pushingpixels.demo.substance.main.check.svg.tango.edit_cut;
import org.pushingpixels.demo.substance.main.check.svg.tango.edit_delete;
import org.pushingpixels.demo.substance.main.check.svg.tango.edit_paste;
import org.pushingpixels.demo.substance.main.check.svg.tango.edit_select_all;
import org.pushingpixels.demo.substance.main.check.svg.tango.format_justify_center;
import org.pushingpixels.demo.substance.main.check.svg.tango.format_justify_fill;
import org.pushingpixels.demo.substance.main.check.svg.tango.format_justify_left;
import org.pushingpixels.demo.substance.main.check.svg.tango.format_justify_right;
import org.pushingpixels.demo.substance.main.check.svg.tango.process_stop;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.SubstanceSlices.ColorSchemeAssociationKind;
import org.pushingpixels.substance.api.SubstanceSlices.DecorationAreaType;
import org.pushingpixels.substance.api.skin.GeminiSkin;

public class StatusBarFrame extends JFrame {
    public StatusBarFrame() {
        super("JXStatusBar example");

        this.setLayout(new BorderLayout());

        JMenuBar jmb = new JMenuBar();
        jmb.add(new JMenu("File"));
        jmb.add(new JMenu("Edit"));
        jmb.add(new JMenu("Source"));
        jmb.add(new JMenu("Refactor"));
        jmb.add(new JMenu("Navigate"));
        jmb.add(new JMenu("Search"));
        jmb.add(new JMenu("Project"));
        this.setJMenuBar(jmb);

        this.add(getToolbar("", 22), BorderLayout.NORTH);

        JXStatusBar statusBar = new JXStatusBar();
        this.add(statusBar, BorderLayout.SOUTH);

        JLabel statusLabel = new JLabel("Smart Insert");
        JXStatusBar.Constraint c1 = new JXStatusBar.Constraint();
        c1.setFixedWidth(100);
        statusBar.add(statusLabel, c1);

        JXStatusBar.Constraint c2 = new JXStatusBar.Constraint(
                JXStatusBar.Constraint.ResizeBehavior.FILL);
        SimpleDateFormat sdf = new SimpleDateFormat("MMMMM dd, yyyy hh:mm aaa");
        final JLabel tabLabel = new JLabel("Created on " + sdf.format(new Date()));
        statusBar.add(tabLabel, c2);

        JLabel statusLabel2 = new JLabel("5 new messages");
        JXStatusBar.Constraint c3 = new JXStatusBar.Constraint();
        c3.setFixedWidth(90);
        statusBar.add(statusLabel2, c3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);

        setIconImage(SubstanceLogo
                .getLogoImage(SubstanceCortex.ComponentScope.getCurrentSkin(this.getRootPane())
                        .getColorScheme(DecorationAreaType.PRIMARY_TITLE_PANE,
                                ColorSchemeAssociationKind.FILL, ComponentState.ENABLED)));

        SubstanceCortex.GlobalScope.registerSkinChangeListener(
                () -> SwingUtilities.invokeLater(() -> setIconImage(SubstanceLogo.getLogoImage(
                        SubstanceCortex.ComponentScope.getCurrentSkin(getRootPane()).getColorScheme(
                                DecorationAreaType.PRIMARY_TITLE_PANE,
                                ColorSchemeAssociationKind.FILL, ComponentState.ENABLED)))));
    }

    public static JToolBar getToolbar(String label, int size) {
        JToolBar toolBar = new JToolBar();
        // toolBar.setLayout(new BoxLayout(toolBar,BoxLayout.LINE_AXIS));
        // toolBar.setFloatable(false);

        JButton buttonCut = new JButton(edit_cut.of(size, size));
        toolBar.add(buttonCut);
        JButton buttonCopy = new JButton(edit_copy.of(size, size));
        toolBar.add(buttonCopy);
        JButton buttonPaste = new JButton(edit_paste.of(size, size));
        toolBar.add(buttonPaste);
        JButton buttonSelectAll = new JButton(edit_select_all.of(size, size));
        toolBar.add(buttonSelectAll);
        JButton buttonDelete = new JButton(edit_delete.of(size, size));
        toolBar.add(buttonDelete);
        toolBar.addSeparator();

        JToggleButton buttonFormatCenter = new JToggleButton(format_justify_center.of(size, size));
        toolBar.add(buttonFormatCenter);
        JToggleButton buttonFormatLeft = new JToggleButton(format_justify_left.of(size, size));
        toolBar.add(buttonFormatLeft);
        JToggleButton buttonFormatRight = new JToggleButton(format_justify_right.of(size, size));
        toolBar.add(buttonFormatRight);
        JToggleButton buttonFormatFill = new JToggleButton(format_justify_fill.of(size, size));
        toolBar.add(buttonFormatFill);
        toolBar.addSeparator();

        toolBar.add(Box.createGlue());
        JButton buttonExit = new JButton(process_stop.of(size, size));
        buttonExit.addActionListener((ActionEvent e) -> System.exit(0));
        toolBar.add(buttonExit);

        return toolBar;
    }

    public static void main(String[] args) throws Exception {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(() -> {
            SubstanceCortex.GlobalScope.setSkin(new GeminiSkin());
            new StatusBarFrame().setVisible(true);
        });
    }
}
