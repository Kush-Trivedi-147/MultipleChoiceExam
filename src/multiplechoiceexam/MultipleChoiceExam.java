package multiplechoiceexam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.Random;
import java.text.*;

public class MultipleChoiceExam extends JFrame {
    
    JLabel headGivenLabel = new JLabel();
    JLabel givenLabel = new JLabel();
    JLabel headAnswerLabel = new JLabel();
    
    JLabel[] answerLabel = new JLabel[2];
    JTextField answerTextField = new JTextField();
    
    JLabel[] AnswerLabel = new JLabel[2];
    JTextField AnswerTextField = new JTextField();
    
    JTextArea commentTextArea = new JTextArea();
    JButton nextButton = new JButton();
    JButton startButton = new JButton();
    
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Exam File");
    JMenuItem openMenuItem = new JMenuItem("Open");
    
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenu optionsMenu = new JMenu("Options");
    JRadioButtonMenuItem header1MenuItem = new JRadioButtonMenuItem("Header 1", true);
    JRadioButtonMenuItem header2MenuItem = new JRadioButtonMenuItem("Header 2", false);
    JRadioButtonMenuItem mcMenuItem = new JRadioButtonMenuItem("Multiple Choice Answer",true);
    JRadioButtonMenuItem typeMenuItem = new JRadioButtonMenuItem("Type In Answer",false);
    ButtonGroup nameGroup = new ButtonGroup();
    ButtonGroup typeGroup = new ButtonGroup();
    
    Font headerFont = new Font("Arial",Font.BOLD,18);
    Font examItemFont = new Font("Arial",Font.BOLD,16);
    Dimension itemSize = new Dimension(500,50);
    Dimension ItemSize = new Dimension(230,40);
    Dimension headSize = new Dimension(500,20);
    
    
    String examTitle;
    String header1,header2;
    int numberTerms;
    String[] term1 = new String[100];
    String[] term2 = new String[100];
    int numberTried,numberCorrect;
    int correctAnswer;
    Random myRandom = new Random();
    
    
    Color Cream = new Color(230,230,250);

    public static void main(String[] args) {
        // create Frame
        new MultipleChoiceExam().show();
    }
    
