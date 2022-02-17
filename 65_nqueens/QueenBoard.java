// Team Blue Pandas (Jacob Ng, PreGuac, Andrew Piatetsky, Burnt Peanut, Emily Ortiz, Applesauce)
// APCS pd6
// HW65: How Many Queens Can a Thinker Place, If a Thinker Can Place Queens...
// 2022-02-16
// time spent: 2hours


/***
 * class QueenBoard
 * Generates solutions for N-Queens problem.
 * USAGE:
 * 1. Peruse. Transcribe your KtS verbiage into block comments preceding each method where necessary.
 * 2. Implement solver method.
 */

public class QueenBoard
{

  private int[][] _board;

  public QueenBoard( int size )
  {
    _board = new int[size][size];
  }


  /***
   * precondition: board is filled with 0's only.
   * postcondition:
   * If a solution is found, board shows position of N queens,
   * returns true.
   * If no solution, board is filled with 0's,
   * returns false.
   */
  public boolean solve()
  {
    return solveH(0);
  }


  /**
   *Helper method for solve.
   */
  private boolean solveH( int col )
  {
    if (col >= _board.length) {
      return true;
    }
    for (int i = 0; i < _board.length; i++) {
      if (addQueen(i, col)) {
        if (solveH(col+1) == false) {
          removeQueen(i, col);
        }
        else {
          return true;
        }
      }
    }
    return false;
  }

  /** Print board, a la toString...
      Except:
      all negs and 0's replaced with underscore
      all 1's replaced with 'Q'
  */
  public void printSolution()
  {
    String ans = "";
    for( int r = 0; r < _board.length; r++ ) {
      for( int c = 0; c < _board[0].length; c++ ) {
        if (_board[r][c] <= 0) {
          ans+= "_";
        }
        if (_board[r][c] == 1) {
          ans+="Q";
        }
        // ans += _board[r][c]+"\t";
      }
      ans += "\n";
    }
    System.out.println(ans);
  }



  //================= YE OLDE SEPARATOR =================

  /***
   * <General description>
   * precondition:
   * postcondition:
   */
  private boolean addQueen(int row, int col)
  {
    if(_board[row][col] != 0){
      return false;
    }
    _board[row][col] = 1;
    int offset = 1;
    while(col+offset < _board[row].length){
      _board[row][col+offset]--;
      if(row - offset >= 0){
        _board[row-offset][col+offset]--;
      }
      if(row + offset < _board.length){
        _board[row+offset][col+offset]--;
      }
      offset++;
    }
    return true;
  }


  /***
   * <General description>
   * precondition:
   * postcondition:
   */
  private boolean removeQueen(int row, int col){
    if ( _board[row][col] != 1 ) {
      return false;
    }
    _board[row][col] = 0;
    int offset = 1;

    while( col+offset < _board[row].length ) {
      _board[row][col+offset]++;
      if( row - offset >= 0 ) {
        _board[row-offset][col+offset]++;
      }
      if( row + offset < _board.length ) {
        _board[row+offset][col+offset]++;
      }
      offset++;
    }
    return true;
  }


  /***
   * <General description>
   * precondition:
   * postcondition:
   */
  public String toString()
  {
    String ans = "";
    for( int r = 0; r < _board.length; r++ ) {
      for( int c = 0; c < _board[0].length; c++ ) {
        ans += _board[r][c]+"\t";
      }
      ans += "\n";
    }
    return ans;
  }


  //main method for testing...
  public static void main( String[] args )
  {
    QueenBoard b = new QueenBoard(5);
    // b.printSolution();
    // System.out.println(b);
    // /** should be...
    //    0	0	0	0	0
    //    0	0	0	0	0
    //    0	0	0	0	0
    //    0	0	0	0	0
    //    0	0	0	0	0
    // */

    // b.addQueen(3,0);
    // b.addQueen(0,1);
    // b.printSolution();
    // System.out.println(b);
    // /** should be...
    //    0	1	-1	-2	-1
    //    0	0	-2	0	0
    //    0	-1	0	-1	0
    //    1	-1	-1	-1	-2
    //    0	-1	0	0	0
    // */

    // b.removeQueen(3,0);
    // b.printSolution();
    // System.out.println(b);
    // /** should be...
    //    0	1	-1	-1	-1
    //    0	0	-1	0	0
    //    0	0	0	-1	0
    //    0	0	0	0	-1
    //    0	0	0	0	0
    // */

    System.out.println(b.solve());
    b.printSolution();
  }

}//end class
