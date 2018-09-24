/*
делаю консольные крестики нолики
Усложнения:
1) + Переделать проверку победы, чтобы она не была реализована просто набором условий,
   например, а с использованием циклов
2) + Попробовать переписать логику проверки победы, чтобы она работала
   для поля 5х5 и количества фишек 4.
   Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
3) -(ну тут не знаю)Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
 */

package xogames;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class XOGames {
    public static Scanner sc = new Scanner(System.in);
    Random rand = new Random();
    //////////////////////////////////////////////////
    final int SIZE = 3;//размер поля
    final int WIN_POINTS = 3;//кол-во знаков в ряду для победы
    final char DOT_X = 'X';//знак Крестик
    final char DOT_O = 'O';//знак Нолик
    final char DOT_EMPTY = '.';//знак пустой клетки
    char [][] map = new char [SIZE][SIZE];//выделение память под массив игрового поля
    public static void main(String[] args) {
        new XOGames().game();
        
    }
    
    //собственно игра
    void game(){
        initMap();//инициализация карты
        printMap();//печать карты (поначалу пустой)
        //цикл игры
        while (true) {
            humanTurn();//ход человека
            if (chekWin(DOT_X)) {//проверка победы человека
                System.out.println("Победа Человека!");
                break;
            }
            if (isMapFull()) {
                System.out.println("НИЧЬЯ! все клетки заполнены");
                break;
            }
            aiTurn();//ход компьютера
            if (chekWin(DOT_O)) {//проверка победы компьютера
                System.out.println("Победа Компьютера!");
                break;
            }
            if (isMapFull()) {
                System.out.println("НИЧЬЯ! все клетки заполнены");
                break;
            }
            printMap();
        }
        System.out.println("GAME OVER!");
        printMap();//покажу результат
    }
    
    //ход компа
    //сделаю копи-паст из образца, сорри
    void aiTurn() {
        int x, y;
        do {
             x = rand.nextInt(SIZE);
             y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[y][x] = DOT_O;
    }
    //ход человека
    void humanTurn() {
        int x,y;
        do {
            System.out.printf("Enter X and Y (1..%d):%n", SIZE);
            //юзер думает в координатах 1..MAX,
            //а у меня массив от с индексами от 0 до MAX-1
            x = sc.nextInt()-1;
            y = sc.nextInt()-1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;//юзер играет крестиками
    }
    
    //проверка победы крестиков или ноликов
    //этож логично, что знак должен представить из себя линию
    //вертикальную, горизонтальную или диагональную
    boolean chekWin(char dot) {
        //1. сформиру координату точки XY которую буду проверять
        for (int y = 0; y < SIZE; y++){
            for (int x = 0; x < SIZE; x++){
                //если в координате содержится проверяемый знак
                //то есть смысл проверять дальше
                if (map[y][x] == dot) {
                    //проверяю в разных направлениях
                    //если подтверждается линия знаков в кол-ве  WIN_POINTS
                    //то выхожу с TRUE что значит победил
                    if (chekPoint(dot, x, y)) return true;
                    //продолжаю сканирование
                }
            }
        }
        return false;
    }
    
    boolean chekPoint(char dot, final int x, final int y){
        //0-поиcк по горизонтали    y = const   x++
        //1-поиск по вертикали      y++         x = const
        //2-поиск по диагонали -45  y++         x++
        //3-поиск по диагонали +45  y++         x--
        if (searchVertical  (dot, x, y)) return true;
        if (searchHorizontal(dot, x, y)) return true;
        if (searchDP45(dot, x, y))       return true;
        if (searchDM45(dot, x, y))       return true;
        return false;
    }
    
    //1-поиск по вертикали      y++         x = const
    boolean searchVertical (char dot, final int x, final int y){
        int _x = x;
        int _y = y;
        int machCount = 0;//счётчик совпадений
        for (int i = 0; i < WIN_POINTS; i++){
            if (map[_y][_x] ==  dot) {
                if (++machCount == WIN_POINTS) return true;
                if (!inPosRange(++_y, SIZE)) return false;
            }
        }
        return false;
    }
    
        //0-поиcк по горизонтали    y = const   x++
    boolean searchHorizontal (char dot, final int x, final int y){
        int _x = x;
        int _y = y;
        int machCount = 0;//счётчик совпадений
        for (int i = 0; i < WIN_POINTS; i++){
            if (map[_y][_x] ==  dot) {
                if (++machCount == WIN_POINTS) return true;
                if (!inPosRange(++_x, SIZE)) return false;
            }
        }
        return false;
    }
    
    //2-поиск по диагонали -45  y++         x++
    boolean searchDP45(char dot, final int x, final int y){
        int _x = x;
        int _y = y;
        int machCount = 0;//счётчик совпадений
        for (int i = 0; i < WIN_POINTS; i++){
            if (map[_y][_x] ==  dot) {
                if (++machCount == WIN_POINTS) return true;
                if (!inPosRange(++_x, SIZE)) return false;
                if (!inPosRange(++_y, SIZE)) return false;
            }
        }
        return false;
    }
    
    //3-поиск по диагонали +45  y++         x--
    boolean searchDM45(char dot, final int x, final int y){
        int _x = x;
        int _y = y;
        int machCount = 0;//счётчик совпадений
        for (int i = 0; i < WIN_POINTS; i++){
            if (map[_y][_x] ==  dot) {
                if (++machCount == WIN_POINTS) return true;
                if (!inPosRange(--_x, SIZE)) return false;
                if (!inPosRange(++_y, SIZE)) return false;
            }
        }
        return false;
    }
    //проверка что поле заполнилось
    //пройдусь по всем ячейкам, если нет пустых (DOT_EMPTY) то поле заполнено
    boolean isMapFull() {
        for (int r = 0; r < SIZE; r++){
            for (int c = 0; c < SIZE; c++){
                if (map[r][c] == DOT_EMPTY) return false;//есть свободные ячейки
            }
        }
        return true;//нет свободных ячеек
    }
    
    //проверка что заданная ячейка:
    // 1) находится в допустимых координатах
    // 2) не заполнена (имеет значение DOT_EMPTY)
    boolean isCellValid(int x, int y) {
        //1) проверка нахождения в диапазоне
        if (!inPosRange(y, SIZE) || !inPosRange(x, SIZE)) return false;
        //так, диапазон прошёл.
        //2) проверка на пустую ячейку
        return map[y][x] == DOT_EMPTY;
    }
    
    //проверка нахождения в диапазоне от НУЛЯ до SIZE
    boolean inPosRange(int x, int range){
        return ((x >= 0) && (x < range));
    }
    
    //initMap - забиваю карту пустыми позициями
    void initMap(){      
        for (int r = 0; r < SIZE; r++){
            for (int c = 0; c < SIZE; c++){
                map[r][c] = DOT_EMPTY;
            }
        }
    }
    
    //printMap - печать карты
    //попытаюсь пооптимизировать.
    //массив согнать в одну строку с символами %n на концах
    //и потом одним махом вывести через printf
    void printMap(){
        String res = "";
        for (int r = 0; r < SIZE; r++){
            for (int c = 0; c < SIZE; c++){
                res = res.concat(Character.toString(map[r][c])+" ");
            }
            res = res.concat("%n");
        }
        System.out.printf(res);
    }
}
