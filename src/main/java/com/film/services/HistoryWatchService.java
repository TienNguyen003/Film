package com.film.services;

import com.film.models.HistoryWatch;

public interface HistoryWatchService {
	HistoryWatch findByUser_watchAndSlug(Long userId, String slug);
	Boolean update(HistoryWatch historyWatch);
	Boolean delete(Integer id);
}
