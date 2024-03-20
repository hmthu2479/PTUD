package gui;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Frm_DatMon_TableButton extends JButton {
    private JLabel label;
    private Frm_DatMon restaurantApp;
    private int tableNumber;

    public Frm_DatMon_TableButton(int tableNumber, Frm_DatMon restaurantApp) {
        this.restaurantApp = restaurantApp;
        this.tableNumber = tableNumber;

        setText(Integer.toString(tableNumber));
        setFont(new Font("Arial", Font.BOLD, 35));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Color.LIGHT_GRAY);
            }
        });

        setLayout(new BorderLayout());
    }

    public void setLabel(String labelText) {
        label.setText(labelText);
    }
}