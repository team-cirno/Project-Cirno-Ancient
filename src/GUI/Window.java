package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import DataObject.Order.Order;
import Tool.Logger;

public class Window extends JFrame implements ActionListener{
	
	JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu("新建");
    JMenu m2 = new JMenu("查看");
    JMenu m3 = new JMenu("帮助");

    
    JMenuItem m11 = new JMenuItem("订单");
    JMenuItem m22 = new JMenuItem("客户");
    JMenuItem m33 = new JMenuItem("不存在的");
    JMenuItem m44 = new JMenuItem("重新连接服务器");
    JMenuItem ls = new JMenuItem("库存");
    JMenuItem ld = new JMenuItem("基价表");

    GUIMain g=null;
    
    ArrayList<Order> ol = null;
    ArrayList<Subwindow> wl = null;
	JList<String> list;
	
	JTable table=null;
    JScrollPane jsp;
	JPanel panel = new JPanel();
	JPanel botpanel = new JPanel();
	
	Logger logger=null;
	
	private DefaultTableModel model;

	public Window(GUIMain g) {
		logger = new Logger(this);
		ol = new ArrayList<Order>();
		wl = new ArrayList<Subwindow>();
		// TODO Auto-generated constructor stub
		this.g=g;
		this.setTitle("Tester");
		
		//this.setResizable(false);
		this.setLayout(null);
		this.setSize(1280, 800);


		//panel.setBackground(Color.red);
		panel.setLayout(null);
		panel.setBounds(0, 0, 1280, 80);
		//botpanel.setBackground(Color.BLUE);
		botpanel.setBounds(0,80,1280,720);
		botpanel.setLayout(null);
		//Creat mauen

		
		
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        
        ls.addActionListener(this); 
        ld.addActionListener(this); 


        m11.addActionListener(this); 
        m22.addActionListener(this); 
        m1.add(m11);
        m1.add(m22);
        
        m2.add(ls);
        m2.add(ld);

        m22.addActionListener(this); 
        m3.add(m33);
        m3.add(m44);
        m44.addActionListener(this);
        m33.addActionListener(this);
        //mb.setLocation(0, 0);
        //mb.setBounds(0, 0, 1280, mb.getWidth());

		//Creat lists
        this.getList();

        jsp.setSize(1280, 700);

        botpanel.add(jsp);
        this.add(panel);
        this.add(botpanel);
        this.setJMenuBar(mb);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(1280, 800);

		this.setVisible(true);
	}

	public void creatOrder() {
		this.wl.add(new Subwindow(this.g));
	}
	public void creatCus() {
		
	}
	
	public void getList() {
		
		String[] columns = new String[] {
	            "ID", "创建时间", "客户", "死线","销售员"
	        };
		
		ArrayList<Order> tmp =this.g.c.PullOrder();
		this.ol=tmp;
		model = new DefaultTableModel(columns, 0);
		//DefaultListModel<String> listModel = new DefaultListModel<>();
	    Object[][] data = new Object[tmp.size()][5];
		for(int i=0;i<tmp.size();i++) {
			String[] sd = new String[] {
					tmp.get(i).getOrderID(),tmp.get(i).getCreatTime(),tmp.get(i).getCus().getName(),tmp.get(i).getDeadLine(),tmp.get(i).getCreaterID()
			};
			model.addRow(sd);
		}
		
		table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row) && isColumnSelected(column)) {
                    ((JComponent) c).setBorder(new LineBorder(Color.red));
                }

                return c;
            }
        };
        table.setGridColor(Color.gray);
        table.setAutoCreateRowSorter(true);

        table.setDefaultEditor(Object.class, null);
        logger.Log("List Updated"+" "+table.getRowCount()+" "+table.getColumnCount());
		//model = (DefaultTableModel)table.getModel();
        jsp= new JScrollPane(table);
        
        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
        
        //model.fireTableDataChanged()
        table.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        	int row = table.getSelectedRow();
        	openOrder(row);
        	//int col = table.getSelectedColumn();
        	}
        	});
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    new OrderViewer(new String[] {"lol","j"},findOrder(table.getValueAt(table.getSelectedRow(), 0).toString()));
                }
            }
        });
		table.revalidate();
		jsp.revalidate();
		this.botpanel.revalidate();
		this.botpanel.updateUI();
		table.updateUI();
		jsp.updateUI();
		this.revalidate();
		//this.list.updateUI();
	}
	
	Order findOrder(String string){
		
		for(Order x : this.ol) {
			if (x.getOrderID().equals(string)){
				logger.Log("opening oreder this =>"+x.toString());
				return x;
			}
		}
		return null;
	}
	
	public void openOrder(int i){
		//new Subwindow(g,this.ol.get(i-1));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==m11)    
			creatOrder();
		if(e.getSource()==m22)
			creatCus();
		if(e.getSource()==m33) {
			shutdown();
		}
		if(e.getSource()==m44) {
			this.g.recount();
		}
		
	}
	
	public void shutdown() {
		logger.Log("Shuting down server.... now");
		this.g.c.ShutDownServer();
		this.dispose();
	}
	
	public void uplist(Order o) {

		logger.Log(o.toString());
		this.ol.add(o);
		String[] sd = new String[] {o.getOrderID(),o.getCreatTime(),o.getCus().getName(),o.getDeadLine(),o.getCreaterID()};
		model.addRow(sd);

	}
}
