package company.view;


import company.model.Label;
import company.repository.gson.GsonPostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label(1, "dsf"));
        labels.add(new Label(2, "rerere"));
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 12, 3, 3, 22);
        LocalDateTime localDateTime2 = LocalDateTime.of(2021, 11, 5, 10, 22);
//        String resOfDate = String.valueOf(localDateTime1);
//        Post post1 = new Post(1,"Emotion",localDateTime2,localDateTime1,labels);
//        System.out.println(post1.parserDataStartToLocalTime().getDayOfWeek());
//        System.out.println(post1.parseDataFinishToLocalDataTime().getDayOfWeek());
//
//        List<Label> labels1 = new ArrayList<>();
//        labels1.add(new Label(12,"Frencis"));
//        labels1.add(new Label(13,"France"));
//
//
//        Post post2 = new Post(2,"message",localDateTime2,localDateTime1,labels1);
//
//
//        List<Post> posts = new ArrayList<>();
//        posts.add(post1);
//        posts.add(post2);
//        GsonPostRepository gsonPostRepository = new GsonPostRepository();
//        System.out.println(gsonPostRepository.getAll());
//        gsonPostRepository.save(new Post(33,"Emotion",localDateTime2,localDateTime1,labels));

        //     MainView mainView = new MainView();
//     mainView.MainMenu();
        PostView postView = new PostView();
        System.out.println(postView.getAllPosts());
        GsonPostRepository gsonPostRepository = new GsonPostRepository();
        //gsonPostRepository.update(new Post(3,"Name",localDateTime1,localDateTime2,labels));
        postView.update();
        System.out.println(gsonPostRepository.getAll());
    }
}
