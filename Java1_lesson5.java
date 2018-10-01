
//Java level 1. Lesson 6;
//1. Создал абстрактный объкт Animal
//2. И от него создал потомков, классы Dog и Cat
//3. При создании рандомизировал получаемые параметры собак и котов в пределах номинальных Rated значений
//4. Выполнил проверку соответствия объекта требуемым характеристикам по бегу прыжкам и плаванию
//5. Примечания:
//5.1. номинальные (опорные) параметры и пределы рандомизации вынес в интерфейсные константы и имплиментировал
//     в соотв. объекты (напр. у кота нет параметров по плаванию). Я прочитал что так делать не камильфо,
//     но и красивого решения не встретил
//5.2. Конструктор удалось объедтнить для всех потомков, только у Собаки добавились параметры плавания
//     Что отразилось на конструкторе и на методе toString, и на методе canSwim

package java1_lesson5;

import java.util.Random;
import static java1_lesson5.IDogConstants.RatedRunLenght;

public class Java1_lesson5 {

    public static void main(String[] args) {
        Animal dog1 = new Dog("Ataman");
        System.out.println(dog1);//вывод параметров собаки
        int dr = 500; System.out.println("пробежит:"+dr+"m ? ="+dog1.canRun(dr));
        float djh = 0.6f;
        float djl = 1.1f;
        System.out.println("прыгнет через:"+djh+"m высоту "+ djl+"m длину? =" +dog1.canJump(djh, djl));
        int ds = 10; System.out.println("проплывёт: "+ds+"m ? ="+dog1.canSwim(ds));
        
        System.out.println();
        
        Animal cat1 = new Cat("Tomas");
        System.out.println(cat1);//вывод параметров кота
        int cr = 210; System.out.println("пробежит:"+cr+"m ? ="+cat1.canRun(cr));
        float cjh = 0.6f;
        float cjl = 1.1f;
        System.out.println("прыгнет через:"+cjh+"m высоту "+ cjl+"m длину? =" +cat1.canJump(cjh, cjl));
        int cs = 10; System.out.println("проплывёт: "+cs+"m ? ="+cat1.canSwim(cs));
    }
    
}

interface IDogConstants {
    public static final int RatedRunLenght = 400;//номинальная дистанция пробежки
    public static int dRunLenght = 200;//разброс в длине пробежки
    public static float RatedJampLenght =  1.0f;//номинальная длина прыжка
    public static float RatedJampHeight =  0.5f;//номинальная высота прыжка
    public static float dJampLenght =  0.5f;//разброс в длине прыжка
    public static float dJampHeight =  0.2f;//разброс в высоте прыжка  
    public static int RatedSwimLenght =  8;//номинальная дистанция заплыва
    public static int dSwimLenght =  4;//разброс в длине заплыва
}

class Dog extends Animal implements IDogConstants  {
    private int swimDistance;//расстояние заплыва (собака умеет плавать)
    Dog (String name){
        super(name, RatedRunLenght, dRunLenght, 
                        RatedJampLenght, dJampLenght,
                            RatedJampHeight, dJampHeight);
        Random rand = new Random();
        //дистанция заплыва случайных 0..4м, плавает только собака
        int r = rand.nextInt(dSwimLenght);
        this.swimDistance = RatedSwimLenght + r;
    }
    
    public boolean canSwim(int distanse){
        return (distanse < this.swimDistance);
    }    
       
    @Override
    public String toString(){
        return super.toString()+
                    "\n| Swim Distanse: "+ swimDistance;
        }
}


interface ICatConstants {
    public final int RatedRunLenght = 180;//номинальная дистанция пробежки
    public final int dRunLenght = 40;//разброс в длине пробежки
    public final float RatedJampLenght =  2.0f;//номинальная длина прыжка
    public final float RatedJampHeight =  1.5f;//номинальная высота прыжка
    public final float dJampLenght =  0.5f;//разброс в длине прыжка
    public final float dJampHeight =  0.5f;//разброс в высоте прыжка
}

class Cat extends Animal implements ICatConstants {
    Cat (String name){
        super(name, RatedRunLenght, dRunLenght, 
                        RatedJampLenght, dJampLenght,
                            RatedJampHeight, dJampHeight);
    }
           
    /*@Override toString полностью наследуется от Animal
    public String toString(){
        return super.toString();
        }
    */
}