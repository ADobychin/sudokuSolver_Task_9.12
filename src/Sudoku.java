import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//����� ����� ������������ �� ���� ���� ������.
public class Sudoku {
    private List<Integer> grid;     //���� ���� ������.


    public Sudoku(List<Integer> grid) {
        this.grid = grid;
    }

    //�������� �������////////////////////////////////////////////////////////////////////////////////////////////////////////
    //2.	�������� � ����� Sudoku ���������� ������������ public Sudoku(String filename), ������� ����� ������ �� �����
    // ���� ����� ������.
    public Sudoku(String filename){
        this.grid = new ArrayList<>();
        File initialDataFile = new File(filename);
        try {
            Scanner readFile = new Scanner(initialDataFile);
            int i=0;
            while(readFile.hasNextInt()){
                this.grid.add(i, readFile.nextInt());
                i++;
            }

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        //���������, ��� �������� �� �����.
        System.out.println("Data from file :");
        for(int i=0; i<grid.size(); i++){
            System.out.print(grid.get(i)+" ");
        }

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //�������� index � ����� grid �� ������ ������ � �������
    private int getIndex(int row, int column) {
        return row * 9 + column;
    }

    //���������� true, ���� ����� ������ ��������, false � ���� ������
    /*
    ����� ������� 3 ��������:
    1. �������� ������������ ����� � ������ ������
    2. �������� ������������ ����� � ������ �������
    3. �������� ������������ ����� � ������ �������� 3�3

     */
    public boolean isValid() {
        //1, 2. �������� ������������ �� ������� � ��������.
        for(int i = 0; i < 9; ++i) {
            Set<Integer> numbersInRow = new HashSet<>();
            Set<Integer> numbersInColumn = new HashSet<>();
            for(int j = 0; j < 9; ++j) {
                int numberInRow = grid.get(getIndex(i, j));
                int numberInColumn = grid.get(getIndex(j, i));
                if(numberInRow != 0) {
                    //�������� ����������
                    if(numbersInRow.contains(numberInRow)) {
                        return false;
                    }
                    numbersInRow.add(numberInRow);
                }
                if(numberInColumn != 0) {
                    //�������� ����������
                    if(numbersInColumn.contains(numberInColumn)) {
                        return false;
                    }
                    numbersInColumn.add(numberInColumn);
                }
            }
        }

        //3. �������� ������������ ������ ��������� 3�3
        for(int i = 0; i < 9; i += 3) {
            for(int j = 0; j < 9; j += 3) {
                //�� ������ ���� �� ����� �����-�� ����� ������� ���� �������� 3�3.
                Set<Integer> numbersInSquare = new HashSet<>();
                //���������� ������������ ������ ������ ��������
                for(int deltaRow = 0; deltaRow < 3; deltaRow++) {
                    for(int deltaColumn = 0; deltaColumn < 3; deltaColumn++) {
                        int row = i + deltaRow;
                        int column = j + deltaColumn;
                        int number = grid.get(getIndex(row, column));
                        if(number != 0) {
                            if(numbersInSquare.contains(number)) {
                                return false;
                            }
                            numbersInSquare.add(number);
                        }
                    }
                }
            }
        }
        return true;
    }

    //��� ��� ��������, ������� ����� � ������� row, column
    public int get(int row, int column) {
        int index = getIndex(row, column);
        return grid.get(index);
    }

    //������������� � ������� row, column �������� value
    public void set(int row, int column, int value) {
        int index = getIndex(row, column);
        grid.set(index, value);
    }

    //�������� ������� //////////////////////////////////////////////////////////////////////////////////////////////////
    //3.	�������� � ����� Sudoku ����� writeToFile(String fileName), ������� ����� ���������� ����� ������ � ����
    public void writeToFile(String fileName){

        File saveDataFile = new File(fileName);
        if(!saveDataFile.exists()){
            try{
                saveDataFile.createNewFile();
            }
            catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }
        try(FileWriter fileWriter = new FileWriter(saveDataFile)){
            for(int i=0; i<grid.size(); i++){
                String s = Integer.toString(grid.get(i));
                fileWriter.write(s);
                fileWriter.write(System.lineSeparator());
                System.out.println(s);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }


}
