package com.android.crystal.ideabook;



public class Ideas {
    private int _id;
    private String _ideaTitle;
    private String _ideaContent;

    public Ideas(String _ideaTitle, String _ideaContent) {
        this._ideaTitle = _ideaTitle;
        this._ideaContent = _ideaContent;
    }

    public Ideas(int _id, String _ideaTitle, String _ideaContent) {
        this._id = _id;
        this._ideaTitle = _ideaTitle;
        this._ideaContent = _ideaContent;
    }

    //empty constructor
    public Ideas(){ }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_ideaTitle(String _ideaTitle) {
        this._ideaTitle = _ideaTitle;
    }

    public void set_ideaContent(String _ideaContent) {
        this._ideaContent = _ideaContent;
    }

    public int get_id() {
        return _id;
    }

    public String get_ideaTitle() {
        return _ideaTitle;
    }

    public String get_ideaContent() {
        return _ideaContent;
    }
}