package game;

import game.entities.PacGum;
import game.entities.SuperPacGum;
import game.entities.ghosts.Ghost;
import game.ghostStates.EatenMode;
import game.ghostStates.FrightenedMode;

import javax.swing.*;
import java.awt.*;

//Panneau de l'interface utilisateur
public class UIPanel extends JPanel implements Observer {
    public static int width;
    public static int height;

    private int score = 0;
    private int lives = 3;
    private ImageIcon lifeIcon = new ImageIcon("assets/img/heart.png");
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel heartLabel1;
    private JLabel heartLabel2;
    private JLabel heartLabel3;

    public UIPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(20.0F));
        scoreLabel.setForeground(Color.white);
        this.add(scoreLabel);

        this.add(Box.createHorizontalGlue());

        livesLabel = new JLabel("Lives: ");
        livesLabel.setFont(scoreLabel.getFont().deriveFont(20.0F));
        livesLabel.setForeground(Color.white);
        this.add(livesLabel);

        heartLabel1 = new JLabel();
        heartLabel1.setIcon(lifeIcon);
        this.add(heartLabel1);

        heartLabel2 = new JLabel();
        heartLabel2.setIcon(lifeIcon);
        this.add(heartLabel2);

        heartLabel3 = new JLabel();
        heartLabel3.setIcon(lifeIcon);
        this.add(heartLabel3);
    }

    public void updateScore(int incrScore) {
        this.score += incrScore;
        this.scoreLabel.setText("Score: " + score);
    }

    public void updateLives() {
        lives--;
        switch (lives){
            case 2:
                heartLabel3.setVisible(false);
                break;
            case 1:
                heartLabel2.setVisible(false);
                break;
            case 0:
                heartLabel1.setVisible(false);
                break;
        }
    }

    public int getScore() {
        return score;
    }

    //L'interface est notifiée lorsque Pacman est en contact avec une PacGum, une SuperPacGum ou un fantôme, et on met à jour le score affiché en conséquence
    @Override
    public void updatePacGumEaten(PacGum pg) {
        updateScore(10);
    }

    @Override
    public void updateSuperPacGumEaten(SuperPacGum spg) {
        updateScore(100);
    }

    @Override
    public void updateGhostCollision(Ghost gh) {
        if (gh.getState() instanceof FrightenedMode) { //Dans le cas où Pacman est en contact avec un fantôme on ne met à jour le score que lorsque ce dernier est en mode "frightened"
            updateScore(500);
        } else if (!(gh.getState() instanceof EatenMode)) {
            updateLives();
        }
    }
}
