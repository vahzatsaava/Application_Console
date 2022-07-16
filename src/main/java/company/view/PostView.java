package company.view;

import company.controller.PostController;
import company.model.Label;
import company.model.Post;

import java.time.LocalDateTime;
import java.util.*;

public class PostView {
    private final PostController postController = new PostController();
    private final Scanner scanner = new Scanner(System.in);


    public Post createPost() {
        System.out.println("Enter the content name for Save");
        String content = scanner.next();
        System.out.println("Enter the beginner time");
        LocalDateTime localDateTimeBegin = enterBeginOrLastTime();
        System.out.println("Enter last time");
        LocalDateTime localDateTimeFinish = enterBeginOrLastTime();
        List<Label> labels = createLabels();
        return postController.createPost(content, localDateTimeBegin, localDateTimeFinish, labels);
    }

    public List<Post> getAllPosts() {
        return postController.getList();
    }

    public void delete() {
        System.out.println("Enter the Post position(id)");
        int id = scanner.nextInt();
        postController.delete(id);
    }

    public Post update() {
        System.out.println("Enter Post id For Update");
        int id = scanner.nextInt();
        System.out.println("Enter the Content Type");
        String content = scanner.next();
        System.out.println("Enter begin time");
        LocalDateTime start = enterBeginOrLastTime();
        System.out.println("Enter finish time");
        LocalDateTime finish = enterBeginOrLastTime();
        List<Label> posts = createLabels();
        return postController.update(new Post(id, content, start, finish, posts));
    }
    public Post get(){
        System.out.println("Enter id");
        int id = scanner.nextInt();
        return postController.getById(id);
    }
    public void closeScanner(){
        scanner.close();
    }

    private List<Label> createLabels() {
        System.out.println("Enter how many Labels you want to create");
        int countOfLabels = scanner.nextInt();
        List<Label> labels = new ArrayList<>();
        while (labels.size() < countOfLabels) {
            System.out.println("Enter the Labels Name");
            String name = scanner.next();
            Label label = new Label(0, name);
            label.setId(generateIdForLabels(labels));
            labels.add(label);
        }
        return labels;
    }

    private Integer generateIdForLabels(List<Label> labels) {
        Label maxVal = labels.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.isNull(maxVal) ? 1 : maxVal.getId() + 1;
    }

    private LocalDateTime enterBeginOrLastTime() {
        System.out.println("Enter years");
        int years = scanner.nextInt();
        System.out.println("Enter months");
        int months = scanner.nextInt();
        System.out.println("Enter days");
        int days = scanner.nextInt();
        System.out.println("Enter hours");
        int hour = scanner.nextInt();
        System.out.println("Enter minutes");
        int minutes = scanner.nextInt();
        return LocalDateTime.of(years, months, days, hour, minutes);
    }
}
