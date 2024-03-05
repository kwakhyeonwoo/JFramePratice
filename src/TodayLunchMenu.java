import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TodayLunchMenu extends JFrame {
    private final Map<String, String[]> lunchMenuBrands = new HashMap<>();
    private final String[] lunchMenu = {"돈까스", "집 밥", "피자", "냉면", "짜장면", "족발", "국밥", "분식", "치킨", "볶음밥"};

    public TodayLunchMenu() {
        setTitle("Today's Lunch Menu");
        setSize(400, 300); // 팝업 창의 크기 설정
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initializeLunchMenuBrands();

        String randomMenu = getRandomMenu();
        JTextArea textArea = new JTextArea("오늘의 점심 메뉴: " + randomMenu);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER); // 텍스트 영역을 중앙에 배치

        JButton brandButton = new JButton("브랜드");
        brandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBrandPopup(randomMenu);
            }
        });
        getContentPane().add(brandButton, BorderLayout.SOUTH); // "브랜드" 버튼을 팝업 창의 아래에 추가

        setVisible(true);
    }

    private void initializeLunchMenuBrands() {
        lunchMenuBrands.put("돈까스", new String[]{"호랑이", "혜화", "바삭하게"});
        lunchMenuBrands.put("집 밥", new String[]{"스팸", "계란", "나물"});
        lunchMenuBrands.put("피자", new String[]{"피자는 여기","서오릉","반올림"});
        lunchMenuBrands.put("냉면", new String[]{"","",""});
        lunchMenuBrands.put("짜장면", new String[]{"희래등","",""});
        lunchMenuBrands.put("족발", new String[]{"","",""});
        lunchMenuBrands.put("국밥", new String[]{"","",""});
        lunchMenuBrands.put("분식", new String[]{"","",""});
        lunchMenuBrands.put("치킨", new String[]{"","",""});
        lunchMenuBrands.put("볶음밥", new String[]{"","",""});       
        // 나머지 메뉴에 대한 브랜드 목록 추가
    }

    private String getRandomMenu() {
        Random random = new Random();
        int index = random.nextInt(lunchMenu.length);
        return lunchMenu[index];
    }

    private void showBrandPopup(String lunchMenu) {
        String[] brands = lunchMenuBrands.get(lunchMenu);
        if (brands != null && brands.length > 0) {
            Random random = new Random();
            String randomBrand = brands[random.nextInt(brands.length)]; // 랜덤하게 브랜드 선택

            JOptionPane.showMessageDialog(this, randomBrand, "브랜드 정보", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "해당 메뉴의 브랜드 정보가 없습니다.", "알림", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodayLunchMenu::new);
    }
    
    private void showLunchMenu() {
        SwingUtilities.invokeLater(() -> {
            TodayLunchMenu lunchMenu = new TodayLunchMenu();
            lunchMenu.setVisible(true);
        });
    }
}
