package com.ll;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static ArrayList<Quote> quotes;
    static int count;
    Scanner sc;



    App() {
        quotes = new ArrayList<>();
        count = 0;
        sc = new Scanner(System.in);
    }

    void run() {

        System.out.println("== 명언 앱 ==");
        while(true) {
            System.out.print("명령) ");

            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            if (cmd.equals("종료")) {
                break;
            }
            else if( cmd.equals("등록")) {

                actionWrite();
            }
            else if(cmd.equals("목록")) {
                actionList();
            }

            else if (cmd.startsWith("삭제")) {
                actionDelete(rq);
            }

            else if (cmd.startsWith("수정")) {
                actionModify(rq);
            }
        }
    }

    void actionWrite() {

        count ++;
        System.out.print("명언 : ");
        String comment  = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        Quote quote = new Quote(count,comment,author);
        quotes.add(quote);
        System.out.println(quote.getId()+"번 명언이 등록되었습니다.");
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = quotes.size() - 1; i >= 0; i--) {
            Quote quote = quotes.get(i);
            System.out.println(quote.getId() + " / " + quote.getAuthor() + " / " + quote.getComment());
        }

    }

    void actionDelete(Rq rq){

        boolean found = false;

        int deleteId = rq.getParamAsInt("id",0);

        if (deleteId == 0) {
            System.out.println("id를 정확하게 입력해주세요.");
            return;
        }
        for (int i = 0; i< quotes.size(); i++) {
            Quote quote = quotes.get(i);
            if(quote.getId() == deleteId) {
                quotes.remove(i);
                System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                found = true;
                break;
            }
        }

        if (!found){
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }


    void actionModify(Rq rq) {
        int modifyId = rq.getParamAsInt("id",0);
        if(modifyId == 0) {
            System.out.println("id를 정확하게 입력해주세요.");
            return;
        }
        Quote quote = null;
        for(Quote q : quotes) {
            if(q.getId() == modifyId) {
                quote = q;
                break;
            }
        }
        String prevComment = quote.getComment();
        String prevAuthor = quote.getAuthor();

        System.out.println("명언(기존) : " + prevComment);
        System.out.print("명언 : ");
        String modiComment = sc.nextLine();
        System.out.println("작가(기존) : " + prevAuthor);
        System.out.print("작가 : ");
        String modiAuthor = sc.nextLine();
        quote.setComment(modiComment);
        quote.setAuthor(modiAuthor);


    }

}