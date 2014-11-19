/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rsssucker.data.entity;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

/** Raw news article data downloaded from the web. */
@Entity
@NamedQueries({
    @NamedQuery(name = "getArticlesAfterDate", 
        query = "SELECT a FROM FeedArticle a WHERE a.datePublished >= :date")
})
@Table(indexes = {@Index(columnList = "datePublished", name = "datePubIndex")})
public class FeedArticle {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToMany(mappedBy = "articles")
    private Collection<Feed> feeds;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePublished;
    
    @Column(length=100000)
    private String text;
    
    @Column(length=10000)
    private String description;
    
    /** Title that is given in the feed entry. */
    @Column(length=1000)
    private String feedTitle;
    
    /** Title extracted from downloaded article. */
    @Column(length=1000)
    private String extractedTitle;
    
    @Column(length=10000,unique = true)
    private String url;
    
    public Collection<Feed> getFeeds() { return feeds; }
    public void setFeeds(Collection<Feed> feeds) { this.feeds = feeds; }   
    
    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getText() {
        return text;
    }

    public void setText(String txt) {
        if (txt.length() > 100000) txt = txt.substring(0, 100000);
        this.text = txt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        if (desc.length() > 10000) desc = desc.substring(0, 10000);
        this.description = desc;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String title) {
        if (title.length() > 1000) title = title.substring(0, 1000);
        this.feedTitle = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtractedTitle() {
        return extractedTitle;
    }

    public void setExtractedTitle(String title) {
        if (title.length() > 1000) title = title.substring(0, 1000);
        this.extractedTitle = title;
    }
    
}