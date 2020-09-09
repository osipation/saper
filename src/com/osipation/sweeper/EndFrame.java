package com.osipation.sweeper;

import com.osipation.JavaSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class EndFrame extends JFrame {
    JPanel endPanel;
    JButton replayButton = new JButton("Заново!");

    JButton exitButton = new JButton("В пизду!");

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

    public EndFrame(JavaSweeper sweep) {
        this.sweep = sweep;
        endPanel = new JPanel();
        endPanel.add(replayButton);
        endPanel.add(exitButton);
        replayButton.addActionListener(new ReplayListener());
        exitButton.addActionListener(new ExitListener());
        this.getContentPane().add(endPanel);
        //возможный вариант через переопределение
//        exitButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(0);
//            }
//        });
//        replayButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                sweep.startGameProcess();
//            }
//        });

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
//Возможен вариант через вложенные классы
    class ReplayListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            sweep.startGameProcess();

        }
    }

    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

}
