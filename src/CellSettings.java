import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class CellSettings extends JButton {
   //fg - flag; bg - background; 

   public static final Color bgNotVisible = Color.DARK_GRAY;  //Not revealed tiles
   public static final Color fgNotVisible = Color.RED;    //Mines, flags
   public static final Color bgVisible = Color.GRAY; //Revealed tiles
   public static final Color fgVisible = Color.YELLOW; //Nubmer of mines
   public static final Font fontForNumbers = new Font("Numbers", Font.PLAIN, 25); //Font for the numbers

   //Rows and columns of the minefield
   int rows, columns;
   
   //Is the tile revealed? Is the tile a mine? Is the tile flagged by the player?
   boolean isRevealed, isMine, isFlagged;

   /**Setup the cells */
   public CellSettings(int rows, int columns) {
      super();   //Creating an empty button
      this.rows = rows;
      this.columns = columns;
      super.setFont(fontForNumbers);
   }

   /**Initialise new game, setup the cells again */
   public void newGame(boolean isMine) {
      this.isRevealed = false; // default
      this.isFlagged = false;  // default
      this.isMine = isMine;  // given
      super.setEnabled(true);  // enable button
      super.setText("");   // display blank
      changeColor();
   }

   /**Check the status, use the initial variables for graphic purposes */
   public void changeColor() {
      super.setForeground(isRevealed? fgVisible: fgNotVisible);
      super.setBackground(isRevealed? bgVisible: bgNotVisible);
   }
}