package com.osipation.sweeper;

import com.osipation.JavaSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class EndFrame extends JFrame {
    public JPanel endPanel;
    JButton replay = new JButton("Заново!");
    JButton exit = new JButton("В пизду!");
    private JavaSweeper sweep;

//    private JPanel initPanel (){
//        return new JPanel(){
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//
//            }
//        };
//    }

    public EndFrame (JavaSweeper sweep){
        this.sweep = sweep;
        endPanel = new JPanel();
        endPanel.add(replay);
        endPanel.add(exit);
        this.getContentPane().add(endPanel);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        replay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sweep.startGameProcess();
                EndFrame.this.dispatchEvent(new WindowEvent(EndFrame.this, WindowEvent.WINDOW_CLOSING));

            }
        });
        initEndFrame();

    }

    private void initEndFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//по умолчанию остановка программы при закрытии
        setTitle("Конец");//заголовок
        setResizable(false);//изменение размера окна
        setSize(new Dimension(300, 75));
        setVisible(true);
        setLocationRelativeTo(null);//окно по центру
    }

}
