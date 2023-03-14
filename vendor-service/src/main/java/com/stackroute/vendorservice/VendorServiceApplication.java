package com.stackroute.vendorservice;

import com.stackroute.vendorservice.filter.VendorFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class VendorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendorServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
    @Bean
    public FilterRegistrationBean filterUrl()
    {
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new VendorFilter());
        filterRegistrationBean.addUrlPatterns("/api/vendor/getallcities");
        filterRegistrationBean.addUrlPatterns("/api/vendor/city");
        filterRegistrationBean.addUrlPatterns("/api/vendor/deletecity/*");
        filterRegistrationBean.addUrlPatterns("/api/vendor/cities");
        filterRegistrationBean.addUrlPatterns("/api/vendor/addaddress");
        filterRegistrationBean.addUrlPatterns("/api/vendor/addprice");
        filterRegistrationBean.addUrlPatterns("/api/vendor/addnameandcontact");
        filterRegistrationBean.addUrlPatterns("/api/vendor/getPrice");
        filterRegistrationBean.addUrlPatterns("/api/vendor/getvendor");
        filterRegistrationBean.addUrlPatterns("/api/vendor/getDomesticPrice");
        filterRegistrationBean.addUrlPatterns("/api/vendor/getInternationalPrice");
        filterRegistrationBean.addUrlPatterns("/api/vendor/patchVendorDetails");
        return filterRegistrationBean;
    }
}
