package crispit.errorextractor;

/**
 * Created by Mikael on 2016-04-20.
 */
public class ErrorReport {
    private String id;
    private String symptom;
    private String comment;
    private String pubdate;
    private int grade;

    public ErrorReport(String id, String symptom, String comment, String pubdate, int grade){

        this.id = id;
        this.symptom = symptom;
        this.comment = comment;
        this.pubdate = pubdate;
        this.grade = grade;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}

