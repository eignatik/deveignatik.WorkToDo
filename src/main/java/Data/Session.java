package Data;

import Data.Entity.User;

/**
 * Created by Eugen on 6/12/2016.
 */
public class Session {
    private String user;
    private String userGroup;
    private int userId;
    private int projectId;
    private static final Session instance = new Session();
    private Session(){}
    public static Session getSession(){return instance;}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
