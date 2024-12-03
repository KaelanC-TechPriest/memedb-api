package App.Domain;

public class User {
    
    private int userId;
    private String username;
    private String email;
    private String password;
    private String joinDate;
    private int numPosts;

    public User(int userId, String username, String email, String password, String joinDate, int numPosts) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.joinDate = joinDate;
        this.numPosts = numPosts;
    }

    public User() { }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public int getNumPosts() {
        return numPosts;
    }

    public void setNumPosts(int numPosts) {
        this.numPosts = numPosts;
    }
}
