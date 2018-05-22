package com.kds.KODE_DEV.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.kds.KODE_DEV.dao.AdminDeleteManageKStoreDao;
import com.kds.KODE_DEV.domain.AdminManageKnowStoreDomain;

@
SuppressWarnings("serial")
public class AdminDeleteKStore extends CommonService {
	
	static final Logger LOGGER = Logger.getLogger(AdminDeleteKStore.class);

    @
    Override
    public void run() throws ServletException, IOException {

        AdminDeleteManageKStoreDao dlt = new AdminDeleteManageKStoreDao();

        AdminManageKnowStoreDomain mks = new AdminManageKnowStoreDomain();

        mks.setKsId(request.getParameter("ksid"));
        mks.setOrgId(request.getParameter("orgid"));
        mks.setUserId(request.getParameter("userid"));
        mks.setKsSize(request.getParameter("kssize"));
        mks.setStatus(request.getParameter("status"));

        String s1 = dlt.deleteValues(mks);

        if (s1.equals("success")) {

            String msg = "Knowledge store deleted successfully !!";
            request.setAttribute("AdminSuccess", msg);

        } else {

            String msg = "Failed to delete knowlwdge store ! please try again ";
            request.setAttribute("AdminFailure", msg);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("../JSP/AdminKnowManage.jsp");
        requestDispatcher.forward(request, response);

    }
}