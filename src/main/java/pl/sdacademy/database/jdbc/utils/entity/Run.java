package pl.sdacademy.database.jdbc.utils.entity;

import java.util.Date;
import java.util.List;

public class Run {

    private Long id;
    private String name;
    private String place;
    private Date startDate;
    private Date startTime;
    private Integer membersLimit;

    public List<Member> getMemberList() {
        return memberList;
    }

    private List<Member> memberList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getMembersLimit() {
        return membersLimit;
    }

    public void setMembersLimit(Integer membersLimit) {
        this.membersLimit = membersLimit;
    }


}
