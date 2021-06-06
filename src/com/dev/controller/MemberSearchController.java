package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberSearchController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String job = request.getParameter("job");
		String path = null;
			
		if(job.equals("search"))
			path = "/result/memberSearchOutput.jsp";
		else if(job.equals("update"))
			path = "/memberUpdate.jsp";
		// service MemberVO return 
		MemberService service = MemberService.getInstance();
		MemberVO member = service.memberSearch(id);
		

		if(member == null) request.setAttribute("result", "No Id");
		request.setAttribute("member", member);
		HttpUtil.forward(request, response, path);
	}

}
