package org.example.repository.interfaces;

import org.example.models.enums.Status;

public interface StatisticsRepository {

   int findTasksCountByStatus(Status status);

   int findTasksCountByToday();

   int findTasksCountByWeek();

   int findAssignedTasksCount(Long managerId);

   int findTokenUsesCountByRequest();

}
