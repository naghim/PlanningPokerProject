package com.example.planningpokerproject;

public class RecyclerItem {

    private String task_description;
    private String start_time;
    private String end_time;
    private boolean isActive;

    public RecyclerItem() {
    }

    public RecyclerItem(String task_description, String start_time, String end_time,boolean isActive){
        this.task_description = task_description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.isActive = isActive;
    }

    public String getTask_description()
    {
        return this.task_description;
    }

    public String getStart_time()
    {
        return this.start_time;
    }

    public String getEnd_time()
    {
        return this.end_time;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
