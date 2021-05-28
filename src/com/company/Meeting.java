package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Meeting extends Conference implements Serializable, ConferenceFunctions {
    public static Map<Integer, Meeting> list = new HashMap<Integer, Meeting>();
    LocalDate date;
    String topic;
    int countMember;

    public Meeting() {}

    public static void start()
    {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Conference.txt")))
        {
            list=(HashMap<Integer,Meeting>)objectInputStream.readObject();
        }
        catch(Exception ex) {System.out.print(ex.getMessage());}
    }

    static void save()
    {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Conference.txt")))
        {
            objectOutputStream.writeObject(list);
        }
        catch(Exception ex) {System.out.print(ex.getMessage());}
    }

    @Override
    public void Add() {
        Scanner in = new Scanner(System.in);
        Meeting meeting = new Meeting();
        System.out.print("ID засідання - ");
        int id = in.nextInt();
        int action = 1;
        for(Map.Entry<Integer ,Meeting> entry: list.entrySet()) {
            int key = entry.getKey();
            Meeting value = entry.getValue();
            if(key == id)
            {
                System.out.print("ID засідання вже існує. Редагувати? (1 - так): ");
                action = in.nextInt();
            }
        }
        if(action == 1)
        {
            System.out.print("Назва конференції - ");
            in.nextLine();
            meeting.name = in.nextLine();
            System.out.print("Місце проведенння - ");
            meeting.place = in.nextLine();
            System.out.print("День засідання - ");
            int day = in.nextInt();
            System.out.print("Місяць засідання - ");
            int month = in.nextInt();
            System.out.print("Рік засідання - ");
            int year = in.nextInt();
            meeting.date = LocalDate.of(year,month,day);
            System.out.print("Тема засідання - ");
            in.nextLine();
            meeting.topic = in.nextLine();
            System.out.print("Кількість учасників - ");
            meeting.countMember = in.nextInt();
            list.put(id, meeting);
            save(); }
    }

    @Override
    public void Show() {
        for(Map.Entry<Integer, Meeting> entry: list.entrySet()) {
            int key = entry.getKey();
            Meeting value = entry.getValue();
            System.out.print("\nID засідання - "+ key);
            System.out.print("\nНазва конференції - "+ value.name);
            System.out.print("\nМісце проведення - "+ value.place);
            System.out.print("\nДата засідання - "+ value.date);
            System.out.print("\nТема засідання - "+ value.topic);
            System.out.print("\nКількість учасників - "+value.countMember + "\n");
        }
    }

    @Override
    public void Delete() {
        Scanner in= new Scanner(System.in);
        System.out.print("ID засідання - ");
        int i = in.nextInt();
        list.remove(i);
        save();
    }

    @Override
    public void AverageNumberMembers() {
        Meeting [] meetings = new Meeting[list.size()];
        int y = 0;

        for(Map.Entry<Integer ,Meeting> entry: list.entrySet()) {
            int key = entry.getKey();
            Meeting value = entry.getValue();
            meetings[y]= value;
            y++;
        }
        int count = 0, temp = 0;
        for (int i = 0; i < meetings.length; i++) {
            count += meetings[i].countMember;
            temp++;
        }
        try {
            int averageNumber = count / temp;
            System.out.print("\nСередня кількість учасників на засіданнях: " + averageNumber +  "\n");
        } catch (Exception ex) {
            System.out.println("Учасників немає! Відбулося ділення на 0!");
        }
    }

    @Override
    public void LargestNumberMembers() {
        Meeting [] meetings = new Meeting[list.size()];
        int y = 0;

        for(Map.Entry<Integer ,Meeting> entry: list.entrySet()) {
            int key = entry.getKey();
            Meeting value = entry.getValue();
            meetings[y]= value;
            y++;
        }
        int max = meetings[0].countMember;
        for (int i = 0; i < meetings.length; i++) {
            if (max < meetings[i].countMember) {
                max = meetings[i].countMember;
            }
        }
        System.out.println("\nНайбільша кількість учасників: " + max + "\n");

        for (int i = 0; i < 1; i++) {
            System.out.print("Назва конференції: " + meetings[i].name);
            System.out.print("\nТема конференції: " + meetings[i].topic + "\n");
        }
    }

    public void LengthName() {
        Meeting [] meetings = new Meeting[list.size()];
        int y = 0;

        for(Map.Entry<Integer ,Meeting> entry: list.entrySet()) {
            int key = entry.getKey();
            Meeting value = entry.getValue();
            meetings[y]= value;
            y++;
        }
        for (int i = 0; i < meetings.length; i++) {
            System.out.println("Довжина назви " + meetings[i].name + ": "+ meetings[i].name.length());
        }
    }
}
