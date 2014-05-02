package com.Josh.protodos.app;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Josh on 4/30/14.
 */
public class Todo {

    private UUID mId;
    private String mTitle;
    private String mDetails;
    private Date mDate;
    private Boolean mSolved;

    //Todo_Constructor
    public Todo(){
           //generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
        mSolved = false;
    }

    @Override
    public String toString(){
        return mTitle;
    }

    public UUID getID() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDetails(){return mDetails;}

    public void setDetails(String detail) {mDetails = detail;}

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved){
        mSolved = solved;
    }



}
