package com.example.newsmvp.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsList implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Parcelable.Creator<NewsList> CREATOR = new Creator<NewsList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NewsList createFromParcel(Parcel in) {
            return new NewsList(in);
        }

        public NewsList[] newArray(int size) {
            return (new NewsList[size]);
        }

    }
            ;

    protected NewsList(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.copyright = ((String) in.readValue((String.class.getClassLoader())));
        this.section = ((String) in.readValue((String.class.getClassLoader())));
        this.lastUpdated = ((String) in.readValue((String.class.getClassLoader())));
        this.numResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (Result.class.getClassLoader()));
    }

    public NewsList() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(copyright);
        dest.writeValue(section);
        dest.writeValue(lastUpdated);
        dest.writeValue(numResults);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }
}
