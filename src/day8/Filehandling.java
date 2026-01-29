package day8;

import java.io.*;
import java.util.*;

public class Filehandling {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "1.Read 2.Count 3.Filter 4.Stats 5.Reverse 6.Write 7.Append 8.Split 9.Records 10.Copy 0.Exit");
            int c = s.nextInt();
            s.nextLine();
            if (c == 0)
                break;
            try {
                switch (c) {
                    case 1:
                        readFile(s);
                        break;
                    case 2:
                        countStats(s);
                        break;
                    case 3:
                        filterWord(s);
                        break;
                    case 4:
                        calcStats(s);
                        break;
                    case 5:
                        readReverse(s);
                        break;
                    case 6:
                        writeInput(s);
                        break;
                    case 7:
                        appendFile(s);
                        break;
                    case 8:
                        splitNum(s);
                        break;
                    case 9:
                        writeRec(s);
                        break;
                    case 10:
                        copyFile(s);
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    static void readFile(Scanner s) throws IOException {
        System.out.print("File:");
        FileReader f = new FileReader(s.nextLine());
        int i;
        while ((i = f.read()) != -1)
            System.out.print((char) i);
        f.close();
        System.out.println();
    }

    static void countStats(Scanner s) throws IOException {
        System.out.print("File:");
        BufferedReader b = new BufferedReader(new FileReader(s.nextLine()));
        String l;
        int ln = 0, w = 0, c = 0;
        while ((l = b.readLine()) != null) {
            ln++;
            w += l.split("\\s+").length;
            c += l.length();
        }
        b.close();
        System.out.println("L:" + ln + " W:" + w + " C:" + c);
    }

    static void filterWord(Scanner s) throws IOException {
        System.out.print("File:");
        BufferedReader b = new BufferedReader(new FileReader(s.nextLine()));
        System.out.print("Word:");
        String w = s.nextLine();
        String l;
        while ((l = b.readLine()) != null)
            if (l.contains(w))
                System.out.println(l);
        b.close();
    }

    static void calcStats(Scanner s) throws IOException {
        System.out.print("File:");
        Scanner f = new Scanner(new File(s.nextLine()));
        int sum = 0, cnt = 0;
        while (f.hasNextInt()) {
            sum += f.nextInt();
            cnt++;
        }
        f.close();
        System.out.println("Sum:" + sum + " Avg:" + (cnt > 0 ? (double) sum / cnt : 0));
    }

    static void readReverse(Scanner s) throws IOException {
        System.out.print("File:");
        BufferedReader b = new BufferedReader(new FileReader(s.nextLine()));
        ArrayList<String> l = new ArrayList<>();
        String line;
        while ((line = b.readLine()) != null)
            l.add(line);
        b.close();
        Collections.reverse(l);
        for (String str : l)
            System.out.println(str);
    }

    static void writeInput(Scanner s) throws IOException {
        System.out.print("File:");
        FileWriter w = new FileWriter(s.nextLine());
        System.out.print("Text:");
        w.write(s.nextLine());
        w.close();
    }

    static void appendFile(Scanner s) throws IOException {
        System.out.print("File:");
        FileWriter w = new FileWriter(s.nextLine(), true);
        System.out.print("Text:");
        w.write(s.nextLine());
        w.close();
    }

    static void splitNum(Scanner s) throws IOException {
        System.out.print("InFile:");
        Scanner f = new Scanner(new File(s.nextLine()));
        FileWriter e = new FileWriter("even.txt"), o = new FileWriter("odd.txt");
        while (f.hasNextInt()) {
            int n = f.nextInt();
            if (n % 2 == 0)
                e.write(n + " ");
            else
                o.write(n + " ");
        }
        f.close();
        e.close();
        o.close();
    }

    static void writeRec(Scanner s) throws IOException {
        System.out.print("File:");
        BufferedWriter b = new BufferedWriter(new FileWriter(s.nextLine()));
        System.out.print("Rec:");
        b.write(s.nextLine());
        b.close();
    }

    static void copyFile(Scanner s) throws IOException {
        System.out.print("Src:");
        FileReader r = new FileReader(s.nextLine());
        System.out.print("Dst:");
        FileWriter w = new FileWriter(s.nextLine());
        int i;
        while ((i = r.read()) != -1)
            w.write(i);
        r.close();
        w.close();
    }
}