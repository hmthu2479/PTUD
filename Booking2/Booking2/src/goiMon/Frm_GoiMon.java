package goiMon;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;

import datBan.Frm_DatBan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frm_GoiMon extends JPanel {

    private TableButton[] tables;

    public Frm_GoiMon() {
        // Initialize tables
        tables = new TableButton[40];

        JPanel tablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 9);

        for (int i = 0; i < 40; i++) {
            tables[i] = new TableButton(i + 1, this);
            tables[i].setBackground(Color.LIGHT_GRAY);

            c.gridx = i % 5;
            c.gridy = i / 5;
            tablePanel.add(tables[i], c);
        }

        // Set a fixed size for the table buttons
        Dimension buttonSize = new Dimension(155, 130);
        for (TableButton table : tables) {
            table.setPreferredSize(buttonSize);
            table.setMinimumSize(buttonSize);
            table.setMaximumSize(buttonSize);
        }
        JScrollPane tableScrollPane = new JScrollPane(tablePanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a bottom border shadow
        Border shadowBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(5, 2, 5, 2, Color.YELLOW),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        );

        add(tablePanel);
        setVisible(true);
        tablePanel.setBorder(shadowBorder);
        setPreferredSize(new Dimension());

    }

    public void showBookingWindow(int tableNumber) {
        Frm_DatBan booking = new Frm_DatBan();
        booking.setVisible(true);
    }

    public void updateTableColor(int tableNumber, Color color) {
        tables[tableNumber - 1].setBackground(color);
    }

    public void updateTableLabel(int tableNumber, String label) {
        tables[tableNumber - 1].setLabel(label);
    }

}