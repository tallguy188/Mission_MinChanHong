package com.ll;

import java.util.Scanner;

public class App {

    private Scanner sc;


    public App() {
        sc = new Scanner(System.in);

    }

    public void run() {

        System.out.println("== 명언 앱 ==");

        QuoteController quoteController = new QuoteController(sc);


        while (true) {
            System.out.print("명령) ");

            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {

                quoteController.actionWrite();
            } else if (cmd.equals("목록")) {
                quoteController.actionList();
            } else if (cmd.startsWith("삭제")) {
                quoteController.actionDelete(rq);
            } else if (cmd.startsWith("수정")) {
                quoteController.actionModify(rq);
            }
        }
    }

}