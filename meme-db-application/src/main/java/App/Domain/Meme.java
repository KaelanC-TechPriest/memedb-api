package App.Domain;

public class Meme {
    
    private int memeId;
    private int size;
    private int timesReported;
    private String uploadDate;
    private int isPrivate;
    private int views;
    private int likes;
    private int dislikes;
    private int creatorId;

    public Meme(int memeId, int size, int timesReported, String uploadDate, int isPrivate, 
                int views, int likes, int dislikes, int creatorId) {
        this.memeId = memeId;
        this.size = size;
        this.timesReported = timesReported;
        this.uploadDate = uploadDate;
        this.isPrivate = isPrivate;
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.creatorId = creatorId;
    }

    public Meme() {}

    public int getMemeId() {
        return memeId;
    }

    public void setMemeId(int memeId) {
        this.memeId = memeId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTimesReported() {
        return timesReported;
    }

    public void setTimesReported(int timesReported) {
        this.timesReported = timesReported;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public int isPrivate() {
        return isPrivate;
    }

    public void setPrivate(int isPrivate) {
        this.isPrivate = isPrivate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
}