    public MultipleChoiceExam(){
        // Frame Constructor
        setTitle("Multiple Choice Exam - N0 File");
        //setResizable(false);
       // this.setLocationRelativeTo(null);
       this.setVisible(false);
       
           
        addWindowListener(new WindowAdapter()
        {
           public void windowClosing(WindowEvent evt){
               exitForm(evt);
           } 
        });
        
    getContentPane().setLayout(new GridBagLayout());
    getContentPane().setBackground(Cream);
    
    GridBagConstraints gridConstraints;
     
    headGivenLabel.setPreferredSize(headSize);
    headGivenLabel.setFont(headerFont);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx=0;
    gridConstraints.gridy=0;
    gridConstraints.insets = new Insets(10,10,0,10);
    getContentPane().add(headGivenLabel, gridConstraints);
    givenLabel.setPreferredSize(itemSize);
    givenLabel.setFont(examItemFont);
    givenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    givenLabel.setBackground(Color.WHITE);
    givenLabel.setForeground(Color.GRAY);
    givenLabel.setOpaque(true);
    givenLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx=0;
    gridConstraints.gridy=1;
    gridConstraints.gridwidth= 5;
    gridConstraints.insets = new Insets(10,10,0,10);
    getContentPane().add(givenLabel, gridConstraints);
   
    headAnswerLabel.setPreferredSize(headSize);
    headAnswerLabel.setFont(headerFont);  
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx=0;
    gridConstraints.gridy=2;
    gridConstraints.insets = new Insets(10,10,0,10);
    getContentPane().add(headAnswerLabel, gridConstraints);
    
    //LEFT SIDE OPTIONS PANEL
    
    
    for(int i =0;i<2;i++){
        answerLabel[i] = new JLabel();
        answerLabel[i].setPreferredSize(ItemSize);
        answerLabel[i].setFont(examItemFont);
        answerLabel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        answerLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        answerLabel[i].setBackground(Color.WHITE);
        answerLabel[i].setForeground(Color.GRAY);
        answerLabel[i].setOpaque(true);
        answerLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = i+3;
        gridConstraints.insets = new Insets(0,10,10,10);
        gridConstraints.anchor = GridBagConstraints.WEST;
        getContentPane().add(answerLabel[i], gridConstraints);
        
        
        answerLabel[i].addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e){
               answerLabelMousePressed(e);
           }
        });    
    }
    
    
    
    answerTextField.setPreferredSize(ItemSize);
    answerTextField.setFont(examItemFont);
    answerTextField.setBackground(Color.WHITE);
    answerTextField.setForeground(Color.GRAY);
    answerTextField.setVisible(false);
    answerTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 3;
    gridConstraints.insets = new Insets(0,10,10,10);
    gridConstraints.anchor = GridBagConstraints.WEST;
    getContentPane().add(answerTextField, gridConstraints);
    
    answerTextField.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           answerTextFieldActionPerformed(e);
       } 
    });
    
    
    //RIGHT SIDE OPTIONS PANEL
    
    for(int i =0;i<2;i++){
        AnswerLabel[i] = new JLabel();
        AnswerLabel[i].setPreferredSize(ItemSize);
        AnswerLabel[i].setFont(examItemFont);
        AnswerLabel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AnswerLabel[i].setBackground(Color.WHITE);
        AnswerLabel[i].setForeground(Color.GRAY);
        AnswerLabel[i].setOpaque(true);
        AnswerLabel[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        AnswerLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = i+3;
        gridConstraints.insets = new Insets(0,10,10,10);
        gridConstraints.anchor = GridBagConstraints.EAST;
        getContentPane().add(AnswerLabel[i], gridConstraints);
        
        
        AnswerLabel[i].addMouseListener(new MouseAdapter(){
           public void mousePressed(MouseEvent e){
               AnswerLabelMousePressed(e);
           }
        });    
    }
    
    
    
    AnswerTextField.setPreferredSize(ItemSize);
    AnswerTextField.setFont(examItemFont);
    AnswerTextField.setBackground(Color.WHITE);
    AnswerTextField.setForeground(Color.GRAY);
    AnswerTextField.setCursor(new Cursor(Cursor.HAND_CURSOR));
    AnswerTextField.setVisible(false);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 3;
    gridConstraints.insets = new Insets(0,10,10,10);
    gridConstraints.anchor = GridBagConstraints.EAST;
    getContentPane().add(AnswerTextField, gridConstraints);
    
    AnswerTextField.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           AnswerTextFieldActionPerformed(e);
       } 
    });
    
    
    //COMMENT TEXT AREA CODE
    
    commentTextArea.setPreferredSize(new Dimension(500,80));
    commentTextArea.setFont(new Font("Courier New",Font.BOLD+Font.ITALIC,18));
    commentTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    commentTextArea.setEditable(false);
    commentTextArea.setBackground(new Color(255,255,196));
    commentTextArea.setForeground(Color.BLACK);
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 5;
    gridConstraints.insets = new Insets(0,10,10,10);
    getContentPane().add(commentTextArea, gridConstraints);
    
    
    
    
    setJMenuBar(mainMenuBar);
   
    mainMenuBar.add(fileMenu);
    fileMenu.add(openMenuItem);
    fileMenu.addSeparator();
    openMenuItem.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
    fileMenu.add(exitMenuItem);
  
    mainMenuBar.add(optionsMenu);
    
   // optionsMenu.add(header1MenuItem);
   // optionsMenu.add(header2MenuItem);
   // optionsMenu.addSeparator();
    optionsMenu.add(mcMenuItem);
    optionsMenu.add(typeMenuItem);
    nameGroup.add(header1MenuItem);
    nameGroup.add(header2MenuItem);
    typeGroup.add(mcMenuItem);
    typeGroup.add(typeMenuItem);
    
    openMenuItem.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           openMenuItemActionPerformed(e);
       } 
    });
    
    exitMenuItem.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           exitMenuItemActionPerformed(e);
           System.exit(0);
       } 
    });
    
    header1MenuItem.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           header1MenuItemActionPerformed(e);
       } 
    });
    
    header2MenuItem.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           header2MenuItemActionPerformed(e);
       } 
    });
    
    mcMenuItem.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           mcMenuItemActionPerformed(e);
       } 
    });
    
    typeMenuItem.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           typeMenuItemActionPerformed(e);
       } 
    });
    
    
    //Next Button
    
    nextButton.setText(" Next Question");
    nextButton.setBackground(Color.WHITE);
    nextButton.setFocusable(false);
    nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 6;
    gridConstraints.insets = new Insets(0,0,10,0);
    getContentPane().add(nextButton, gridConstraints);
    
    nextButton.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           nextButtonActionPerformed(e);
       } 
    });
    
    
    //Start Button
    
    startButton.setText("   Start Exam   ");
    startButton.setBackground(Color.WHITE);
    startButton.setFocusable(false);
    startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    gridConstraints = new GridBagConstraints();
    gridConstraints.gridx = 0;
    gridConstraints.gridy = 7;
    gridConstraints.insets = new Insets(0,0,10,0);
    getContentPane().add(startButton, gridConstraints);
    
    startButton.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){
           startButtonActionPerformed(e);
       } 
    });
    
    //initialize form
    
    startButton.setEnabled(false);
    nextButton.setEnabled(false);
    optionsMenu.setEnabled(false);
    commentTextArea.setText(centerTextArea("Open Exam File to Start"));
    
    pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    
    private void exitForm(WindowEvent evt){
        System.exit(0);
    }
    
    private void answerLabelMousePressed(MouseEvent e){
        boolean correct = false;
        int labelSelected;
        // make sure exam has started and question has not been answered 
        if(startButton.getText().equals("   Start Exam   ") || nextButton.isEnabled())
        return;
        // determine which label was clicked
        // get upper left corner of clicked label
        Point p = e.getComponent().getLocation();
        // determine index based on p
        for (labelSelected = 0; labelSelected < 20; labelSelected++) {
            if (p.x == answerLabel[labelSelected].getX() && p.y ==
                answerLabel[labelSelected].getY()) 
                break;  
        }
        // If already answered, exit
        numberTried++;
        if (header1MenuItem.isSelected()){
            if (answerLabel[labelSelected].getText().equals(term1[correctAnswer])) correct = true;      
        }
        else{
        if (answerLabel[labelSelected].getText().equals(term2[correctAnswer])) correct = true;
        }
        updateScore(correct);
       
    }
    
    private void answerTextFieldActionPerformed(ActionEvent e){
        // Check type in answer
        boolean correct;
        String ucTypedAnswer, ucAnswer;
        // make sure exam has started and question has not been answered 
        if(startButton.getText().equals("   Start Exam   ") || nextButton.isEnabled())
        return;
        answerTextField.setEditable(false);
        numberTried++;
        ucTypedAnswer = answerTextField.getText().toUpperCase(); 
        if(header1MenuItem.isSelected())
        ucAnswer = term1[correctAnswer].toUpperCase();
        else
        ucAnswer = term2[correctAnswer].toUpperCase();
        correct = false;
        if (ucTypedAnswer.equals(ucAnswer) ||
        soundex(ucTypedAnswer).equals(soundex(ucAnswer))) correct = true;
        updateScore(correct);

    }
    
     private void AnswerLabelMousePressed(MouseEvent e){
        boolean correct = false;
        int labelSelected;
        // make sure exam has started and question has not been answered 
        if(startButton.getText().equals("   Start Exam   ") || nextButton.isEnabled())
        return;
        // determine which label was clicked
        // get upper left corner of clicked label
        Point p = e.getComponent().getLocation();
        // determine index based on p
        for (labelSelected = 0; labelSelected < 20; labelSelected++) {
            if (p.x == AnswerLabel[labelSelected].getX() && p.y ==
                AnswerLabel[labelSelected].getY()) 
                break;  
        }
        // If already answered, exit
        numberTried++;
        if (header1MenuItem.isSelected()){
            if (AnswerLabel[labelSelected].getText().equals(term1[correctAnswer])) correct = true;      
        }
        else{
        if (AnswerLabel[labelSelected].getText().equals(term2[correctAnswer])) correct = true;
        }
        updateScore(correct);
    }
    
    private void AnswerTextFieldActionPerformed(ActionEvent e){
        // Check type in answer
        boolean correct;
        String ucTypedAnswer, ucAnswer;
        // make sure exam has started and question has not been answered 
        if(startButton.getText().equals("   Start Exam   ") || nextButton.isEnabled())
        return;
        answerTextField.setEditable(false);
        numberTried++;
        ucTypedAnswer = answerTextField.getText().toUpperCase(); if
        (header1MenuItem.isSelected())
        ucAnswer = term1[correctAnswer].toUpperCase();
        else
        ucAnswer = term2[correctAnswer].toUpperCase();
        correct = false;
        if (ucTypedAnswer.equals(ucAnswer) ||
        soundex(ucTypedAnswer).equals(soundex(ucAnswer))) correct = true;
        updateScore(correct);
    }
    
    private void nextButtonActionPerformed(ActionEvent e){
        // Generate next question
        nextButton.setEnabled(false);
        nextQuestion();
    }
    
    private void startButtonActionPerformed(ActionEvent e){
        String message;
        if (startButton.getText().equals("   Start Exam   ")){
            startButton.setText("    Stop Exam    ");
            nextButton.setEnabled(false);
            // Reset the score
            numberTried = 0;
            numberCorrect = 0;
            commentTextArea.setText("");
            fileMenu.setEnabled(false);
            optionsMenu.setEnabled(false);
            nextQuestion();
        }
        else{
            startButton.setText("   Start Exam   ");
            nextButton.setEnabled(false);
            if (numberTried > 0){
            message = "Questions Tried: " + String.valueOf(numberTried)+ "\n";
            message += "Questions Correct: " +String.valueOf(numberCorrect) + "\n\n"; 
            message += "Your Score: " + new DecimalFormat("0.0").format(100.0 * ((double) numberCorrect / numberTried)) + "%";
            JOptionPane.showConfirmDialog(null, message,
            examTitle + " Results", JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE); }
            givenLabel.setText("");
            answerLabel[0].setText("");
            answerLabel[1].setText("");
            AnswerLabel[0].setText("");
            AnswerLabel[1].setText("");
            answerTextField.setText("");
            AnswerTextField.setText("");
            commentTextArea.setText(centerTextArea(" Choose your Preferred Option Type or press\nStart Exam to continue.")); 
            fileMenu.setEnabled(true);
            optionsMenu.setEnabled(true);
        }
    }
    
    private void openMenuItemActionPerformed(ActionEvent e){
        String myLine;
        JFileChooser openChooser = new JFileChooser();
        openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        openChooser.setDialogTitle("Open Exam File");
        openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Exam Files", "csv")); 
        if(openChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        try{
            BufferedReader inputFile = new BufferedReader(new
            FileReader(openChooser.getSelectedFile()));
            myLine = inputFile.readLine();
            examTitle = parseLeft(myLine);
            myLine = inputFile.readLine();
            header1 = parseLeft(myLine);
            header2 = parseRight(myLine);
            numberTerms = 0;
        do{
            numberTerms++;
            myLine = inputFile.readLine();
            term1[numberTerms - 1] = parseLeft(myLine);
            term2[numberTerms - 1] = parseRight(myLine);
          }
        while (inputFile.ready() && numberTerms < 100);
        if(numberTerms < 5)
           {
            JOptionPane.showConfirmDialog(null, "Must have at least 5 entries in exam file.", 
            "Exam File Error",JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            return;
            }
            inputFile.close();
            // establish frame title
            this.setTitle("Multiple Choice Exam - " + examTitle); 
            // set up menu items
            header1MenuItem.setText(header1 + ", Given " + header1);
            header2MenuItem.setText(header2 + ", Given " + header2); 
            if(header1MenuItem.isSelected()){
                headGivenLabel.setText(header2);
                headAnswerLabel.setText(header1);
                }
            else
                {
                    headGivenLabel.setText(header1);
                    headAnswerLabel.setText(header2);
                }
                    startButton.setEnabled(true);
                    optionsMenu.setEnabled(true);
                    commentTextArea.setText(centerTextArea("  File Loaded,Choose your preferred Option \n    type or press Start Exam to continue.")); 
                }
        catch (Exception ex)
                        {
                        JOptionPane.showConfirmDialog(null, "Error reading in input file - make sure file is correct format.",
                        "Multiple Choice Exam File Error", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE); 
                        return; 
                        }
            }
    }
    
    private void exitMenuItemActionPerformed(ActionEvent e){
        
    }
    
    private void header1MenuItemActionPerformed(ActionEvent e){
        headGivenLabel.setText(header2);
        headAnswerLabel.setText(header1);
    }
    
    private void header2MenuItemActionPerformed(ActionEvent e){
        headGivenLabel.setText(header1);
        headAnswerLabel.setText(header2);
    }
    
    private void mcMenuItemActionPerformed(ActionEvent e){
        answerLabel[0].setVisible(true);
        answerLabel[1].setVisible(true);
        AnswerLabel[0].setVisible(true);
        AnswerLabel[1].setVisible(true);
        answerTextField.setVisible(false);
        AnswerTextField.setVisible(false);
    }
    
    private void typeMenuItemActionPerformed(ActionEvent e){
        answerLabel[0].setVisible(false);
        answerLabel[1].setVisible(false);
        AnswerLabel[0].setVisible(false);
        AnswerLabel[1].setVisible(false);
        answerTextField.setVisible(true);
        answerTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        AnswerTextField.setVisible(false);
    }
    
    private String parseLeft(String s){
        int cl;
        // find comma
        cl = s.indexOf(",");
        return (s.substring(0, cl));
    }
    private String parseRight(String s){
        int cl;
        // find comma
        cl = s.indexOf(",");
        return (s.substring(cl + 1));
    }
    private String centerTextArea(String s){
        // centers up to two lines in text area
        int charsPerLine = 33;
        String sOut = "";
        int j = s.indexOf("\n");
        int nSpaces;
        if (j == -1){
            // single line
            sOut = "\n" + spacePadding((int) ((charsPerLine - s.length()) / 1))+ s; }
        else{
            // first line
            String l = s.substring(0, j);
            sOut = "\n" + spacePadding((int) ((charsPerLine - l.length()) / 1.2))+ l; 
            // second line
            l = s.substring(j + 1);
            sOut += "\n" + spacePadding((int) ((charsPerLine - l.length()) / 1.2))+ l ; }
            return(sOut);
        }
    private String spacePadding(int n){
        String s = "";
            if (n != 0)
                for (int i = 0; i < n; i++)
                s += " ";
                return(s);
        }
    
    private void nextQuestion(){
        boolean[] termUsed = new boolean[numberTerms];
        int[] index = new int[4];
        int j;
        commentTextArea.setText("");
        // Generate the next question based on selected options 
        correctAnswer = myRandom.nextInt(numberTerms);
        if (header1MenuItem.isSelected()){
            givenLabel.setText(term2[correctAnswer]);
        }
        else{
            givenLabel.setText(term1[correctAnswer]);
        }
        if (mcMenuItem.isSelected()){
            // Multiple choice answers
            for (int i = 0; i < numberTerms; i++){
                termUsed[i] = false;
            }
            // Pick four random possiblities
            for (int i = 0; i < 4; i++){
                do{
                    j = myRandom.nextInt(numberTerms);
                }
                while (termUsed[j] || j == correctAnswer);
                termUsed[j] = true;
                index[i] = j;
            }
                // Replace one with correct answer
                index[myRandom.nextInt(4)] = correctAnswer;
                // Display multiple choice answers in label boxes 
            if(header1MenuItem.isSelected()){
                answerLabel[0].setText(term1[index[0]]);
                answerLabel[1].setText(term1[index[1]]);
                AnswerLabel[0].setText(term1[index[2]]);
                AnswerLabel[1].setText(term1[index[3]]);
            }
            else{
                answerLabel[0].setText(term2[index[0]]);
                answerLabel[1].setText(term2[index[1]]);
                AnswerLabel[0].setText(term2[index[2]]);
                AnswerLabel[1].setText(term2[index[3]]);
            }
        }
        else{
            // Type-in answers
            answerTextField.setEditable(true);
            answerTextField.setText("");
            answerTextField.requestFocus();
            AnswerTextField.setEditable(true);
            AnswerTextField.setText("");
            AnswerTextField.requestFocus();
        }
    }
    
    private void updateScore(boolean correct){
        // Check if answer is correct
        if (correct){
            numberCorrect++;
            commentTextArea.setText(centerTextArea("Correct Answer !!!"));
        }
        else
            commentTextArea.setText(centerTextArea("  Sorry it's a Wrong Answer...Correct Answer\n Shown above.")); 
            commentTextArea.setForeground(Color.RED);
            // Display correct answer
        if (mcMenuItem.isSelected()){
            if (header1MenuItem.isSelected())
                answerLabel[0].setText(term1[correctAnswer]);
                              
            else
                answerLabel[0].setText(term2[correctAnswer]);
                answerLabel[1].setText("");
                AnswerLabel[0].setText("");
                AnswerLabel[1].setText("");
        }
        else{
            if (header1MenuItem.isSelected())
                answerTextField.setText(term1[correctAnswer]);
            
        else
        answerTextField.setText(term2[correctAnswer]);
        
        }
        startButton.setEnabled(true);
        nextButton.setEnabled(true);
        nextButton.requestFocus();
    }
    
    public String soundex(String w){
    // Generates Soundex code for W based on Unicode value 
   // Allows answers whose spelling is close, but not exact 
        String wTemp, 
        s = "";
        int l;
        int wPrev, wSnd, cIndex;
   // Load soundex function array
        int[] wSound = {0, 1, 2, 3, 0, 1, 2, 0, 0, 2, 2, 4, 5, 5, 0, 1, 2, 6, 2, 3, 0, 1, 0,2, 0, 2};
        wTemp = w.toUpperCase();
        l = w.length();
        if (l != 0){
            s = String.valueOf(w.charAt(0));
            wPrev = 0;
        if (l > 1){
            for (int i = 1; i < l; i++){
                cIndex = (int) wTemp.charAt(i) - 65;
                if (cIndex >= 0 && cIndex <= 25){
                    wSnd = wSound[cIndex] + 48;
                if (wSnd != 48 && wSnd != wPrev){
                    s += String.valueOf((char) wSnd);
                    }
            wPrev = wSnd;
                }
            }
        }
        else
            s = "";
        }
        return(s);
    }

        
}
