package com.film.services;

import org.springframework.data.domain.Page;

import com.film.models.HistoryWatch;

public interface HistoryWatchService {
	HistoryWatch findByUser_watchAndSlug(Long userId, String slug);
	
	Boolean update(HistoryWatch historyWatch);

	Boolean delete(Integer id);
	
	Page<HistoryWatch> getPaginatedRecordsForHistory(int pageNumber, int pageSize, int id);
}
