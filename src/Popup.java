
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Popup extends JFrame {
    private final MovieAPI movieAPI;
    

    public Popup() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        movieAPI = new MovieAPI("de922608690b9703d6b8a689391a10b0");

        JPanel contentPane = new JPanel(new GridLayout(3, 3));
        contentPane.setBackground(new Color(250, 150, 230));

        for (int i = 0; i < 9; i++) {
            JButton button;
            switch (i) {
                case 0:
                    button = new JButton("Tictac");
                    break;
                case 1:
                    button = new JButton("Lotto");
                    break;
                case 2:
                    button = new JButton("User");
                    break;
                case 3:
                    button = new JButton("Movie");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            showMovieInfo();
                        }
                    });
                    break;
                case 4:
                    button = new JButton("LunchMenu");                    
                    break;
                case 5:
                    button = new JButton("SnakeGame");                   
                    break;
                case 6:
                    button = new JButton("LanguageGame");
                    break;
//                case 7:
//                    button = new JButton("CheckWeather");
//                    button.addActionListener(new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//                            showWeatherInfo();
//                        }
//                    });
//                    break;
                case 8:
                    button = new JButton("Note");
                    break;
                default:
                    button = new JButton("Button " + (i + 1));
            }
            button.setForeground(Color.black);
            button.setBackground(Color.green);
            button.addActionListener(new ButtonActionListener(i + 1));
            contentPane.add(button);
        }

        setContentPane(contentPane);
        setVisible(true);
    }

    private void showMovieInfo() {
        SwingUtilities.invokeLater(() -> {
            String movieInfo = movieAPI.getRealTimeMovieInfo();
            JFrame movieFrame = new JFrame("Real Time Movie Info");
            JTextArea textArea = new JTextArea(movieInfo);
            JScrollPane scrollPane = new JScrollPane(textArea);
            movieFrame.add(scrollPane);
            movieFrame.setSize(800, 600);
            movieFrame.setLocationRelativeTo(null);
            movieFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            movieFrame.setVisible(true);
        });
    }

//    private void showWeatherInfo() {
//        SwingUtilities.invokeLater(() -> {
//            new WeatherPopup(weather); // WeatherPopup 객체 생성
//        });
//    }
    

    private class ButtonActionListener implements ActionListener {
        private int buttonNumber;

        public ButtonActionListener(int buttonNumber) {
            this.buttonNumber = buttonNumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (buttonNumber) {
                case 1:
                    new Tictac().setVisible(true);
                    break;
                case 2:
                    new LottoNumber().actionPerformed(e);
                    break;
                case 3:
                	new User().actionPerformed(e);
                    break;
                case 5: 
                	new TodayLunchMenu();
                	break;
                case 6:
                	new GameFrame();
                	break;
                case 7:
                	new LanguageGame();
                	break;
////                case 8:
////                	showWeatherInfo();
//                	break;
                case 9:
                	new Note();
                	break;
                
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Popup();
        });
    }
}
