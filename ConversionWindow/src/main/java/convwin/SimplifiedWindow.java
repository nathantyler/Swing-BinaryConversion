package convwin;

import static convwin.Converter.*;

import java.awt.*;
import java.awt.event.*;
import java.math.*;

import javax.swing.*;
import javax.swing.text.*;

/*
 * By Nathan Tyler N.
 * Last edit 2-1-2020.
 *
 * */

public class SimplifiedWindow {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Binary Conversion");
        Font   font;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1));

        JPanel panel = new JPanel(new GridLayout(3, 1));

        JButton decToBinButton = new JButton("Convert to Binary");
        decToBinButton.setPreferredSize(new Dimension(150, 50));
        font = decToBinButton.getFont().deriveFont(Font.BOLD, 18f);
        decToBinButton.setFont(font);
        panel.add(decToBinButton);

        JTextField convText = new JTextField("", 25);
        font = convText.getFont().deriveFont(Font.PLAIN, 20f);
        convText.setFont(font);
        convText.setHorizontalAlignment(JTextField.CENTER);
        panel.add(convText);

        JButton binToDecButton = new JButton("Convert to Decimal");
        binToDecButton.setPreferredSize(new Dimension(150, 50));
        font = binToDecButton.getFont().deriveFont(Font.BOLD, 18f);
        binToDecButton.setFont(font);
        panel.add(binToDecButton);

        decToBinButton.addActionListener(al -> {
            String decStr = convText.getText();
            convText.requestFocusInWindow();
            try {
                BigInteger dec = new BigInteger(decStr);
                convText.setText(decimalToBinary(dec));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(convText, "That's not a number!");
            }
        });

        binToDecButton.addActionListener(al -> {
            String binStr = convText.getText();
            convText.requestFocusInWindow();
            if (!isBinary(binStr))
                JOptionPane.showMessageDialog(convText, "That's not binary!");
            else {
                convText.setText(binaryToDecimalBigInteger(binStr).toString());
            }
        });
        // Let's add a right-click popup menu to our text field.
        JPopupMenu popMenu   = new JPopupMenu();
        Action     cut       = new DefaultEditorKit.CutAction();
        Action     copy      = new DefaultEditorKit.CopyAction();
        Action     paste     = new DefaultEditorKit.PasteAction();
        SelectAll  selectAll = new SelectAll();
        cut.putValue(Action.NAME, "Cut");
        cut.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control X"));
        popMenu.add(cut);
        copy.putValue(Action.NAME, "Copy");
        copy.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control C"));
        popMenu.add(copy);
        paste.putValue(Action.NAME, "Paste");
        paste.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control V"));
        popMenu.add(paste);
        popMenu.addSeparator();
        popMenu.add(selectAll);
        convText.setComponentPopupMenu(popMenu);
/*
        convText.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent ev) {
                if (ev.isPopupTrigger()) {
                    popMenu.show(ev.getComponent(), ev.getX(), ev.getY());
                }
            }
            public void mouseReleased(MouseEvent ev) {
                if (ev.isPopupTrigger()) {
                    popMenu.show(ev.getComponent(), ev.getX(), ev.getY());
                }
            }
            public void mouseClicked(MouseEvent ev) {
            }
        });
*/
        // Now let's start the window in the center of the screen;
        // First let's get the dimension's of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.add(panel);
        frame.pack();
        // Now we can calculate the x and y coordinates for the new location of the window
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // Set the position the window to the new coordinates
        frame.setLocation(x, y);
        convText.requestFocusInWindow();
        frame.setVisible(true);
    }

    public static class SelectAll extends TextAction {

        public SelectAll() {
            super("Select All");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("control A"));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JTextComponent jTextComponent = getFocusedComponent();
            jTextComponent.selectAll();
            jTextComponent.requestFocusInWindow();
        }
    }

}
