package com.dp.spring.template.common;

import lombok.Data;

@Data
public class UserContext {

    private String userId;
    private String userName;
    private String email;
    private String remoteIp;
    private String currentProjectId;
    private String currentProjectName;
    private String currentRoleId;
    private String currentLocale;
    private String projectId;
    private String timeZone;

    public UserContext() {

    }

    public UserContext(String userId, String currentProjectId, String currentRoleId, String projectId, String timeZone) {
        this.userId = userId;
        this.currentProjectId = currentProjectId;
        this.currentRoleId = currentRoleId;
        this.projectId = projectId;
        this.timeZone = timeZone;
    }

    public UserContext(
            String userId,
            String userName,
            String email,
            String remoteIp,
            String currentProjectId,
            String currentLocale,
            String projectId,
            String timeZone
    ) {
        this.userId = userId;
        this.userName = userName;
        this.remoteIp = remoteIp;
        this.currentProjectId = currentProjectId;
        this.currentLocale = currentLocale;
        this.projectId = projectId;
        this.email = email;
        this.timeZone = timeZone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getCurrentProjectId() {
        return currentProjectId;
    }

    public void setCurrentProjectId(String currentProjectId) {
        this.currentProjectId = currentProjectId;
    }

    public String getCurrentRoleId() {
        return currentRoleId;
    }

    public void setCurrentRoleId(String currentRoleId) {
        this.currentRoleId = currentRoleId;
    }

    public String getCurrentLocale() {
        if (currentLocale == null) return "en";
        return currentLocale;
    }

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get user's own projectId. Normally user's projectId is the same with userId.
     *
     * @return
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * Set user's own projectId. Normally user's projectId is the same with userId.
     *
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUserAvatarImageUrl() {
        /*return WebUtil.getUserAvatarThumnailImageUrl(this.userId);*/
        return "";
    }

    public String getCurrentProjectName() {
        return currentProjectName;
    }

    public void setCurrentProjectName(String currentProjectName) {
        this.currentProjectName = currentProjectName;
    }
}
