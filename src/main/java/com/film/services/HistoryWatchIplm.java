package com.film.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.HistoryWatch;
import com.film.repository.HistoryWatchRepository;

@Service
public class HistoryWatchIplm implements HistoryWatchService{
	@Autowired
	private HistoryWatchRepository historyWatchRepository;

	@Override
	public HistoryWatch findByUser_watchAndSlug(Long id, String slug) {
		return this.historyWatchRepository.findByUser_watchAndSlug(slug, id);
	}

	@Override
	public Boolean update(HistoryWatch historyWatch) {
		try {
			this.historyWatchRepository.save(historyWatch);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.historyWatchRepository.deleteById(id);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
