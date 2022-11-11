package com.example.quiz.service;

import com.example.quiz.domain.room.Room;
import com.example.quiz.dto.room.request.RoomCreateRequest;
import com.example.quiz.repository.room.RoomRepository;
import com.example.quiz.repository.user.UserRepository;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@NoArgsConstructor
public class RoomServiceTest {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;

    @AfterEach
    public void clear(){roomRepository.deleteAll();}

    @Test
    @DisplayName("방 생성이 정상 작동한다.")
    void createRoomTest(){
        //given
        RoomCreateRequest request=RoomCreateRequest.builder().roomName("test").capacity(10).hostNo(1).build();

        //when
        roomService.createRoom(request);

        //then
        List<Room> results=roomRepository.findAll();
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getRoomName()).isEqualTo("test");
        assertThat(results.get(0).getCapacity()).isEqualTo(10);
        assertThat(results.get(0).getHostNo()).isEqualTo(1);
    }

}
