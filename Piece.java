import java.awt.*;
import javax.swing.*;


class Piece {
	
  //  0  1  2  3
  //  4  5  6  7
  //  8  9 10 11
  // 12 13 14 15
  //
  // i%4 --> x, i/4 --> y 
  // x+y*4 --> i
  
   private static int[] shapeI_0= new int[] {
   	  0, 0, 0, 0,
      1, 1, 1, 1,
      0, 0, 0, 0, 
      0, 0, 0, 0, 
   }; //16是因為 變形都會在16格中變化         	
   private static int[] shapeI_1= new int[] {
      0, 1, 0, 0,
      0, 1, 0, 0,
      0, 1, 0, 0, 
      0, 1, 0, 0,
   }; 
   private static int[] shapeS_0= new int[] {
      0, 1, 1, 0, 
      1, 1, 0, 0,
      0, 0, 0, 0,
      0, 0, 0, 0,  
   }; 
   private static int[] shapeS_1= new int[] {
      1, 0, 0, 0,
      1, 1, 0, 0,
      0, 1, 0, 0,
      0, 0, 0, 0,   
   }; 
   private static int[] shapeZ_0= new int[] {
      1, 1, 0, 0,
      0, 1, 1, 0, 
      0, 0, 0, 0, 
      0, 0, 0, 0  
   }; 
   private static int[] shapeZ_1= new int[] {
      0, 1, 0, 0,  
      1, 1, 0, 0, 
      1, 0, 0, 0, 
      0, 0, 0, 0
   }; 

   private static int[] shapeG_0= new int[] { // Gamma 型, 反L型
      0, 1, 0, 0, 
      0, 1, 0, 0, 
      1, 1, 0, 0, 
      0, 0, 0, 0 
   }; 
   private static int[] shapeG_1= new int[] {
      1, 0, 0, 0, 
      1, 1, 1, 0, 
      0, 0, 0, 0, 
      0, 0, 0, 0
   }; 
   private static int[] shapeG_2= new int[] {
      1, 1, 0, 0, 
      1, 0, 0, 0, 
      1, 0, 0, 0, 
      0, 0, 0, 0
   }; 
   private static int[] shapeG_3= new int[] {
      1, 1, 1, 0, 
      0, 0, 1, 0, 
      0, 0, 0, 0, 
      0, 0, 0, 0
   }; 
   
   private static int[] shapeO_0= new int[] {
      1, 1, 0, 0, 
      1, 1, 0, 0, 
      0, 0, 0, 0, 
      0, 0, 0, 0
   }; 

   private static int[] shapeL_0= new int[] {
      1, 0, 0, 0, 
      1, 0, 0, 0, 
      1, 1, 0, 0, 
      0, 0, 0, 0
   }; 
   private static int[] shapeL_1= new int[] {
      1, 1, 1, 0, 
      1, 0, 0, 0, 
      0, 0, 0, 0, 
      0, 0, 0, 0 
   }; 
   private static int[] shapeL_2= new int[] {
      1, 1, 0, 0, 
      0, 1, 0, 0, 
      0, 1, 0, 0, 
      0, 0, 0, 0
   }; 
   private static int[] shapeL_3= new int[] {
      0, 0, 1, 0, 
      1, 1, 1, 0, 
      0, 0, 0, 0, 
      0, 0, 0, 0
   }; 
   
   private static int[] shapeT_0= new int[] {
      0, 1, 0, 0, 
      1, 1, 1, 0, 
      0, 0, 0, 0, 
      0, 0, 0, 0
   }; 
   private static int[] shapeT_1= new int[] {
      0, 1, 0, 0, 
      1, 1, 0, 0, 
      0, 1, 0, 0, 
      0, 0, 0, 0
   }; 
   private static int[] shapeT_2= new int[] {
      1, 1, 1, 0, 
      0, 1, 0, 0, 
      0, 0, 0, 0, 
      0, 0, 0, 0 
   }; 
   private static int[] shapeT_3= new int[] {
      0, 1, 0, 0, 
      0, 1, 1, 0, 
      0, 1, 0, 0, 
      0, 0, 0, 0 
   }; 
   private static int[][][] shapes= new int[][][] {
      { shapeI_0, shapeI_1, shapeI_0, shapeI_1 },
      { shapeS_0, shapeS_1, shapeS_0, shapeS_1 },
      { shapeZ_0, shapeZ_1, shapeZ_0, shapeZ_1 },
      { shapeG_0, shapeG_1, shapeG_2, shapeG_3 },
      { shapeO_0, shapeO_0, shapeO_0, shapeO_0 },
      { shapeL_0, shapeL_1, shapeL_2, shapeL_3 },
      { shapeT_0, shapeT_1, shapeT_2, shapeT_3 } 
   };
    
