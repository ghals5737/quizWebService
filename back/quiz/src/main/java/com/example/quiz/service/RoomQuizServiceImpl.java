package com.example.quiz.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("roomQuizService")
public class RoomQuizServiceImpl implements RoomQuizService{
//    @Autowired
//    private  final RoomQuizRepository roomQuizRepository;
//
//    @Override
//    public void createRoomQuiz(RoomQuizNo roomQuizNo) {
//        long roomNo= roomQuizNo.getRoomNo();
//        Room room=Room.builder().roomNo(roomNo).build();
//        int order=1;
//        for(Long el:roomQuizNo.getQuizNoList()) {
//            RoomQuiz roomQuiz = new RoomQuiz();
//            Quiz quiz = new Quiz();
//            quiz.setQuizNo(el);
//            roomQuiz.setRoom(room);
//            roomQuiz.setQuiz(quiz);
//            roomQuiz.setQuizOrder(order++);
//            roomQuizRepository.save(roomQuiz);
//        }
//    }
//
//    @Override
//    public List<Quiz> findAllByRoomNo(long roomNo) {
//        System.out.println("roomNo "+roomNo);
//        Room room=Room.builder().roomNo(roomNo).build();
//        List<RoomQuiz> roomQuizList=roomQuizRepository.findRoomQuizByRoomOrderByQuizOrder(room);
//        List<Quiz>result=new ArrayList<>();
//        roomQuizList.forEach(el->result.add(el.getQuiz()));
//        return result;
//    }
//
//    @Override
//    public void deleteRoomQuizsByQuiz(Long quizNo) {
//        Quiz quiz=Quiz.builder().quizNo(quizNo).build();
//        roomQuizRepository.deleteRoomQuizsByQuiz(quiz);
//    }
//
//    @Override
//    public void deleteRoomQuizsByRoom(Long roomNo) {
//        Room room=Room.builder().roomNo(roomNo).build();
//        roomQuizRepository.deleteRoomQuizsByRoom(room);
//    }
}
