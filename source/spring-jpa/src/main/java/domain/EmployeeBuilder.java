package domain;

import java.util.Date;

public class EmployeeBuilder {
    private Long id;
    private String name;
    private String title;
    private String startDate;

    /**
     * 생성자
     */
    public EmployeeBuilder() {
        
    }
    public EmployeeBuilder(Long id, String name, String title, String startDate) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.startDate = startDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder name(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder title(String title) {
        this.title = title;
        return this;
    }

    public EmployeeBuilder startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    @Override
    public String toString() {
        return "EmployeeBuilder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
