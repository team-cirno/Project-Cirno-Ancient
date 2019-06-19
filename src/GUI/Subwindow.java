package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import DataObject.Order.Comp;
import DataObject.Order.Customer;
import DataObject.Order.Order;
import DataObject.Product.Product;

public class Subwindow extends JFrame implements ActionListener, FocusListener{
	
	JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu("下单");
    JMenu m2 = new JMenu("帮助");
    
    JMenuItem m11 = new JMenuItem("确定");
    //JMenuItem m22 = new JMenuItem("客户");
    
    JMenuItem m33 = new JMenuItem("不存在的");
    
    GUIMain g=null;
    
    ArrayList<Comp> cl = null;
    ArrayList<Subwindow> wl = null;
	JList<String> list;
	
	JTable table=null;
    JScrollPane jsp;
	JPanel panel = new JPanel();
	JPanel botpanel = new JPanel();
	
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,tL,bL;
	public JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12;
	JTextArea ins;
	public JTextField[] tl = new JTextField[] {
			t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12
	};
	
	Customer cus;
	Order o;
	
	private DefaultTableModel model;
	
	public Subwindow(GUIMain g) {
		
		o = new Order();
		cl = new ArrayList<Comp>();
		wl = new ArrayList<Subwindow>();
		this.g=g;
		this.setTitle("Tester");

		this.setLayout(null);
		this.setSize(640, 800);


		panel.setLayout(null);
		panel.setBounds(0, 0, 640, 120);
		botpanel.setBounds(0,120,640,680);
		botpanel.setLayout(null);
		//Creat mauen
		
	    l1=new JLabel("客户");  
	    l1.setBounds(20,5, 100,15); 
	    l2=new JLabel("联系人");  
	    l2.setBounds(20,20, 100,15); 
	    l3=new JLabel("TEL");  
	    l3.setBounds(20,35, 100,15);  
	    l4=new JLabel("FAX");  
	    l4.setBounds(20,50, 100,15);  
	    l5=new JLabel("公司");  
	    l5.setBounds(20,65, 100,15);
	    l6=new JLabel("仓库");  
	    l6.setBounds(20,80, 100,15);
	    
	    l7=new JLabel("下单时间");  
	    l7.setBounds(270,5, 100,15);  
	    l8=new JLabel("订单号");  
	    l8.setBounds(270,20, 100,15);  
	    l9=new JLabel("业务员");  
	    l9.setBounds(270,35, 100,15);  
	    l10=new JLabel("客户代号");  
	    l10.setBounds(270,50, 100,15);
	    l11=new JLabel("交货时间");  
	    l11.setBounds(270,65, 100,15);
	    l12=new JLabel("付款方式");  
	    l12.setBounds(270,80, 100,15);
	    
	    for(int i=0;i<tl.length;i++) {
	    	tl[i] =new JTextField(10);
	    	if(i>5)
		    	tl[i].setBounds(340,20+15*(i-7), 200,15);
	    	else
		    	tl[i].setBounds(60,20+15*(i-1), 200,15);
	    	
	    	panel.add(tl[i]);

	    }
	    
	    tl[6].setText(this.o.getCreatTime());
	    
	    tl[6].setEditable(false);
	    //tl[7].setEditable(false);

	    panel.add(l1);
	    panel.add(l2);
	    panel.add(l3);
	    panel.add(l4);
	    panel.add(l5);
	    panel.add(l6);
	    panel.add(l7);
	    panel.add(l8);
	    panel.add(l9);
	    panel.add(l10);
	    panel.add(l11);
	    panel.add(l12);
	    
        mb.add(m1);
        mb.add(m2);

        m11.addActionListener(this); 
        tl[0].addFocusListener(this);
        m1.add(m11);
        m2.add(m33);
		
		//Creat lists
        this.getList();

        		
        jsp.setSize(640, 500);

        botpanel.add(jsp);
        tL=new JLabel("合计:");
        tL.setBounds(530, 495, 100, 30);
        bL=new JLabel("件数:");
        bL.setBounds(350, 495, 100, 30);
        ins=new JTextArea("",7,30);
        ins.setLayout(null);
        ins.setLineWrap(true);
        ins.setWrapStyleWord(true);
        ins.setSize(640, 180);
		JScrollPane jsp2= new JScrollPane(ins);
		jsp2.setBounds(0, 520, 640, 110);
		jsp2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		this.botpanel.add(jsp2);
        this.botpanel.add(bL);
        this.botpanel.add(tL);
        this.add(panel);
        this.add(botpanel);
        this.setJMenuBar(mb);
        this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	            "商品ID", "色号","包装克数", "总体包数","件数", "价格","基价","单项总款"
	        };
		
		model = new DefaultTableModel(columns, 40);
		
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
        table.setRowHeight(20);
        
