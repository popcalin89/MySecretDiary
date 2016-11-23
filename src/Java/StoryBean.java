import java.util.Date;

/**
 * Created by caly on 10/19/2016.
 */
public class StoryBean {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Date getInsertDate() {return insertDate;}

    public void setInsertDate(Date insertDate) {this.insertDate = insertDate;}

    private long id;
    private long iduser;
    private String story;
    private Date insertDate;

}

