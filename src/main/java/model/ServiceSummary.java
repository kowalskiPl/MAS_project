package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "serviceSummary")
public class ServiceSummary {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    protected long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String title;

    private String description;

    private boolean approved = false;

    @ManyToOne
    private Vehicle vehicle = null;

    public ServiceSummary() {
    }

    public ServiceSummary(Date date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public ServiceSummary(Date date, String title, String description, boolean approved) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.approved = approved;
    }

    public void addVehicle(Vehicle vehicle){
        if (this.vehicle == null){
            this.vehicle = vehicle;
            vehicle.addServiceSummary(this);
        }
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "ServiceSummary{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", approved=" + approved +
                '}';
    }
}
