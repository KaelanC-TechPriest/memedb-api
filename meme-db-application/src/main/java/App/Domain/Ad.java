package App.Domain;

public class Ad {
    private int number;
    private String publisher;
    private String url;
    private String datePublished;

    public Ad(int number, String publisher, String url, String datePublished) {
        this.number = number;
        this.publisher = publisher;
        this.url = url;
        this.datePublished = datePublished;
    }

    public Ad() {}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }
}
