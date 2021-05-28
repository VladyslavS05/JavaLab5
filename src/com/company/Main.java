package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = null;
        int way = 5;
        while(way!= 0) {
            try {
                in = new Scanner(System.in);
                System.out.println("Виберіть завдання:\n1 - Підприємство. 2 - Конференція. 0 - Вихід");
                way = in.nextInt();
                switch(way) {
                    case 1: one(); break;
                    case 2: two(); break;
                    case 3: break; }
            }
            catch(Exception ex) {System.out.print("Невірний формат даних!");};
        }
    }

    public static void one()
    {
        WorkResult workResult = new WorkResult();
        workResult.start();
        Scanner in = null;
        int way = 0;
        do {
            try {
                in = new Scanner(System.in);
                System.out.println("\nВиберіть завдання:\n1 - додати/редагувати | 2 - вивести | 3 - видалити | 4 -  недовиконаний план | 5 - вихід");
                way=in.nextInt();
                switch(way) {
                    case 1: workResult.Add(); break;
                    case 2: workResult.Show(); break;
                    case 3: workResult.Delete(); break;
                    case 4: workResult.Task(); break;
                    case 5: break;
                    default: System.out.print("Невірний формат даних!");

                }
            }
            catch(Exception ex) {System.out.print(ex.getMessage());};
        }while(way != 5);
    }

    public static void two()
    {
        Meeting meeting =new Meeting();
        meeting.start();
        Scanner in = null;
        int way = 0;
        do {
            try {
                in = new Scanner(System.in);
                System.out.println("\nВиберіть завдання:\n1 - додати/редагувати | 2 - вивести | 3 - видалити \n4 - Середня кількість учасників на засіданнях"
                        + "\n5 - Засідання з найбільшою кількістю учасників\n6 - Довжина назви конференції\n7 - Вихід");
                way = in.nextInt();
                switch(way) {
                    case 1: meeting.Add(); break;
                    case 2: meeting.Show(); break;
                    case 3: meeting.Delete(); break;
                    case 4: meeting.AverageNumberMembers(); break;
                    case 5: meeting.LargestNumberMembers();break;
                    case 6: meeting.LengthName();break;
                    case 7: break;
                    default: System.out.print("Неправильний формат даних!");
                }
            }
            catch(Exception ex) {System.out.print(ex.getMessage());};
        }while(way != 7);
    }
}
