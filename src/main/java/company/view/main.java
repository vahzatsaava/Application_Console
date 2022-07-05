package company.view;


import com.google.gson.Gson;
import company.model.Skill;
import company.repository.GsonSkillRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        GsonSkillRepository skillRepository = new GsonSkillRepository();
        skillRepository.save(new Skill(1,"ere"));
        skillRepository.save(new Skill(2,"Trre"));
        skillRepository.save(new Skill(7,"Koko"));
        skillRepository.save(new Skill(33,"Angel"));
        skillRepository.save(new Skill(55,"Terry"));

        System.out.println(skillRepository.getAll());
        skillRepository.update(new Skill(0,"Franke Sinatra"));
        System.out.println(skillRepository.getAll());


    }
}
