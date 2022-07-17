package company.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import company.model.Post;
import company.repository.PostRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonPostRepository implements PostRepository {
    private final Gson POST_GSON = new Gson();
    private final String PATH_TO_POST_JSON_FILE = "src/main/resources/post.json";

    private List<Post> getPostFromFile() {
        Type targetClassType = new TypeToken<ArrayList<Post>>() {
        }.getType();
        return POST_GSON.fromJson(readStringFromJsonPost(), targetClassType);
    }

    private String readStringFromJsonPost() {
        StringBuilder builder = new StringBuilder();
        try (FileReader reader = new FileReader(PATH_TO_POST_JSON_FILE)) {
            while (reader.ready()) {
                builder.append((char) reader.read());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void writeToJsonFile(List<Post> posts) {
        String jsonFormat = POST_GSON.toJson(posts);
        writePostJsonToFile(jsonFormat);
    }

    private void writePostJsonToFile(String jsonString) {
        try (FileWriter writer = new FileWriter(PATH_TO_POST_JSON_FILE)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer generateId(List<Post> posts) {
        Post maxPost = posts.stream().max(Comparator.comparing(Post::getId)).orElse(null);
        return Objects.isNull(maxPost) ? 1 : maxPost.getId() + 1;
    }

    @Override
    public Post getById(Integer integer) {
        List<Post> posts = getAll();
        return posts.stream().filter(i -> i.getId().equals(integer)).findFirst().orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return getPostFromFile();
    }

    @Override
    public Post save(Post post) {
        List<Post> current = getAll();
        if (current == null) {
            current = new ArrayList<>();
        }
        current.add(post);
        post.setId(generateId(current));
        writeToJsonFile(current);
        return post;
    }

    @Override
    public Post update(Post post) {
        List<Post> current = getAll();
        current.forEach(s->{
            if (s.getId().equals(post.getId())){
                s.setContent(post.getContent());
                s.setDataFinish(post.parseDataFinishToLocalDataTime());
                s.setDataStart(post.parserDataStartToLocalTime());
                s.setLabels(post.getLabels());
            }
        });
        writeToJsonFile(current);
         return post;
    }

    @Override
    public void deleteById(Integer integer) {
        List<Post> current = getAll();
        current.removeIf(i->i.getId().equals(integer));
        writeToJsonFile(current);
    }
}
