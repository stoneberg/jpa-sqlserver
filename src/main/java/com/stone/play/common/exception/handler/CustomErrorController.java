package com.stone.play.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public ModelAndView handleError(ModelAndView mav, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode;

        if (status != null) {
            statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                mav.setViewName("/error/404");
                return mav;
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                mav.setViewName("/error/401");
                return mav;
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                mav.setViewName("/error/403");
                return mav;
            } else {
                mav.setViewName("/error/500");
                return mav;
            }

        }

        mav.setViewName("/error/error");
        return mav;

    }

    @RequestMapping(value = "/error/401")
    public ModelAndView handleUnauthorized(ModelAndView mav) {
        mav.setViewName("/error/401");
        return mav;
    }

    @RequestMapping(value = "/error/403")
    public ModelAndView handleForbidden(ModelAndView mav) {
        mav.setViewName("/error/403");
        return mav;
    }

    @Override
    public String getErrorPath() {
        return "/templates/error";
    }
}
