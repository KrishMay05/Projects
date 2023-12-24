package HomeWorks;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
 
/**
 * Paint.java
 *
 * makes a paint program that takes mouse input
 *
 * @author Krish Patel, 119
 *
 *@version 11/13
 *
 */
public class Paint extends JComponent implements ActionListener {
    private Image image;
    private Graphics2D graphics2D;
    private int curX, curY, oldX, oldY;
    private int red = 255;
    private int blue = 255;
    private int green = 255;
    private JButton clearButton, fillButton, eraseButton, randomButton, hexButton, rgbButton;
    private JTextField hexText, rText, gText, bText;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Paint Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new Paint());
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public Paint() {

        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        clearButton = new JButton("Clear");
        fillButton = new JButton("Fill");
        eraseButton = new JButton("Erase");
        randomButton = new JButton("Random");

        topPanel.add(clearButton);
        topPanel.add(fillButton);
        topPanel.add(eraseButton);
        topPanel.add(randomButton);

        setPreferredSize(new Dimension(600, 400));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                curX = e.getX();
                curY = e.getY();
                graphics2D.drawLine(oldX, oldY, curX, curY);
                repaint();
                oldX = curX;
                oldY = curY;
            }
        });

        JPanel bottomPanel = new JPanel();
        hexText = new JTextField("#", 10);
        rText = new JTextField("", 5);
        gText = new JTextField("", 5);
        bText = new JTextField("", 5);
        hexButton = new JButton("Hex");
        rgbButton = new JButton("RGB");

        bottomPanel.add(hexText);
        bottomPanel.add(hexButton);
        bottomPanel.add(rText);
        bottomPanel.add(gText);
        bottomPanel.add(bText);
        bottomPanel.add(rgbButton);

        clearButton.addActionListener(this);
        fillButton.addActionListener(this);
        eraseButton.addActionListener(this);
        randomButton.addActionListener(this);
        hexButton.addActionListener(this);
        rgbButton.addActionListener(this);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setPaint(Color.white);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            graphics2D.setPaint(Color.black);
            graphics2D.setStroke(new BasicStroke(5));
            repaint();
        }
        g.drawImage(image, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            clearCanvas();
        } else if (e.getSource() == fillButton) {
            fillCanvas();
        } else if (e.getSource() == eraseButton) {
            eraseCanvas();
        } else if (e.getSource() == randomButton) {
            setRandomColor();
        } else if (e.getSource() == hexButton) {
            setHexColor();
        } else if (e.getSource() == rgbButton) {
            setRGBColor();
        }
    }

    private void clearCanvas() {
        graphics2D.setPaint(Color.white);
        red = 255;
        blue = 255;
        green = 255;
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        resetTextFields();
        repaint();
    }

    private void fillCanvas() {
        Color currentColor = graphics2D.getColor();
        red = currentColor.getRed();
        blue = currentColor.getBlue();
        green = currentColor.getGreen();
        graphics2D.setPaint(currentColor);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        hexText.setText("#");
        rText.setText("");
        gText.setText("");
        bText.setText("");
        repaint();
    }

    private void eraseCanvas() {
        Color backgroundColor = new Color(red, green, blue);
        graphics2D.setPaint(backgroundColor);
        repaint();
    }

    private void setRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Color randomColor = new Color(r, g, b);
        graphics2D.setPaint(randomColor);
        setTextFieldValues(randomColor);
        repaint();
    }

    private void setHexColor() {
        try {
            Color hexColor = Color.decode(hexText.getText());
            graphics2D.setPaint(hexColor);
            setTextFieldValues(hexColor);
            repaint();
        } catch (Exception e) {
            showErrorDialog("Error", "Not a valid Hex Value");
        }
    }

    private void setRGBColor() {
        try {
            int r = Integer.parseInt(rText.getText());
            int g = Integer.parseInt(gText.getText());
            int b = Integer.parseInt(bText.getText());
            Color rgbColor = new Color(r, g, b);
            graphics2D.setPaint(rgbColor);
            setTextFieldValues(rgbColor);
            repaint();
        } catch (Exception e) {
            showErrorDialog("Error", "Not a valid RGB Value");
        }
    }

    private void setTextFieldValues(Color color) {
        hexText.setText(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
        rText.setText(String.valueOf(color.getRed()));
        gText.setText(String.valueOf(color.getGreen()));
        bText.setText(String.valueOf(color.getBlue()));
    }

    private void resetTextFields() {
        hexText.setText("#");
        rText.setText("");
        gText.setText("");
        bText.setText("");
    }

    private void showErrorDialog(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    
}
