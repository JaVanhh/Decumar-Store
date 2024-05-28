package datn.shopmypham.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Thay đổi '*' thành domain của bạn nếu bạn chỉ muốn chấp nhận từ một domain cụ thể
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("sessionId") // Chấp nhận sessionId trong header
                .allowCredentials(true);
    }
}
