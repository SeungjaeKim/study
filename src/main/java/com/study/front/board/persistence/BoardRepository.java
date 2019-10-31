package com.study.front.board.persistence;
//package com.study.persistence;
//
//import com.study.domain.Board;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.Collection;
//import java.util.List;
//
//public interface BoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board> {
//
//    public List<Board> findBoardByTitle(String title);
//
//    public Collection<Board> findByWriter(String writer);
//
//    public Collection<Board> findByWriterContaining(String writer);
//
//    public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
//
//    public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
//
//    public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
//
//    @Query("SELECT b FROM Board b WHERE b.title Like %?1% AND b.bno > 0 ORDER BY b.bno DESC")
//    public List<Board> findByTitle(String title);
//}
