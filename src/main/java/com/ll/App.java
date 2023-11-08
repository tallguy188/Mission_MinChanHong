package com.ll;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {

    static ArrayList<Quote> quotes;
    static Set<Integer> availableIds;
    Scanner sc;

    ObjectMapper objectMapper = new ObjectMapper();

    File jsonFile = new File("data.json");


    App() {
        quotes = new ArrayList<>();
        sc = new Scanner(System.in);
        availableIds = new HashSet<>();
        loadQuotesFromJson();
    }

    void run() {

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");

            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {

                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) {
                actionDelete(rq);
            } else if (cmd.startsWith("수정")) {
                actionModify(rq);
            } else if (cmd.equals("빌드")) {
                buildJsonFile();
            }
        }
    }

    void actionWrite() {

        int newId = getNextId();
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        Quote quote = new Quote(newId, content, author);
        quotes.add(quote);
        System.out.println(quote.getId() + "번 명언이 등록되었습니다.");

        saveQuotesToJson();
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = quotes.size() - 1; i >= 0; i--) {
            Quote quote = quotes.get(i);
            System.out.println(quote.getId() + " / " + quote.getAuthor() + " / " + quote.getContent());
        }

    }

    void actionDelete(Rq rq) {

        boolean found = false;

        int deleteId = rq.getParamAsInt("id", 0);

        if (deleteId == 0) {
            System.out.println("id를 정확하게 입력해주세요.");
            return;
        }
        Quote foundQuote = null;
        for (Quote quote : quotes) {
            if (quote.getId() == deleteId) {
                foundQuote = quote;
                break;
            }
        }

        if (foundQuote != null) {
            quotes.remove(foundQuote);
            System.out.println(deleteId + "번 명언이 삭제되었습니다.");
            saveQuotesToJson();
        } else {
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        }
    }


    void actionModify(Rq rq) {
        int modifyId = rq.getParamAsInt("id", 0);
        if (modifyId == 0) {
            System.out.println("id를 정확하게 입력해주세요.");
            return;
        }
        Quote quote = null;
        for (Quote q : quotes) {
            if (q.getId() == modifyId) {
                quote = q;
                break;
            }
        }
        String prevcontent = quote.getContent();
        String prevAuthor = quote.getAuthor();

        System.out.println("명언(기존) : " + prevcontent);
        System.out.print("명언 : ");
        String modicontent = sc.nextLine();
        System.out.println("작가(기존) : " + prevAuthor);
        System.out.print("작가 : ");
        String modiAuthor = sc.nextLine();
        quote.setContent(modicontent);
        quote.setAuthor(modiAuthor);

        saveQuotesToJson();
    }

    void loadQuotesFromJson() {
        if (jsonFile.exists()) {
            try {
                quotes = objectMapper.readValue(jsonFile, new TypeReference<ArrayList<Quote>>() {
                });

                for(Quote quote : quotes) {
                    availableIds.remove(quote.getId());
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    void saveQuotesToJson() {
        try {
            objectMapper.writeValue(jsonFile,quotes);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    int getNextId() {
        if (!availableIds.isEmpty()) {
            int smallestId = Integer.MAX_VALUE;
            for (int id : availableIds) {
                if (id < smallestId) {
                    smallestId = id;
                }
            }
            availableIds.remove(smallestId);
            return smallestId;
        } else {
            return quotes.size() + 1;
        }
    }


    void buildJsonFile() {

        try {
            objectMapper.writeValue(jsonFile, quotes);
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}