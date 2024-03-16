package com.film.services;

import java.util.List;

import com.film.models.HistoryWatch;

public interface HistoryWatchService {
	HistoryWatch findByUser_watchAndSlug(Long userId, String slug);
	List<HistoryWatch> findByUser_watch(Long userId);
	Boolean update(HistoryWatch historyWatch);
	Boolean delete(Integer id);
}
