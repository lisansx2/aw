package cn.com.cslc.aw.core.common.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	protected static final Logger LOG = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);

	@Value("${403url}")
	private String errorPage;

	/**
	 * 增加ajax请求的处理
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		if (isAjax) {
			String jsonObject = "{\"message\":\"您没有权限访问此资源\"}";
			String contentType = "application/json";
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType(contentType);
			PrintWriter out = response.getWriter();
			out.print(jsonObject);
			out.flush();
			out.close();
			return;
		} else {
			if (!response.isCommitted()) {
				if (errorPage != null) {
					// Put exception into request scope (perhaps of use to a
					// view)
					request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

					// Set the 403 status code.
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);

					// forward to error page.
					RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
					dispatcher.forward(request, response);
				} else {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
				}
			}
		}
	}

	/**
	 * The error page to use. Must begin with a "/" and is interpreted relative
	 * to the current context root.
	 *
	 * @param errorPage
	 *            the dispatcher path to display
	 *
	 * @throws IllegalArgumentException
	 *             if the argument doesn't comply with the above limitations
	 */
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}
}