   //static int blockType, turnState;  //: b4 A6.004.D
   private int blockType, turnState;
   private int posX, posY;

   Piece(int bt) {
   	  this.blockType=bt;   this.turnState=0;
   	  this.posX=0;  this.posY=0;
   }

   void setPos00() {
   	  this.posX=0;  this.posY=0;
   }
   void setPos40() {
   	  this.posX=4;  this.posY=0;
   }
   static void paintCell(
      Graphics g, int blockType, int winX, int winY
   ) {
      g.drawImage(Map.color[blockType], winX, winY, null);
   }

   //  static void paintPiece(
   //   Graphics g, int baseX, int baseY, 
   //   int blockType, int turnState, int posX, int posY) {
   void paintPiece(
      Graphics g, int baseX, int baseY 
      //int posX, int posY 
   ) {
      // for(int i = 0; i < 16; i++) {
      //    if(Piece.shapes[this.blockType][this.turnState][i] == 1) {
      //        Piece.paintCell(
      //           g, this.blockType, 
      //           (i%4+posX)*33+3+baseX, (i/4+posY)*33+3+baseY
      //        );                 
      //    }
      // }
      for(int x= 0; x<4; x++) {
         for(int y= 0; y<4; y++) {
            if(Piece.shapes[this.blockType][this.turnState][y*4+x] == 1) {
                Piece.paintCell(
                   g, this.blockType, 
                   (x+posX)*33+3+baseX, (y+posY)*33+3+baseY
                );                 
            }    
         }
      }
   }    
   
//   this.posX, this.posY, 
//   this.blockType, this.turnState
   
   // // public boolean blow(int x, int y, int type, int state) {
   // public boolean blow() {
   public boolean free() {  //: not collision
      for(int x= 0; x<4; x++) {
         for(int y= 0; y<4; y++) {
      //for(int i = 0; i < 16; i++) {
            if(Piece.shapes[this.blockType][this.turnState][y*4+x] == 1) {
               //if(this.posX+i%4 >= 10 || this.posY+i/4 >= 20 
               //   || this.posX+i%4 < 0 || this.posY+i/4 < 0)           
               if(TetrisPanel.map.indexOutside(this.posX+x,this.posY+y))
                   return false;
               //if(Map.map[this.posX+i%4][this.posY+i/4] != 0)
               if(TetrisPanel.map.cellEmpty(this.posX+x,this.posY+y))
                   return false;
            }       
         }
      }
      return true;
    }

    public void rotate() {
        int oldState = this.turnState;
        this.turnState = (oldState+1)%4;
        if(this.free()) {
           ;
        }
        else {
        	  this.turnState = oldState;
        } 
        //repaint();
    }
   
   public boolean r_shift() {
        boolean canShift = false;
        int oldPosX= this.posX;
        this.posX++;
        if(this.free()) {
            //this.movingPiece.posX++;
            canShift = true;
        }
        else {
        	 this.posX= oldPosX;
        }
        //repaint();
        return canShift;
    }
   
   public boolean l_shift() {
        boolean canShift = false;
        int oldPosX= this.posX;
        this.posX--;
        if(this.free()) {
           canShift = true;
        }
        else{
           this.posX= oldPosX;
        }
        //repaint();
        return canShift;		
    }
   
   public boolean down_shift() {
        int oldY= this.posY;
        this.posY++;
        boolean canDown = this.free();
        if(canDown) {
           ;
        }
        else {
           this.posY= oldY;
           //stopDown(); 
        }
        //repaint();
        return canDown;
   }

   //void setBlock(int x, int y, int type, int state)  {
   void plugPiece()  {
       //this.notFalling = true;
       for(int x= 0; x<4; x++) {
         for(int y= 0; y<4; y++) {
       //for(int i = 0; i < 16; i++) {
              if(Piece.shapes[this.blockType][this.turnState][y*4+x] == 1) {
                //  TetrisPanel.map.array2D[this.posX+i%4][this.posY+i/4] 
                //     = this.blockType+1;
                TetrisPanel.map.setCell(this.posX+x,this.posY+y,this.blockType+1); 
              }                    
         }
       }
   }
    
}