package rtbms.terxlabs.com;

public class info_news {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public info_news(String description, String head, String Date, String image) {
        this.description = description;
        this.head = head;
        this.date = Date;
        this.image = image;
    }
public info_news(){

    }
    private String head;
    private String date;
    private String image;
}
