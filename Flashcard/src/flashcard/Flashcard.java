/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcard;

/*Is this working*/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ubryc
 */
public class Flashcard implements ActionListener {
    
    private int count = 0;
    private int prog = 0; //counts progress of cardArray
    private JLabel question;
    private JFrame frame;
    private JPanel panel;
    private final JFileChooser openFileChooser;
    private File cardFile;
    private BufferedReader cardReader;
    private FileSystem fs;
    private Path pathToFile;
    private InputStream cardIn = null;
    private ArrayList <String> cardArray = new ArrayList<String>();
    private int index = 0;
    
    public void selectTheFile(){
            int returnValue = openFileChooser.showOpenDialog(frame);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                cardFile = openFileChooser.getSelectedFile();
                cardFiler();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "No File Choosen\n" + e.getMessage());
            }
            
        }
        else {
            JOptionPane.showMessageDialog(frame, "No File Choosen");
        }
        }
    
    public void showRecord(){
        
        
                /*this.fnameField.setText(contactList.get(index).getFname());
                this.lnameField.setText(contactList.get(index).getLname());
                this.companyField.setText(contactList.get(index).getCompany());
                this.emailField.setText(contactList.get(index).getEmail());*/
        
        //frame.setTitle("Record # " + index + " pf " + cardArray.size());
    }
    
    public void cardFiler() {
        
        fs = FileSystems.getDefault();
        pathToFile = fs.getPath(cardFile.getPath());
        card openedCard;
        String line = "";
        
        try {
            cardIn = Files.newInputStream(pathToFile);
            cardReader = new BufferedReader(new InputStreamReader(cardIn));
            //read the file one line at a time
            
            while((line = cardReader.readLine()) != null){
            String records[] = line.split("~");
            openedCard = new card();
            
            try {
                openedCard.setSide1(records[0]);
                openedCard.setSide2(records[1]);
                
                cardArray.add(records[0]);
                cardArray.add(records[1]);
                
            } catch (NumberFormatException numberFormatException) {
                //ignores lines with int id errors
            }//end of inner try
        }//end of while
            
            //JOptionPane.showMessageDialog(this, "Records read " + contactList.size());
            cardIn.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot open file\n" + ex.getMessage());
            System.exit(1);
        }
        showRecord();
    }
    
    public Flashcard(){
        
        fs = FileSystems.getDefault();
        openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("c\\temp"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (.txt)", "txt"));
        
        frame = new JFrame();
        
        JButton button = new JButton("Flip Card");
        JButton nextButton = new JButton("Next Card");
        JButton addButton = new JButton("Add Card");
        JButton fileButton = new JButton("Open file...");
        button.addActionListener(this);
        
        question = new JLabel("A question for the flashcard");
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(question);
        question.setPreferredSize(new Dimension(500, 16));
        panel.add(button);
        
        panel.add(nextButton);
        
        /*String temp = "123456789";
        JOptionPane.showMessageDialog(frame, temp.length());*/
        
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //fileButton actions
                prog+= 2;
               question.setText(cardArray.get(prog));
               
            }
        });
        
        panel.add(addButton);
addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //addCard actions


            }
        });
        
        panel.add(fileButton);
        fileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //fileButton actions
               selectTheFile(); 
               question.setText(cardArray.get(prog));
               
            }
        });
        
    
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Flash Cards");
        frame.pack();
        frame.setVisible(true);
        
        
        
    }
    
    public static void main(String[] args) {
        new Flashcard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        try {
            question.setText(cardArray.get(prog+1));
        } catch (Exception ec) {
            question.setText("Answer to the question");
        }
    }
    
}
