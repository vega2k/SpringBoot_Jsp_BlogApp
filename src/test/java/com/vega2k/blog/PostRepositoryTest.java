package com.vega2k.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.vega2k.blog.domain.model.entity.Post;
import com.vega2k.blog.infrastructure.dao.PostRepository;

@RunWith(SpringRunner.class)
//Mysql
@SpringBootTest
//H2 
//@DataJpaTest
public class PostRepositoryTest {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PostRepository postRepository;
	
	@Test
	public void di() throws SQLException {
		try(Connection connection = dataSource.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println(metaData.getURL());
			System.out.println(metaData.getDriverName());
			System.out.println(metaData.getUserName());
		}
	}
	
	@Test
	public void save() {
		Post post = new Post();
		post.setId(1);
		post.setSubject("나이아가라 폭포1");
		post.setContent("얼마전 나이아가라와 토론토를 다녀왔습니다. 나이아가라 폭포는 미국쪽보다 캐나다 쪽에서 보는게 더 멋있다고해서 캐나다 쪽으로 가게되었고 나이아가라에서 그렇게 멀지않은  토론토도 가기로 결정을해서 나이아가라에서 1박 토론토에서 2박 총 3박 4일로 다녀왔습니다.\r\n" + 
				"\r\n" + 
				"토론토 피어슨 국제공항(Toronto Pearson International Airport)에서 대중 교통으로 나이아가로 가려면 여러번 갈아타야하고 택시는 너무 비싸서 Niagara Airbus 를 이용했습니다. 공항 <-> 호텔 왕복 $162(캐나다 달러) 정도로 싼건 아니지만 다른방법보다는 좀 편한 이동방법이었습니다. 인터넷 예약은 이곳 에서 하시고. 이용 방법은 이곳에 설명되어있습니다.");
		post.setRegDate(LocalDateTime.now());
		
		Post newPost = postRepository.save(post);
		System.out.println(newPost);
		
		assertThat(newPost).isNotNull();
		
		Post existPost = postRepository.findBySubject(newPost.getSubject());		
		assertThat(existPost).isNotNull();

	}
}
