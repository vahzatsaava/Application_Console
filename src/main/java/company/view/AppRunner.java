package company.view;


import company.controller.WriterController;
import company.model.Label;
import company.model.Post;
import company.model.Writer;
import company.repository.gson.GsonWriterRepository;

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
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, "message", localDateTime1, localDateTime2, labels));
        posts.add(new Post(2, "Service", localDateTime2, localDateTime1, labels));
        Writer writer = new Writer(1, "fedor", "fedin", posts);
        Writer writer2 = new Writer(7, "gena", "gena2", posts);
        Writer writer3 = new Writer(1, "lena", "dfffdfdfdfdfdf", posts);

        MainView mainView = new MainView();
        mainView.mainMenu();


    }
}
