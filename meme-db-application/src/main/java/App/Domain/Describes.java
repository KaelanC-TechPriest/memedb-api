package App.Domain;

public class Describes {

    private String tagName;
    private int memeId;

    public Describes() { }

    public Describes(String tagName, int memeId) {
        this.tagName = tagName;
        this.memeId = memeId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getMemeId() {
        return memeId;
    }

    public void setMemeId(int memeId) {
        this.memeId = memeId;
    }

    @Override
    public String toString() {
        return "Describes{" +
                "tagName='" + tagName + '\'' +
                ", memeId=" + memeId +
                '}';
    }
}
