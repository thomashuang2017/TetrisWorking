import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;

public class TetrisPanel extends JPanel implements KeyListener {     //繼承keylistener    
    static final int spaceX0=150, spaceY0=0;
    final int holdX0=3, holdY0=80;
    final int nextX0=530, nextY0=80;  

    static Map map;
    Piece movingPiece, holdPiece, nextPiece;  // holdPiece 拿,  nextPiece 看下個 

    private boolean change;           // 換新的Piece
    
    // private int flag = 0;  //: b4 A6.006.A
    private boolean notFalling = false;
    // [] S、Z、L、J、I、O、T [] Rotate [] 4*4
    

    public TetrisPanel() {    //創造PANEL
        this.setLayout(null);
        this.setBackground(Color.BLACK); //背景
        this.map= new Map(10,20); 
       // this.map.colorInit();
        JLabel NEXT = new JLabel("NEXT");  //可改成BUTTON
        NEXT.setFont(new Font("", Font.BOLD, 50));
        NEXT.setBounds(500, 0, 200, 100);
        NEXT.setForeground(Color.white);
        this.add(NEXT);
        JLabel HOLD = new JLabel("HOLD");
        HOLD.setFont(new Font("", Font.BOLD, 50));
        HOLD.setBounds(0, 0, 200, 100);
        HOLD.setForeground(Color.white);
        this.add(HOLD);
        this.map.clearMap();//?
        this.nextPiece= new Piece((int)(Math.random()*7)); 
        newPiece(); // must after nextPiece is initialized
        holdPiece=null;
        Timer timer = new Timer(800, new TimerListener()); //延遲緩面下降
        timer.start(); //開始延遲
    }
    
    // public void newBlock() {  //: b4 A6.006.F
    private void newPiece() {
        this.notFalling = false;
        //D if(this.nextPiece==null) throw new Error();
        this.movingPiece = this.nextPiece;
        this.change = true;
        this.nextPiece= new Piece((int)(Math.random()*7));
        //Piece.turnState = 0;
        this.movingPiece.setPos40();
    	  if(gameOver()) {
            this.map.clearMap();
            //JOptionPane.showMessageDialog(null, "GAME OVER");
        }
        repaint();
    }
    /*
    private void setBlock(int x, int y, int type, int state)  {
        //this.notFalling = true;
        for(int i = 0; i < 16; i++) {
            if(Piece.shapes[type][state][i] == 1) {
                this.map.map[x+i%4][y+i/4] = type+1;
            }
        }
    }
    */
    
    //public int gameOver(int x, int y) {
    public boolean gameOver() {
    	 if(this.movingPiece==null) throw new Error();
       if(this.movingPiece.free() == false) return true;
       else{
        	return false;
       }	
    }
    

    void stopDown(){
    //Sleep(500);
       this.movingPiece.plugPiece();
       this.notFalling = true;
       this.map.updateMap();
       newPiece();            
    }


    public void paintComponent(Graphics g) {  //ok
        super.paintComponent(g);
        this.map.paintMap(g);
        if(this.notFalling == false) { //畫(運動中的)落下的piece
            this.movingPiece.paintPiece(g, spaceX0, spaceY0);            
        }
        if(holdPiece!=null) {//畫hold
            holdPiece.paintPiece(g, holdX0, holdY0);
        }
        //for (int i = 0; i < 16; i++) { //畫next
            // //Piece.paintPiece(g, nextX0, nextY0, this.next, 0, 0, 0);
            // Piece.paintPiece(g, nextX0, nextY0, this.nextPiece.blockType, 0, 0, 0);
            this.nextPiece.paintPiece(g, nextX0, nextY0);
        //}
    }
    
    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
    
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_DOWN:
            if(this.movingPiece.down_shift()==false){
               stopDown();
            }  
            repaint();          
            break;
        case KeyEvent.VK_UP:
            this.movingPiece.rotate();
            repaint();
            break;
        case KeyEvent.VK_RIGHT:
            this.movingPiece.r_shift();
            repaint();
            break;
        case KeyEvent.VK_LEFT:
            this.movingPiece.l_shift();
            repaint();
            break;
        case KeyEvent.VK_SPACE:
            while(this.movingPiece.down_shift()) { repaint();  }
            stopDown();
            break;
        case KeyEvent.VK_SHIFT:
            if(this.change) {
            	 if(holdPiece==null) {
                  holdPiece= movingPiece;
                  holdPiece.setPos00();
                  repaint();
                  newPiece();
               }   
               else {
            	 	  {
                     Piece tmp= holdPiece;  
                     holdPiece= movingPiece;  movingPiece= tmp;
                  }   
                  this.holdPiece.setPos00();
                  this.movingPiece.setPos40();
                  this.change= false;
                  repaint();
               }   
            }
            break;
        }
    }
    
    void Sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Unexcepted interrupt");
            System.exit(0);
        }
    }

    void doClock(){   
      if(this.movingPiece.down_shift()==false){
        stopDown();
      }
      else{
         repaint();
      }
    }

    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           doClock();
        }
    }
}
