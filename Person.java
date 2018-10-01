/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catsobjects;

/**
 *
 * @author info
 */
public class Person {
    private String name;//ФИО
    private String jobTitle;//должность
    private String eMail;//e-mail
    private String phoneNum;//
    private int salary;//заработная плата
    private int age;//возраст
    //конструктор по дефолту
    public Person (){
        name = "unknown";
        jobTitle = "unknown";
        eMail = "unknown";
        phoneNum = "unknown";
        salary = 0;
        age = 0;
    }
    //конструктор с начальной инициализацией полей
    public Person (String _name, String _jobTitle,
                        String _eMail, String _phoneNum,
                            int _salary, int _age){//
        name        = _name;
        jobTitle    = _jobTitle;
        eMail       = _eMail;
        phoneNum    = _phoneNum;
        salary      = _salary;
        age         = _age;
    }
    
    //конструктор с начальной инициализацией полей
    public Person (String[] s){//
        //это немного шляпа, надо проверять наличие соотв. индексов в строке
        name        = s[0];
        jobTitle    = s[1];
        eMail       = s[2];
        phoneNum    = s[3];
        salary      = Integer.parseInt(s[4]);
        age         = Integer.parseInt(s[5]);
    }
    
    //предоставляет форматированню строку с данными Person'a
    public String getFullInfo(){
        String res = "";
        res = name+"%n";
        res = res.concat("  job title:"+jobTitle+"%n");
        res = res.concat("     e-mail:"+eMail+"%n");
        res = res.concat("     phone:"+phoneNum+"%n");
        res = res.concat("    salary:"+String.valueOf(salary)+"%n");
        res = res.concat("       age:"+String.valueOf(age)+"%n");
        return res;
    }
    
    //геттер для возраста
    public int getAge(){
        return age;
    }
}
