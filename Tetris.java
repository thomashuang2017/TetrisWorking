import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;
//主程式視窗 
public class Tetris extends JFrame {                        //創一個jframe 視窗
    public final int WIDTH = 700, HEIGHT = 800;							//設寬 高
    public Tetris() {																			//有個Tetris函數
        this.setTitle("Tetris Test");
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TetrisPanel panel = new TetrisPanel();          //造一個面板
        //this.setLayout(null);
        //panel.setBounds(0, 0, 700, 700);                //面板寬高
        this.add(panel);																			//加進panel
        this.addKeyListener(panel);                 //傾聽器給panel              
    }
    public static void main(String[] dummy) {
        Tetris gui = new Tetris();
        gui.setVisible(true);     //呼叫Tetris
    }
}
