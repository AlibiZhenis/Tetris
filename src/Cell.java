// Name: Alibi Zhenis
// Student ID: 57065469
// Lab section: T01
public class Cell{
    private int row, col;
    private BlockColor color;

    public Cell(int row, int col, BlockColor color){
        this.row=row;
        this.col=col;
        this.color=color;
    }
    public Cell(Cell cell){
        this.row = cell.row;
        this.col = cell.col;
        this.color = cell.color;
    }

    public void transpose(double x, double y){
        double temp = row;
        this.row = (int) (col - y + x);
        this.col = (int) (temp - x + y);
    }

    public void reverse_row(double y){
        col = (int)(2*y-col);
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setColor(BlockColor color) {
        this.color = color;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getCol() {
        return col;
    }
    public BlockColor getColor() {
        return color;
    }
    public int getRow() {
        return row;
    }
    public void left(){
        col--;
    }
    public void right(){
        col++;
    }
    public void down(){
        row++;
    }
    public String toString(){
        return "("+row+","+col+","+color+")";
    }
}

