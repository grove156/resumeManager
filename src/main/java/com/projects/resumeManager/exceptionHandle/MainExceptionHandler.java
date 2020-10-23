package com.projects.resumeManager.exceptionHandle;

import com.projects.resumeManager.dto.SessionUser;
import com.projects.resumeManager.exceptionHandle.exceptions.ResumeListNoExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class MainExceptionHandler {

    @Autowired
    HttpSession httpSession;

    //exception handler
    @ExceptionHandler(value = {ResumeListNoExistException.class})
    public String handleResumeListException(ResumeListNoExistException e,
                                            Model model){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("exceptionMsg", e.getMessage());
        model.addAttribute("user", sessionUser);
        return "dashboard";
    };
}
