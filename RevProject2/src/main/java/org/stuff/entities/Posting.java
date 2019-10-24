package org.stuff.entities;

import java.sql.Blob;

import javax.persistence.*;

@Entity
@Table(name = "posting")
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int p_id;

    @JoinColumn(name = "u_id")
    @ManyToOne
    private User user; 

    @Column(name = "title")
    private String title;

    @Column (name = "description")
    private String description;

    @Column (name = "category")
    private String category;

    @Column (name = "location")
    private String location;

    @Column (name = "init_date")
    private long init_date;

    @Column (name = "end_date")
    private long end_date;

    @Column (name = "img")
    private Blob img;

    public Posting() { }
    public int getId() {
        return p_id;
    }

    public void setId(int p_id) {
        this.p_id = p_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getInit_date() {
        return init_date;
    }

    public void setInit_date(long init_date) {
        this.init_date = init_date;
    }

    public long getEnd_date() {
        return end_date;
    }

    public void setEnd_date(long end_date) {
        this.end_date = end_date;
    }

    public Blob getImg() {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Posting [id=" + p_id + ", user=" + user + ", title=" + title + ", description=" + description
                + ", category=" + category + ", location=" + location + ", init_date=" + init_date + ", end_date="
                + end_date + "]";
    }

}
