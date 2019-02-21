package Tool;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
public class Test
{
  private static final Dimension MAIN_SIZE = new Dimension(400, 300);
  private JPanel mainPanel = new JPanel();
   
  public Test()
  {
    JButton makeImageBtn = new JButton("Make Image");
    makeImageBtn.addActionListener(new BtnListener());
    mainPanel.add(makeImageBtn);
    mainPanel.setPreferredSize(MAIN_SIZE);
  }
   
  public JComponent getPanel()
  {
    return mainPanel;
  }
   
  private class BtnListener implements ActionListener
  {
    public void actionPerformed(ActionEvent arg0)
    {
      JFrame win = (JFrame)SwingUtilities.getWindowAncestor(mainPanel);
      Dimension size = win.getSize();
      //BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
      BufferedImage image = (BufferedImage)win.createImage(size.width, size.height);
      Graphics g = image.getGraphics();
      win.paint(g);
      g.dispose();
      try
      {
        ImageIO.write(image, "jpg", new File("MyFrame2.jpg"));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  private static void createAndShowGUI()
  {
    JFrame frame = new JFrame("PaintJFrame Application");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new Test().getPanel());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
 
  public static void main(String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        createAndShowGUI();
      }
    });
 
  }
}