package datBan;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;

public class Frm_DatBan extends JPanel {
    private JSpinner dateSpinner;
    private JComboBox<String> timeComboBox;
    private JComboBox<Integer> peopleComboBox;
    private JTextField nameTextField;
    private JTextField phoneTextField;
    private JTextField  adTextField;
    private JComboBox<String> areaComboBox;
    private JComboBox<String> roomComboBox;
    private JTextArea noteTextArea;
    private String[] roomOptions = new String[20];

    public Frm_DatBan() {
        setLayout(new BorderLayout());

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout(15, 5));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        Color lightYellow = new Color(255, 255, 153);
        mainPanel.setBackground(Color.WHITE);

        // Create the left panel for name, phone, and status
        JPanel leftPanel = new JPanel(new GridLayout(6, 1, 0, 0));
        leftPanel.setBackground(Color.WHITE); // White

        // Area
        JPanel areaPanel = new JPanel(new BorderLayout());
        areaPanel.setBackground(Color.WHITE); // White
        areaPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Khu vực", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        String[] areaOptions = {"Khu vực 1", "Khu vực 2", "Tầng 1", "Tầng 2"};
        areaComboBox = new JComboBox<>(areaOptions);
        areaComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        areaPanel.add(areaComboBox, BorderLayout.CENTER);
        leftPanel.add(areaPanel);
        
     // Room
        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.setBackground(Color.WHITE);
        roomPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Bàn", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));

        String[] roomOptions = new String[25]; // Increase the size to include the new options

        // Populate the roomOptions array with options
        for (int i = 0; i < 20; i++) {
            roomOptions[i] = "Bàn " + (i + 1);
        }

        // Add the additional options
        roomOptions[20] = "Phòng VIP 1";
        roomOptions[21] = "Phòng VIP 2";
        roomOptions[22] = "Phòng VIP 3";
        roomOptions[23] = "Phòng Karaoke 1";
        roomOptions[24] = "Phòng Karaoke 2";

        JComboBox<String> roomComboBox = new JComboBox<>(roomOptions);
        roomComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        roomComboBox.setForeground(Color.BLACK);
        roomPanel.add(roomComboBox, BorderLayout.CENTER);
        leftPanel.add(roomPanel);
        // Name
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground(Color.WHITE); // White
        namePanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Họ tên khách hàng", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        nameTextField = new JTextField(30);
        nameTextField.setFont(new Font("Roboto", Font.BOLD, 16));
        namePanel.add(nameTextField, BorderLayout.CENTER);
        leftPanel.add(namePanel);

        // Phone
        JPanel phonePanel = new JPanel(new BorderLayout());
        phonePanel.setBackground(Color.WHITE); // White
        phonePanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "SĐT", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        phoneTextField = new JTextField(30);
        phoneTextField.setFont(new Font("Arial", Font.BOLD, 16));
        phonePanel.add(phoneTextField, BorderLayout.CENTER);
        leftPanel.add(phonePanel);

     // Address
        JPanel adPanel = new JPanel(new BorderLayout());
        adPanel.setBackground(Color.WHITE); // White
        adPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Địa Chỉ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        adTextField = new JTextField(30);
        adTextField.setFont(new Font("Arial", Font.BOLD, 16));
        adPanel.add(adTextField, BorderLayout.CENTER);
        leftPanel.add(adPanel);

