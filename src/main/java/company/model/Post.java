package company.model;


import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private Integer id;
    private String content;
    private String dataStart;
    private String dataFinish;
    private List<Label> labels;

    public Post(Integer id, String content, LocalDateTime dataStart, LocalDateTime dataFinish, List<Label> labels) {
        this.id = id;
        this.content = content;
        this.dataStart = String.valueOf(dataStart);
        this.dataFinish = String.valueOf(dataFinish);
        this.labels = labels;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDataStart() {
        return dataStart;
    }

    public void setDataStart(LocalDateTime dataStart) {
        this.dataStart = String.valueOf(dataStart);
    }

    public String getDataFinish() {
        return dataFinish;
    }

    public void setDataFinish(LocalDateTime dataFinish) {
        this.dataFinish = String.valueOf(dataFinish);
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public  LocalDateTime parserDataStartToLocalTime() {
        return LocalDateTime.parse(getDataStart());
    }
    public LocalDateTime parseDataFinishToLocalDataTime(){
        return LocalDateTime.parse(getDataFinish());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", dataStart=" + dataStart +
                ", dataFinish=" + dataFinish +
                ", labels=" + labels +
                '}';
    }
}
