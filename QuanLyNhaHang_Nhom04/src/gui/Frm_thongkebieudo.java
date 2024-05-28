package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.BieuDoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;

public class Frm_thongkebieudo extends JFrame implements ActionListener {
    private JComboBox<String> cbThongKe;
    private ChartPanel chartPanel;
    private BieuDoDAO thongKeDAO;

    public Frm_thongkebieudo() {
        thongKeDAO = new BieuDoDAO();
        
        setTitle("Thống kê theo ngày/tháng lập hóa đơn");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create JComboBox
        cbThongKe = new JComboBox<>(new String[]{"Tổng tiền theo ngày", "Tổng tiền theo tháng"});
        cbThongKe.addActionListener(this);
        add(cbThongKe, BorderLayout.NORTH);

        // Create ChartPanel
        chartPanel = new ChartPanel(null);
        add(chartPanel, BorderLayout.CENTER);

        // Display the default chart for total money per day
        hienThiBieuDoTongTienTheoNgay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbThongKe) {
            String selected = (String) cbThongKe.getSelectedItem();
            if (selected.equals("Tổng tiền theo ngày")) {
                hienThiBieuDoTongTienTheoNgay();
            } else if (selected.equals("Tổng tiền theo tháng")) {
                hienThiBieuDoTongTienTheoThang();
            }
        }
    }

    private void hienThiBieuDoTongTienTheoNgay() {
        Map<LocalDate, Double> thongKe = thongKeDAO.thongKeTongTienTheoNgay();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<LocalDate, Double> entry : thongKe.entrySet()) {
            dataset.addValue(entry.getValue(), "Tổng tiền", entry.getKey().toString());
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Thống kê tổng tiền theo ngày lập",
            "Ngày lập",
            "Tổng tiền",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        chartPanel.setChart(chart);
    }

    private void hienThiBieuDoTongTienTheoThang() {
        Map<Integer, Double> thongKe = thongKeDAO.thongKeTongTienTheoThang();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Double> entry : thongKe.entrySet()) {
            dataset.addValue(entry.getValue(), "Tổng tiền", "Tháng " + entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Thống kê tổng tiền theo tháng lập",
            "Tháng lập",
            "Tổng tiền",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        chartPanel.setChart(chart);
    }

    public static void main(String[] args) {
        new Frm_thongkebieudo().setVisible(true);
        
    }
}
