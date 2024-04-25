package com.film.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.historyWatchRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public Page<HistoryWatch> getPaginatedRecordsForHistory(int pageNumber, int pageSize, int id) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return this.historyWatchRepository.findHistoryByUser(id, pageable);
	}
}
