package cn.com.cslc.aw.core.common.security.sso;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParseUserIdentifierFromTokenServiceImplTest {

	private String tokenStr;
	
	private String userId;
	
	@Autowired
	private ParseUserIdentifierFromTokenService parseUserIdentifierFromTokenService;
	
	@Before
	public void setUp() throws Exception {
		this.tokenStr = "gHGrlFtcP9We7OFkFxtJvzdef97MBvfP12o1t7j2QaeC6+LURjw+wASNR+Jf69OfWHLj/MaryCaFetl8KIlZOxIAJGSjjDKVFxf8Md0o1XvhsB+2EzEBsAoZT45JqQM2EK1jRG6taATLNCJnmEx7MFkBlMuMGH3Aqh5CFoTINhEmEKgalkoCtzRTTLbc/0b5M9BliwKSPg0L6T2sEcLlEr0JUsZo0Up/6E0DgvLn1gQIPuKSlYZwrXgRexvn6/WgZcFuVdqqkzSRbA+7PwXGLPY/Y/sglNRG8jBfQ6sylmr2O8VbT8wc//uH/FqcyBe55jnL+s1ePPPW93O8vQpdLY4OIonoLogQiFCI4lu2C3U==";
		this.userId = "1000421660";
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserIdentifier() {
		String userId = parseUserIdentifierFromTokenService.getUserIdentifier(this.tokenStr);
		Assertions.assertThat(userId).isEqualTo(this.userId);
	}

}
