/*
Угадать число от 0 до 9 за 3 попытки.
При вводе ответа пользователя, программа:
1) делает проверку на корректность ввода (требуются числа)
2) если на входе не число, то даёт возможность исправить ввод (вдруг опечатка)
3) сравнивает число пользователя с искомым
4) сравнивает и сообщает, больше или меньше число пользователя по сравнению с искомым числом
Если пользователь угадал загаданное число, то программа сообщает что
пользователь угадал..
Если пользователь проиграл (не угадал число за 3 попытки), то програма сообщает о проигрыше и показывает загаданное число
В любом случае, программа предлагает сыграть ещё раз, при этом 1-да, играем ещё, 0-конец игры.
 */
package app1;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;
/**
 *
 * @author info
 */
public class MainClass {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int exitCode = 0;
        do {
            //сначала сгенерю случайное число
            Random rand = new Random();
            int x = rand.nextInt(9);
            //System.out.println(x);//debug что загадал комп
            //выведу приглашение
            System.out.printf("Привет! Я загадал число от 0 до 9.%n"
                    + "Отгадай какое! У тебя Три всего попытки%n>");
            int tryCount = 3;//количество попыток
            do {
                int d = getNumberFromScaner();
                if (chekNumbers(x, d)){
                    break;
                }
                else {
                   if (--tryCount == 0){
                       System.out.printf("Вы проиграли. Зададанное число:%d%n", x);
                       break;
                   } 
                   System.out.printf("Осталось попыток:%d%n>",tryCount);
                }
                
            }
            while (true);
            System.out.println("Повторить игру ещё раз? 1-да/ 0-нет");
            exitCode = sc.nextInt();
        }
        while (exitCode == 1);
    }
    
    //ввод значения пользователя с проверкой корректности
    public static int getNumberFromScaner(){
        int x = 0;
        do {
            try {
                x = sc.nextInt();
                break;
            }
            catch (InputMismatchException e){
                sc.nextLine();//сброс сканера
                System.out.printf("Ой! Кажется очепятка. Попробуй ещё раз%n>");
            }
        } while (true);
        return x;
    }
    
    //сравнение ввода пользователя с загаданным числом
    public static boolean chekNumbers(int definedNumber, int userNumber){
        if (userNumber == definedNumber) {
            System.out.println("Угадал!!!");
            return true;
        }
        else {
            if (userNumber > definedNumber) {
                System.out.println("моё число меньше.");
                return false;
            }
            if (userNumber < definedNumber) {
                System.out.println("моё число больше");
                return false;
            }
        }
        return false;
    }
    
}
