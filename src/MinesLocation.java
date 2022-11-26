
/**Location of mines */
public class MinesLocation {
   
   int numMines;
   boolean[][] isMine = new boolean[MineFieldSettings.ROWS][MineFieldSettings.COLS];

   public MinesLocation() {
      super(); //Create blank object
   }

   /**Creating a new mine field (predefined as for now) */
   public void newMineField(int numMines) {
      this.numMines = numMines;
      isMine[0][0] = true;
      isMine[5][2] = true;
      isMine[9][5] = true;
      isMine[6][7] = true;
      isMine[8][2] = true;
      isMine[2][4] = true;
      isMine[5][7] = true;
      isMine[7][7] = true;
      isMine[3][6] = true;
      isMine[4][8] = true;
   }
}