package game.entities;

import game.GameplayPanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//Classe abtraite pour décrire une entité mouvante
public abstract class MovingEntity extends Entity {
    protected int spd;
    protected int xSpd = 0;
    protected int ySpd = 0;
    protected BufferedImage sprite;
    protected float subimage = 0;
    protected int nbSubimagesPerCycle;
    protected int direction = 0;
    protected float imageSpd = 0.2f;

    public MovingEntity(int size, int xPos, int yPos, int spd, String spriteName, int nbSubimagesPerCycle, float imageSpd) {
        super(size, xPos, yPos);
        this.spd = spd;
        try {
            this.sprite = ImageIO.read(new File("assets/img/" + spriteName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.nbSubimagesPerCycle = nbSubimagesPerCycle;
            this.imageSpd = imageSpd;
    }

    @Override
    public void update() {
        updatePosition();
    }

    public void updatePosition() {
        //Cập nhật vị trí thực thể
        if (!(xSpd == 0 && ySpd == 0)) { //Nếu tốc độ ngang hoặc tốc độ dọc không bằng 0 thì chúng ta tăng vị trí ngang và dọc tương ứng
            xPos+=xSpd;
            yPos+=ySpd;

            // Tùy thuộc vào hướng được thực hiện, chúng tôi thay đổi giá trị của hướng (một số nguyên cho phép chúng tôi biết cụ thể phần hình ảnh sẽ hiển thị)
            if (xSpd > 0) {
                direction = 0;
            } else if (xSpd < 0) {
                direction = 1;
            } else if (ySpd < 0) {
                direction = 2;
            } else if (ySpd > 0) {
                direction = 3;
            }

            //Chúng tôi tăng giá trị của hình ảnh hiện tại của hoạt ảnh cần hiển thị (tốc độ có thể thay đổi) và tùy thuộc vào số lượng hình ảnh của hoạt ảnh mà các vòng lặp giá trị
            subimage += imageSpd;
            if (subimage >= nbSubimagesPerCycle) {
                subimage = 0;
            }
        }

        //Nếu thực thể vượt ra ngoài rìa của khu vực chơi, nó sẽ chuyển sang phía bên kia
        if (xPos > GameplayPanel.width) {
            xPos = 0 - size + spd;
        }

        if (xPos < 0 - size + spd) {
            xPos = GameplayPanel.width;
        }

        if (yPos > GameplayPanel.height) {
            yPos = 0 - size + spd;
        }

        if (yPos < 0 - size + spd) {
            yPos = GameplayPanel.height;
        }
    }

    @Override
    public void render(Graphics2D g) {
        //Theo mặc định, chúng tôi coi mỗi "sprite" chứa 4 biến thể hoạt ảnh tương ứng với một hướng và mỗi hoạt ảnh có một số lượng hình ảnh nhất định
        //Biết được điều này nên chúng ta chỉ hiển thị phần ảnh sprite tương ứng với đúng hướng và khung bên phải của ảnh động
        g.drawImage(sprite.getSubimage((int)subimage * size + direction * size * nbSubimagesPerCycle, 0, size, size), this.xPos, this.yPos,null);
    }

    //Méthode pour savoir si l'entité est bien positionnée sur une case de la grille de la zone de jeu ou non
    public boolean onTheGrid() {
        return (xPos%8 == 0 && yPos%8 == 0);
    }

    //Méthode pour savoir si l'entité est dans la zone de jeu ou non
    public boolean onGameplayWindow() { return !(xPos<=0 || xPos>= GameplayPanel.width || yPos<=0 || yPos>= GameplayPanel.height); }

    public Rectangle getHitbox() {
        return new Rectangle(xPos, yPos, size, size);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void setSprite(String spriteName) {
        try {
            this.sprite = ImageIO.read(new File("assets/img/" + spriteName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getSubimage() {
        return subimage;
    }

    public void setSubimage(float subimage) {
        this.subimage = subimage;
    }

    public int getNbSubimagesPerCycle() {
        return nbSubimagesPerCycle;
    }

    public void setNbSubimagesPerCycle(int nbSubimagesPerCycle) {
        this.nbSubimagesPerCycle = nbSubimagesPerCycle;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getxSpd() {
        return xSpd;
    }

    public void setxSpd(int xSpd) {
        this.xSpd = xSpd;
    }

    public int getySpd() {
        return ySpd;
    }

    public void setySpd(int ySpd) {
        this.ySpd = ySpd;
    }

    public int getSpd() {
        return spd;
    }
}
