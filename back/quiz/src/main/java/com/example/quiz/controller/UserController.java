package com.example.quiz.controller;

import com.example.quiz.dto.User;
import com.example.quiz.service.RoomEntryService;
import com.example.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    @Resource(name = "userService")
    private final UserService userService;

    @Resource(name ="roomEntryService")
    private final RoomEntryService roomEntryService;

    @PostMapping()
    private User insert(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping()
    private List<User> getUserListByRoom(long roomNo){
        return roomEntryService.findAllByRoomNo(roomNo);
    }

    @PutMapping()
    private User update(@RequestBody User user){
        return userService.updateUser(user);
    }
    @DeleteMapping()
    private void delete(long userNo){
        userService.deleteUser(userNo);
    }
}
