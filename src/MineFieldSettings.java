import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MineFieldSettings extends JPanel {
   private static final long serialVersionUID = 1L;  // to prevent serial warning

   // Define named constants for the game properties
   public static final int rows = 10;      // number of cells
   public static final int columns = 10;

   // Define named constants for UI sizes
   public static final int cellSize = 60;  // Cell width and height, in pixels
   public static final int canvasWidth  = cellSize * columns; // Game board width/height
   public static final int canvasHeight = cellSize * rows;

   // Define properties (package-visible)
   /** The game board composes of ROWSxCOLS cells */
   CellSettings cells[][] = new CellSettings[rows][columns];

   /** Number of mines */
   int numMines = 10;

   /** Constructor for the array*/
   public MineFieldSettings() {
      super.setLayout(new GridLayout(rows, columns, 2, 2));  // JPanel

      // Allocate the 2D array of Cell, and added into content-pane.
      for (int row = 0; row < rows; ++row) {
         for (int col = 0; col < columns; ++col) {
            cells[row][col] = new CellSettings(row, col);
            super.add(cells[row][col]);
         }
      }

      // [TODO 3] Allocate a common listener as the MouseEvent listener for all the
      //  Cells (JButtons)
      CellMouseListener listener = new CellMouseListener();
      // [TODO 4] Every cell adds this common listener
      for (int row = 0; row < rows; ++row) {
       for (int col = 0; col < columns; ++col) {
          cells[row][col].addMouseListener(listener);   // For all rows and cols
       }
      }

      // Set the size of the content-pane and pack all the components
      //  under this container.
      super.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
   }

   // Initialize and re-initialize a new game
   public void newGame() {
      // Get a new mine map
      MinesLocation mineMap = new MinesLocation();
      mineMap.newMineField(numMines);

      // Reset cells, mines, and flags
      for (int row = 0; row < rows; row++) {
         for (int col = 0; col < columns; col++) {
            // Initialize each cell with/without mine
            cells[row][col].newGame(mineMap.isMine[row][col]);
         }
      }
   }

   // Return the number of mines [0, 8] in the 8 neighboring cells
   //  of the given cell at (srcRow, srcCol).
   private int getSurroundingMines(int srcRow, int srcCol) {
      int numMines = 0;
      for (int row = srcRow - 1; row <= srcRow + 1; row++) {
         for (int col = srcCol - 1; col <= srcCol + 1; col++) {
            // Need to ensure valid row and column numbers too
            if (row >= 0 && row < rows && col >= 0 && col < columns) {
               if (cells[row][col].isMine) numMines++;
            }
         }
      }
      return numMines;
   }

   // Reveal the cell at (srcRow, srcCol)
   // If this cell has 0 mines, reveal the 8 neighboring cells recursively
   private void revealCell(int srcRow, int srcCol) {
      int numMines = getSurroundingMines(srcRow, srcCol);
     cells[srcRow][srcCol].setText(numMines + "");
     cells[srcRow][srcCol].isRevealed = true;
     cells[srcRow][srcCol].changeColor();  // based on isRevealed
      if (numMines == 0) {
        // Recursively reveal the 8 neighboring cells
         for (int row = srcRow - 1; row <= srcRow + 1; row++) {
            for (int col = srcCol - 1; col <= srcCol + 1; col++) {
               // Need to ensure valid row and column numbers too
               if (row >= 0 && row < rows && col >= 0 && col < columns) {
                  if (!cells[row][col].isRevealed) revealCell(row, col);
               }
            }
         }
      }
   }

   // Return true if the player has won (all cells have been revealed or were mined)
   public boolean hasWon() {
      // ......
      return true;
   }

   // [TODO 2] Define a Listener Inner Class
   private class CellMouseListener extends MouseAdapter {
      @Override
      public void mouseClicked(MouseEvent e) {         // Get the source object that fired the Event
         CellSettings sourceCell = (CellSettings)e.getSource();
         // For debugging
         System.out.println("You clicked on (" + sourceCell.rows + "," + sourceCell.columns + ")");

         // Left-click to reveal a cell; Right-click to plant/remove the flag.
         if (e.getButton() == MouseEvent.BUTTON1) {  // Left-button clicked
            // [TODO 5]
            // if you hit a mine, game over
            // else reveal this cell
            if (sourceCell.isMine) {
               System.out.println("Game Over");
               sourceCell.setText("*");
            } else {
               revealCell(sourceCell.rows, sourceCell.columns);
            }
         } else if (e.getButton() == MouseEvent.BUTTON3) { // right-button clicked
            // [TODO 6]
            // If this cell is flagged, remove the flag
            // else plant a flag.
            // ......
         }

         // [TODO 7] Check if the player has won, after revealing this cell
         // ......
      }
   }
}
