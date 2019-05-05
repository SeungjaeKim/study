//package com.study;
//
//import com.study.domain.Board;
//import com.study.persistence.BoardRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by seungjae 2018-07-25
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BoardRepositoryTests {
//
//    @Autowired
//    private BoardRepository boardRepo;
//
//    @Test
//    public void testInsert(){
//        Board board = new Board();
//        board.setTitle("게시물의 제목");
//        board.setContent("게시물 내용 넣기.....");
//        board.setWriter("user00");
//
//        boardRepo.save(board);
//    }
//
//    @Test
//    public void testInsert200(){
//        for (int i = 0; i <= 200; i++){
//            Board board = new Board();
//            board.setTitle("제목.."+ i);
//            board.setContent("내용 ...." + i + " 채우기");
//            board.setWriter("user0" + (i % 10));
//            boardRepo.save(board);
//        }
//    }
//
//    @Test
//    public void testByTitle() {
//
//        //before Java 8
//        /*List<Board> list = boardRepo.findBoardByTitle("제목..177");
//
//        for(int i=0, len = list.size(); i < len; i++) {
//            System.out.println(list.get(i));
//        }*/
//        // Java 8
//        boardRepo.findBoardByTitle("제목..177").forEach(board -> System.out.println(board));
//    }
//
//    @Test
//    public void testByWriter(){
//
//        Collection<Board> results = boardRepo.findByWriter("user00");
//
//        results.forEach(
//                board -> System.out.println(board)
//        );
//    }
//
//    @Test
//    public void testByWriterContaining(){
//
//        Collection<Board> results = boardRepo.findByWriterContaining("05");
//
//        results.forEach(
//                board -> System.out.println(board)
//        );
//    }
//
//    @Test
//    public void testByTitleAndBno(){
//
//        Collection<Board> results = boardRepo.findByTitleContainingAndBnoGreaterThan("5", 50L);
//
//        results.forEach(board -> System.out.println("board = " + board));
//    }
//
//    @Test
//    public void testBnoOrderBy(){
//
//        Collection<Board> results = boardRepo.findByBnoGreaterThanOrderByBnoDesc(90L);
//
//        results.forEach(board -> System.out.println(board));
//    }
//
//    @Test
//    public void testByTitle2(){
//        boardRepo.findByTitle("17").forEach(board -> System.out.println("board = " + board));
//    }
//
//
//
//}
