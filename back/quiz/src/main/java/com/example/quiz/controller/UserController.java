package com.example.quiz.controller;

import com.example.quiz.dto.user.request.UserCreateRequest;
import com.example.quiz.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping()
    private void create(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }

//    @Resource(name ="roomEntryService")
//    private final RoomEntryService roomEntryService;
//
//    @PostMapping()
//    private User insert(@RequestBody User user){
//        return userService.createUser(user);
//    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<TokenDto> authorize(@RequestBody User user) {
//       return userService.authenticate(user);
//    }
//
//    @PostMapping("/login")
//    private User login(@RequestBody User user){
//        try {
//            return userService.loginUser(user);
//        }catch (RestException e){
//            throw e;
//        }
//    }
//
//    @GetMapping()
//    private User getUser(Long userNo){
//        return userService.getUser(userNo);
//    }
//
//    @GetMapping("/inside-room")
//    private List<User> getUserListByRoom(long roomNo){
//        return roomEntryService.findAllByRoomNo(roomNo);
//    }
//
//    @PutMapping()
//    private User update(@RequestBody User user){
//        return userService.updateUser(user);
//    }
//
//    @DeleteMapping()
//    private void delete(long userNo){
//        userService.deleteUser(userNo);
//    }
//
//    @PostMapping("/userNames")
//    private List<String> getUserNameByUserNo(@RequestBody List<Long> userNoList){
//        return userService.getUserNameByUserNo(userNoList);
//    }
}
