import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;
//�D�{������ 
public class Tetris extends JFrame {                        //�Ф@��jframe ����
    public final int WIDTH = 700, HEIGHT = 800;							//�]�e ��
    public Tetris() {																			//����Tetris���
        this.setTitle("Tetris Test");
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TetrisPanel panel = new TetrisPanel();          //�y�@�ӭ��O
        //this.setLayout(null);
        //panel.setBounds(0, 0, 700, 700);                //���O�e��
        this.add(panel);																			//�[�ipanel
        this.addKeyListener(panel);                 //��ť����panel              
    }
    public static void main(String[] dummy) {
        Tetris gui = new Tetris();
        gui.setVisible(true);     //�I�sTetris
    }
}
