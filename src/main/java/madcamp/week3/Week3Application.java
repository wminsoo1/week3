package madcamp.week3;

import lombok.AllArgsConstructor;
import madcamp.week3.model.*;
import madcamp.week3.repository.*;
import madcamp.week3.service.EvaluationService;
import madcamp.week3.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class Week3Application {

	private UserRepository userRepository;
	private IdeaRepository ideaRepository;
	private PostRepository postRepository;
	private CommentRepository commentRepository;
	private ProjectRepository projectRepository;
	private EvaluationRepository evaluationRepository;
	private UserService userService;
	private EvaluationService evaluationService;

	public static void main(String[] args) {
		SpringApplication.run(Week3Application.class, args);

	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository) {
		return args -> {

			User user1 = new User();
			user1.setPassword("1111");
			user1.setUserName("user1");
			user1.setEducation("컴퓨터 공학");
			user1.setStackList(Arrays.asList(Stack.JAVA, Stack.PYTHON));
			user1.setAward1("최고 코더 상");
			user1.setAward2("혁신상");
			user1.setOneLineProfile("새로운 기술에 관심 있는 열정적인 개발자");
			user1.setGithubUrl("https://github.com/사용자1");
			userRepository.save(user1);
			// 더미 사용자 2
			User user2 = new User();
			user2.setPassword("2222");
			user2.setUserName("user2");
			user2.setEducation("전기 공학");
			user2.setStackList(Arrays.asList(Stack.REACT));
			user2.setAward1("우수한 연구상");
			user2.setAward2("리더십상");
			user2.setOneLineProfile("혁신적인 응용프로그램 개발에 열정적");
			user2.setGithubUrl("https://github.com/사용자2");
			userRepository.save(user2);

			// 더미 사용자 3
			User user3 = new User();
			user3.setPassword("3333");
			user3.setUserName("user3");
			user3.setEducation("소프트웨어 공학");
			user3.setStackList(Arrays.asList(Stack.PYTHON, Stack.FLUTTER));
			user3.setAward1("창의적인 개발자 상");
			user3.setOneLineProfile("다양한 분야에 관심 있는 소프트웨어 엔지니어");
			user3.setGithubUrl("https://github.com/사용자3");
			userRepository.save(user3);

			// 더미 사용자 4
			User user4 = new User();
			user4.setPassword("4444");
			user4.setUserName("user4");
			user4.setEducation("정보 보안");
			user4.setStackList(Arrays.asList(Stack.JAVA, Stack.PYTHON));
			user4.setAward1("보안 전문가 상");
			user4.setAward2("우수한 보안 프로젝트");
			user4.setOneLineProfile("정보 보안에 관심 있는 프로페셔널");
			user4.setGithubUrl("https://github.com/사용자4");
			userRepository.save(user4);

			// 더미 사용자 5
			User user5 = new User();
			user5.setPassword("5555");
			user5.setUserName("user5");
			user5.setEducation("데이터 과학");
			user5.setStackList(Arrays.asList(Stack.PYTHON, Stack.R, Stack.SQL));
			user5.setAward1("데이터 분석가 상");
			user5.setOneLineProfile("데이터 과학과 머신러닝에 열정적인 개발자");
			user5.setGithubUrl("https://github.com/사용자5");
			userRepository.save(user5);

			// 더미 사용자 6
			User user6 = new User();
			user6.setPassword("6666");
			user6.setUserName("user6");
			user6.setEducation("산업 공학");
			user6.setStackList(Arrays.asList(Stack.JAVA, Stack.REACT));
			user6.setAward1("산업 공학자 상");
			user6.setOneLineProfile("효율적인 생산 과정에 기여하는 엔지니어");
			user6.setGithubUrl("https://github.com/사용자6");
			userRepository.save(user6);

			// 더미 사용자 7
			User user7 = new User();
			user7.setPassword("7777");
			user7.setUserName("user7");
			user7.setEducation("소프트웨어 공학");
			user7.setStackList(Arrays.asList(Stack.JAVA, Stack.PYTHON, Stack.REACT));
			user7.setAward1("소프트웨어 개발자 상");
			user7.setOneLineProfile("다양한 플랫폼에 개발된 소프트웨어 엔지니어");
			user7.setGithubUrl("https://github.com/사용자7");
			userRepository.save(user7);

			// 더미 사용자 8
			User user8 = new User();
			user8.setPassword("8888");
			user8.setUserName("user8");
			user8.setEducation("전자공학");
			user8.setStackList(Arrays.asList(Stack.REACT, Stack.FLUTTER));
			user8.setAward1("전자공학자 상");
			user8.setOneLineProfile("다양한 전자기기에 기여하는 전자공학자");
			user8.setGithubUrl("https://github.com/사용자8");
			userRepository.save(user8);

			// 더미 사용자 9
			User user9 = new User();
			user9.setPassword("9999");
			user9.setUserName("user9");
			user9.setEducation("컴퓨터 공학");
			user9.setStackList(Arrays.asList(Stack.JAVA, Stack.PYTHON, Stack.FLUTTER));
			user9.setAward1("다국적 프로젝트 상");
			user9.setOneLineProfile("다국적 프로젝트에서 경험 많은 소프트웨어 엔지니어");
			user9.setGithubUrl("https://github.com/사용자9");
			userRepository.save(user9);

			// 더미 사용자 10
			User user10 = new User();
			user10.setPassword("0000");
			user10.setUserName("user10");
			user10.setEducation("데이터 과학");
			user10.setStackList(Arrays.asList(Stack.PYTHON, Stack.R, Stack.SQL));
			user10.setAward1("빅데이터 분석가 상");
			user10.setOneLineProfile("빅데이터 분석과 예측에 관심 있는 데이터 과학자");
			user10.setGithubUrl("https://github.com/사용자10");
			userRepository.save(user10);


			// user1의 아이디어
			Idea idea1 = new Idea("스마트 홈 관리 앱", "사용자의 스마트 기기를 한 곳에서 편리하게 관리하는 애플리케이션", user1, Arrays.asList(user2, user3, user4));
			ideaRepository.save(idea1);

			// user2의 아이디어
			Idea idea2 = new Idea("친환경 쓰레기 수거로봇", "도시의 길거리에서 친환경적으로 쓰레기를 수거하는 로봇", user2, Arrays.asList(user1, user5, user6, user7, user8));
			ideaRepository.save(idea2);

			// user3의 아이디어
			Idea idea3 = new Idea("가상 현실 학습 플랫폼", "학생들에게 실제로 체험해보지 못한 경험을 제공하는 VR 학습 애플리케이션", user3, Arrays.asList(user9, user10));
			ideaRepository.save(idea3);

			// user4의 아이디어
			Idea idea4 = new Idea("음성 기반 일정 관리", "음성 명령으로 일정을 추가하고 관리할 수 있는 스마트 일정 애플리케이션", user4);
			ideaRepository.save(idea4);

			// user5의 아이디어
			Idea idea5 = new Idea("지능형 건강 모니터링 시스템", "건강 데이터를 실시간으로 모니터링하고 사용자에게 피드백을 제공하는 시스템", user5);
			ideaRepository.save(idea5);

			// user6의 아이디어
			Idea idea6 = new Idea("스마트 농업 솔루션", "센서 및 IoT 기술을 활용하여 농업 생산성을 향상시키는 솔루션", user6);
			ideaRepository.save(idea6);

			// user7의 아이디어
			Idea idea7 = new Idea("언어 학습 플랫폼", "다국어 학습을 위한 효율적이고 흥미로운 온라인 학습 플랫폼", user7);
			ideaRepository.save(idea7);

			// user8의 아이디어
			Idea idea8 = new Idea("환경 친화적인 교통 애플리케이션", "친환경 교통 수단을 촉진하고 지속 가능한 교통 방법을 장려하는 애플리케이션", user8);
			ideaRepository.save(idea8);

			// user9의 아이디어
			Idea idea9 = new Idea("사회 참여 플랫폼", "지역 사회 참여를 촉진하고 사람들 간의 소통을 도울 수 있는 플랫폼", user9);
			ideaRepository.save(idea9);

			// user10의 아이디어
			Idea idea10 = new Idea("협업을 위한 프로젝트 관리 도구", "팀원 간의 협업을 용이하게 하는 프로젝트 관리 및 커뮤니케이션 도구", user10);
				ideaRepository.save(idea10);


			Project project1 = new Project("AI 기반 음성인식 프로젝트", "2024-01-15", "음성인식 기술을 활용한 인공지능 프로젝트", 5);
			projectRepository.save(project1);

			Project project2 = new Project("빅데이터 분석 플랫폼 구축", "2024-01-16", "대용량 데이터를 분석하기 위한 플랫폼 개발 프로젝트", 7);
			projectRepository.save(project2);
			Project project3 = new Project("자율 주행 자동차 시스템", "2024-01-17", "자동차의 주행을 자율적으로 제어하는 시스템 구현 프로젝트", 4);
			projectRepository.save(project3);
			Project project4 = new Project("온라인 쇼핑 플랫폼 개발", "2024-01-18", "다양한 상품을 판매하는 온라인 쇼핑 플랫폼 구현 프로젝트", 6);
			projectRepository.save(project4);
			Project project5 = new Project("스마트 건축 시스템", "2024-01-19", "건물의 에너지 효율을 높이는 스마트 건축 시스템 개발 프로젝트", 3);
			projectRepository.save(project5);
			Project project6 = new Project("인공지능 기반 의료 진단", "2024-01-20", "의료 진단을 위한 인공지능 기술 개발 프로젝트", 5);
			projectRepository.save(project6);
			Project project7 = new Project("사물인터넷 기반 스마트 홈", "2024-01-21", "사물인터넷을 활용한 스마트 홈 시스템 구현 프로젝트", 4);
			projectRepository.save(project7);
			Project project8 = new Project("블록체인 기술 연구", "2024-01-22", "블록체인 기술의 원리와 적용 가능성을 연구하는 프로젝트", 6);
			projectRepository.save(project8);
			Project project9 = new Project("양자 컴퓨팅 알고리즘 개발", "2024-01-23", "양자 컴퓨팅을 위한 새로운 알고리즘 개발 프로젝트", 3);
			projectRepository.save(project9);
			Project project10 = new Project("스마트 헬스케어 시스템", "2024-01-24", "건강 데이터를 관리하고 모니터링하는 스마트 헬스케어 시스템 프로젝트", 5);
			projectRepository.save(project10);

			user1.setVotedIdea(idea2);
			user2.setVotedIdea(idea2);
			user3.setVotedIdea(idea2);
			user4.setVotedIdea(idea2);
			user5.setVotedIdea(idea2);
			user6.setVotedIdea(idea5);
			user7.setVotedIdea(idea5);
			user8.setVotedIdea(idea5);
			user9.setVotedIdea(idea8);
			user10.setVotedIdea(idea8);
			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);
			userRepository.save(user4);
			userRepository.save(user5);
			userRepository.save(user6);
			userRepository.save(user7);
			userRepository.save(user8);
			userRepository.save(user9);
			userRepository.save(user10);


			// user1의 포스트
			Post post1 = new Post("스마트 홈 앱 개발 중", "스마트 홈 관리 앱을 개발하고 있습니다. 사용자들의 의견을 기다립니다.", project1, user1, null);
			postRepository.save(post1);

			// user2의 포스트
			Post post2 = new Post("친환경 로봇 프로젝트 소식", "친환경 쓰레기 수거로봇 프로젝트에 참여하고 있습니다. 현재 진행 상황을 공유합니다.", project2, user2, null);
			postRepository.save(post2);

			// user3의 포스트
			Post post3 = new Post("VR 학습 플랫폼 공개 테스트", "가상 현실 학습 플랫폼의 공개 테스트를 시작했습니다. 피드백을 기다립니다.", project3, user3, null);
			postRepository.save(post3);

			// user4의 포스트
			Post post4 = new Post("음성 명령 일정 앱 출시 예정", "음성 명령으로 일정을 추가하고 관리할 수 있는 스마트 일정 애플리케이션을 개발 중입니다.", project4, user4, null);
			postRepository.save(post4);

			// user5의 포스트
			Post post5 = new Post("건강 데이터 모니터링 시작", "지능형 건강 모니터링 시스템을 통해 건강 데이터를 실시간으로 모니터링하고 있습니다.", project5, user5, null);
			postRepository.save(post5);

			// user6의 포스트
			Post post6 = new Post("스마트 농업 솔루션 개발 중", "센서 및 IoT 기술을 활용하여 농업 생산성을 향상시키는 솔루션을 개발 중입니다.", project6, user6, null);
			postRepository.save(post6);

			// user7의 포스트
			Post post7 = new Post("다국어 학습 플랫폼에 참여하고 싶어요", "언어 학습 플랫폼 프로젝트에 참여하고자 합니다. 연락 부탁드립니다.", project7, user7, null);
			postRepository.save(post7);

			// user8의 포스트
			Post post8 = new Post("환경 친화적 교통 앱 기획", "환경 친화적인 교통 애플리케이션을 기획 중입니다. 의견 공유 부탁드립니다.", project8, user8, null);
			postRepository.save(post8);

			// user9의 포스트
			Post post9 = new Post("지역 사회 참여 플랫폼 의견 나누기", "지역 사회 참여 플랫폼에 대한 의견을 공유하고 싶습니다. 함께 이야기 나누어요.", project9, user9, null);
			postRepository.save(post9);

			// user10의 포스트
			Post post10 = new Post("협업 프로젝트 관리 도구 의견 모집", "협업을 위한 프로젝트 관리 도구에 대한 의견을 주고 싶습니다. 함께 토론해봐요.", project10, user10, null);
			postRepository.save(post10);

			// user1의 댓글
			Comment comment1 = new Comment("스마트 홈 앱에 대한 의견을 남깁니다. 편리한 기능이 많이 추가되면 좋겠어요.", user3, post1);
			commentRepository.save(comment1);

			Comment reply1_1 = new Comment("저도 스마트 홈에 대한 의견을 남기고 싶어요. 어떤 기능이 더 필요할까요?", user2, post1);
			commentRepository.save(reply1_1);

			Comment reply1_2 = new Comment("스마트 홈 앱에서는 에너지 효율을 개선하는 기능이 있으면 좋겠어요.", user4, post1);
			commentRepository.save(reply1_2);

			Comment reply1_3 = new Comment("전등, 에어컨 등을 목소리로 제어하는 기능이 있으면 편리할 것 같아요.", user6, post1);
			commentRepository.save(reply1_3);

			Comment reply1_4 = new Comment("스마트 홈에 관심이 많아 참여하고 싶어졌어요. 어떻게 참여할 수 있을까요?", user8, post1);
			commentRepository.save(reply1_4);

			Comment reply1_5 = new Comment("스마트 홈 앱 개발에 도움이 필요하면 연락주세요. 함께 진행해보아요.", user10, post1);
			commentRepository.save(reply1_5);

			// user2의 댓글
			Comment comment2 = new Comment("로봇 프로젝트에 참여하고 싶어요. 어떻게 참여할 수 있을까요?", user5, post2);
			commentRepository.save(comment2);

			// user3의 댓글
			Comment comment3 = new Comment("VR 학습 플랫폼 테스트에 참여하고 싶습니다. 어떻게 참여할 수 있을까요?", user1, post3);
			commentRepository.save(comment3);

			// user4의 댓글
			Comment comment4 = new Comment("음성 명령 일정 앱에 대한 의견을 공유합니다. 추가 기능이 궁금해요.", user7, post4);
			commentRepository.save(comment4);

			// user5의 댓글
			Comment comment5 = new Comment("건강 데이터 관리에 대한 의견을 나누고 싶어요. 어떻게 연락하면 될까요?", user2, post5);
			commentRepository.save(comment5);

			// user6의 댓글
			Comment comment6 = new Comment("스마트 농업 솔루션 개발에 흥미 있습니다. 협력하고 싶어요.", user8, post6);
			commentRepository.save(comment6);

			// user7의 댓글
			Comment comment7 = new Comment("다국어 학습 플랫폼에 대한 의견을 나누고 싶어요. 어떻게 연락하면 될까요?", user4, post7);
			commentRepository.save(comment7);

			// user8의 댓글
			Comment comment8 = new Comment("환경 친화적 교통 앱 기획에 도움이 필요하면 말씀해주세요. 함께 고민해봐요.", user6, post8);
			commentRepository.save(comment8);

			// user9의 댓글
			Comment comment9 = new Comment("지역 사회 참여 플랫폼에 대한 의견을 나누고 싶습니다. 어떻게 참여할 수 있을까요?", user10, post9);
			commentRepository.save(comment9);

			// user10의 댓글
			Comment comment10 = new Comment("협업 프로젝트 관리 도구에 대한 의견을 주고 싶어요. 어떻게 연락하면 될까요?", user9, post10);
			commentRepository.save(comment10);

			Evaluation evaluation1 = new Evaluation(user1, user2, 4);
			evaluationRepository.save(evaluation1);
			userService.updateScore(user2.getId(), 4);

			Evaluation evaluation2 = new Evaluation(user1, user3, 5);
			evaluationRepository.save(evaluation2);
			userService.updateScore(user3.getId(), 5);

			Evaluation evaluation3 = new Evaluation(user2, user1, 3);
			evaluationRepository.save(evaluation3);
			userService.updateScore(user1.getId(), 3);

			Evaluation evaluation4 = new Evaluation(user3, user1, 4);
			evaluationRepository.save(evaluation4);
			userService.updateScore(user1.getId(), 4);

			Evaluation evaluation5 = new Evaluation(user4, user7, 5);
			evaluationRepository.save(evaluation5);
			userService.updateScore(user7.getId(), 5);

			Evaluation evaluation6 = new Evaluation(user5, user2, 4);
			evaluationRepository.save(evaluation6);
			userService.updateScore(user2.getId(), 4);

			Evaluation evaluation7 = new Evaluation(user6, user8, 3);
			evaluationRepository.save(evaluation7);
			userService.updateScore(user8.getId(), 3);

			Evaluation evaluation8 = new Evaluation(user7, user4, 5);
			evaluationRepository.save(evaluation8);
			userService.updateScore(user4.getId(), 5);

			Evaluation evaluation9 = new Evaluation(user8, user6, 4);
			evaluationRepository.save(evaluation9);
			userService.updateScore(user6.getId(), 4);

			Evaluation evaluation10 = new Evaluation(user9, user10, 5);
			evaluationRepository.save(evaluation10);
			userService.updateScore(user10.getId(), 5);

		};
	}
}
