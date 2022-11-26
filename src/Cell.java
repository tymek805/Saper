import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Cell extends JButton {
   //FG - flag; BG - background; 

   public static final Color bgNotRevealed = Color.DARK_GRAY;
   public static final Color fgNotRevealed = Color.RED;    // flag, mines
   public static final Color bgRevealed = Color.GRAY;
   public static final Color fgRevealed = Color.YELLOW; // number of mines
   public static final Font fontForNumbers = new Font("Monospaced", Font.BOLD, 20);

   //Rows and columns of the minefield
   int rows, columns;
   
   //Is the tile revealed? 
   boolean isRevealed;

   //Is the tile a mine?
   boolean isMine;

   //Is the tile flagged by the player
   boolean isFlagged;

   public Cell(int rows, int columns) {
      super();   //Creating an empty button
      this.rows = rows;
      this.columns = columns;
      // Set JButton's default display properties
      super.setFont(fontForNumbers);
   }

   /** Reset this cell, ready for a new game */
   public void newGame(boolean isMine) {
      this.isRevealed = false; // default
      this.isFlagged = false;  // default
      this.isMine = isMine;  // given
      super.setEnabled(true);  // enable button
      super.setText("");       // display blank
      paint();
   }

   /** Paint itself based on its status */
   public void paint() {
      super.setForeground(isRevealed? fgRevealed: fgNotRevealed);
      super.setBackground(isRevealed? bgRevealed: bgNotRevealed);
   }
}