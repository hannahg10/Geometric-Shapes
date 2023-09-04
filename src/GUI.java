// File name: GUI

// Written by: Hannah Guillen
 
// Description: Graphical program that enables the user to control various aspects of drawing from a 
// JComboBox and display all information. The comboBox allows the user to choose between different shapes
// and choose a color for that using JColorChooser.
//
// Challenges: Using the mousePressed and mouseReleased methods to initiate the drawing and get the coordinates.
// in GUI. Event and exception handling.
// Time Spent: About 7 hours

// Revision History:
// Date:         		By:      Action:
// ---------------------------------------------------
/* 11/24/2020   (HG)                       
 * Started GUI button listeners
 * 11/27/2020   (HG)                       
 * Built menus and started on the MouseHandler method
 * 11/29/2020   (HG)
 * PaintComponent and MouseHandler
*/    
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    
    private JComboBox comboBox;
    private JLabel optionLabel, widthLabel, lblPoints, lblArea, lblPerimeter, statusLabel;
    private JCheckBox filled;
    private JTextField txtWidth;
    private JCheckBoxMenuItem Bold, Italic;
    private JRadioButtonMenuItem Black, Blue, Red, Green, Serif, Sans, Monospaced, White, Cyan, Yellow, Gray;
    private JButton colorButton, clearButton, undoButton;
    private JPanel mainPanel, buttonPanel, combinedPanel, mousePanel, combinedPanel2;
    private JMenuBar menuBar;
    private JMenu fileMenu, shapeMenu, editMenu, textMenu, colorMenu, fontMenu, bgMenu;
    private JMenuItem shapeItem, circleItem, rectangleItem, squareItem, lineItem, undoItem, clearItem, exitItem;
    private JTextArea jtArea, mouseArea;
    private JColorChooser jcc;
    
    private ArrayList<DrawableShapes> aShapes = new ArrayList<>(); // global array list
    
    String[] shape = {"Rectangle", "Circle", "Square", "Line"};
    String selectedShape;
    int width;
    Color color;
    Font font;
    
    public static void main (String[] args)
    {   // create comboBox object.
        GUI p = new GUI();
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        p.setVisible(true);
        p.pack();
        p.setLocationRelativeTo(null); //setup the location
    }//end main method
    
    public GUI()
    {
        super("Java 2D Drawings");
        
        //********** SETTING BUTTONS **********
        
        //create "Clear" button
        clearButton = new JButton("Clear");
        clearButton.setBounds(50,100,95,35);
        clearButton.addActionListener(new ClearListener()); //source object
        
        //create "Undo" button
        undoButton = new JButton("Undo");
        undoButton.setBounds(50,100,95,35);
        undoButton.addActionListener(new UndoListener());
        
        //********** SETTING ALL PANELS **********
        
        //Set main panel for frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(3,1));
        
        //Set top panel with comboBox
        optionLabel = new JLabel("Shape: "); //Label for shape option
        widthLabel = new JLabel("Line width: ");
        txtWidth = new JTextField(10);
        filled = new JCheckBox(" Filled", false);
        comboBox = new JComboBox(shape); //comboBox options
        comboBox.setMaximumRowCount(4); //automatically add scrollbar
        comboBox.setBorder(BorderFactory.createLineBorder(Color.black,1)); //set border color
        selectedShape = (String)comboBox.getSelectedItem().toString();
        
        JPanel topPanel = new JPanel(); //create top panel
        topPanel.add(optionLabel);
        topPanel.add(comboBox);
        topPanel.add(widthLabel);
        topPanel.add(txtWidth);
        topPanel.add(filled);
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        //Set bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        colorButton = new JButton("Color..."); //create "Color" button
        buttonPanel.add(colorButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(clearButton);
        
        combinedPanel = new JPanel(); //create combined panel for Top and Button panel
        combinedPanel.setLayout(new BorderLayout());
        combinedPanel.add(topPanel,BorderLayout.CENTER);
        combinedPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(combinedPanel, BorderLayout.NORTH); //add to main panel
        
        //********** DRAWING FIELD **********
        mousePanel = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g); //clear the background
                Graphics2D g2d = (Graphics2D)g;

                g2d.setFont(font); //Set default font
                g2d.setStroke(new BasicStroke(width)); //get width of stroke from user input;

                for (DrawableShapes s: aShapes) { //loop array list shapes
                s.draw(g2d);
                }
                repaint(); //repaints to color chosen
            }
        };
        mousePanel.setBackground(Color.WHITE);
        mousePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mousePanel.setPreferredSize(new Dimension(150, 150));
        mousePanel.setForeground(Color.BLACK); //default drawing color
        mainPanel.add(mousePanel,BorderLayout.CENTER); //add to JFrame
        
        //********** MOUSE LISTENER **********
        MouseHandler handler = new MouseHandler();
        mousePanel.addMouseListener(handler);
        mousePanel.addMouseMotionListener(handler);
        
        //********** TEXT AREA **********
        jtArea = new JTextArea(5, 5);
        jtArea.setBackground(Color.LIGHT_GRAY); // set background to light gray
        jtArea.setPreferredSize(new Dimension(100, 100));
        jtArea.setForeground(Color.BLACK); //default text area color
        mainPanel.add(jtArea, BorderLayout.SOUTH);
        add(mainPanel);
        
        statusLabel = new JLabel("Moved at ");
        
        combinedPanel2 = new JPanel();
        combinedPanel2.setLayout(new BorderLayout(2,1));
        combinedPanel2.add(mousePanel, BorderLayout.NORTH);
        combinedPanel2.add(jtArea, BorderLayout.SOUTH);
        mainPanel.add(combinedPanel2, BorderLayout.CENTER);
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        //********** COLOR CHOOSER **********
        colorButton.setBounds(50,100,95,35);
        jcc = new JColorChooser(); //sets color for mousePanel drawing
        colorButton.addActionListener(new ColorListener());
        
        //********** MENU BAR **********
        
        menuBar = new JMenuBar(); //creates menu bar object
        buildFileMenu();
        buildTextMenu();
        menuBar.add(fileMenu);
        menuBar.add(textMenu);
        setJMenuBar(menuBar); //set up the manu bar onto frame
        
        //********** DEFAULT SETTINGS **********
        this.setBackground(Color.WHITE); //set background color
        font = new Font("Serif", Font.PLAIN, 20);
        
    }
    private class MouseHandler implements MouseListener, MouseMotionListener {
        //declare variables
        int x1;
        int x2;
        int y1;
        int y2;
        
        @Override
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            
            statusLabel.setText(String.format("Pressed at [%d, %d]", e.getX(), e.getY()));
            repaint();
        }     
    
        @Override
        public void mouseReleased(MouseEvent e) {
            x2 = e.getX();
            y2 = e.getY();
            
            try {
                width = Integer.parseInt(txtWidth.getText()); //get width of stroke from user input
                selectedShape = (String)comboBox.getSelectedItem().toString(); //get selected shape from combobox
                switch(selectedShape)
                {
                    case "Circle":
                        Circles circles = new Circles(x1, y1, x2, y2, color, filled.isSelected(), width); //calls circles class
                        jtArea.setText("Cirlce with the points " + circles.getPoints());
                        jtArea.append("\n Area: " + circles.getArea());
                        jtArea.append("\n Perimeter: " + circles.getPerimeter());
                        aShapes.add(circles); //add to array
                        
                        break;
                    case "Rectangle":
                        Rectangles rectangles = new Rectangles(x1, y1, x2, y2, color, filled.isSelected(), width); //calls circles class
                        jtArea.setText("Rectangle with the points " + rectangles.getPoints());
                        jtArea.append("\n Area: " + rectangles.getArea());
                        jtArea.append("\n Perimeter: " + rectangles.getPerimeter());
                        aShapes.add(rectangles); //add to array
                        break;
                    case "Square":
                        Squares squares = new Squares(x1, y1, x2, y2, color, filled.isSelected(), width); //calls circles class
                        jtArea.setText("Square with the points " + squares.getPoints());
                        jtArea.append("\n Area: " + squares.getArea());
                        jtArea.append("\n Perimeter: " + squares.getPerimeter());
                        aShapes.add(squares); //add to array
                        break;
                    case "Line":
                        Lines lines = new Lines(x1, y1, x2, y2, color, width); //calls circles class
                        jtArea.setText("Line with the points " + lines.getPoints());
                        aShapes.add(lines); //add to array
                        break;
                } //end switch statement
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter positive numderical digits only for 'LINE WIDTH'!", "ERROR", JOptionPane.ERROR_MESSAGE); //error message for exception handler
            } //end try-catch
            
            statusLabel.setText(String.format("Released at [%d, %d]", e.getX(), e.getY()));
        }
        
        //Do-nothing methods
        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
        @Override
        public void mouseDragged(MouseEvent e) {}
        @Override
        public void mouseMoved(MouseEvent e) {}
    }
    
    private class ColorListener implements ActionListener //contents for Color button
    {
        @Override
        /*  */  public void actionPerformed(ActionEvent e)
        {    
            color = new Color(128, 100, 100);
            jcc.showDialog(null, "Choose a color", Color.LIGHT_GRAY);
                        
            color = jcc.getColor();
            mousePanel.setForeground(color); //sets color from JColorChooser
            
            if (color == null)
                color = Color.LIGHT_GRAY; //default to light gray if no color is chosen
        }
    }
    
    private class ClearListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
           comboBox.setSelectedIndex(0); //set to default shape
            txtWidth.setText("");//clear textfield
            statusLabel.setText("");
            jtArea.setText("");
            filled.setSelected(false); //uncheck checkbox
            aShapes.clear();
            mousePanel.setBackground(Color.WHITE); //clears JPanel
            //repaint();
        }
    }
    
    private class UndoListener implements ActionListener //contents for exit button
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (!aShapes.isEmpty()) //when array is empty
                aShapes.remove(aShapes.size() - 1);
        }
    }
    
    private void buildFileMenu()
    {
        //set fileMenu and submenus
        fileMenu = new JMenu("File");
        shapeMenu = new JMenu("Shape");
        editMenu = new JMenu("Edit");
        
        //create menu items
        exitItem = new JMenuItem("Exit"); 
        undoItem = new JMenuItem("Undo");
        clearItem = new JMenuItem("Clear");
        
        //create submenu items
        circleItem = new JMenuItem("Circle");
        rectangleItem = new JMenuItem("Rectangle");
        squareItem = new JMenuItem("Square");
        lineItem = new JMenuItem("Line");
               
        exitItem.setMnemonic(KeyEvent.VK_X); //add mnemonic key
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK)); //add accelerator
        exitItem.addActionListener((ActionEvent event) -> {
            System.exit( 0 ); // exit application
        } // end method actionPerformed
        ); // end call to addActionListener
        
        //Set combobox and clear contents of textfields when shape is selected
        clearItem.addActionListener((ActionEvent event) -> {
           comboBox.setSelectedIndex(0); //set to default shape
            txtWidth.setText("");//clear textfields
            statusLabel.setText("");
            jtArea.setText("");
            filled.setSelected(false);
            aShapes = null;
            //mousePanel.setBackground(Color.WHITE); //clears JPanel
            //mousePanel.setForeground(Color.WHITE); //clears JPanel
            //repaint();
            
        });
        
        undoItem.addActionListener((ActionEvent event) -> {
            if (!aShapes.isEmpty())
                aShapes.remove(aShapes.size() - 1);
            
        });
        
        circleItem.addActionListener((ActionEvent event) -> {
            comboBox.setSelectedIndex(1); //sets combo box to Circle
            txtWidth.setText("");//clear textfields
            statusLabel.setText("");
            jtArea.setText("");
            filled.setSelected(false);
            mousePanel.setBackground(Color.WHITE); //clears JPanel
        });
        
        rectangleItem.addActionListener((ActionEvent event) -> {
            comboBox.setSelectedIndex(0); //sets combo box to Circle
            txtWidth.setText("");//clear textfields
            statusLabel.setText("");
            jtArea.setText("");
            filled.setSelected(false);
            mousePanel.setBackground(Color.WHITE); //clears JPanel
        });
        
        squareItem.addActionListener((ActionEvent event) -> { 
            comboBox.setSelectedIndex(2); //sets combo box to Circle
            txtWidth.setText("");//clear textfields
            statusLabel.setText("");
            jtArea.setText("");
            filled.setSelected(false);
            mousePanel.setBackground(Color.WHITE); //clears JPanel
        });
        
        lineItem.addActionListener((ActionEvent event) -> {
            comboBox.setSelectedIndex(3); //sets combo box to Circle
            txtWidth.setText("");//clear textfields
            statusLabel.setText("");
            jtArea.setText("");
            filled.setSelected(false);
            mousePanel.setBackground(Color.WHITE); //clears JPanel
        });
       
        //Add menu items to fileMenu
        fileMenu.add(shapeMenu);
        fileMenu.add(editMenu);
        fileMenu.add(exitItem);
        
        //Add menu items to shapeMenu submenu
        shapeMenu.add(circleItem);
        shapeMenu.add(rectangleItem);
        shapeMenu.add(squareItem);
        shapeMenu.add(lineItem);
        editMenu.add(undoItem);
        editMenu.add(clearItem);
    }
    
    private void buildTextMenu() {
        
        //set fileMenu and submenus
        textMenu = new JMenu("Text");
        colorMenu = new JMenu("Color");
        fontMenu = new JMenu("Font");
        bgMenu = new JMenu ("Background");
       
        //create checkbox and radio button items
        Black = new JRadioButtonMenuItem("Black", true);
        Blue = new JRadioButtonMenuItem("Blue", false);
        Red = new JRadioButtonMenuItem("Red", false);
        Green = new JRadioButtonMenuItem("Green", false);
        Serif = new JRadioButtonMenuItem("Serif", true);
        Monospaced = new JRadioButtonMenuItem("Monospaced", false);
        Sans = new JRadioButtonMenuItem("Sans-Serif", false);
        Italic = new JCheckBoxMenuItem("Italic", false);
        Bold = new JCheckBoxMenuItem("Bold", false);
        White = new JRadioButtonMenuItem("White", false);
        Cyan = new JRadioButtonMenuItem("Cyan", false);
        Yellow = new JRadioButtonMenuItem("Yellow", false);
        Gray = new JRadioButtonMenuItem("Gray", false);
        
        Black.addActionListener((ActionEvent event) -> {
            jtArea.setForeground(Color.BLACK); //sets text color black
      } // end method actionPerformed
      ); // end call to addActionListener
        
        Blue.addActionListener((ActionEvent event) -> {
            jtArea.setForeground(Color.BLUE); //sets text color blue
      } // end method actionPerformed
      ); // end call to addActionListener
        
        Red.addActionListener((ActionEvent event) -> {
            jtArea.setForeground(Color.RED); //sets text color red
      } // end method actionPerformed
      ); // end call to addActionListener
        
        Green.addActionListener((ActionEvent event) -> {
            jtArea.setForeground(Color.GREEN); //sets text color green
      } // end method actionPerformed
      ); // end call to addActionListener
        
        Serif.addActionListener((ActionEvent event) -> {
            font = new Font("Serif", Font.PLAIN, 20); //sets font
            if (Italic.isSelected())
                font = new Font("Serif", Font.ITALIC, 20); //sets font italic
            else if (Bold.isSelected())
               font = new Font("Serif", Font.BOLD, 20); //sets font bold
            jtArea.setFont(font);
      } // end method actionPerformed
      ); // end call to addActionListener
        
        Monospaced.addActionListener((ActionEvent event) -> {
            font = new Font("Monospaced", Font.PLAIN, 20); //sets font
            if (Italic.isSelected())
                font = new Font("Monospaced", Font.ITALIC, 20); //sets font italic
            else if (Bold.isSelected())
               font = new Font("Monospaced", Font.BOLD, 20); //sets font bold
            jtArea.setFont(font);
      } // end method actionPerformed
      ); // end call to addActionListener
        
        Sans.addActionListener((ActionEvent event) -> {
            font = new Font("Sans-Serif", Font.PLAIN, 20); //sets font
            if (Italic.isSelected())
                font = new Font("Sans-Serif", Font.ITALIC, 20); //sets font italic
            else if (Bold.isSelected())
               font = new Font("Sans-Serif", Font.BOLD, 20);  //sets font bold
            jtArea.setFont(font); 
      } // end method actionPerformed
      ); // end call to addActionListener
        
        White.addActionListener((ActionEvent event) -> {
            jtArea.setBackground(Color.WHITE); // set background to white
      } // end method actionPerformed
      ); // end call to addActionListener
        
        Cyan.addActionListener((ActionEvent event) -> {
            jtArea.setBackground(Color.CYAN); // set background to cyan
      } // end method actionPerformed
      ); // end call to addActionListener
        
        Yellow.addActionListener((ActionEvent event) -> {
            jtArea.setBackground(Color.YELLOW); // set background to yellow
      } // end method actionPerformed
      ); // end call to addActionListener

        Gray.addActionListener((ActionEvent event) -> {
            jtArea.setBackground(Color.GRAY); // set background to gray
      } // end method actionPerformed
      ); // end call to addActionListener


        //Add menu items to menus
       textMenu.add(colorMenu);
       textMenu.add(fontMenu);
       textMenu.add(bgMenu);
       colorMenu.add(Black);
       colorMenu.add(Blue);
       colorMenu.add(Red);
       colorMenu.add(Green);
       fontMenu.add(Serif);
       fontMenu.add(Monospaced);
       fontMenu.add(Sans);
       fontMenu.add(Italic);
       fontMenu.add(Bold);
       bgMenu.add(White);
       bgMenu.add(Cyan);
       bgMenu.add(Yellow);
       bgMenu.add(Gray);
    }
};
