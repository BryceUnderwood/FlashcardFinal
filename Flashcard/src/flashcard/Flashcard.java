/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcard;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ubryc
 */
public class Flashcard implements ActionListener {
    
    private int count = 0;
    private JLabel question;
    private JFrame frame;
    private JPanel  panel;

    public Flashcard(){
        
        frame = new JFrame();
        
        JButton button = new JButton("Flip Card");
        JButton nextButton = new JButton("Next Card");
        button.addActionListener(this);
        
        question = new JLabel("A question for the flashcard");
        
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 100, 300));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(question);
        panel.add(button);
        panel.add(nextButton);
    
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
        question.setText("Answer to the question");
    }
    
}
