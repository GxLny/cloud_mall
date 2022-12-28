package com.lny.mall.coupon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    @org.junit.Test
    public void streamTest() {
        List<String> list = new ArrayList<>();
        list.add("A001");
        list.add("A002");
        list.add("A003");
        list.add("A004");
        list.add("B001");
        list.add("B002");
        list.add("B003");
        list.add("B004");

        //通过stream获取集合总数
        long count = list.stream().count();
        System.out.println("count = " + count);

        //通过stream流过滤内容
        list.stream().filter((String str) -> {
            //根据
            return str.startsWith("A00");
            //遍历
        }).forEach((String str)->{
            System.out.println("str = " + str);
        });


        //过滤对象
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("小明",25,1));
        students.add(new Student("小华",23,1));
        students.add(new Student("小花",21,0));
        students.add(new Student("小李",20,0));
        students.add(new Student("小王",19,0));

        //根据年龄排序,选取未毕业的学生

        students.stream().filter((Student stu) -> {
            return stu.getGrad().equals(0);
        }).sorted(Comparator.comparing(Student::getAge)).forEach((Student student)->{
            System.out.println("student = " + student);
        });

    }
}


class Student{
    private String username;
    private Integer age;
    //是否毕业（0：未毕业。1：已毕业）
    private Integer grad;
    public Student() {

    }
    public Student(String username, Integer age,Integer grad) {
        this.username = username;
        this.age = age;
        this.grad = grad;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getGrad() {
        return grad;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", grad=" + grad +
                '}';
    }
}

