package App.Domain;

public class Tag {

    private String name;
    private int timesUsed;

    public Tag() {}

    public Tag(String name) {
        this.name = name;
        this.timesUsed = 0;
    }

    public Tag(String name, int timesUsed) {
        this.name = name;
        this.timesUsed = timesUsed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed) {
        this.timesUsed = timesUsed;
    }
}

