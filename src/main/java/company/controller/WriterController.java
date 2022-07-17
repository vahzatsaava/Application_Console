package company.controller;

import company.model.Post;
import company.model.Writer;
import company.repository.gson.GsonWriterRepository;

import java.util.List;

public class WriterController {
    private final GsonWriterRepository gsonWriterRepository = new GsonWriterRepository();

    public Writer create(String firstName, String lastName, List<Post> posts) {
        Writer writer = new Writer(0, firstName, lastName, posts);
        return gsonWriterRepository.save(writer);
    }

    public List<Writer> getAll() {
        return gsonWriterRepository.getAll();
    }

    public Writer update(Writer writer) {
        return gsonWriterRepository.update(writer);
    }

    public void delete(Integer id) {
        gsonWriterRepository.deleteById(id);
    }

    public Writer getByID(Integer id) {
        return gsonWriterRepository.getById(id);
    }
}
