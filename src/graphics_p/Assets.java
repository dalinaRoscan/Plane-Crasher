package graphics_p;

import java.awt.image.BufferedImage;

public class Assets {//incarca o singura data imaginile
    public static BufferedImage player, enemy, follower, sky, cloud, marginCloud, fire, fireEnemy, start, gameOver, instructionsPage, win;
    public static BufferedImage[] newGame = new BufferedImage[2];
    public static BufferedImage[] back = new BufferedImage[2], instructions = new BufferedImage[2];
    public static BufferedImage[] exit = new BufferedImage[2];

    public static void init(){
        player = ImageLoader.loadImage("/images/HeroC.png");
        enemy = ImageLoader.loadImage("/images/EnemyC.png");
        follower = ImageLoader.loadImage("/images/Follower.png");
        sky = ImageLoader.loadImage("/images/Sky.png");
        cloud = ImageLoader.loadImage("/images/Cloud.png");
        marginCloud = ImageLoader.loadImage("/images/Margin_Cloud.png");
        fire = ImageLoader.loadImage("/images/Fire_hero.png");
        fireEnemy = ImageLoader.loadImage("/images/Fire_enemy.png");

        newGame[0] = ImageLoader.loadImage("/images/New Game.png");
        newGame[1] = ImageLoader.loadImage("/images/new_game_hover.png");

        back[0] = ImageLoader.loadImage("/images/Back.png");
        back[1] = ImageLoader.loadImage("/images/back_hover.png");

        instructions[0] = ImageLoader.loadImage("/images/Instructions.png");
        instructions[1] = ImageLoader.loadImage("/images/instructions_hover.png");

        exit[0] = ImageLoader.loadImage("/images/Exit.png");
        exit[1] = ImageLoader.loadImage("/images/exit_hover.png");

        start = ImageLoader.loadImage("/images/Start.png");
        gameOver = ImageLoader.loadImage("/images/Game Over.png");
        instructionsPage = ImageLoader.loadImage("/images/instructionsPage.png");
        win = ImageLoader.loadImage("/images/Win.png");
    }
}
