package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Object.Order.Comp;
import Object.Order.Customer;
import Object.Order.Order;
import Tool.Tool;

public class OrderViewer extends JFrame implements ActionListener, FocusListener{
	
	private static final long serialVersionUID = 5278916024103616581L;
	JMenuBar mb = new JMenuBar();
    JMenu m1 = new JMenu("切换");
    JMenu m2 = new JMenu("帮助");
    JMenuItem m11 = new JMenuItem("清单");
    JMenuItem m22 = new JMenuItem("基价表");
    JMenuItem m44 = new JMenuItem("生产安排打单");
    JMenuItem m55 = new JMenuItem("保存为图片");
    JMenuItem m33 = new JMenuItem("不存在的");
    GUIMain g=null;
    ArrayList<Comp> cl = null;
	JList<String> list;
	JTable table=null;
    JScrollPane jsp;
	JPanel mainp = new JPanel();
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
	
	public OrderViewer(String[] s, Order o) {
		
		System.out.println("Object.Order opend: "+o.toString());
		this.o = o;
		cl = o.getCompList();
		this.setTitle("Object.Order Viewer");
		this.setLayout(null);
		this.setSize(640, 800);

		panel.setLayout(null);
		panel.setBounds(0, 0, 640, 120);
		botpanel.setBounds(0,120,640,680);
		botpanel.setLayout(null);
		mainp.setBounds(0, 0, 640, 800);
		
		
		//Creat mauen
		

        this.getList();
        this.add(panel);
        this.add(botpanel);
        //this.add(mainp);
        this.setJMenuBar(mb);
        this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setSize(1280, 800);

		this.setVisible(true);
	}
	
	  public JComponent getPanel()
	  {
	    return mainp;
	  }
	
	private void Swish(String s) {
		this.getContentPane().removeAll();
		botpanel.removeAll();
		this.panel.removeAll();
		this.setTitle(s);
		
		if(s.equals("清单")) {
			System.out.println("生成清单");
			getList();
		} else if(s.equals("基价表")){
			System.out.println("生成基价表");
			getBasForm();
		} else if(s.equals("生产安排单")) {
			System.out.println("生产安排单");
			getFForm();
		} else if(s.equals("出货清单")) {
			
		}
        this.add(panel);
        this.add(botpanel);
        this.setJMenuBar(mb);
        this.setResizable(false);
        
		this.revalidate();
		this.repaint();
	}
	
