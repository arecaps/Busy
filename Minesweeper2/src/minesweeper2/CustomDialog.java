package minesweeper2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomDialog extends JDialog {
    JTextField rows = new JTextField();
    JTextField columns = new JTextField();
    JTextField bombs = new JTextField();
    
    int buttonSelected;
    
    public CustomDialog(JFrame parent) {
        super(parent, "Custom", true);
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 4, 4));
        panel.add(new JLabel("Rows"));
        panel.add(rows);
        
        panel.add(new JLabel("Columns"));
        panel.add(columns);
        
        panel.add(new JLabel("Bombs"));
        panel.add(bombs);
        
        JPanel buttons = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // should perform validation here
                buttonSelected = JOptionPane.YES_OPTION;
                setVisible(false);
            }
        });
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSelected = JOptionPane.NO_OPTION;
                setVisible(false);
            }
        });
        buttons.add(ok);
        buttons.add(cancel);
        
        add(buttons, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
    
    public int getNumberOfRows() {
        int numberOfRows = 0;
        try {
            numberOfRows = Integer.parseInt(rows.getText());
        } catch(NumberFormatException e) {}
        return numberOfRows;
    }
    
    public int getNumberOfColumns() {
        int numberOfColumns = 0;
        try {
            numberOfColumns = Integer.parseInt(columns.getText());
        } catch(NumberFormatException e) {}
        return numberOfColumns;
    }
    
    public int getNumberOfBombs() {
        int numberOfBombs = 0;
        try {
            numberOfBombs = Integer.parseInt(bombs.getText());
        } catch(NumberFormatException e) {}
        return numberOfBombs;
    }
    
    public int getButton() {
        return buttonSelected;
    }
}
