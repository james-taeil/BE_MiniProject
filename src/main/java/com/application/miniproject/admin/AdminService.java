package com.application.miniproject.admin;

import com.application.miniproject.admin.dto.AdminResponse;
import com.application.miniproject.event.Event;
import com.application.miniproject.event.dto.EventResponse;
import com.application.miniproject.user.User;
import com.application.miniproject.event.type.OrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public List<AdminResponse.EventRequestListDTO> getEventRequestList() {
        List<Event> events = adminRepository.findAllEvents();

        return events.stream()
                .map(AdminResponse.EventRequestListDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void approveEventRequest(Long eventId) {
        adminRepository.updateEventStatusById(eventId, OrderState.APPROVED);
    }

    @Transactional
    public void rejectEventRequest(Long eventId) {
        adminRepository.updateEventStatusById(eventId, OrderState.REJECTED);
    }

    @Transactional
    public List<User> getAllUsers() {
        return adminRepository.findAllUsers();
    }

    @Transactional
    public User getUserDetails(Long userId) {
        return adminRepository.findUserById(userId);
    }
}
