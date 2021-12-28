package fastfoodbackend.fastfoodbackend.Class;

import java.time.LocalDateTime;

public class LogError {
    private Integer Id;
    private String ApplicationId;
    private String ProcedureName;
    private String ControllerName;
    private String FormName;
    private String UserName;
    private LocalDateTime DateTimeMade;
    private String MethodName;
    private String ErrorMessage;
    private String IpAddress;

    public LogError(){};

    public LogError(Integer id, String applicationId, String procedureName, String controllerName, String formName, String userName, LocalDateTime dateTimeMade, String methodName, String errorMessage, String ipAddress) {
        Id = id;
        ApplicationId = applicationId;
        ProcedureName = procedureName;
        ControllerName = controllerName;
        FormName = formName;
        UserName = userName;
        DateTimeMade = dateTimeMade;
        MethodName = methodName;
        ErrorMessage = errorMessage;
        IpAddress = ipAddress;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(String applicationId) {
        ApplicationId = applicationId;
    }

    public String getProcedureName() {
        return ProcedureName;
    }

    public void setProcedureName(String procedureName) {
        ProcedureName = procedureName;
    }

    public String getControllerName() {
        return ControllerName;
    }

    public void setControllerName(String controllerName) {
        ControllerName = controllerName;
    }

    public String getFormName() {
        return FormName;
    }

    public void setFormName(String formName) {
        FormName = formName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public LocalDateTime getDateTimeMade() {
        return DateTimeMade;
    }

    public void setDateTimeMade(LocalDateTime dateTimeMade) {
        DateTimeMade = dateTimeMade;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String methodName) {
        MethodName = methodName;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }
}
