public class Move {
    private int value;
    private int row;
    private int col;

    Move(int value){
        this.value = value;
    }
    Move(int row,int col){
        this.row = row;
        this.col = col;
    }
    Move(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    void setRow(int i){
        this.row = i;
    }
    void setCol(int i){
        this.col = i;
    }
    void setValue(int i){
        this.value = i;
    }

    int getRow(){
        return this.row;
    }
    int getCol(){
        return this.col;
    }
    int getValue(){
        return this.value;
    }
}
