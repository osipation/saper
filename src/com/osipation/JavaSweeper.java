package com.osipation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.osipation.sweeper.*;
import com.osipation.sweeper.Box;

public class JavaSweeper extends JFrame {//в виде окна
    private Game game;

    private JPanel panel;//создание панели
    private JLabel label;//статус игры
    private final int COLS = 15;
    private final int ROWS = 15;
    private final int BOMBS = 40;
    private final int IMAGE_SIZE = 50;//размер одной картинки



    public static void main(String[] args) {

        new JavaSweeper();//создание программы
    }

    private JavaSweeper() {//конструктор
        game = new Game(COLS,ROWS,BOMBS);
        startGameProcess();
    }

    public void startGameProcess(){
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel(){//добавление метки о статусе игры
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);//сноска с надписью внизу(юг)
    }


    private void initPanel() {
        panel = new JPanel() {
            @Override//ctrl + о (переопределение)
            protected void paintComponent(Graphics g) {//рисует
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords() ) {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }

            }
        };

        panel.addMouseListener(new MouseAdapter() {//добавил мышечный адаптор
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton (coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton (coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMassege());//после нажатия мыши надпись
                panel.repaint();
                if(game.getState() == GameState.BOMBED || game.getState() == GameState.WINNER){
                    new EndFrame(JavaSweeper.this);
                }

            }
        });

        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));//установка размера
        add(panel);
    }

    private String getMassege() {//чекается состояние игры и появляется надпись
        switch (game.getState()){
            case PLAYED: return "Act wisely";
            case BOMBED: return "ALL IT STARTS WITH A BIG BANG! YOU LOSE...";
            case WINNER: return "Congratulations! You are truly a master";
            default: return  "";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//по умолчанию остановка программы при закрытии
        setTitle("Сапёрчик");//заголовок
        setResizable(false);//изменение размера окна
        setVisible(true);
        pack(); //устанавливает минимальный размер окна
        setLocationRelativeTo(null);//окно по центру
        setIconImage(getImage("icon"));
    }

    private void setImages() {//установка всех картинок
        for (Box box : Box.values()) {//цикл чтоб перебрать все картинки из бокса
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage(String name) {//получение картинок
        String filename = "img/" + name + ".png";//определение имени файла
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(filename));
        return icon.getImage();
    }

}
