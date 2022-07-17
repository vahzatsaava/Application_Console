package company.repository.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import company.model.Writer;
import company.repository.WriterRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GsonWriterRepository implements WriterRepository {
    private final Gson WRITER_GSON = new Gson();
    private final String PATH_TO_WRITER = "src/main/resources/writer.json";

    private List<Writer> getListFromJsonFile() {
        Type targetClassType = new TypeToken<ArrayList<Writer>>() {
        }.getType();
        return WRITER_GSON.fromJson(readStringFromJsonFromFile(), targetClassType);
    }

    private String readStringFromJsonFromFile() {
        StringBuilder builder = new StringBuilder();
        try (FileReader reader = new FileReader(PATH_TO_WRITER)) {
            while (reader.ready()) {
                builder.append((char) reader.read());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private void writeJsonToFile(List<Writer> writers) {
        String jsonFormat = WRITER_GSON.toJson(writers);
        write(jsonFormat);
    }

    private void write(String jsonString) {
        try (FileWriter writer = new FileWriter(PATH_TO_WRITER)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Integer generateId(List<Writer> writers){
        Writer writer = writers.stream().max(Comparator.comparing(Writer::getId)).orElse(null);
        return Objects.isNull(writer) ? 1:writer.getId() + 1;
    }

    @Override
    public Writer getById(Integer integer) {
        List<Writer> writers = getListFromJsonFile();
        return writers.stream().filter(i -> i.getId().equals(integer)).findFirst().orElse(null);
    }

    @Override
    public List<Writer> getAll() {
        return getListFromJsonFile();
    }

    @Override
    public Writer save(Writer writer) {
        List<Writer> listWriters = getListFromJsonFile();
        if (listWriters == null) {
            listWriters = new ArrayList<>();
        }
        listWriters.add(writer);
        writer.setId(generateId(listWriters));

        writeJsonToFile(listWriters);
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> listWriter = getListFromJsonFile();
        listWriter.forEach(i ->{
            if (i.getId().equals(writer.getId())){
                i.setFirstName(writer.getFirstName());
                i.setLastName(writer.getLastName());
                i.setPosts(writer.getPosts());
            }
        });
        return writer;
    }

    @Override
    public void deleteById(Integer integer) {
        List<Writer> writers = getListFromJsonFile();
        writers.removeIf(i -> i.getId().equals(integer));
        writeJsonToFile(writers);
    }
}
