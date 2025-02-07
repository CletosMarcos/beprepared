package com.cmb.beprepared.service.impl;

import com.cmb.beprepared.dto.response.StatsResponse;
import com.cmb.beprepared.exception.BadRequestException;
import com.cmb.beprepared.exception.EntityNotFoundException;
import com.cmb.beprepared.model.User;
import com.cmb.beprepared.repository.AlertRepository;
import com.cmb.beprepared.repository.CitizenRepository;
import com.cmb.beprepared.repository.UserRepository;
import com.cmb.beprepared.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AlertRepository alertRepository;
    private final CitizenRepository citizenRepository;
    @Override
    public String createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("e-mail is owned by another user, try new one");
        }
        userRepository.save(user);
        return "User Created Successfully";
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found!"));
    }

    @Override
    public StatsResponse getAllStats() {
        return StatsResponse.builder()
                .citizens(citizenRepository.count())
                .totalAlerts(alertRepository.count())
                .activeAlerts(alertRepository.countByActive(true))
                .build();
    }
}
