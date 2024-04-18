package com.film.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.Badges;
import com.film.repository.BadgesRepository;

@Service
public class BadgesServiceIplm implements BadgesService{
	@Autowired
	private BadgesRepository badgesRepository;
	
	@Override
	public List<Badges> getAll() {
		try {
			return this.badgesRepository.findAll();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Boolean create(Badges badges) {
		try {
			this.badgesRepository.save(badges);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		try {
			this.badgesRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<String> findImageById(String id) {
		try {
			if(id!=null) {
				List<Integer> idList = Arrays.asList(id.split(",")).stream()
	                    .map(String::trim)
	                    .map(Integer::parseInt)
	                    .collect(Collectors.toList());
				return this.badgesRepository.findImageById(idList);
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
