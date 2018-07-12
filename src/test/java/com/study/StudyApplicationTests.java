package com.study;

import com.querydsl.core.BooleanBuilder;
import com.study.domain.Board;
import com.study.domain.QBoard;
import com.study.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyApplicationTests {

	@Autowired
	private BoardRepository repo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testPredicate(){

		String type = "t";
		String keyword = "17";

		BooleanBuilder builder = new BooleanBuilder();

		QBoard board = QBoard.board;

		if(type.equals("t")){
			builder.and(board.title.like("%" + keyword + "%"));
		}

		//bno > 0
		builder.and(board.bno.gt(0L));

		Pageable pageable = PageRequest.of(0, 10);

		Page<Board> result = repo.findAll(builder, pageable);

		System.out.println("PAGE SIZE: " + result.getSize());
		System.out.println("TOTAL PAGES : " + result.getTotalPages());
		System.out.println("TOTAL COUNT : " + result.getTotalElements());
		System.out.println("NEXT: " + result.nextPageable());

		List<Board> list = result.getContent();
		list.forEach(b -> System.out.println(b));

	}

}
