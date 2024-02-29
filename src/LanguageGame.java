import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class LanguageGame extends JFrame {
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;

    private final String[] initialConsonants = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    public LanguageGame() {
        setTitle("훈민정음 게임");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();

        setVisible(true);
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        String initialConsonant = getCorrectAnswer(); // 랜덤한 초성 가져오기
        String word = generateWord(initialConsonant); // 해당 초성으로 단어 생성
        questionLabel = new JLabel("아래 자음에 해당하는 단어를 입력하세요: " + initialConsonant);
        answerField = new JTextField();
        submitButton = new JButton("확인");

        panel.add(questionLabel);
        panel.add(answerField);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(word); // 수정: 단어를 매개변수로 전달
            }
        });

        add(panel);
    }

    private void checkAnswer(String word) { // 수정: 단어를 매개변수로 받음
        String userInput = answerField.getText().trim();
        if (userInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "단어를 입력하세요.");
            return;
        }

        if (userInput.equals(word)) {
            JOptionPane.showMessageDialog(this, "정답입니다!");
        } else {
            JOptionPane.showMessageDialog(this, "틀렸습니다. 정답은 " + word + "입니다.");
        }
    }

    private String getCorrectAnswer() {
        Random random = new Random();
        int index = random.nextInt(initialConsonants.length);
        return initialConsonants[index];
    }

    private String generateWord(String initialConsonant) {
        // 사용자가 입력한 초성에 맞는 단어를 생성하여 반환하는 로직 작성
        // 예를 들어, ㄱ이면 "가나다"와 같은 단어를 생성하거나 API를 통해 단어를 가져오도록 구현 가능
        return "가";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LanguageGame::new);
    }
}
