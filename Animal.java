package java1_lesson5;

import java.util.Random;
import static java1_lesson5.IDogConstants.dRunLenght;

interface IAnimal {
    boolean canRun(int distanse);
    boolean canSwim(int distanse);
    boolean canJump(float height, float lenght);
}

public abstract class Animal implements IAnimal{
        protected String name;
        protected float jampLenght;//длина прыжка
        protected float jampHeight;//высота прыжка
        protected int runDistance;//расстояние пробежки (на макс скорости наверно)
        
        Animal (String name, int RatedRunLenght, int dRunLenght, 
                        float RatedJampLenght, float dJampLenght,
                            float RatedJampHeight, float dJampHeight){
            this.name = name;
            Random rand = new Random();
            //дистанция пробежки 
            int r = rand.nextInt(dRunLenght);
            this.runDistance = RatedRunLenght + r;
            //прыжок
            float f = rand.nextFloat();
            this.jampLenght = RatedJampLenght + (f * dJampLenght);
                  f = rand.nextFloat();
            this.jampHeight = RatedJampHeight + (f * dJampHeight);
        }
        
        public boolean canRun(int distanse){
            return (distanse < this.runDistance);
        }
    
        public boolean canSwim(int distanse){
            return false;
        }    
    
        //прыжок характеризуется высотой и длиной 
        public boolean canJump(float height, float lenght){
            return ((height < this.jampHeight) && (lenght < this.jampLenght));
        }
        
        @Override
        public String toString(){
            return name+":"+getClass().getSimpleName()+
                    "\n| Jamp Lenght:  "+ jampLenght+
                    "\n| Jamp Height:  "+ jampHeight+
                    "\n| Run Distanse: "+ runDistance;
        }
}
