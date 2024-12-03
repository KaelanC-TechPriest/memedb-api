package App.Domain;

public class Relates {

    private int adId;
    private String tagId;


    public Relates(String tagId, int adId) {
        this.tagId = tagId;
        this.adId = adId;
    }

    public Relates() {}

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
