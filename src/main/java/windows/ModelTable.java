package windows;

public class ModelTable {
    private long id;
    private String date;
    private String title;
    private String approved;

    public ModelTable(long id, String date, String title, boolean approved) {
        this.id = id;
        this.date = date;
        this.title = title;
        if (approved)
            this.approved = "yes";
        else
            this.approved = "no";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }
}
