package company.view;

import company.controller.WriterController;
import company.model.Label;
import company.model.Post;
import company.model.Writer;

import java.time.LocalDateTime;
import java.util.*;

public class WriterView implements View<Writer> {
    private final Scanner scanner = new Scanner(System.in);
    private final WriterController writerController = new WriterController();

    @Override
    public Writer create() {
        System.out.println("Enter first name");
        String firstName = scanner.next();
        System.out.println("Enter last name");
        String lastName = scanner.next();
        List<Post> writers = createPost();
        return writerController.create(firstName,lastName,writers);
    }

    @Override
    public List<Writer> getAll() {
        return writerController.getAll();
    }

    @Override
    public void delete() {
        System.out.println("Enter id number");
        int id = scanner.nextInt();
        writerController.delete(id);
    }

    @Override
    public Writer update() {
        System.out.println("Enter id number");
        int id = scanner.nextInt();
        System.out.println("Enter first Name for updating");
        String firstName = scanner.next();
        System.out.println("Enter second name for updating");
        String lastName = scanner.next();
        List<Post> posts = createPost();
        return writerController.update(new Writer(id,firstName,lastName,posts));
    }

    @Override
    public Writer get() {
        System.out.println("Enter id number");
        int id = scanner.nextInt();
        return writerController.getByID(id);
    }

    @Override
    public void close() {
        scanner.close();
    }

    private Integer generateLabelId(List<Label> labels) {
        Label label = labels.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.isNull(label) ? 1 : label.getId() + 1;
    }

    private Integer generatePostId(List<Post> posts) {
        Post maxPost = posts.stream().max(Comparator.comparing(Post::getId)).orElse(null);
        return Objects.isNull(maxPost) ? 1 : maxPost.getId() + 1;
    }

    private List<Post> createPost() {
        System.out.println("Enter how many Posts you want to create");
        int countOfPosts = scanner.nextInt();
        List<Post> postsList = new ArrayList<>();
        while (postsList.size() < countOfPosts) {
            System.out.println("Enter the content !");
            String content = scanner.next();
            System.out.println("Enter first time");
            LocalDateTime start = enterBeginOrLastTime();
            System.out.println("Enter second time");
            LocalDateTime finish = enterBeginOrLastTime();
            List<Label> labels = createLabels();
            Post post = new Post(0, content, start, finish, labels);
            post.setId(generatePostId(postsList));
            postsList.add(post);
        }
        return postsList;
    }

    private List<Label> createLabels() {
        System.out.println("Enter how many Labels you want to create");
        int countOfLabels = scanner.nextInt();
        List<Label> labels = new ArrayList<>();
        while (labels.size() < countOfLabels) {
            System.out.println("Enter the Labels Name");
            String name = scanner.next();
            Label label = new Label(0, name);
            label.setId(generateLabelId(labels));
            labels.add(label);
        }
        return labels;
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
