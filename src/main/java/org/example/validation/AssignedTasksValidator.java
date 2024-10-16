package org.example.validation;

public class AssignedTasksValidator {

    public boolean isValidTokenRequest(Long taskId, Long userId) {
        return taskId != null && userId != null && taskId > 0 && userId > 0 ;
    }
}
