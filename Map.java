import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;

class Map {

   static private Image b1, b2;  //: 深淺兩種底色
   static Image[] color = new Image[7];      //7種俄羅斯方塊的cell

   //static void colorInit() {  
   static {  
      b1 = Toolkit.getDefaultToolkit().getImage("pngs/background1.png"); //背景灰黑
      b2 = Toolkit.getDefaultToolkit().getImage("pngs/background2.png");
      color[0] = Toolkit.getDefaultToolkit().getImage("pngs/blue.png");   //創造COLOR 射進COLOR陣列
      color[1] = Toolkit.getDefaultToolkit().getImage("pngs/green.png");
      color[2] = Toolkit.getDefaultToolkit().getImage("pngs/red.png");
      color[3] = Toolkit.getDefaultToolkit().getImage("pngs/deepblue.png");
      color[4] = Toolkit.getDefaultToolkit().getImage("pngs/yellow.png");
      color[5] = Toolkit.getDefaultToolkit().getImage("pngs/orange.png");
      color[6] = Toolkit.getDefaultToolkit().getImage("pngs/pink.png");
   }
  
   //static int width=10, height=20;
   final int width, height;
   //public static int[][] map = new int[width][height];  //寬10 高20, 二維陣列
   private int[][] array2D; // = new int[width][height];  //寬10 高20, 二維陣列
   // x to right: 0..width-1 
   // y to down:  0..height-1

   

   Map(int w, int h) {  
   	  this.width=w;  this.height=h; 
   	  this.array2D= new int[width][height];  //寬10 高20, 二維陣列
   } 

   

   //static void clearMap() {
   void clearMap() {
      for(int i = 0; i < width; i++)
         for(int j = 0; j < height; j++)  this.array2D[i][j] = 0;
   }

   //static void paintBgCell(Graphics g, int i, int j) {
   void paintBgCell(Graphics g, int i, int j) {
      int winX= i*30+3*(i+1)+TetrisPanel.spaceX0;
      int winY= j*30+3*(j+1);
      if((i+j)%2 == 0)
         g.drawImage(Map.b1, winX, winY, null);
      else
         g.drawImage(Map.b2,winX,winY, null);
   }

   void setCell(int x, int y, int value) {
      this.array2D[x][y] 
          = value;
 	 }

   boolean indexOutside(int x, int y){
      return x>=this.width||y>=this.height||x<0||y<0;    
   }
	 boolean cellEmpty(int x, int y) {
      return this.array2D[x][y]!=0;
 	 }


   // static void paintCell(Graphics g, int i, int j){
   void paintCell(Graphics g, int i, int j){
      int winX= i*30+3*(i+1)+TetrisPanel.spaceX0;
      int winY= j*30+3*(j+1);
      int blockType= this.array2D[i][j]-1;
      g.drawImage(Map.color[blockType], winX, winY, null);
   }

   //static void paintMap(Graphics g) {
   void paintMap(Graphics g) {
      for(int i = 0; i < width; i++) {
         for(int j = 0; j < height; j++) {
            if(this.array2D[i][j] == 0) {
               this.paintBgCell(g, i, j); 
            } 
            else {
               this.paintCell(g, i, j);
            }
         }         
      }      
   }

   //static void updateMap() { 
   void updateMap() { 
      //  int idx = 19, access = 0;
      int newline = height-1;
      boolean success = false;
      for(int line = height-1; line >= 0; line--) {
      // process from bottom to top
         int cnt = 0;
         for(int j = 0; j < width; j++) {
             if(this.array2D[j][line] != 0)  cnt++;
         }
         if(cnt == width) {
             success = true;
             //for(int j = 0; j < 10; j++) {
             //    Map.array2D[j][line] = 0;
             //}
         } 
         else {
             for(int j = 0; j < width; j++) {
                 this.array2D[j][newline] = this.array2D[j][line];
             }
             newline--;
         }
      }
      /*if(success == 1)
          Sleep(500);*/
   }
  
    
}