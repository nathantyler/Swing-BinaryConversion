package convwin;

import static convwin.Conversion.*;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

/* 
 * By Nathan Tyler N.
 * Last edit 10-22-2019.
 * 
 * To do:
 * Convert data type in Conversion class from long to BigInteger, 
 * so the numbers can be arbitrarily (within reason) large.
 * */

public class SimplifiedWindow {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Binary Conversion");
		Font font;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 1));

		JPanel panel = new JPanel(new GridLayout(3, 1));

		JButton decToBinButton = new JButton("Convert to Binary");
		decToBinButton.setPreferredSize(new Dimension(150, 50));
		font = decToBinButton.getFont().deriveFont(Font.BOLD, 18f);
		decToBinButton.setFont(font);
		panel.add(decToBinButton);

		JTextField convText = new JTextField("0", 25);
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
			try {
				long dec = Long.parseLong(decStr);
				convText.setText(decimalToBinary(dec));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "That's not a number!");
			}
		});

		binToDecButton.addActionListener(al -> {
			String binStr = convText.getText();
			if (!isBinary(binStr))
				JOptionPane.showMessageDialog(null, "That's not binary!");
			else {
				convText.setText(Long.valueOf(binaryToDecimal(binStr)).toString());
			}
		});

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

	}

}
