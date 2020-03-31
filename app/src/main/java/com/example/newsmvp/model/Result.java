package com.example.newsmvp.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Parcelable{

    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subsection")
    @Expose
    private String subsection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("material_type_facet")
    @Expose
    private String materialTypeFacet;
    @SerializedName("kicker")
    @Expose
    private String kicker;
    @SerializedName("des_facet")
    @Expose
    private List<String> desFacet = null;
    @SerializedName("org_facet")
    @Expose
    private List<Object> orgFacet = null;
    @SerializedName("per_facet")
    @Expose
    private List<Object> perFacet = null;
    @SerializedName("geo_facet")
    @Expose
    private List<String> geoFacet = null;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = null;
    @SerializedName("short_url")
    @Expose
    private String shortUrl;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }
    };

    protected Result(Parcel in) {
        this.section = ((String) in.readValue((String.class.getClassLoader())));
        this.subsection = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this._abstract = ((String) in.readValue((String.class.getClassLoader())));
        this.url = ((String) in.readValue((String.class.getClassLoader())));
        this.uri = ((String) in.readValue((String.class.getClassLoader())));
        this.byline = ((String) in.readValue((String.class.getClassLoader())));
        this.itemType = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.publishedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.materialTypeFacet = ((String) in.readValue((String.class.getClassLoader())));
        this.kicker = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.desFacet, (java.lang.String.class.getClassLoader()));
        in.readList(this.orgFacet, (java.lang.Object.class.getClassLoader()));
        in.readList(this.perFacet, (java.lang.Object.class.getClassLoader()));
        in.readList(this.geoFacet, (java.lang.String.class.getClassLoader()));
        in.readList(this.multimedia, (Multimedium.class.getClassLoader()));
        this.shortUrl = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Result() {
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public List<Object> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<Object> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<Object> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<Object> perFacet) {
        this.perFacet = perFacet;
    }

    public List<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(section);
        dest.writeValue(subsection);
        dest.writeValue(title);
        dest.writeValue(_abstract);
        dest.writeValue(url);
        dest.writeValue(uri);
        dest.writeValue(byline);
        dest.writeValue(itemType);
        dest.writeValue(updatedDate);
        dest.writeValue(createdDate);
        dest.writeValue(publishedDate);
        dest.writeValue(materialTypeFacet);
        dest.writeValue(kicker);
        dest.writeList(desFacet);
        dest.writeList(orgFacet);
        dest.writeList(perFacet);
        dest.writeList(geoFacet);
        dest.writeList(multimedia);
        dest.writeValue(shortUrl);
    }

    public int describeContents() {
        return 0;
    }

}
