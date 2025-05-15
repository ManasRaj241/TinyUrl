package com.tinyurl.resolver.entity;

@Entity
@Table(name = "short_urls")
public class UrlMapping {

    @Id
    private String shortId;

    @Column(nullable = false)
    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
