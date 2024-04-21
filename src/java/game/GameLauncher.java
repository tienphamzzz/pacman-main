package game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

//Lớp chứa phương thức main để chạy game
public class GameLauncher {
    private static UIPanel uiPanel;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Pacman");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setLayout(new BorderLayout());

        //Khởi tạo khu vực chơi game
        try {
            window.add(new GameplayPanel(448,496), BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Khởi tạo khu vực hiển thị thông tin( Điểm )
        uiPanel = new UIPanel(448,40);
        window.add(uiPanel, BorderLayout.SOUTH);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    //Phương thức trả về UIPanel
    public static UIPanel getUIPanel() {
        return uiPanel;
    }
}