	private void getList() {
	    SetCus(o.getCus());
	    tl[6].setText(o.getCreatTime());
	    tl[7].setText(o.getOrderID());
	    tl[8].setText(o.getCreaterID());;
	    tl[10].setText(o.getDeadLine());
	    tl[11].setText(o.getPayMathend());
	    for(JTextField x:tl) {
	    	x.setEditable(false);
	    }

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
        m22.addActionListener(this);
        m44.addActionListener(this);
        m55.addActionListener(this);

        m1.add(m11);
        //m2.add(m33);
        m1.add(m44);
        m2.add(m55);
        m1.add(m22);
		
		//Creat lists
		
		String[] columns = new String[] {
	            "商品ID", "色号","包装克数", "总体包数","件数", "价格","单项总款"
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
        table.setEnabled(false);
        
        //table.setDefaultEditor(Object.class, null);
        System.out.println("List Updated"+" "+table.getRowCount()+" "+table.getColumnCount());
		//model = (DefaultTableModel)table.getModel();
        jsp= new JScrollPane(table);
	    SetComp();

		table.revalidate();
		jsp.revalidate();
		this.botpanel.revalidate();
		this.botpanel.updateUI();
		table.updateUI();
		jsp.updateUI();
		
		this.revalidate();
		
		 jsp.setSize(640, 500);

	        botpanel.add(jsp);
	        tL=new JLabel("合计:  "+Double.toString(this.o.getPayAmout()));
	        tL.setBounds(530, 495, 100, 30);
	        bL=new JLabel("件数: "+Integer.toString(this.o.getBoxNum()));
	        bL.setBounds(350, 495, 100, 30);
	        ins=new JTextArea("",7,30);
	        ins.setLayout(null);
	        ins.setLineWrap(true);
	        ins.setWrapStyleWord(true);
	        ins.setSize(640, 180);
	        ins.setText(o.getInstrotion());
	        ins.setEditable(false);
			JScrollPane jsp2= new JScrollPane(ins);
			jsp2.setBounds(0, 520, 640, 110);
			jsp2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
			
			this.botpanel.add(jsp2);
	        this.botpanel.add(bL);
	        this.botpanel.add(tL);
		//this.list.updateUI();
	}
	
	private void getBasForm() {

	    SetCus(o.getCus());
	    tl[6].setText(o.getCreatTime());
	    tl[7].setText(o.getOrderID());
	    tl[8].setText(o.getCreaterID());;
	    tl[10].setText(o.getDeadLine());
	    tl[11].setText(o.getPayMathend());
	    for(JTextField x:tl) {
	    	x.setEditable(false);
	    }

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
		
		String[] columns = new String[] {
	            "商品ID", "色号", "包数", "价格","基价","单项总款","单项基价总款"
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
            
                public boolean isCellEditable(int row, int column){
                  if(row == 4) return true;
                  return false;
                 }
        };
        
        table.setGridColor(Color.gray);
        table.setRowHeight(20);
        table.setEnabled(true);
        
        //table.setDefaultEditor(Object.class, null);
        System.out.println("List Updated"+" "+table.getRowCount()+" "+table.getColumnCount());
		//model = (DefaultTableModel)table.getModel();
        jsp= new JScrollPane(table);
        
        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
        
        //model.fireTableDataChanged()

	    SetCompForBase();

		table.revalidate();
		jsp.revalidate();
		this.botpanel.revalidate();
		this.botpanel.updateUI();
		table.updateUI();
		jsp.updateUI();
		
		this.revalidate();
		
		 jsp.setSize(640, 500);

	        botpanel.add(jsp);
	        tL=new JLabel("客户订单总货款");
	        tL.setBounds(370, 495, 300, 30);
	        this.botpanel.add(tL);
	        tL=new JLabel(": "+Double.toString(this.o.getPayAmout()));
	        tL.setBounds(500, 495, 300, 30);
	        this.botpanel.add(tL);
	        tL=new JLabel("订单基价总货款");
	        tL.setBounds(370, 515, 300, 30);
	        this.botpanel.add(tL);
	        tL=new JLabel(": "+Double.toString(this.o.getCost()));
	        tL.setBounds(500, 515, 300, 30);
	        this.botpanel.add(tL);
	        tL=new JLabel("客户价比基价高");
	        tL.setBounds(370, 535, 300, 30);
	        this.botpanel.add(tL);
	        
	        tL=new JLabel(": "+Tool.toPercentage(((float) (this.o.getPayAmout()-(float)this.o.getCost()))/ (float)this.o.getCost(),2));
	        tL.setBounds(500, 535, 300, 30);
	        this.botpanel.add(tL);
	        tL=new JLabel("客户价比基价总款高");
	        tL.setBounds(370, 555, 300, 30);
	        this.botpanel.add(tL);
	        tL=new JLabel(": "+Double.toString(this.o.getPayAmout()-this.o.getCost()));
	        tL.setBounds(500, 555, 300, 30);
	        this.botpanel.add(tL);
	     
	}
	
	private void getFForm() {

		
		    SetCus(o.getCus());
		    tl[6].setText(o.getCreatTime());
		    tl[7].setText(o.getOrderID());
		    tl[8].setText(o.getCreaterID());;
		    tl[10].setText(o.getDeadLine());
		    tl[11].setText(o.getPayMathend());
		    for(JTextField x:tl) {
		    	x.setEditable(false);
		    }

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
			
			String[] columns = new String[] {
		            "商品ID", "色号", "包数","总量(千克)","件数"
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
	        table.setEnabled(false);
	        
	        //table.setDefaultEditor(Object.class, null);
	        System.out.println("List Updated"+" "+table.getRowCount()+" "+table.getColumnCount());
			//model = (DefaultTableModel)table.getModel();
	        jsp= new JScrollPane(table);
	        
	        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
	        
	        //model.fireTableDataChanged()

		    SetCompForF();

			table.revalidate();
			jsp.revalidate();
			this.botpanel.revalidate();
			this.botpanel.updateUI();
			table.updateUI();
			jsp.updateUI();
			
			this.revalidate();
			
			 jsp.setSize(640, 500);

		        botpanel.add(jsp);
		        tL=new JLabel("合计:  "+Double.toString(this.o.getPayAmout()));
		        bL.setBounds(530, 495, 100, 30);
		        
		        ins=new JTextArea("",7,30);
		        ins.setLayout(null);
		        ins.setLineWrap(true);
		        ins.setWrapStyleWord(true);
		        ins.setSize(640, 180);
		        ins.setText(o.getInstrotion());
		        ins.setEditable(false);
				JScrollPane jsp2= new JScrollPane(ins);
				jsp2.setBounds(0, 520, 640, 110);
				jsp2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
				
				this.botpanel.add(jsp2);

		        //this.botpanel.add(bL);
		        //this.botpanel.add(tL);
	}
	
	private void getOForm() {
		  
		    SetCus(o.getCus());
		    tl[6].setText(o.getOrderedDate());
		    tl[7].setText(o.getOrderID());
		    tl[8].setText(o.getCreaterID());;
		    tl[10].setText(o.getDeadLine());
		    tl[11].setText(o.getPayMathend());
		    for(JTextField x:tl) {
		    	x.setEditable(false);
		    }

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
			
			String[] columns = new String[] {
		            "商品ID", "色号", "包数","总量","件数"
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
	        table.setEnabled(false);
	        
	        //table.setDefaultEditor(Object.class, null);
	        System.out.println("List Updated"+" "+table.getRowCount()+" "+table.getColumnCount());
			//model = (DefaultTableModel)table.getModel();
	        jsp= new JScrollPane(table);
	        
	        ((AbstractTableModel)table.getModel()).fireTableDataChanged();
	        
	        //model.fireTableDataChanged()

		    SetCompForF();

			table.revalidate();
			jsp.revalidate();
			this.botpanel.revalidate();
			this.botpanel.updateUI();
			table.updateUI();
			jsp.updateUI();
			
			this.revalidate();
			
			 jsp.setSize(640, 500);

		        botpanel.add(jsp);
		        tL=new JLabel("合计:  "+Double.toString(this.o.getPayAmout()));
		        tL.setBounds(530, 495, 100, 30);

		        this.botpanel.add(bL);
		        this.botpanel.add(tL);
	}
	
	private void SetCompForF() {

		System.out.println(o.getCompList().toString());
		for(int i=0;i<o.getCompList().size();i++) {
			Comp tmp = o.getCompList().get(i);
			System.out.println("Here is "+tmp.getP().getId());
			table.getModel().setValueAt(tmp.getP().getId(), i, 0);
			table.getModel().setValueAt(tmp.getP().getColor(), i, 1);
			table.getModel().setValueAt(tmp.getBagNum(), i, 2);
			table.getModel().setValueAt(tmp.getAmount()/1000, i, 3);
			table.getModel().setValueAt(tmp.getBoxNum(), i, 4);
			System.out.println("Here is table "+table.toString());
		}
	}
	
	private void SetCompForO() {

		System.out.println(o.getCompList().toString());
		for(int i=0;i<o.getCompList().size();i++) {
			Comp tmp = o.getCompList().get(i);
			System.out.println("Here is "+tmp.getP().getId());
			table.getModel().setValueAt(tmp.getP().getId(), i, 0);
			table.getModel().setValueAt(tmp.getP().getColor(), i, 1);
			table.getModel().setValueAt(tmp.getBagNum(), i, 2);
			table.getModel().setValueAt(tmp.getAmount(), i, 3);
			table.getModel().setValueAt(tmp.getBoxNum(), i, 4);
			System.out.println("Here is table "+table.toString());
		}
	}
	
	private void SetComp() {
		System.out.println(o.getCompList().toString());
		for(int i=0;i<o.getCompList().size();i++) {
			Comp tmp = o.getCompList().get(i);
			System.out.println("Here is "+tmp.getP().getId());
			table.getModel().setValueAt(tmp.getP().getId(), i, 0);
			table.getModel().setValueAt(tmp.getP().getColor(), i, 1);
			table.getModel().setValueAt(tmp.getKGPBB(), i, 2);
			table.getModel().setValueAt(tmp.getBagNum(), i, 3);
			table.getModel().setValueAt(tmp.getBoxNum(), i, 4);
			table.getModel().setValueAt(tmp.getPrice(), i, 5);
			table.getModel().setValueAt(tmp.getTotle(), i, 6);
			System.out.println("Here is table "+table.toString());
		}
	}

	private void SetCompForBase() {
		// TODO Auto-generated method stub
		System.out.println(o.getCompList().toString());
		for(int i=0;i<o.getCompList().size();i++) {
			Comp tmp = o.getCompList().get(i);
			System.out.println("Here is "+tmp.getP().getId());
			table.getModel().setValueAt(tmp.getP().getId(), i, 0);
			table.getModel().setValueAt(tmp.getP().getColor(), i, 1);
			table.getModel().setValueAt(tmp.getBagNum(), i, 2);
			table.getModel().setValueAt(tmp.getPrice(), i, 3);
			table.getModel().setValueAt(tmp.getP().getBp(), i, 4);
			table.getModel().setValueAt(tmp.getPrice()*tmp.getBagNum(), i, 5);
			table.getModel().setValueAt(tmp.getP().getBp()*tmp.getBagNum(), i, 6);
			System.out.println("Here is table "+table.toString());
		}
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==m11)  {  
			Swish("清单");
		} else if(e.getSource()==m22) {
			Swish("基价表");
		}else if(e.getSource()==m44) {
			Swish("生产安排单");
		}else if(e.getSource()==m55) {

		      JFrame win = (JFrame)SwingUtilities.getWindowAncestor(this.getContentPane());
		      Dimension size = win.getSize();
		      //BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		      BufferedImage image = (BufferedImage)win.createImage(size.width, size.height);

		      
		      File path = new File("red");
		      
		      try
		      {
			    BufferedImage overlay = ImageIO.read(new File(path, "Header.png"));
			    Graphics g = overlay.getGraphics();
			    win.paint(g);
			    g = image.getGraphics();
			    
			    g.setClip(0, 0,image.getWidth()*4, image.getHeight()*4);
			    win.paint(g);
			    g.dispose();
		        ImageIO.write(image, "jpg", new File("订单.jpg"));

		      }
		      catch (IOException ex)
		      {
		        ex.printStackTrace();
		      }
		}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
	}
		
	public void SetCus(Customer c) {
		
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
	    
	    System.out.println(o.getCus());
	    
		Customer tmp = c;
		if(tmp!=null&&tmp.getName()!=""){
			System.out.println("info get: "+tmp.toString());
			this.cus=tmp;
			if(tmp.getName()!=null)
				this.tl[0].setText(tmp.getName());
			if(tmp.getContect()!=null)
				this.tl[1].setText(tmp.getContect());
			if(tmp.getTEL()!=null)
				this.tl[2].setText(tmp.getTEL());
			if(tmp.getFAX()!=null)
				this.tl[3].setText(tmp.getFAX());
			if(tmp.getCompy()!=null)
				this.tl[4].setText(tmp.getCompy());
			if(tmp.getStorgeRoom()!=null)
				this.tl[5].setText(tmp.getStorgeRoom());
			if(tmp.getStorgeRoom()!=null)
				this.tl[9].setText(tmp.getID());
		}
	}
}