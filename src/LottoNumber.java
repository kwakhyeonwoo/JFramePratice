import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import java.util.Arrays;
import java.util.Random;

public class LottoNumber implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] lottoNumbers = generateNumbers();
        StringBuilder sb = new StringBuilder();
        sb.append("로또번호: ");
        for (int number : lottoNumbers) {
            sb.append(number).append(" ");
        }
        
        JOptionPane.showMessageDialog(null, sb.toString(), "로또 번호", JOptionPane.INFORMATION_MESSAGE);
    }

    private int[] generateNumbers() {
        Random random = new Random();
        int[] lottoNumbers = new int[6];
        boolean[] usedNumbers = new boolean[46]; // 인덱스 1부터 사용할 것이므로 46으로 설정

        for (int i = 0; i < 6; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(45) + 1;
            } while (usedNumbers[randomNumber]); // 이미 사용될 번호일 경우 다시 번호 생성

            lottoNumbers[i] = randomNumber;
            usedNumbers[randomNumber] = true;
        }
        
        Arrays.sort(lottoNumbers);

        return lottoNumbers;
    }
}
