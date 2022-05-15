package com.example.enetcom_intranet.service;

import com.example.enetcom_intranet.model.Admin;
import com.example.enetcom_intranet.model.Student;

import java.util.List;

public interface AdminService {

    List<Admin> getAdmins();

    Admin getAdminById(Integer id);

    Admin insert(Admin s);

    void updateAdmin(Integer id, Admin s);

    void deleteAdmin(Integer id);

}
