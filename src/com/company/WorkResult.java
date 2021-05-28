package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WorkResult implements CompanyFunctions, Serializable {
    public static Map<Integer,WorkResult> list=new HashMap<Integer,WorkResult>();
    String month, name;
    int  enterpriseID, releasePlan, factPlan;

    WorkResult() {}

    static void save()
    {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("CompanyWorkResults.txt")))
        {
            objectOutputStream.writeObject(list);
        }
        catch(Exception ex) {System.out.print(ex.getMessage());}
    }

    public static void start()
    {
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("CompanyWorkResults.txt")))
        {
            list=(HashMap<Integer,WorkResult>)objectInputStream.readObject();
        }
        catch(Exception ex) {System.out.print(ex.getMessage());}
    }

    @Override
    public void Add() {
        Scanner in = new Scanner(System.in);
        WorkResult workResult = new WorkResult();
        System.out.print("ID підприємства - ");
        workResult.enterpriseID = in.nextInt();
        int action = 1;
        for (Map.Entry<Integer, WorkResult> entry : list.entrySet()) {
            int key = entry.getKey();
            WorkResult value = entry.getValue();
            if (key == workResult.enterpriseID) {
                System.out.println("ID підприємства існує. Редагувати? (1 - так)");
                action = in.nextInt();
            }
        }
        if (action == 1) {
            System.out.print("Назва - ");
            in.nextLine();
            workResult.name = in.nextLine();
            System.out.print("Місяць - ");
            workResult.month = in.nextLine();
            System.out.print("План випуску продукції - ");
            workResult.releasePlan = in.nextInt();
            System.out.print("Фактичний випуск продукції - ");
            workResult.factPlan = in.nextInt();
            list.put(workResult.enterpriseID, workResult);
            save();
        }
    }

    @Override
    public void Delete() {
        Scanner in= new Scanner(System.in);
        System.out.print("ID підприємства - ");
        int i = in.nextInt();
        list.remove(i);
        save();
    }

    @Override
    public void Show() {
        int y = 0;
        WorkResult [] workResult = new WorkResult[list.size()];

            for(Map.Entry<Integer ,WorkResult> entry: list.entrySet()) {
                int key = entry.getKey();
                WorkResult value = entry.getValue();
                workResult[y]= value;
                y++;
            }

        for (WorkResult result : workResult) {
            System.out.print("\n\nID підприємства - " + result.enterpriseID);
            System.out.print("\nНазва підприємства - " + result.name);
            System.out.print("\nМісяць - " + result.month);
            System.out.print("\nПлан випуску продукції - " + result.releasePlan);
            System.out.print("\nФактичний випуск  продукції - " + result.factPlan + "\n");
        }
    }


    @Override
    public void Task() {
        WorkResult [] workResult = new WorkResult[list.size()];
        int y = 0;

        for(Map.Entry<Integer ,WorkResult> entry: list.entrySet()) {
            int key = entry.getKey();
            WorkResult value = entry.getValue();
            workResult[y]= value;
            y++;
        }
        System.out.println("Не було виконано норму: ");
        for (int i = 0; i < workResult.length; i++) {
            if (workResult[i].releasePlan > workResult[i].factPlan) {
                System.out.print("\n\nID підприємства - " + workResult[i].enterpriseID);
                System.out.print("\nНазва підприємства - " + workResult[i].name);
                System.out.print("\nМісяць - " + workResult[i].month);
                System.out.print("\nПлан випуску продукції - " + workResult[i].releasePlan);
                System.out.print("\nФактичний випуск  продукції - " + workResult[i].factPlan + "\n");
            }
        }
    }
}
