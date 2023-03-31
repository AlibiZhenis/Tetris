// Name: Alibi Zhenis
// Student ID: 57065469
// Lab section: T01

public class Board {
	public static final int BOARD_HEIGHT = 20;
    public static final int BOARD_WIDTH = 10;
    private BlockColor [] [] cells;
    private Block activeBlock;
    private int numFullLinesRemoved;
    public Board (){
        cells = new BlockColor [BOARD_HEIGHT][BOARD_WIDTH];
        for(int i=0; i<BOARD_HEIGHT; i++){
            for(int j=0; j<BOARD_WIDTH; j++){
                cells[i][j] = BlockColor.NO_COLOR;
            }
        }
        numFullLinesRemoved=0;
        activeBlock=null;
    }
    public BlockColor [] [] getCells(){
        return cells;
    }
    public Block activeBlock (){
        return activeBlock;
    }
    public BlockColor blockAt(int x, int y){
        return cells[x][y];
    }
    public void clear(){
        for(int i=0; i<BOARD_HEIGHT; i++){
            for(int j=0; j<BOARD_WIDTH; j++){
                cells[i][j] = BlockColor.NO_COLOR;
            }
        }
        activeBlock=null;
    }
    public void blockLanded(){
        for(Cell cell: activeBlock.getCells()){
            int x = cell.getRow();
            int y = cell.getCol();
            cells[x][y] = activeBlock.getColor();
        }
        activeBlock=null;
    }
    public boolean canMove(){
        for(int j=0; j<BOARD_WIDTH; j++){
            if(cells[0][j]!=BlockColor.NO_COLOR){
                return false;
            }
        }
        return true;
    }
    public boolean rotate () throws OutOfBoardException{
        Cell[] copy = Block.copyCells(activeBlock.getCells());
        try{
            activeBlock.rotate();
            if(!activeBlock.insideBoard()){
                throw new OutOfBoardException();
            }
            checkCellsTaken(activeBlock().getCells());
            return true;
        } catch (OutOfBoardException e){
            activeBlock.cells = copy;
            return false;
        }
    }
    public boolean oneLineDown () throws OutOfBoardException{
        Cell[] copy = Block.copyCells(activeBlock.getCells());
        try{
            activeBlock.moveDown();
            if(!activeBlock.insideBoard()){
                throw new OutOfBoardException();
            }
            checkCellsTaken(activeBlock().getCells());
            return true;
        } catch (OutOfBoardException e){
            activeBlock.cells = copy;
            return false;
        }
    }
    public void checkCellsTaken(Cell[] cells) throws OutOfBoardException{
        for(Cell cell: cells){
            int x = cell.getRow();
            int y = cell.getCol();
            if(this.cells[x][y]!=BlockColor.NO_COLOR){
                throw new OutOfBoardException();
            }
        }
    }
    public boolean moveLeft () throws OutOfBoardException{
        Cell[] copy = Block.copyCells(activeBlock.getCells());
        try{
            activeBlock.moveLeft();
            if(!activeBlock.insideBoard()){
                throw new OutOfBoardException();
            }
            checkCellsTaken(activeBlock().getCells());
            return true;
        } catch (OutOfBoardException e){
            activeBlock.cells = copy;
            return false;
        }
    }
    public boolean moveRight () throws OutOfBoardException{
        Cell[] copy = Block.copyCells(activeBlock.getCells());
        try{
            activeBlock.moveRight();
            if(!activeBlock.insideBoard()){
                throw new OutOfBoardException();
            }
            checkCellsTaken(activeBlock().getCells());
            return true;
        } catch (OutOfBoardException e){
            activeBlock.cells = copy;
            return false;
        }
    }
    public boolean newBlock (){
        if(canMove()){
            activeBlock = Block.randomBlock();
            return true;
        }
        return false;
    }
    public int removeFullLines(){
        int removed = 0;
        while(true){
            boolean full = true;
            for(int j=0; j<BOARD_WIDTH; j++){
                if(cells[BOARD_HEIGHT-1][j]==BlockColor.NO_COLOR){
                    full=false;
                    break;
                }
            }

            if(full){
                removed++;
                for(int i=BOARD_HEIGHT-1; i>0; i--){
                    cells[i]=cells[i-1];
                }
                cells[0] = new BlockColor[BOARD_WIDTH];
                for(int j=0; j<BOARD_WIDTH; j++){
                    cells[0][j] = BlockColor.NO_COLOR;
                }
            }
            else{
                break;
            }
        }
        numFullLinesRemoved+=removed;
        return removed;
    }
    public int numFullLinesRemoved(){
        return numFullLinesRemoved;
    }
}
