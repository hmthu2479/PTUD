package gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.ThongKeDAO;


public class Frm_ThongKeTheoNgay extends JFrame implements ActionListener {
    private JComboBox<String> cbThongKe;
    private ChartPanel chartPanel;

    public Frm_ThongKeTheoNgay() {
        setTitle("Thống kê theo ngày/tháng lập hóa đơn");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create JComboBox
        cbThongKe = new JComboBox<>(new String[]{"Số lượng hóa đơn theo ngày", "Số lượng hóa đơn theo tháng"});
        cbThongKe.addActionListener(this);
        add(cbThongKe, BorderLayout.NORTH);

        // Create ChartPanel
        chartPanel = new ChartPanel(null);
        add(chartPanel, BorderLayout.CENTER);

        // Display the default chart for daily invoice count
        hienThiBieuDoSoLuongTheoNgay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cbThongKe) {
            String selected = (String) cbThongKe.getSelectedItem();
            switch (selected) {
                case "Số lượng hóa đơn theo ngày":
                    hienThiBieuDoSoLuongTheoNgay();
                    break;
                case "Số lượng hóa đơn theo tháng":
                    hienThiBieuDoSoLuongTheoThang();
                    break;
            }
        }
    }

    private void hienThiBieuDoSoLuongTheoNgay() {
        ThongKeDAO thongKeDAO = new ThongKeDAO();
        Map<LocalDate, Integer> thongKe = thongKeDAO.thongKeTheoNgayLap();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<LocalDate, Integer> entry : thongKe.entrySet()) {
            dataset.addValue(entry.getValue(), "Số lượng hóa đơn", entry.getKey().toString());
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Thống kê số lượng hóa đơn theo ngày lập",
            "Ngày lập",
            "Số lượng hóa đơn",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        chartPanel.setChart(chart);
    }

    private void hienThiBieuDoSoLuongTheoThang() {
        ThongKeDAO thongKeDAO = new ThongKeDAO();
        Map<Integer, Integer> thongKe = thongKeDAO.thongKeTheoThangLap();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<Integer, Integer> entry : thongKe.entrySet()) {
            dataset.addValue(entry.getValue(), "Số lượng hóa đơn", "Tháng " + entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Thống kê số lượng hóa đơn theo tháng lập",
            "Tháng",
            "Số lượng hóa đơn",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        chartPanel.setChart(chart);
    }
    public static void main(String[] args) {
		new Frm_ThongKeTheoNgay().setVisible(true);
	}
}
