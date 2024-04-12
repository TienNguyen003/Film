package com.film.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.models.UserModel;
import com.film.repository.UserRepository;

@Service
public class UserServiceIplm implements UserService {

	@Autowired
	private UserRepository userRP;
	
	@Override
	public UserModel findByUserName(String userName) {
		return userRP.findByUserName(userName);
	}

	@Override
	public Boolean save(UserModel userModel) {
		try {
			this.userRP.save(userModel);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int findByUser(String name) {
		return this.userRP.findByUser(name);
	}

	@Override
	public int findByEmail(String email) {
		return this.userRP.findByEmail(email);
	}

	@Override
	public Boolean updateFullName(String name, int point, int id) {
		try {
			this.userRP.updateFullName(name, point, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Object[][] queryByPoint(int id) {		
		return this.userRP.queryByPoint(id);
	}

	@Override
	public Boolean updateMaxim(String maxim, int id) {
		try {
			this.userRP.updateMaxim(maxim, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateIsActivity(int isActivity, String lastActive, int id) {
		try {
			this.userRP.updateIsActivity(isActivity, lastActive, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean updateAvatar(String img, int id) {
		try {
			this.userRP.updateAvatar(img, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int findByEmailRtId(String email) {
		return this.userRP.findByEmailRtId(email);
	}

	@Override
	public Object[][] queryAttendanceById(int id) {
		try {
			return this.userRP.queryAttendanceById(id);			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public Boolean updateIsAttendance(String attendance_day, String attendance, int point, int id) {
		try {
			this.userRP.updateIsAttendance(attendance_day, attendance, point, id);
			return true;
		} catch (Exception e) {
			System.out.println("loi roi");
		}
		return false;
	}

	@Override
	public String findBadgesById(int id) {
		try {
			return this.userRP.findBadgesById(id);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Object[][] queryPointCrytalById(int id) {
		return this.userRP.queryPointCrytalById(id);
	}

	@Override
	public Boolean updateBadgesById(String img, int id, int point, int crytal) {
		try {
			this.userRP.updateBadgesById(img, id, point, crytal);
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
}
