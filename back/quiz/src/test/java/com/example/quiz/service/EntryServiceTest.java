package com.example.quiz.service;

import com.example.quiz.domain.entry.Entry;
import com.example.quiz.domain.room.Room;
import com.example.quiz.domain.user.User;
import com.example.quiz.dto.Entry.request.EntryCreateRequest;
import com.example.quiz.repository.entry.EntryRepository;
import com.example.quiz.repository.room.RoomRepository;
import com.example.quiz.repository.user.UserRepository;
import com.example.quiz.service.entry.EntryService;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@NoArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntryServiceTest {
    @Autowired
    private EntryService entryService;

    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    private Room saveTestRoom;
    private User saveTestUserA;
    private User saveTestUserB;

    @BeforeAll
    void init(){
        saveTestRoom=roomRepository.save(Room.builder().
                roomName("test")
                .hostNo(1)
                .capacity(10)
                .regTime(LocalDateTime.now())
                .build());
        saveTestUserA=userRepository.save(User.builder()
                .userId("testA")
                .userPw("testA")
                .regTime(LocalDateTime.now())
                .build());
        saveTestUserB=userRepository.save(User.builder()
                .userId("testB")
                .userPw("testB")
                .regTime(LocalDateTime.now())
                .build());
    }

    @AfterEach
    void clear(){
        entryRepository.deleteAll();
    }

    @AfterAll
    void cleanAll(){
        roomRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("방인원 생성이 정상 작동한다")
    void createEntryTest(){
        //given
        EntryCreateRequest request=EntryCreateRequest.builder().roomNo(saveTestRoom.getRoomNo()).userNo(saveTestUserA.getUserNo()).build();

        //when
        entryService.createEntry(request);

        //then
        List<Entry> results=entryRepository.findAll();
        assertThat(results).hasSize(1);
        assertThat(results.get(0).getUser().getUserNo()).isEqualTo(saveTestUserA.getUserNo());
        assertThat(results.get(0).getRoom().getRoomNo()).isEqualTo(saveTestRoom.getRoomNo());
    }
    @Test
    @DisplayName("방안에 있는 유저들의 정보를 조회한다.")
    void getAllUsersInRoomTest(){
        //given
        entryRepository.save(Entry.builder()
                .user(User.builder().userNo(saveTestUserA.getUserNo()).build())
                .room(Room.builder().roomNo(saveTestRoom.getRoomNo()).build())
                .score(0)
                .regTime(LocalDateTime.now())
                .build());
        entryRepository.save(Entry.builder()
                .user(User.builder().userNo(saveTestUserB.getUserNo()).build())
                .room(Room.builder().roomNo(saveTestRoom.getRoomNo()).build())
                .score(0)
                .regTime(LocalDateTime.now())
                .build());

        //when
        List<Entry> results=entryService.findEntryByRoomNo(saveTestRoom.getRoomNo());

        //then
        assertThat(results).hasSize(2);
        assertThat(results)
                .extracting("user")
                .extracting("userNo")
                .containsExactlyInAnyOrder(saveTestUserA.getUserNo(),saveTestUserB.getUserNo());
    }

    @Test
    @DisplayName("방안의 회원수를 반환한다.")
    void countEntryByRoomTest(){
        //given
        entryRepository.save(Entry.builder()
                .user(User.builder().userNo(saveTestUserA.getUserNo()).build())
                .room(Room.builder().roomNo(saveTestRoom.getRoomNo()).build())
                .score(0)
                .regTime(LocalDateTime.now())
                .build());
        entryRepository.save(Entry.builder()
                .user(User.builder().userNo(saveTestUserB.getUserNo()).build())
                .room(Room.builder().roomNo(saveTestRoom.getRoomNo()).build())
                .score(0)
                .regTime(LocalDateTime.now())
                .build());

        //when
        long result=entryService.countEntryByRoom(saveTestRoom.getRoomNo());

        //then
        assertThat(result).isEqualTo(2);
    }
}
