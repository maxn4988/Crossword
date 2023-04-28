import java.util.ArrayList;

public class Main {
  private static char[][] board = new char[75][75];//create the board itself, needs to be large to avoid out of bounds error
  
  public static void main(String[] args) {
    
    ArrayList<String> holiday = new ArrayList<String>();
    
    String[] temp = rand();
    int numb = (int)(Math.random() * 4) + 5;
    
    for (int i = 0; i < numb; i++) {
      
      holiday.add(temp[i].toUpperCase());//convert to capital letters
      
    }
    
    for (int j = 0; j < board.length; j++){
      
      for (int k = 0; k < board[j].length; k++){
        
        board[j][k] = ' ';//fill
        
      }
    }
//generating of the board and positions
    int pos = 10;int index = 0;
    
    
    for (String string : holiday){
      if (index == 0){
        for (int i = 0; i < string.length(); i++){
          
          char og1 = string.charAt(i);
          board[10][pos] = og1;
          
          pos ++;
          
        }
      }
        
      else{
        
        int coord[] = generate(board, string);
        if (coord[3] == 1){
    
          int count = 0;
          
          for (int i = coord[2]; i < coord[2] + string.length(); i++){
            board[coord[1]][i] = string.charAt(count);
            count++;
            
          }

          
        }
        
        else{
          
          int count = 0;
          
          for (int i = coord[1]; i < coord[1] + string.length(); i++){
            
            board[i][coord[2]] = string.charAt(count);
            
            count++;
            
          }
        }
      }
      
      index++;
    }
  
    for (int i = 0; i < board.length; i++){
      
      for (int j = 0; j < board[i].length; j++){
        
        System.out.print(board[i][j] + " ");
        
      }
      System.out.println();
    }
  }
  
  public static String[] rand(){
    //array of holidays to pick randomly from
    String[] holidays = {"Thanksgiving", "Hanukkah", "Christmas", "Kwanzaa", "New Year", "Lunar New Year", "Groundhog", "Valentines", "Presidents", "Martin Luther King", "St Patrick",  "Ramadan ", "Passover", "Good Friday", "Easter", "Cinco de Mayo", "Independence", "Rosh Hashana", "Yom Kippur", "Veterans"};
    
    for (int i = 0; i < 30; i++){
      
      int og1 = (int)(Math.random() * 19);
      
      String temp = holidays[og1];
      holidays[og1] = temp;

      
    }
    return holidays;
  }
  
  public static int[] generate(char[][] puzzle, String string){
    
    int[] chose = {-1, -1, -1, -1};
    
    for (int i = 0; i < 75; i++){
      for (int j = 0; j < 75; j++){
        if (i + string.length() <= 75){
          if ((i + string.length() == 75 || puzzle[i + string.length()][j] == ' ') && (i == 0 || puzzle[i - 1][j] == ' ')){
            
            String m = "";String n = "";
            
            if (j - 1 >= 0){
              
              n = yFill(puzzle, i, j - 1, string.length());
            }
            if (j + 1 < 75){
              
              m = yFill(puzzle, i, j + 1, string.length());
            }
            
            String verify = yFill(puzzle, i, j, string.length());

            
            int pos = inter(verify, string);
            
            if (pos > chose[0] && test(m, pos) && test(n, pos)){
              
              chose[0] = pos;chose[1] = i;chose[2] = j;chose[3] = 0;
            }
          }
        }
        
        if (j + string.length() <= 75) { 
          
          if ((j + string.length() == 75 || puzzle[i][j + string.length()] == ' ' )){
           if (j == 0 || puzzle[i][j - 1] == ' '){
            
            String o = "";String p = "";
            
            if (i - 1 >= 0) {
              
              p = xFill(puzzle, i - 1, j, string.length());
            }
            
            if (i + 1 < 75){
              
              o= xFill(puzzle, i + 1, j, string.length());
              
            }
            
            String verify1 = xFill(puzzle, i, j, string.length());
            int pos1 = inter(verify1, string);
            
            if (pos1 > chose[0]){
              
             if (test(o, pos1) && test(p, pos1)){
              
              chose[0] = pos1;chose[1] = i;chose[2] = j;chose[3] = 1;
              }
             }
            }
          }
        }
      }
    }
    
    return chose;
  }
  /*
     need to fill in vertical and horizontal spaces on board
  */
  public static String yFill(char[][] puzzle1, int i, int j, int run){
    
    String string = "";
    int num = i + run;
    
    while (i < num){
      string += puzzle1[i][j];
      i++;
    }
    
    return string;
  }
  
  public static String xFill(char[][] puzzle2, int i, int j, int run1){
    
    String string = "";
    int num = j + run1;
    
    while (j < num){
      
      string += puzzle2[i][j];
      j++;
    }
    
    return string;
  }
  
public static int inter(String x, String y){
  int chose = 0;
  
  for (int i = 0; i < x.length(); i++){
   if (y.charAt(i) == x.charAt(i)){
     chose ++;
     
   }
     
   else if (x.charAt(i) != ' '){
     return -1;
     
   }
    
  }
  return chose;
  }
  
public static boolean test(String s, int empty){
  
  for (int i = 0; i < s.length() - 1; i++){
    
    if (s.charAt(i) != ' '){
      empty --;
      
      if (s.charAt(i + 1) != ' '){
      return false;
        
      }
    }
  }
  return (empty >= 0);
  }
 
}