        //table.setDefaultEditor(DataObject.class, null);
        System.out.println("List Updated"+" "+table.getRowCount()+" "+table.getColumnCount());
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
                if (mouseEvent.getClickCount() == 1 && table.getSelectedColumn() ==7) {
                    table.setValueAt(Double.parseDouble(table.getValueAt(table.getSelectedRow(), 5).toString())*Double.parseDouble(table.getValueAt(table.getSelectedRow(), 3).toString()),table.getSelectedRow(), 7);
                }
            }
        });
        table.addFocusListener(this);
		table.revalidate();
		jsp.revalidate();
		this.botpanel.revalidate();
		this.botpanel.updateUI();
		table.updateUI();
		jsp.updateUI();
		
		this.revalidate();
		//this.list.updateUI();
	}
	
	public void openOrder(int i){
		//new Subwindow(g,this.ol.get(i-1));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==m11)    
			if(PlaseOrder()) {
				this.close();
			} else {
				System.out.println("Flash");
				Color c =this.getBackground();
				this.setBackground(Color.red);
				this.repaint();
				
				try {
					
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.setBackground(c);
				this.repaint();

			}
	
	}
	
	private void close() {
		this.dispose();
	}
	
	public void uplist(Comp o) {

		System.out.println(o.toString());
		this.cl.add(o);
		String[] sd = new String[] {o.getP().getId(),o.getP().getColor(),Double.toString(o.getPrice()),Double.toString(o.getAmount()),Integer.toString(o.getBoxNum())};
		model.addRow(sd);
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource()==tl[0]) {
			if(tl[0].getText()!=null&&tl[0].getText()!="") {
				System.out.println("requse info for "+tl[0].getText().trim());
				this.g.c.GetCus(tl[0].getText().trim(),this);
				}else {
					this.cus = new Customer(this.t1.getText().trim());
				}
			}
		else if(e.getSource()==table) {
			double counter = 0.0;
			int bboxc=0;
			for(int i=0;table.getValueAt(i, 7)!=null&&i<40 &&table.getValueAt(i, 7).toString()!="";i++) {
				counter=counter+Double.parseDouble(table.getValueAt(i, 7).toString());
				bboxc=bboxc+Integer.parseInt(table.getValueAt(i, 4).toString());
			}
			tL.setText("合计: "+Double.toString(counter));
			bL.setText("件数: "+Integer.toString(bboxc));
		}
			
		}

		
	public void SetCus(Customer c) {
		Customer tmp = c;
		if(tmp!=null&&tmp.getName()!=""){
			System.out.println("info get: "+tmp.toString());
			this.cus=tmp;
			if(tmp.getContect()!=null)
				this.tl[1].setText(tmp.getContect());
			else if(tmp.getTEL()!=null)
				this.tl[2].setText(tmp.getTEL());
			else if(tmp.getFAX()!=null)
				this.tl[3].setText(tmp.getFAX());
			else if(tmp.getCompy()!=null)
				this.tl[4].setText(tmp.getCompy());
			else if(tmp.getStorgeRoom()!=null)
				this.tl[5].setText(tmp.getStorgeRoom());
			else if(tmp.getStorgeRoom()!=null)
				this.tl[9].setText(tmp.getID());
		}
	}
	
	private boolean PlaseOrder() {
		
		if(this.cus==null)
			return false;
		System.out.println("Cus check");
		if(this.cl==null)
			return false;
		System.out.println("CL check");

		for(JTextField x : this.tl) {
			if(x.getText() == null)
				x.setText("");;
		}
		System.out.println("tl check");

		if(tl[0].getText()==""||tl[7].getText()==""||tl[8].getText()==""||tl[10].getText()==""||tl[11].getText()=="") {
			return false;
		}
		System.out.println("tl iner check");

		//refine cus
		if(tl[1].getText()!="")
			this.cus.setContect(tl[1].getText());
		if(tl[2].getText()!="")
			this.cus.setTEL(tl[2].getText());
		if(tl[3].getText()!="")
			this.cus.setFAX(tl[3].getText());
		if(tl[4].getText()!="")
			this.cus.setCompy(tl[4].getText());
		if(tl[5].getText()!="")
			this.cus.setStorgeRoom(tl[5].getText());
		if(tl[9].getText()!="")
			this.cus.setID(tl[9].getText());
		

		//refine order
		this.cl= new ArrayList<Comp>();
		for(int i=0;i<table.getRowCount();i++) {
			try {
				if(table.getModel().getValueAt(i, 0).toString()==null||table.getModel().getValueAt(i, 0).toString()=="") {
					break;
				}
			} catch (NullPointerException e) {
				break;
			}
			System.out.println(table.getModel().getValueAt(i, 2).toString());
			double tmpkpg =Double.parseDouble(table.getModel().getValueAt(i, 2).toString());
			int tmpb =Integer.parseInt(table.getModel().getValueAt(i, 3).toString());
			int tmpbx=Integer.parseInt(table.getModel().getValueAt(i, 4).toString());
			double tmppr =Double.parseDouble(table.getModel().getValueAt(i, 5).toString());
			double tmpt =tmppr*tmpb;
			double tmpa = tmpb*tmpkpg;
			Product tmpp = new Product(this.table.getModel().getValueAt(i, 0).toString(),this.table.getModel().getValueAt(i, 1).toString());
			tmpp.setBp(Double.parseDouble(table.getModel().getValueAt(i, 6).toString()));
			Comp TmpComp = new Comp(tmpp,tmpkpg,tmpb,tmpbx,tmppr,tmpt,tmpa);
			
			cl.add(TmpComp);
			
		}

		Order tmpo = new Order(this.cus,this.cl,this.ins.getText(),this.tl[7].getText(),tl[7].getText(),tl[10].getText(),tl[8].getText(),tl[11].getText());
		tmpo.setCreatTime(this.tl[6].getText());
		tmpo.setPayAmout(Double.parseDouble(this.tL.getText().substring(this.tL.getText().indexOf(':'), this.tL.getText().length())));
		if(this.g.c.PlaseOrder(tmpo)) {
			System.out.println("DataObject.Order check"+tmpo.toString());
			return true;
		}
		return false;
	}
}
