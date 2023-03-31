// Name: Alibi Zhenis
// Student ID: 57065469
// Lab section: T01

public abstract class Block{
    protected Cell[] cells;
    protected char shape;
    protected int status;
    protected double xPivot, yPivot;
    public Block (Cell c1, Cell c2, Cell c3, Cell c4, char shape, int status){
        cells = new Cell[4];
        cells[0] = c1;
        cells[1] = c2;
        cells[2] = c3;
        cells[3] = c4;
        this.shape=shape;
        this.status=status;
    }
//    protected Block(Block block){
//        cells = new Cell[4];
//        Cell[] other = block.cells;
//        cells[0] = new Cell(other[0]);
//        cells[1] = new Cell(other[1]);
//        cells[2] = new Cell(other[2]);
//        cells[3] = new Cell(other[3]);
//        this.shape = block.getShape();
//        this.status = block.getStatus();
//        this.xPivot = block.xPivot;
//        this.yPivot = block.yPivot;
//    }
    public Cell[] getCells(){return cells;}
    public char getShape(){return shape;}
    public int getStatus(){return status;}
    public BlockColor getColor(){return cells[0].getColor();}
    public void setStatus (int status) throws InvalidInputException {
        if(shape=='O'&&status!=0){
            throw new InvalidInputException();
        }
        this.status=status;
    }
    public void moveLeft() throws OutOfBoardException{
        for(Cell cell:cells){
            if(cell.getCol()==0){
                throw new OutOfBoardException();
            }
        }

        for(Cell cell:cells){
            cell.left();
        }
        yPivot--;
    }
    public void moveRight() throws OutOfBoardException{
        for(Cell cell:cells){
            if(cell.getCol()==9){
                throw new OutOfBoardException();
            }
        }

        for(Cell cell:cells){
            cell.right();
        }
        yPivot++;
    }
    public void moveDown() throws OutOfBoardException{
        for(Cell cell:cells){
            if(cell.getRow()==19){
                throw new OutOfBoardException();
            }
        }

        for(Cell cell:cells){
            cell.down();
        }
        xPivot++;
    }
    public void rotate() throws OutOfBoardException{
        Cell[] old = copyCells(cells);

        for(Cell cell: cells){
            cell.transpose(xPivot, yPivot);
            cell.reverse_row(yPivot);
        }
        if(!insideBoard()){
            cells = old;
            throw new OutOfBoardException();
        }

        status = (status+1)%4;
    }
    public static Cell[] copyCells(Cell[] cells){
        Cell[] copy = new Cell[4];
        copy[0] = new Cell(cells[0]);
        copy[1] = new Cell(cells[1]);
        copy[2] = new Cell(cells[2]);
        copy[3] = new Cell(cells[3]);
        return copy;
    }
    public boolean insideBoard() {
        for(Cell cell:cells){
            if(cell.getRow()>19||cell.getCol()>9||cell.getCol()<0||cell.getRow()<0){
                return false;
            }
        }
        return true;
    }

    public static Block randomBlock() {
        int num = (int)(Math.random()*7);
        switch(num){
            case 0:{
                return new IBlock();
            }
            case 1:{
                return new JBlock();
            }
            case 2:{
                return new LBlock();
            }
            case 3:{
                return new OBlock();
            }
            case 4:{
                return new SBlock();
            }
            case 5:{
                return new TBlock();
            }
            case 6:{
                return new ZBlock();
            }
        }
        return null;
    }

}

class IBlock extends Block{
    public IBlock(){
        super(new Cell(0,4, BlockColor.I_COLOR),
                new Cell(0,3, BlockColor.I_COLOR),
                new Cell(0,5, BlockColor.I_COLOR),
                new Cell(0,6, BlockColor.I_COLOR), 'I', 0);
        xPivot = 0.5;
        yPivot = 4.5;
    }
}
class JBlock extends Block{
    public JBlock(){
        super(new Cell(0,3, BlockColor.J_COLOR),
                new Cell(0,4, BlockColor.J_COLOR),
                new Cell(0,5, BlockColor.J_COLOR),
                new Cell(-1,3, BlockColor.J_COLOR), 'J', 0);
        xPivot = 0;
        yPivot = 4;
    }
}
class LBlock extends Block{
    public LBlock(){
        super(new Cell(0,3, BlockColor.L_COLOR),
                new Cell(0,4, BlockColor.L_COLOR),
                new Cell(0,5, BlockColor.L_COLOR),
                new Cell(-1,5, BlockColor.L_COLOR), 'L', 0);
        xPivot = 0;
        yPivot = 4;
    }
}
class OBlock extends Block{
    public OBlock(){
        super(new Cell(0,4, BlockColor.O_COLOR),
                new Cell(-1,4, BlockColor.O_COLOR),
                new Cell(-1,5, BlockColor.O_COLOR),
                new Cell(0,5, BlockColor.O_COLOR), 'O', 0);
    }
    @Override
    public void rotate(){}
}
class SBlock extends Block{
    public SBlock(){
        super(new Cell(0,3, BlockColor.S_COLOR),
                new Cell(0,4, BlockColor.S_COLOR),
                new Cell(-1,4, BlockColor.S_COLOR),
                new Cell(-1,5, BlockColor.S_COLOR), 'S', 0);
        xPivot = 0;
        yPivot = 4;
    }
}
class TBlock extends Block{
    public TBlock(){
        super(new Cell(0,3, BlockColor.T_COLOR),
                new Cell(0,4, BlockColor.T_COLOR),
                new Cell(0,5, BlockColor.T_COLOR),
                new Cell(-1,4, BlockColor.T_COLOR), 'T', 0);
        xPivot = 0;
        yPivot = 4;
    }
}
class ZBlock extends Block{
    public ZBlock(){
        super(new Cell(-1,3, BlockColor.Z_COLOR),
                new Cell(0,4, BlockColor.Z_COLOR),
                new Cell(-1,4, BlockColor.Z_COLOR),
                new Cell(0,5, BlockColor.Z_COLOR), 'Z', 0);
        xPivot = 0;
        yPivot = 4;
    }
}








