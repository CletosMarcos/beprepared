package com.cmb.beprepared.service;

import com.cmb.beprepared.dto.response.StatsResponse;
import com.cmb.beprepared.model.User;

public interface UserService {

    String createUser(User user);

    User getUserById(Long id);

    StatsResponse getAllStats();
}
