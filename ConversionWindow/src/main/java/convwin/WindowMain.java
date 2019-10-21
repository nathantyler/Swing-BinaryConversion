package convwin;

import static convwin.Conversion.*;

import java.awt.GridLayout;

import javax.swing.*;

public class WindowMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Binary Conversion");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 1));

		JPanel panel = new JPanel(new GridLayout(0, 1));

		JLabel decimal = new JLabel("Decimal:");
		panel.add(decimal);

		JTextField decText = new JTextField("0", 25);
		panel.add(decText);

		JButton decButton = new JButton("Convert");
		panel.add(decButton);

		JLabel binary = new JLabel("Binary:");
		panel.add(binary);

		JTextField binText = new JTextField("0", 25);
		panel.add(binText);

		JButton binButton = new JButton("Convert");
		panel.add(binButton);

		decButton.addActionListener(al -> {
			String decStr = decText.getText();
			try {
				long dec = Long.parseLong(decStr);
				binText.setText(decimalToBinary(dec));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "That's not a number!");
			}
		});

		binButton.addActionListener(al -> {
			String binStr = binText.getText();
			if (!isBinary(binStr))
				JOptionPane.showMessageDialog(null, "That's not binary!");
			else {
				decText.setText(Long.valueOf(binaryToDecimal(binStr)).toString());
			}
		});

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

	}

}
