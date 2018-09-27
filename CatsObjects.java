/*Так как забивать в программе много данных для инициализации объектов это ... 
  ну так себе решение, я решил напрячься и сделать через хранение данных в файле.
  К формату даных в файле требований не было, я решил использовать простые строки
  (одна строка = один юзер) поля в строке разделены слэшами:
    Иванов Василий Петрович/Директор/info@r&k.biz/+7 913 966 6666/1000000/42/
    ...
    Петров Евгений Павлович/Инженер/support@r&k.biz/+7 913 854 4987/45000/48/

  Референцы:
  1) Материал которым пользовался для чтения файла
    https://vertex-academy.com/tutorials/ru/java-8-stream-files/
  2) вот тут про способы обхода массивов
    https://juja.com.ua/java/java-collections/iterate-arraylist-java/
  Раз уж чтение файла началось с коллекций ArrayList то решил в том же духе продолжить..
  1) Прочитал файл в строковый ArrayList с именем lines
     В lines каждая стрка про юзера по отдельности
  2) Заранее завёл ArrayList типа Person для пользователей чтобы динамически его заполнять
     ибо кол-во юзров (строк) может быть не предсказуемо
  3) Прошёлся forEach по строкам юзеров, извлечённые строки распарсивал в массив строк
     содержащих поля данных конкретного юзера.
  4) массив полей передавал конструктору объекта Person
  5) созданные объекты добавлял в динамический список lPersons
  6) прошёлся foreach по lPersons, отсеял возрастных канидатов.. вроде меня ;-)

    В Person.java реализован метод для форматированного вывода getFullInfo,
    где инфо о юзере выводится с отстпупами, вот так:
    Иванов Василий Петрович
      job title:Директор
         e-mail:info@r&k.biz
          phone:+7 913 966 6666
         salary:1000000
            age:42
*/
package catsobjects;

//список
import java.util.List;
import java.util.ArrayList;
//работа с файлами
import java.nio.file.Files;;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
//Потоки
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class CatsObjects {
     
    public static void main(String[] args) {
        final int MaxAge = 40;//вывести информацию только о сотрудниках старше MaxAge
        List<Person> lPersons = new ArrayList();//дин.список сотрудников
        List <String> lines = new ArrayList<>();
        //файл должен помещатся вне папок src тогда он откроется по относительному пути (от проекта же считается)
        Path path = Paths.get("persons.txt");//относительный путь к файлу преобразуется в абсолютный с учётом операционной системы
        try (Stream<String> lineStream = Files.newBufferedReader(path).lines()) {//чтение файла в поток
            lines = lineStream.collect(Collectors.toList());//выгрузка потока в список строк
            //теперь вытаскиваю каждую строку и добавляю в объект, предварительно его создав
            for (String s: lines){//в s получаю строку сырых данных из списка
                //System.out.println(s);//debug покажу строку
                lPersons.add(new Person(getParseStrings(s)));//создаю объект Person,
                                                             //которому для инициализации передаю массив строк со значениями полей
                                                             //объект добавляю в список
            }
            //Покажу сотрудников только старше MaxAge лет
            for (Person p : lPersons) {
                if (p.getAge() >= MaxAge)
                    System.out.printf(p.getFullInfo());
            }
            /* IDE подсказывает что можно использовать функциональную операцию
            lPersons.stream().filter((p) -> (p.getAge() >= MaxAge)).forEachOrdered((p) -> {
                System.out.printf(p.getFullInfo());
            });
            */
        }
        catch (IOException ignored) {
        }
    }
    
    //парсинг строки по делимтеру "/"
    //возвращаю массив строк
    private static String[] getParseStrings(String source){
        String[] subStr;
        subStr = source.split("/");
        return subStr; 
    }
    
}
