package pl.unlimited.windows.serwer.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.unlimited.windows.serwer.model.SystemUser;
import pl.unlimited.windows.serwer.model.WindowSize;
import pl.unlimited.windows.serwer.model.dto.WindowSizeDto;
import pl.unlimited.windows.serwer.repository.SystemUserRepository;
import pl.unlimited.windows.serwer.repository.WindowSizeRepository;
import pl.unlimited.windows.serwer.security.service.MyUserDetailsService;
import pl.unlimited.windows.serwer.security.util.JwtUtil;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WindowSizeItTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String PATH = "http://localhost:%d/api/windows/sizes";

    private final String AUTH_PATH = "http://localhost:%d/api/authentication";

    private String url;

    @Autowired
    private WindowSizeRepository windowSizeRepository;
    @Autowired
    private MyUserDetailsService userService;
    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Before
    public void setup() {
        url = String.format(PATH, port);
        var windowSize = new WindowSize();
        windowSize.setWidth(1.1);
        windowSize.setHeight(2.2);

        windowSizeRepository.save(windowSize);

        windowSize = new WindowSize();
        windowSize.setWidth(4.4);
        windowSize.setHeight(6.6);

        windowSizeRepository.save(windowSize);
    }

    @After
    public void tearDown() {
        windowSizeRepository.deleteAllInBatch();
        systemUserRepository.deleteAllInBatch();
    }

    @Test
    public void getAll_should_return_status_ok() {
        //given
        var size = this.windowSizeRepository.findAll().size();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getTwtToken());

        //when
        var result = testRestTemplate.exchange(String.format(PATH, port), HttpMethod.GET ,new HttpEntity<>(null, headers) , WindowSizeDto[].class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isEqualTo(size);
    }

    @Test
    public void getById_should_return_status_ok() {
        //given
        var listWindowsSize = this.windowSizeRepository.findAll();
        var fistObj = listWindowsSize.stream().findFirst().get();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getTwtToken());

        //when
        var result = testRestTemplate.exchange(String.format(PATH, port)+"/"+fistObj.getId(), HttpMethod.GET ,new HttpEntity<>(null, headers) , WindowSizeDto.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getWidth()).isEqualTo(fistObj.getWidth());
        assertThat(result.getBody().getHeight()).isEqualTo(fistObj.getHeight());
    }

    @Test
    public void addWindowsSize_should_result_created() {
        //given
        var windowSize = new WindowSize();
        windowSize.setHeight(3.4);
        windowSize.setWidth(4.3);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ getTwtToken());

        HttpEntity<WindowSizeDto> request = new HttpEntity<>(windowSize.toDto(), headers);

        //when
        var result = testRestTemplate.postForEntity(String.format(PATH, port), request, WindowSizeDto.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getHeight()).isEqualTo(3.4);
        assertThat(result.getBody().getWidth()).isEqualTo(4.3);
    }

    private String getTwtToken(){
        url = (String.format(AUTH_PATH, port));
       var newUser = new SystemUser();
       newUser.setLogin("TestUser");
       newUser.setPassword("test123");
       var user = userService.saveCandidate(newUser);

       final UserDetails userDetails = userService.loadUserByUsername(user.getLogin());

       final String jwt = jwtTokenUtil.generateToken(userDetails);

       return jwt;
    }
}
