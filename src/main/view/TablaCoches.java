package main.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TablaCoches extends JPanel {

private Object[][] data ;
	public TablaCoches() {
        super();
        this.setSize(600, 400);
        initGui();
	}
	
	private void initGui() {
		JPanel mainPanel = new JPanel();
		setName("Tabla Coches");
		 String[] columnNames = {"Matrícula", "Marca", "Color"};
		 
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JTable tableP = new JTable(model);
		
        tableP.setBorder(BorderFactory.createEmptyBorder());
        mainPanel.add(tableP);
        
        setPreferredSize(new Dimension(700, 400));
        setVisible(false);
		
		
	}
}