        JPanel notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.X_AXIS));
        notePanel.setBackground(Color.WHITE);
        notePanel.setBorder(new EmptyBorder(15, 10, 0, 0));

        JButton addButton = new JButton("Thêm");
        JButton xoaButton = new JButton("Xóa");
        JButton xoaRButton = new JButton("Xóa rỗng");
        JButton searchButton = new JButton("Tìm kiếm");

        /*addButton.setBackground(new Color(173, 216, 230)); // Light blue color
        xoaButton.setBackground(new Color(173, 216, 230)); // Light blue color
        xoaRButton.setBackground(new Color(173, 216, 230)); // Light blue color
        searchButton.setBackground(new Color(173, 216, 230)); // Light blue color*/
        
               
        notePanel.add(addButton);
        notePanel.add(Box.createHorizontalStrut(10)); // Add horizontal space between buttons
        notePanel.add(xoaButton);
        notePanel.add(Box.createHorizontalStrut(10)); // Add horizontal space between buttons
        notePanel.add(xoaRButton);
        notePanel.add(Box.createHorizontalStrut(10)); // Add horizontal space between buttons
        notePanel.add(searchButton);

        Font bFont = new Font("Arial", Font.BOLD, 16);
        addButton.setFont(bFont);
        xoaButton.setFont(bFont);
        xoaRButton.setFont(bFont);
        searchButton.setFont(bFont);

        leftPanel.add(notePanel);
        leftPanel.setPreferredSize(new Dimension(400,400));
       

        mainPanel.add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new BorderLayout());

        // Table
        String[] columnNames = {"Mã", "Khu vực", "Số bàn", "SL","Ngày đặt","Ngày lập","Giờ đặt","Họ Tên", "SDT", "Địa chỉ"};
        Object[][] data = {
            {"000000", "Khu vực 1", "Bàn 1", "5","11/3/2024","10/3/2024","10:00 AM", "Trần Văn A", "1234567890", "đường Nguyễn Thái Sơn, quận Gò Vấp"},
            {"000001", "Tầng 2", "Phòng VIP 1", "5","12/3/2024", "10/3/2024","11:00 AM","Nguyễn Thị B", "9876543210", "đường Phan Văn Trị, quận Gò Vấp"}
        };

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Arial", Font.BOLD, 16));

        // Center align text in table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        // Increase row height
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        Color lightBlue = new Color(173, 216, 230); // Light blue color
        header.setBackground(lightBlue);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	     // Set preferred width for each column
	     table.getColumnModel().getColumn(0).setPreferredWidth(100);
	     table.getColumnModel().getColumn(1).setPreferredWidth(130);
	     table.getColumnModel().getColumn(2).setPreferredWidth(120);
	     table.getColumnModel().getColumn(3).setPreferredWidth(50);
	     table.getColumnModel().getColumn(4).setPreferredWidth(120);
	     table.getColumnModel().getColumn(5).setPreferredWidth(120);
	     table.getColumnModel().getColumn(6).setPreferredWidth(90);
	     table.getColumnModel().getColumn(7).setPreferredWidth(160);
	     table.getColumnModel().getColumn(8).setPreferredWidth(120);
	     table.getColumnModel().getColumn(9).setPreferredWidth(350);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      
        rightPanel.add(tableScrollPane, BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        // Create the top panel for date, time, and people
        JPanel topPanel = new JPanel(new GridLayout(1, 3, 0, 10));
        topPanel.setBackground(Color.WHITE); // White
        topPanel.setBorder(new EmptyBorder(5, 0, 10, 0));

        // Date
        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.setBackground(Color.WHITE); // White
        datePanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 2), "Ngày đặt", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy"));
        dateSpinner.setFont(new Font("Arial", Font.BOLD, 20));
        datePanel.add(dateSpinner, BorderLayout.CENTER);
        topPanel.add(datePanel);

        // Time
        JPanel timePanel = new JPanel(new BorderLayout());
        timePanel.setBackground(Color.WHITE); // White
        timePanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Giờ đặt", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        String[] timeOptions = {"10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM"};
        timeComboBox = new JComboBox<>(timeOptions);
        timeComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        timePanel.add(timeComboBox, BorderLayout.CENTER);
        topPanel.add(timePanel);

        // People
        JPanel peoplePanel = new JPanel(new BorderLayout());
        peoplePanel.setBackground(Color.WHITE); // White
        peoplePanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2), "Số lượng người", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK));
        Integer[] peopleOptions = {1, 2, 3, 4, 5};
        peopleComboBox = new JComboBox<>(peopleOptions);
        peopleComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        peoplePanel.add(peopleComboBox, BorderLayout.CENTER);
        topPanel.add(peoplePanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        add(mainPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); 
        btnPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        
        // Create buttons
        JButton btnChonMon = new JButton("Chọn món");
        JButton btnLuu = new JButton("Lưu");
        JButton btnQuayLai = new JButton("Quay lại");

        /*btnChonMon.setBackground(lightYellow);
        btnLuu.setBackground(lightYellow);
        btnQuayLai.setBackground(lightYellow);*/

        Font btnFont = new Font("Arial", Font.BOLD, 17); 
        btnChonMon.setFont(btnFont);
        btnLuu.setFont(btnFont);
        btnQuayLai.setFont(btnFont);

     // Set thicker border for the buttons
        btnChonMon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnLuu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnQuayLai.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Increase button size
        Dimension buttonSize = new Dimension(110, 30);
        btnChonMon.setPreferredSize(buttonSize);
        btnLuu.setPreferredSize(buttonSize);
        btnQuayLai.setPreferredSize(buttonSize);
        
        // Add buttons to the panel
        btnPanel.add(btnChonMon);
        btnPanel.add(btnLuu);
        btnPanel.add(btnQuayLai);

        // Add the panel to the container
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
    }
    	

    /*public Booking getBooking() {
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String status = (String) statusComboBox.getSelectedItem();
        String note = noteTextArea.getText();

        LocalDate date = ((java.util.Date) dateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String time = (String) timeComboBox.getSelectedItem();
        int people = (int) peopleComboBox.getSelectedItem();

        return new Booking(name, phone, status, note, date, time, people);
    */
}