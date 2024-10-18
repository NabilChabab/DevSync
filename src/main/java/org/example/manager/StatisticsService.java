package org.example.manager;

import org.example.models.enums.Status;
import org.example.repository.interfaces.StatisticsRepository;

public class StatisticsService {

    private StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    public int findTasksCountByStatus(String status) {
        return statisticsRepository.findTasksCountByStatus(Status.valueOf(status));
    }

    public int findTasksCountByToday() {
        return statisticsRepository.findTasksCountByToday();
    }

    public int findTasksCountByWeek() {
        return statisticsRepository.findTasksCountByWeek();
    }

    public int findAssignedTasksCount(Long managerId) {
        return statisticsRepository.findAssignedTasksCount(managerId);
    }

    public int findTokenUsesCountByRequest() {
        return statisticsRepository.findTokenUsesCountByRequest();
    }


}
