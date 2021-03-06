package journal.nanodegree.capstone.prof.journal_capstonnanodegree.helpers.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Prof-Mohamed Atef on 2/26/2019.
 */
@Entity(tableName = "Articles")
public class ArticlesEntity implements Serializable{
    public ArticlesEntity(){
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    public int ID;

    @NonNull
    @ColumnInfo(name = "AUTHOR")
    public String AUTHOR;

    @NonNull
    @ColumnInfo(name = "TITLE")
    public String TITLE;

    @NonNull
    @ColumnInfo(name = "DESCRIPTION")
    public String DESCRIPTION;


    @NonNull
    @ColumnInfo(name = "ARTICLE_URL")
    public String ARTICLE_URL;


    @NonNull
    @ColumnInfo(name = "IMAGE_URL")
    public String IMAGE_URL;

    @NonNull
    @ColumnInfo(name = "PUBLISHED_AT")
    public String PUBLISHED_AT;


    @NonNull
    @ColumnInfo(name = "CATEGORY")
    public String CATEGORY;


    @NonNull
    @ColumnInfo(name = "SOURCE_NAME")
    public String SOURCE_NAME;

    public ArticlesEntity(String author_str, String title_str, String description_str, String url_str, String url_to_image_str, String published_at_str, String name_str) {
        this.AUTHOR=author_str;
        this.TITLE=title_str;
        this.DESCRIPTION=description_str;
        this.ARTICLE_URL=url_str;
        this.IMAGE_URL=url_to_image_str;
        this.PUBLISHED_AT=published_at_str;
        this.SOURCE_NAME=name_str;
    }

    @NonNull
    public int getID() {
        return ID;
    }

    public void setID(@NonNull int ID) {
        this.ID = ID;
    }

    @NonNull
    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(@NonNull String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    @NonNull
    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(@NonNull String TITLE) {
        this.TITLE = TITLE;
    }

    @NonNull
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(@NonNull String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    @NonNull
    public String getARTICLE_URL() {
        return ARTICLE_URL;
    }

    public void setARTICLE_URL(@NonNull String ARTICLE_URL) {
        this.ARTICLE_URL = ARTICLE_URL;
    }

    @NonNull
    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(@NonNull String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }

    @NonNull
    public String getPUBLISHED_AT() {
        return PUBLISHED_AT;
    }

    public void setPUBLISHED_AT(@NonNull String PUBLISHED_AT) {
        this.PUBLISHED_AT = PUBLISHED_AT;
    }

    @NonNull
    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(@NonNull String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    @NonNull
    public String getSOURCE_NAME() {
        return SOURCE_NAME;
    }

    public void setSOURCE_NAME(@NonNull String SOURCE_NAME) {
        this.SOURCE_NAME = SOURCE_NAME;
    }
}
